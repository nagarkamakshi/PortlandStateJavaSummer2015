package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Collection;
import java.util.Date;

/**
 * The client-side interface to the ping service
 */
public interface PingServiceAsync {

  /**
   * Return the current date/time on the server
   */
  void addPhoneCall(String CustomerName, String caller, String callee, String start, String end, AsyncCallback<AbstractPhoneBill> asyncCallback);
    void displayPhoneBill(AsyncCallback<AbstractPhoneBill> async);
}
