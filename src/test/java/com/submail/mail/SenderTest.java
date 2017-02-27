package com.submail.mail;

import org.junit.Assert;
import org.junit.Test;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.config.ConfigLoader;

/**
 * mail send tester.
 * 
 * @author L.Yang
 * @version v1.0 2017年2月27日 上午11:28:02
 */
public class SenderTest {

    private static final String TO = "leo@submail.cn";
    private static final String CC = "mailer@submail.cn";
    private static final String BCC = "leo@drinkfans.com";


    @Test
    public void testMailSend() {
        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
        Sender submail = new Sender(config);
        submail.addTo(TO, "leo");
        submail.addCc(CC, "");
        submail.addBcc(BCC, "");
        submail.setSender("no-reply@submail.cn", "SUBMAIL");
        submail.setReply("service@submail.cn");
        submail.setSubject("test SDK");
        submail.setText("test SDK text");
        submail.addAttachment("D:\\Program Files\\eclipse-php-luna-SR1-win32\\eclipse\\epl-v10.html");
        submail.setHtml("test SDK html @var(name),@var(age) <a href=\"var://@link(test)\">testLink</a> <a href=\"var://@link(test2)\">testLink2</a>");
        submail.addVar("name", "leo");
        submail.addVar("age", "32");
        submail.addLink("developer", "http://submail.cn/chs/developer");
        submail.addLink("store", "http://submail.cn/chs/store");
        submail.addHeaders("X-Accept", "zh-cn");
        submail.addHeaders("X-Mailer", "leo App");
        Response response = submail.send();
        Assert.assertTrue(response.isSuccess());
    }

}
