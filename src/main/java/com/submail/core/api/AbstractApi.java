package com.submail.core.api;

import java.util.Map;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.core.utils.RequestEncoder;

import static com.submail.config.AppConfig.SignType;

/**
 * A class implements ISender contains common methods to process something that occured before
 * sending.That is, create a signature and build the request data.
 * 
 * @see MailApi
 * @see MessageApi
 * @version 1.0 at 2014/10/30
 */
public abstract class AbstractApi implements Api {

    protected AppConfig config = null;

    private static final String API_TIMESTAMP = "http://api.submail.cn/service/timestamp.json";
    public static final String APPID = "appid";
    public static final String TIMESTAMP = "timestamp";
    public static final String SIGN_TYPE = "sign_type";
    public static final String SIGNATURE = "signature";

    public abstract Response send(Map<String, Object> data);

    public abstract Response xsend(Map<String, Object> data);

    public abstract Response subscribe(Map<String, Object> data);

    public abstract Response unsubscribe(Map<String, Object> data);

    /**
     * Request to url with data.
     * 
     * @param url
     *            请求 地址
     * @param data
     *            请求form 数据
     * @return boolean 是否请求成功
     */
    protected Response request(String url, Map<String, Object> data) {
        data.put(APPID, config.getAppId());
        data.put(SIGN_TYPE, config.getSignType());
        data.put(TIMESTAMP, this.getTimestamp());
        data.put(SIGNATURE, createSignature(RequestEncoder.formatRequest(data)));
        HttpResponse response = HttpRequest.post(url).form(data).send();
        return buildResult(response);
    }

    /**
     * Send a request to require timestamp from server.
     * 
     * @return timestamp
     */
    private String getTimestamp() {
        HttpResponse response = HttpRequest.get(API_TIMESTAMP).send();
        JSONObject json = JSONObject.parseObject(response.bodyText());
        return json.getString("timestamp");
    }

    private String createSignature(String data) {
        if (SignType.NORMAL.value().equals(config.getSignType())) {
            return config.getAppKey();
        } else {
            return buildSignature(data);
        }
    }

    /**
     * When the {@link AppConfig#setSignType(String)} is not normal,create a signature with sign
     * type.
     * 
     * @param data
     *            is the request data
     * @return signature
     */
    private String buildSignature(String data) {
        String app = config.getAppId();
        String appKey = config.getAppKey();
        String jointData = app + appKey + data + app + appKey;
        String signtype = config.getSignType();
        if (SignType.MD5.value().equals(signtype)) {
            return RequestEncoder.encode(SignType.MD5.value(), jointData);
        } else if (SignType.SHA1.value().equals(signtype)) {
            return RequestEncoder.encode(SignType.SHA1.value(), jointData);
        }
        return jointData;
    }

    private static final String SUCCESS = "success";

    /**
     * 构建远程服务执行结果.
     * 
     * @param response
     *            http响应
     * @return 邮件接口中的请求结果
     */
    private Response buildResult(HttpResponse response) {
        JSONObject obj = JSON.parseObject(response.body());
        Response result = null;
        if (obj.get("status").toString().equalsIgnoreCase(SUCCESS)) {
            result = new Response().success();
        } else {
            result = new Response(Integer.parseInt(
                obj.get("code").toString()), obj.get("msg").toString());
        }
        return result;
    }
}
