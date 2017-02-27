package com.submail.mail;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.core.SenderWapper;
import com.submail.core.api.MailApi;
import com.submail.core.api.MessageApi;

/**
 * A SenderWapper class as decoration class for user to send request by mail. User can set the basic
 * information of request by included several methods. Then,send the request data by a mode of
 * mail.It is diffirent from {@link Sender} ,user don't need to offer html source or mail
 * content,or even mail title and sender, change the mail content by variable dynamicly if prividing
 * only id which created in submail application.
 * 
 * @see Sender
 * @see MailApi
 * @version 1.0 at 2014/10/28
 */
public class XSender extends SenderWapper {

    private static final String TO = "to";
    private static final String ADDRESSBOOK = "addressbook";
    private static final String FROM = "from";
    private static final String FROM_NAME = "from_name";
    private static final String REPLY = "reply";
    private static final String CC = "cc";
    private static final String BCC = "bcc";
    private static final String SUBJECT = "subject";
    private static final String PROJECT = "project";
    private static final String VARS = "vars";
    private static final String LINKS = "links";
    private static final String HEADERS = "headers";

    /**
     * If the mode is mail,it's an instance of {@link MailConfig}. If the mode is message,it's an
     * instance of {@link MessageConfig}
     */
    public XSender(AppConfig config) {
        api = new MessageApi(config);
    }

    public XSender addTo(String address, String name) {
        requestData.addWithBracket(TO, name, address);
        return this;
    }

    public XSender addAddressBook(String addressbook) {
        requestData.addWithComma(ADDRESSBOOK, addressbook);
        return this;
    }

    public XSender setSender(String sender, String name) {
        requestData.put(FROM, sender);
        requestData.put(FROM_NAME, name);
        return this;
    }

    public XSender setReply(String reply) {
        requestData.put(REPLY, reply);
        return this;
    }

    public XSender addCc(String address, String name) {
        requestData.addWithBracket(CC, name, address);
        return this;
    }

    public XSender addBcc(String address, String name) {
        requestData.addWithBracket(BCC, name, address);
        return this;
    }

    public XSender setSubject(String subject) {
        requestData.put(SUBJECT, subject);
        return this;
    }

    public XSender setProject(String project) {
        requestData.put(PROJECT, project);
        return this;
    }

    public XSender addVar(String key, String val) {
        requestData.addWithJson(VARS, key, val);
        return this;
    }

    public XSender addLink(String key, String val) {
        requestData.addWithJson(LINKS, key, val);
        return this;
    }

    public XSender addHeaders(String key, String val) {
        requestData.addWithJson(HEADERS, key, val);
        return this;
    }

    public Response xsend() {
       return api.xsend(requestData);
    }
}
