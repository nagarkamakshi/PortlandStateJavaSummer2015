package edu.pdx.cs410J.kamakshi.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;

/**
 * Created by vaio on 01-08-2015.
 */
public class SearchPage extends Composite {


    private VerticalPanel verticalPanel= new VerticalPanel();
    private FlexTable flexTable = new FlexTable();
    MainPage mainPage;

    TextBox customer = new TextBox();
    TextBox startTime1 = new TextBox();DatePicker datePicker = new DatePicker();  Button dateBtn= new Button("Cal");
    ListBox startTime2a = new ListBox(); ListBox startTime2b = new ListBox();
    ListBox startTime3 = new ListBox();
    TextBox endTime1 = new TextBox();DatePicker datePicker2 = new DatePicker();  Button dateBtn2= new Button("Cal");
    ListBox endTime2a = new ListBox();  ListBox endTime2b = new ListBox();
    ListBox endTime3 = new ListBox();


    public SearchPage(MainPage main){
        initWidget(this.verticalPanel);
        this.mainPage = main;
        flexTable.setCellPadding(5);
        flexTable.setCellSpacing(5);


        Label label= new Label("Enter Customer Name And Time");
        flexTable.setWidget(1,1,label);
        flexTable.getFlexCellFormatter().setColSpan(1, 1, 5);

        Label label1= new Label("Customer Name: ");
        flexTable.setWidget(2,0,label1);
        flexTable.setWidget(2,1,customer);
        customer.setMaxLength(25);
        customer.getElement().setPropertyString("placeholder", "Name");
        flexTable.getFlexCellFormatter().setColSpan(2, 1, 4);

        Label label4= new Label("Start Time: ");
        flexTable.setWidget(3,0,label4);
        startTime1.setWidth("80px"); startTime1.setEnabled(false);
        dateBtn.setWidth("50px");
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
        startTime2a.setWidth("50px"); for(String n: num){startTime2a.addItem(n);}for(int i=10;i<13;i++){startTime2a.addItem(String.valueOf(i));}
        startTime2b.setWidth("50px"); for(String n: num){startTime2b.addItem(n);}
        for(int j=10;j<60;j++){startTime2b.addItem(String.valueOf(j));}

        startTime3.setWidth("50px");startTime3.addItem("AM");startTime3.addItem("PM");
        flexTable.setWidget(3, 1, startTime1);
        flexTable.setWidget(3,2,dateBtn);flexTable.setWidget(4,1,datePicker);
        flexTable.setWidget(3,3,startTime2a);flexTable.setWidget(3,4,startTime2b);
        flexTable.setWidget(3,5,startTime3);


        Label label5= new Label("End Time: ");
        flexTable.setWidget(5,0,label5);
        endTime1.setWidth("80px"); endTime1.setEnabled(false);
        startTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        endTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        dateBtn2.setWidth("50px");
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

        endTime2a.setWidth("50px");for(String n: num){endTime2a.addItem(n);}for(int i=10;i<13;i++){endTime2a.addItem(String.valueOf(i));}
        endTime2b.setWidth("50px"); for(String n: num){endTime2b.addItem(n);}
        for(int j=10;j<60;j++){endTime2b.addItem(String.valueOf(j));}
        endTime3.setWidth("50px");
        endTime3.addItem("AM");endTime3.addItem("PM");
        flexTable.setWidget(5,1,endTime1);
        flexTable.setWidget(5,2,dateBtn2);flexTable.setWidget(6,1,datePicker2);
        flexTable.setWidget(5,3,endTime2a);flexTable.setWidget(5,4,endTime2b);flexTable.setWidget(5,5,endTime3);


        Button searchButton = new Button("Search Call");
        flexTable.setWidget(7,1,searchButton);
        searchButton.addClickHandler(new SearchButtonClickHandler());
        flexTable.getFlexCellFormatter().setColSpan(7, 1, 5);
        this.verticalPanel.add(flexTable);
    }

    private class SearchButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {

            String customerName = customer.getText();
            String start= startTime1.getText()+" "+startTime2a.getSelectedValue()+":"+startTime2b.getSelectedValue()+" "+startTime3.getSelectedValue();
            String end= endTime1.getText()+" "+endTime2a.getSelectedValue()+":"+endTime2b.getSelectedValue()+" "+ endTime3.getSelectedValue();
            final Date startDate = validateTime(start);
            Date endDate = validateTime(end);
            if(customerName.equals("") || customerName == null){Window.alert("Invalid Customer Name");}
            else{mainPage.openBillPage(startDate, endDate);}

        }

        private Date validateTime(String time){
            Date newDate= null;
            try {
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
                newDate = dateTimeFormat.parse(time);
            } catch (Exception e)
            {
                Window.alert("Date Parsing Error"+ e);
            }
            return newDate;
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
}
