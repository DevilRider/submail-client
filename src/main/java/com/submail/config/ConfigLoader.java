package com.submail.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class obtain the value and create configuration object by loading file
 * app_config.properties,including creating <strong>MailConfig<strong> and creating
 * <strong>MessageConfig<strong>.
 * 
 * @see AppConfig
 * @see MailConfig
 * @see MessageConfig
 * @version 1.0 at 2014/10/28
 */
public class ConfigLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);

    private static Properties pros = null;

    /**
     * Loading file while class loading.The operation inside of static block will be run once.
     */
    static {
        pros = new Properties();
        try {
            pros.load(ConfigLoader.class
                .getResourceAsStream("/submail-client.properties"));
        } catch (IOException e) {
            LOGGER.error("Properties load error: {}", e);
        }
    }

    /**
     * enum define two kinds of configuration.
     */
    public static enum ConfigType {
        Mail, Message
    };

    /**
     * A static method for outer class to create configuration by loading file.
     * 
     * @param type
     *            ConfigType
     * @return If the type is ConfigType#Mail,return the instance of {@link MailConfig}. And,if the
     *         type is ConfigType#Message,return the instance of {@link MessageConfig}.
     */
    public static AppConfig load(ConfigType type) {
        switch (type) {
            case Mail:
                return createMailConfig();
            case Message:
                return createMessageConfig();
            default:
                return null;
        }
    }

    private static AppConfig createMailConfig() {
        AppConfig config = new MailConfig();
        config.setAppId(pros.getProperty(MailConfig.APP_ID));
        config.setAppKey(pros.getProperty(MailConfig.APP_KEY));
        config.setSignType(pros.getProperty(MailConfig.APP_SIGNTYPE));
        config.setApiDomain(pros.getProperty(AppConfig.API_REQUEST_DOMAIN));
        return config;
    }

    private static AppConfig createMessageConfig() {
        AppConfig config = new MessageConfig();
        config.setAppId(pros.getProperty(MessageConfig.APP_ID));
        config.setAppKey(pros.getProperty(MessageConfig.APP_KEY));
        config.setSignType(pros.getProperty(MessageConfig.APP_SIGNTYPE));
        config.setApiDomain(pros.getProperty(AppConfig.API_REQUEST_DOMAIN));
        return config;
    }

}
