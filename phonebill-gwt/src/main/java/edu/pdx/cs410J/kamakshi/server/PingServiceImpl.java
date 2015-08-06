package edu.pdx.cs410J.kamakshi.server;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.kamakshi.client.PhoneBill;
import edu.pdx.cs410J.kamakshi.client.PhoneBillGwtException;
import edu.pdx.cs410J.kamakshi.client.PhoneCall;
import edu.pdx.cs410J.kamakshi.client.PingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.lang.Override;
import java.util.*;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{

    private PhoneBill bill = new PhoneBill();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bill = new PhoneBill();
    }
  @Override
    public AbstractPhoneBill addPhoneCall(String customerName, String caller, String callee, String start, String end){
      //PhoneBill phonebill = new PhoneBill(customerName);
      bill.setCustomer(customerName);
      bill.addPhoneCall(new PhoneCall(caller,callee,start,end));
      return bill;
  }

    @Override
    public AbstractPhoneBill displayPhoneBill() {
        Collection<AbstractPhoneCall> call = bill.getPhoneCalls();
        AbstractPhoneCall[] cal= call.toArray(new PhoneCall[call.size()]);
        for(AbstractPhoneCall pc: cal){
            bill.addPhoneCall(pc);
        }
        return bill ;
    }

    @Override
    public AbstractPhoneBill searchPhoneCall(String customerName, Date start, Date end) throws PhoneBillGwtException{

        Collection<PhoneCall> call = bill.getPhoneCalls();
        SortedSet<PhoneCall> sortedTree = new TreeSet<>();
        sortedTree.addAll(call);
        Iterator iter = sortedTree.iterator();

        while (iter.hasNext()) {
            PhoneCall cal = (PhoneCall) iter.next();
            String b = cal.getStartTimeString();
            long i = Integer.valueOf( b);
           // DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm a");
           // Date newDate = dateTimeFormat.parse(b);

        }

        /*Collection<AbstractPhoneCall> call = bill.getPhoneCalls();
        AbstractPhoneCall[] cal= call.toArray(new PhoneCall[call.size()]);
        String a= start.toString();
        for(AbstractPhoneCall pc: cal){
            String b= pc.getStartTimeString();
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd hh:mm a");
           // Date newDate = dateTimeFormat.parse(b);

            /*if(newDate.getTime()>= start.getTime()){
                bill.addPhoneCall(pc);
            }
            else{
                    throw new PhoneBillGwtException("there is some error while search ");
            } */
           // bill.addPhoneCall(pc);




       // Window.alert("call: "+ cal.toString());
        return bill;
    }




    /**
   * Log unhandled exceptions to standard error
   *
   * @param unhandled
   *        The exception that wasn't handled
   */
  @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }
}
