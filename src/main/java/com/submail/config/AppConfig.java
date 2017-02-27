package com.submail.config;


/**
 * A class contains properties about application.The signature,made up of these properties, will be
 * a part of request-data to ensure the safety while sending to HTTP.The value of properties are
 * read from file <strong>app_config.properties</strong>.The class have two subclasses,MailConfig
 * for mail settings and MessageConfig for message settings.
 * 
 * @see MailConfig
 * @see MessageConfig
 * @version 1.0 at 2014/10/28
 */
public class AppConfig {

    public static final String API_REQUEST_DOMAIN = "api.request_domain";
    
    /**
     * Application Id offered by user as the part of signature.
     */
    protected String appId;

    /**
     * Application Key offered by user as the part of signature.
     */
    protected String appKey;

    /**
     * api request domain.
     */
    protected String domain;

    /**
     * Assign the type for encryption. md5:by md5 algorithm.If by this algorithm,the format of
     * signature is md5({@link #appId}{@link #appKey} <variable>RequestData</variable>{@link #appId}
     * {@link #appKey}); sha1:by sha1 algorithm. normal:use the the value of {@link #appKey} without
     * no algorithm.It also the default value.
     */
    protected String signType = null;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * The method to get the {@link #signType}.It the value from the properties file is not
     * valid,return normal.Otherwise,return the current value.
     * 
     * @return signType that is valid.
     */
    public String getSignType() {
        if (this.checkType(this.signType)) {
            return SignType.valueOf(this.signType).value;
        }
        return SignType.NORMAL.value();
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * Judge the value of {@link #signType} is valid or not.
     * 
     * @param signType
     * @return If the value is inside of {@value #TYPE_MD5},{@value #TYPE_SHA1} and
     *         {@value #TYPE_NORMAL}, return true,otherwise return false;
     */
    private boolean checkType(String signType) {
        return SignType.NORMAL.getDeclaringClass().equals(signType)
               || SignType.MD5.equals(signType)
               || SignType.SHA1.equals(signType);
    }

    public String getApiDomain() {
        return domain;
    }

    public void setApiDomain(String domain) {
        this.domain = domain;
    }
    
    /**
     * sign type 枚举.
     * 
     * <pre>
     * normal/md5/sha1
     * </pre>
     * 
     * @author L.Yang
     * @version v1.0 2016年9月6日 下午11:32:08
     */
    public static enum SignType {

        NORMAL("normal"),
        MD5("md5"),
        SHA1("sha1");
        
        private String value;

        private SignType(String value) {
            this.value = value;
        }

        /**
         * 获取  sign type value.
         * @return 返回   sign type value
         */
        public String value() {
            return value;
        }

    }
}
