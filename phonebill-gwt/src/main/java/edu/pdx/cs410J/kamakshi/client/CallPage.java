package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DatePicker;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.io.IOException;
import java.text.ParseException;

import java.util.Collection;
import java.util.Date;

/**
 * Created by vaio on 01-08-2015.
 */
public class CallPage extends Composite {

private VerticalPanel verticalPanel= new VerticalPanel();
private FlexTable flexTable = new FlexTable();
    MainPage mainPage;
    TextBox customer = new TextBox();
    TextBox caller1 = new TextBox();TextBox caller2 = new TextBox();TextBox caller3 = new TextBox();
    TextBox callee1 = new TextBox();TextBox callee2 = new TextBox();TextBox callee3 = new TextBox();
    TextBox startTime1 = new TextBox();DatePicker datePicker = new DatePicker();  Anchor dateBtn= new Anchor("Date");
    ListBox startTime2a = new ListBox(); ListBox startTime2b = new ListBox();
    ListBox startTime3 = new ListBox();
    TextBox endTime1 = new TextBox();DatePicker datePicker2 = new DatePicker();  Anchor dateBtn2= new Anchor("Date");
    ListBox endTime2a = new ListBox();  ListBox endTime2b = new ListBox();
    ListBox endTime3 = new ListBox();
    Button addCallBtn= new Button("Add Call");
    boolean flag = false;

    public CallPage(MainPage main){
        initWidget(this.verticalPanel);
        this.mainPage = main;

        Label label= new HTML("<h2>Make A New Call</h2>");
        flexTable.setCellPadding(5);
        flexTable.setCellSpacing(5);
        flexTable.setWidget(0,1,label);
        flexTable.getFlexCellFormatter().setColSpan(0, 1, 5);

        Label label1= new Label("Customer Name: ");
        customer.getElement().setPropertyString("placeholder", "Name");
         customer.setMaxLength(25);
        flexTable.setWidget(2, 0, label1);
        flexTable.setWidget(2,1,customer);
        flexTable.getFlexCellFormatter().setColSpan(2,1,4);

        Label label2= new Label("Caller Number: ");
        caller1.addKeyPressHandler(new AddKeyPressHandler());
        caller1.setWidth("50px");
        caller1.setMaxLength(3);caller1.getElement().setPropertyString("placeholder","000");
        caller2.addKeyPressHandler(new AddKeyPressHandler());
            caller2.setWidth("50px");
        caller2.setMaxLength(3);caller2.getElement().setPropertyString("placeholder", "000");
        caller3.addKeyPressHandler(new AddKeyPressHandler()); caller3.setWidth("50px");caller3.setMaxLength(4);caller3.getElement().setPropertyString("placeholder","0000");
        flexTable.setWidget(3,0,label2);
        flexTable.setWidget(3,1,caller1);flexTable.setWidget(3,2,caller2);flexTable.setWidget(3,3,caller3);

       Label label3= new Label("Callee Number: ");
        callee1.addKeyPressHandler(new AddKeyPressHandler()); callee1.setWidth("50px");callee1.setMaxLength(3);callee1.getElement().setPropertyString("placeholder", "000");
        callee2.addKeyPressHandler(new AddKeyPressHandler());  callee2.setWidth("50px");callee2.setMaxLength(3);callee2.getElement().setPropertyString("placeholder", "000");
        callee3.addKeyPressHandler(new AddKeyPressHandler());callee3.setWidth("50px"); callee3.setMaxLength(4);callee3.getElement().setPropertyString("placeholder","0000");
        flexTable.setWidget(4,0,label3);
        flexTable.setWidget(4,1,callee1);flexTable.setWidget(4,2,callee2);flexTable.setWidget(4,3,callee3);

        Label label4= new Label("Start Time: ");
        flexTable.setWidget(5,0,label4);
        startTime1.setWidth("80px"); startTime1.setEnabled(false);
        startTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        //dateBtn.setWidth("50px");
        dateBtn.addClickHandler(new AddCalClickHandler());
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateStr = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
                startTime1.setText(dateStr);
                datePicker.setVisible(false);
            }
        });
        datePicker.setVisible(false);

        String[] num = {"00","01","02","03","04","05","06","07","08","09"};
       // startTime2a.setWidth("50px");
        for(String n: num){startTime2a.addItem(n);}for(int i=10;i<13;i++){startTime2a.addItem(String.valueOf(i));}
       // startTime2b.setWidth("50px");
        for(String n: num){startTime2b.addItem(n);}
        for(int j=10;j<60;j++){startTime2b.addItem(String.valueOf(j));}

       // startTime3.setWidth("50px");
        startTime3.addItem("AM");startTime3.addItem("PM");
        flexTable.setWidget(5, 1, startTime1);
        flexTable.setWidget(5,2,dateBtn);flexTable.setWidget(6,1,datePicker);
        flexTable.setWidget(5,3,startTime2a);flexTable.setWidget(5,4,startTime2b);
        flexTable.setWidget(5,5,startTime3);


        Label label5= new Label("End Time: ");
        flexTable.setWidget(7,0,label5);
        endTime1.setWidth("80px"); endTime1.setEnabled(false);
        endTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
       // dateBtn2.setWidth("50px");
        dateBtn2.addClickHandler(new AddCal2ClickHandler());
        datePicker2.addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateStr = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
                endTime1.setText(dateStr);
                datePicker2.setVisible(false);
            }
        });
        datePicker2.setVisible(false);

       // endTime2a.setWidth("50px");
        for(String n: num){endTime2a.addItem(n);}for(int i=10;i<13;i++){endTime2a.addItem(String.valueOf(i));}
      //  endTime2b.setWidth("50px");
        for(String n: num){endTime2b.addItem(n);}
        for(int j=10;j<60;j++){endTime2b.addItem(String.valueOf(j));}
       // endTime3.setWidth("50px");
        endTime3.addItem("AM");endTime3.addItem("PM");
        flexTable.setWidget(7,1,endTime1);
        flexTable.setWidget(7,2,dateBtn2);flexTable.setWidget(8,1,datePicker2);
        flexTable.setWidget(7,3,endTime2a);flexTable.setWidget(7,4,endTime2b);flexTable.setWidget(7,5,endTime3);


        flexTable.setWidget(9,1,addCallBtn);
        addCallBtn.addClickHandler(new AddCallClickHandler());
         flexTable.getFlexCellFormatter().setColSpan(9, 1, 5);

        this.verticalPanel.add(flexTable);

    }

    private class AddKeyPressHandler implements KeyPressHandler{

        @Override
        public void onKeyPress(KeyPressEvent event) {

           if (!Character.isDigit(event.getCharCode())) {
                ((TextBox) event.getSource()).cancelKey();
            }
        }
    }

    private class AddCalClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
            datePicker.setVisible(true);
        }
    }
    private class AddCal2ClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
            datePicker2.setVisible(true);
        }
    }

    private class AddCallClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
            String customerName = customer.getText();
            String start= startTime1.getText()+" "+startTime2a.getSelectedValue()+":"+startTime2b.getSelectedValue()+" "+startTime3.getSelectedValue();
            String end= endTime1.getText()+" "+endTime2a.getSelectedValue()+":"+endTime2b.getSelectedValue()+" "+ endTime3.getSelectedValue();
            String callerNumber = validatePhoneNumbers(caller1.getText()+"-"+caller2.getText()+"-"+caller3.getText());
            String calleeNumber = validatePhoneNumbers(callee1.getText()+"-"+callee2.getText()+"-"+callee3.getText());

            if(customerName.equals("") || customerName == null){Window.alert("Invalid Customer Name");}
            if(flag){flag = false; Window.alert("Please fill Phone number again");}
            else{
                PingServiceAsync async = GWT.create(PingService.class);
            async.addPhoneCall(customerName,callerNumber,calleeNumber,start,end, new AsyncCallback<AbstractPhoneBill>() {
                @Override
                public void onFailure(Throwable throwable) {
                    Window.alert(throwable.toString());
                }
                @Override
                public void onSuccess(AbstractPhoneBill phonebill) {
                   mainPage.openBillPage();
                }
            }); }

        }
        private String validatePhoneNumbers(String number){

            String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";
            if (number== null || "".equals(number) || !number.matches(phoneNumberPattern)){
                flag= true;
                Window.alert("Invalid Phone number"+ number);
            }
            return number;
        }


    }

}
