package com.submail;

/**
 * 请求响应对象.
 * 
 * @author L.Yang
 * @version 1.0, 2015年8月26日
 */
public class Response {

    /** 发送失败 - 邮件原因. */
    public static final int FAIL = -1;

    /** 发送成功. */
    private static final int SUCCESS = 200;

    /** 响应编号. */
    private int code = FAIL;

    /** 出错信息 . */
    private String message;

    public Response() {
        super();
    }

    public Response(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 成功方法.
     */
    public Response success() {
        this.code = SUCCESS;
        this.message = "发送成功";
        return this;
    }

    /**
     * 获取 code.
     * 
     * @return 返回 code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置响应值 .
     * 
     * @param code
     *            响应值
     */
    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置 出错信息.
     * 
     * @param message
     *            the error message to set
     */
    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 获取出错信息.
     * 
     * @return the error message 出错信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 是否发送成功.
     * 
     * @return 成功，失败
     */
    public boolean isSuccess() {
        return this.code == SUCCESS;
    }
}
