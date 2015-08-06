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
 * Created by vaio on 01-08-2015.
 */
public class BillPage extends Composite {


    private VerticalPanel verticalPanel= new VerticalPanel();
    HTML html = new HTML();


    public BillPage(){
        initWidget(this.verticalPanel);
        Label label= new Label("This is a Bill Page");
        label.setWidth("3");

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
        this.verticalPanel.add(label);
        this.verticalPanel.add(html);

    }
    public BillPage(final Date start, final Date end){
        initWidget(this.verticalPanel);
        final Label label= new Label("This is a Search Bill Page");
        label.setWidth("3");

        PingServiceAsync async = GWT.create(PingService.class);
        async.displayPhoneBill(new AsyncCallback<AbstractPhoneBill>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert(throwable.toString());
            }
            @Override
            public void onSuccess(AbstractPhoneBill phonebill) {

                StringBuilder sb = new StringBuilder();
                sb.append("<h1>Your Phone Bill</h1>");

                SortedSet<PhoneCall> sortedTree = new TreeSet<>();
                sortedTree.addAll(phonebill.getPhoneCalls());
                Iterator iter = sortedTree.iterator();
                //StringBuilder sb = new StringBuilder();
                //sb.append("<h1>Your Phone Bill</h1>");
                while (iter.hasNext()) {
                    PhoneCall call = (PhoneCall) iter.next();

                    if (call.getStartTime().getTime() >= start.getTime() && call.getStartTime().getTime()<= end.getTime()) {
                        sb.append("Customer:  " + phonebill.getCustomer()+"</br></br>");
                        sb.append("Caller:  " + call.getCaller()+"</br></br>");
                        sb.append("Callee:  " + call.getCallee()+"</br></br>");
                        sb.append("StartTime:  " + call.getStartTimeString()+"</br></br>");
                        sb.append("EndTime:  " + call.getEndTimeString()+"</br></br>");
                        long l= call.getEndTime().getTime()- call.getStartTime().getTime();
                        int i= (int) l;
                        int hour = i/(1000*60*60);
                        int minutes = (i%(1000*60*60))/(1000*60);
                        sb.append("Call duration:  " + hour + " Hours "+ minutes+" Minutes "+"</br></br>");
                        sb.append("</br>");
                    }
                    html.setHTML(sb.toString());
                    }
            }
        });

        this.verticalPanel.add(label);
        this.verticalPanel.add(html);
    }
    private void displayPhoneBill(AbstractPhoneBill abstractPhoneBill){

        StringBuilder sb = new StringBuilder();
        sb.append("<h1><i>Your Phone Bill</i></h1>");

        SortedSet<PhoneCall> sortedTree = new TreeSet<>();
        sortedTree.addAll(abstractPhoneBill.getPhoneCalls());

        Iterator iter = sortedTree.iterator();

        while (iter.hasNext()) {
            PhoneCall call = (PhoneCall) iter.next();

            sb.append("Customer:  " + abstractPhoneBill.getCustomer()+"</br></br>");
            sb.append("Caller:  " + call.getCaller()+"</br></br>");
            sb.append("Callee:  " + call.getCallee()+"</br></br>");
            sb.append("StartTime:  " + call.getStartTimeString()+"</br></br>");
            sb.append("EndTime:  " + call.getEndTimeString()+"</br></br>");
            long l= call.getEndTime().getTime()- call.getStartTime().getTime();
            int i= (int) l;
            int hour = i/(1000*60*60);
            int minutes = (i%(1000*60*60))/(1000*60);

            sb.append("Call duration:  " + hour + " Hours "+ minutes+" Minutes "+"</br></br>");
            sb.append("</br>");
        }
        html.setHTML(sb.toString());

    }

}
