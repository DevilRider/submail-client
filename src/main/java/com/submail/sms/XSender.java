package com.submail.sms;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.core.SenderWapper;
import com.submail.core.api.MessageApi;

/**
 * A SenderWapper class as decoration class for user to send request by message.
 * User can set the basic information of request by included several methods.
 * Then,send the request data by a mode of message.By this pattern,user needn't
 * offer the message content and message signature,change the message content by variable dynamicly if prividing only id which
 * created in submail application.
 * 
 * 
 * @version 1.0 at 2014/10/28
 * */
public class XSender extends SenderWapper {

    private static final String TO = "to";
    private static final String PROJECT = "project";
    private static final String VARS = "vars";

    /**
     * If the mode is mail,it's an instance of {@link MailConfig}. If the mode is message,it's an
     * instance of {@link MessageConfig}
     */
	public XSender(AppConfig config) {
	    api = new MessageApi(config);
	}

	public XSender addTo(String address) {
		requestData.addWithComma(TO, address);
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

	public Response xsend(){
	   return api.xsend(requestData);
	}
}
