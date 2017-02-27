package com.submail.message;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.submail.Response;
import com.submail.config.AppConfig;
import com.submail.config.ConfigLoader;
import com.submail.sms.AddressBook;

/**
 * message address book tester.
 * 
 * @author L.Yang
 * @version v1.0 2017年2月27日 上午11:27:22
 */
public class AddressBookTest {

    /** 测试电话号. */
    private static final String TEST_TEL = "18513955226";
    
    private AddressBook addressbook;

    @Before
    private void initAddressBook() {
        AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
        addressbook = new AddressBook(config);
    }

    @Test
    public void testSubscribe() {
        addressbook.setAddress(TEST_TEL);
        Response response = addressbook.subscribe();
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testUnsubscribe() {
        addressbook.setAddress(TEST_TEL);
        Response response = addressbook.unsubscribe();
        Assert.assertTrue(response.isSuccess());
    }

}
