package com.submail.mail;

import org.junit.Assert;
import org.junit.Test;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.config.ConfigLoader;

/**
 * mail xsend tester.
 * 
 * @author L.Yang
 * @version v1.0 2017年2月27日 上午11:28:02
 */
public class XSenderTest {

    private static final String TO = "leo@submail.cn";
    private static final String CC = "leo@submail.cn";
    private static final String BCC = "leo@submail.cn";
    
    private static final String PROJECT = "uigGk1";

    @Test
    public void testXSend() {
        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
        XSender submail = new XSender(config);
        submail.addTo(TO, "leo");
        submail.addCc(CC, "cc1");
        submail.addCc(BCC, "bcc1");
        submail.setSender("no-reply@submail.cn","SUBMAIL");
        submail.setProject(PROJECT);
        submail.addVar("name", "leo");
        submail.addVar("age", "32");
        submail.addLink("developer", "http://submail.cn/chs/developer");
        submail.addLink("store", "http://submail.cn/chs/store");
        submail.addHeaders("X-Accept", "zh-cn");
        submail.addHeaders("X-Mailer", "leo App");
        Response response = submail.xsend();
        Assert.assertTrue(response.isSuccess());

    }

}
