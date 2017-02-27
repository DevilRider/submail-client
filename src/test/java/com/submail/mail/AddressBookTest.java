package com.submail.mail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.config.ConfigLoader;

/**
 * mail address book tester.
 *  
 * @author L.Yang
 * @version v1.0 2017年2月27日  上午11:28:02
 */
public class AddressBookTest {

    private AddressBook addressbook;
    
    @Before
    private void initAddressBook(){
        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Mail);
        addressbook = new AddressBook(config);
    }
    
    @Test
    public void testSubscribe() {
        addressbook.setAddress("leo@apple.cn", "leo");
        Response response = addressbook.subscribe();
        Assert.assertTrue(response.isSuccess());
    }
    
    @Test
    public void testUnsubscribe() {
        addressbook.setAddress("leo@apple.cn", "leo");
        Response response = addressbook.unsubscribe();
        Assert.assertTrue(response.isSuccess());
    }

}
