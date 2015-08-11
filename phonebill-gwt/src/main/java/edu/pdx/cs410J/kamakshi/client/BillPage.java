package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.*;


/**
 * This class displays a <code>PhoneBill</code> after a call has been made
 * or search has been performed.
 *
 * @author Kamakshi Nagar
 */
public class BillPage extends Composite {


    private VerticalPanel verticalPanel= new VerticalPanel();
    HTML html = new HTML();
    boolean flag= false; boolean b = false;


    public BillPage(){
        initWidget(this.verticalPanel);

        PingServiceAsync async = GWT.create(PingService.class);
        async.displayPhoneBill(new AsyncCallback<AbstractPhoneBill>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert(throwable.toString());
            }
            @Override
            public void onSuccess(AbstractPhoneBill phonebill) {

                displayPhoneBill(phonebill);
            }
        });
       // this.verticalPanel.add(label);
        this.verticalPanel.add(html);

    }


    public BillPage(final String customerName,final Date start, final Date end){
        initWidget(this.verticalPanel);
        PingServiceAsync async = GWT.create(PingService.class);
        async.displayPhoneBill(new AsyncCallback<AbstractPhoneBill>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert(throwable.toString());
            }

            @Override
            public void onSuccess(AbstractPhoneBill phonebill) {
                if (phonebill.getPhoneCalls().size() != 0 && !phonebill.getCustomer().equals(customerName)) {
                    Window.alert("This is a phone bill for "+ phonebill.getCustomer());
                }
                displaySearchPhoneBill(customerName,start, end, phonebill);
            }
        });
        this.verticalPanel.add(html);
    }


    /**
     * This method display a Phone Bill for the calls with comes under search criteria.
      * @param customer name of customer
     * @param start start time of call
     * @param end end time of range
     * @param phoneBill phonebill of the customer
     */
    private void displaySearchPhoneBill(String customer,Date start, Date end,AbstractPhoneBill phoneBill){

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Your Phone Bill</h1>");

        SortedSet<PhoneCall> sortedTree = new TreeSet<>();
        sortedTree.addAll(phoneBill.getPhoneCalls());

        for (PhoneCall call : sortedTree) {
            if (customer.equals(phoneBill.getCustomer()) && call.getStartTime().getTime() >= start.getTime() && call.getStartTime().getTime() <= end.getTime()) {
                sb.append("Customer:  ").append(phoneBill.getCustomer()).append("</br></br>");
                sb.append("Caller:  ").append(call.getCaller()).append("</br></br>");
                sb.append("Callee:  ").append(call.getCallee()).append("</br></br>");
                sb.append("StartTime:  ").append(call.getStartTimeString()).append("</br></br>");
                sb.append("EndTime:  ").append(call.getEndTimeString()).append("</br></br>");
                long l = call.getEndTime().getTime() - call.getStartTime().getTime();
                int i = (int) l;
                int hour = i / (1000 * 60 * 60);
                int minutes = (i % (1000 * 60 * 60)) / (1000 * 60);
                sb.append("Call duration:  ").append(hour).append(" Hours ").append(minutes).append(" Minutes ").append("</br></br>");
                sb.append("</br>");
                b = true;
            } else {
                flag = true;
            }
        }
        if (phoneBill.getPhoneCalls().size()==0){sb.append("<h2>There are no calls in your bill.</h2>");}
        else if (flag && !b) {
            sb.append("<h2>There are no matching calls</h2>");
        }
        html.setHTML(sb.toString());
    }

    /**
     * This method displays all the calls in the <code>PhoneBill</code>
     * @param abstractPhoneBill PhoneBill of the customer with all calls
     */
    private void displayPhoneBill(AbstractPhoneBill abstractPhoneBill){

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Your Phone Bill</h1>");

        if(abstractPhoneBill.getPhoneCalls().size()==0){
            sb.append("<h2>There are no calls in your bill</h2>");
        }
        SortedSet<PhoneCall> sortedTree = new TreeSet<>();
        sortedTree.addAll(abstractPhoneBill.getPhoneCalls());

        for (PhoneCall call : sortedTree) {
            sb.append("Customer:  ").append(abstractPhoneBill.getCustomer()).append("</br></br>");
            sb.append("Caller:  ").append(call.getCaller()).append("</br></br>");
            sb.append("Callee:  ").append(call.getCallee()).append("</br></br>");
            sb.append("StartTime:  ").append(call.getStartTimeString()).append("</br></br>");
            sb.append("EndTime:  ").append(call.getEndTimeString()).append("</br></br>");
            long l = call.getEndTime().getTime() - call.getStartTime().getTime();
            int i = (int) l;
            int hour = i / (1000 * 60 * 60);
            int minutes = (i % (1000 * 60 * 60)) / (1000 * 60);

            sb.append("Call duration:  ").append(hour).append(" Hours ").append(minutes).append(" Minutes ").append("</br></br>");
            sb.append("</br>");
        }
        html.setHTML(sb.toString());

    }

}
