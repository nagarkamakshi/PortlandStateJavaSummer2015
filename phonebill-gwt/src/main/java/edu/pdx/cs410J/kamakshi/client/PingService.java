package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * A GWT remote service that returns a Phone Bill
 */
@RemoteServiceRelativePath("ping")
public interface PingService extends RemoteService {

    AbstractPhoneBill addPhoneCall(String CustomerName, String caller, String callee, String start, String end) throws PhoneBillGwtException;

    AbstractPhoneBill displayPhoneBill();

}
