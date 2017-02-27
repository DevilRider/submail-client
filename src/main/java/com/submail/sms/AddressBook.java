package com.submail.sms;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.core.SenderWapper;
import com.submail.core.api.MailApi;
import com.submail.core.api.MessageApi;

/**
 * A SenderWapper class as decoration class for user to subscribe and unsubscribe by message. User
 * can set the basic information of request by included several methods. Then,send the request data
 * by a mode of message.
 * 
 * @see MailApi
 * @version 1.0 at 2014/10/28
 */
public class AddressBook extends SenderWapper {

    private static final String ADDRESS = "address";
    private static final String TARGET = "target";
    
    /**
     * If the mode is mail,it's an instance of {@link MailConfig}. If the mode is message,it's an
     * instance of {@link MessageConfig}
     */
    public AddressBook(AppConfig config) {
        api = new MessageApi(config);
    }

    public AddressBook setAddress(String address) {
        requestData.put(ADDRESS, address);
        return this;
    }

    public AddressBook setAddressbook(String target) {
        requestData.put(TARGET, target);
        return this;
    }

    public Response subscribe() {
        return api.subscribe(requestData);
    }

    public Response unsubscribe() {
        return api.unsubscribe(requestData);
    }
}
