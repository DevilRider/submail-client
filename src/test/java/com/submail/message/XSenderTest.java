package com.submail.message;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.config.ConfigLoader;
import com.submail.sms.XSender;

/**
 * message xsend tester.
 * 
 * @author L.Yang
 * @version v1.0 2017年2月27日 上午11:27:33
 */
public class XSenderTest {

    /** 测试电话号. */
    private static final String TEST_TEL = "18513955226";
    
    private static final String PROJCET = "185rS3";
    
    Logger logger = LoggerFactory.getLogger(XSenderTest.class);
    
    @Test
    public void testMessageXSende() {
        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
        XSender submail = new XSender(config);
        submail.addTo(TEST_TEL); 
        submail.setProject(PROJCET);
        submail.addVar("code", "3102");
        submail.addVar("time", "5分钟");
        Response response = submail.xsend();
        logger.debug("{}", new Object[]{response.getMessage()});
        Assert.assertTrue(response.isSuccess());
    }


}
