package com.submail.mail;

import java.io.File;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.core.SenderWapper;
import com.submail.core.api.MailApi;

/**
 * A SenderWapper class as decoration class for user to send request by mail. User can set the basic
 * information of request by included several methods. Then,send the request data by a mode of mail.
 * 
 * @see MailApi
 * @version 1.0 at 2014/10/28
 */
public class Sender extends SenderWapper {

    private static final String TO = "to";
    private static final String ADDRESSBOOK = "addressbook";
    private static final String FROM = "from";
    private static final String FROM_NAME = "from_name";
    private static final String REPLY = "reply";
    private static final String CC = "cc";
    private static final String BCC = "bcc";
    private static final String SUBJECT = "subject";
    private static final String TEXT = "text";
    private static final String HTML = "html";
    private static final String VARS = "vars";
    private static final String LINKS = "links";
    private static final String ATTACHMENTS = "attachments";
    private static final String HEADERS = "headers";

    /**
     * If the mode is mail,it's an instance of {@link MailConfig}. If the mode is message,it's an
     * instance of {@link MessageConfig}
     */
    public Sender(AppConfig config) {
        this.api = new MailApi(config);
    }

    public Sender addTo(String address, String name) {
        requestData.addWithBracket(TO, name, address);
        return this;
    }

    public Sender addAddressBook(String addressbook) {
        requestData.addWithComma(ADDRESSBOOK, addressbook);
        return this;
    }

    public Sender setSender(String sender, String name) {
        requestData.put(FROM, sender);
        requestData.put(FROM_NAME, name);
        return this;
    }

    public Sender setReply(String reply) {
        requestData.put(REPLY, reply);
        return this;
    }

    public Sender addCc(String address, String name) {
        requestData.addWithBracket(CC, name, address);
        return this;
    }

    public Sender addBcc(String address, String name) {
        requestData.addWithBracket(BCC, name, address);
        return this;
    }

    public Sender setSubject(String subject) {
        requestData.put(SUBJECT, subject);
        return this;
    }

    public Sender setText(String text) {
        requestData.put(TEXT, text);
        return this;
    }

    public Sender setHtml(String html) {
        requestData.put(HTML, html);
        return this;
    }

    public Sender addVar(String key, String val) {
        requestData.addWithJson(VARS, key, val);
        return this;
    }

    public Sender addLink(String key, String val) {
        requestData.addWithJson(LINKS, key, val);
        return this;
    }

    public Sender addAttachment(String file) {
        requestData.addWithIncrease(ATTACHMENTS, new File(file));
        return this;
    }

    public Sender addHeaders(String key, String val) {
        requestData.addWithJson(HEADERS, key, val);
        return this;
    }

    public Response send() {
        return api.send(requestData);
    }

}
