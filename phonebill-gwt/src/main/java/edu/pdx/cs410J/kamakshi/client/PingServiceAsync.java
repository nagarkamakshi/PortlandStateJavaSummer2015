package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * The client-side interface to the ping service
 */
public interface PingServiceAsync {

    /**
     * Return the current PhoneBill on the server
     */
    void addPhoneCall(String CustomerName, String caller, String callee, String start, String end, AsyncCallback<AbstractPhoneBill> asyncCallback) throws PhoneBillGwtException;

    void displayPhoneBill(AsyncCallback<AbstractPhoneBill> async);

}
