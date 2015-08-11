package edu.pdx.cs410J.kamakshi.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.kamakshi.client.PhoneBill;
import edu.pdx.cs410J.kamakshi.client.PhoneBillGwtException;
import edu.pdx.cs410J.kamakshi.client.PhoneCall;
import edu.pdx.cs410J.kamakshi.client.PingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService {

    private PhoneBill bill;

    public PingServiceImpl() {
        bill = new PhoneBill();
    }


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bill = new PhoneBill();
    }

    @Override
    public AbstractPhoneBill addPhoneCall(String customerName, String caller, String callee, String start, String end) throws PhoneBillGwtException {
        if ((bill.getPhoneCalls().size() != 0) && !bill.getCustomer().equals(customerName)) {
            throw new PhoneBillGwtException("Customer name "+ customerName+" does not match " + bill.getCustomer());
        }

        bill.setCustomer(customerName);
        bill.addPhoneCall(new PhoneCall(caller, callee, start, end));
        return bill;
    }

    @Override
    public AbstractPhoneBill displayPhoneBill() {
        return bill;
    }

    /**
     * Log unhandled exceptions to standard error
     *
     * @param unhandled The exception that wasn't handled
     */
    @Override
    protected void doUnexpectedFailure(Throwable unhandled) {
        unhandled.printStackTrace(System.err);
        super.doUnexpectedFailure(unhandled);
    }
}
