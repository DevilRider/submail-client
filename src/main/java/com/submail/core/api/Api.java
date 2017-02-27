package com.submail.core.api;

import java.util.HashMap;
import java.util.Map;

import com.submail.Response;

/**
 * A interface define a method of {@link #send(Map)}.All classes what can send request data must
 * implement it.And the classes are always designed as communication mode or a tool,that is,mail
 * mode and message mode.No matter what kinds of mode or tool we choose,we can send a request.
 * 
 * @see MailApi
 * @version 1.0 at 2014/10/28
 */
public interface Api {

    /**
     * Send the request data.
     * 
     * @param data
     *            {@link HashMap}contains the request data.Such as
     *            <p>
     *            to->xxx@submail.cn cc->nnn@submail.cn text->Hello,world!
     *            </p>
     * @return If send successfully,return true.Error occurs,return false.
     */
    Response send(Map<String, Object> data);

    /**
     * Send the request data.
     * 
     * @param data
     *            {@link HashMap}contains the request data.Such as
     *            <p>
     *            to->xxx@submail.cn cc->nnn@submail.cn text->Hello,world!
     *            </p>
     * @return If send successfully,return true.Error occurs,return false.
     */
    Response xsend(Map<String, Object> data);

    /**
     * Send the request data.
     * 
     * @param data
     *            {@link HashMap}contains the request data.Such as
     *            <p>
     *            to->xxx@submail.cn cc->nnn@submail.cn text->Hello,world!
     *            </p>
     * @return If send successfully,return true.Error occurs,return false.
     */
    Response subscribe(Map<String, Object> data);

    /**
     * Send the request data.
     * 
     * @param data
     *            {@link HashMap}contains the request data.Such as
     *            <p>
     *            to->xxx@submail.cn cc->nnn@submail.cn text->Hello,world!
     *            </p>
     * @return If send successfully,return true.Error occurs,return false.
     */
    Response unsubscribe(Map<String, Object> data);

}
