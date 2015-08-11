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
 * This class defines the UI for searching phone call for given time period
 * and also method to search the calls in the Phone bill in server.
 *
 * @author Kamakshi Nagar
 */
public class SearchPage extends Composite {


    private VerticalPanel verticalPanel = new VerticalPanel();
    private FlexTable flexTable = new FlexTable();
    MainPage mainPage;

    TextBox customer = new TextBox();
    TextBox startTime1 = new TextBox();
    DatePicker datePicker = new DatePicker();
    Anchor dateBtn = new Anchor("Cal");
    ListBox startTime2a = new ListBox();
    ListBox startTime2b = new ListBox();
    ListBox startTime3 = new ListBox();
    TextBox endTime1 = new TextBox();
    DatePicker datePicker2 = new DatePicker();
    Anchor dateBtn2 = new Anchor("Cal");
    ListBox endTime2a = new ListBox();
    ListBox endTime2b = new ListBox();
    ListBox endTime3 = new ListBox();


    public SearchPage(MainPage main) {

        initWidget(this.verticalPanel);
        this.mainPage = main;
        flexTable.setCellPadding(5);
        flexTable.setCellSpacing(5);


        Label label = new HTML("<h2> Search Your Calls </h2>");
        flexTable.setWidget(1, 1, label);
        flexTable.getFlexCellFormatter().setColSpan(1, 1, 5);

        Label reqLabel = new Label("* All fields are required");
        reqLabel.setStyleName("red_label");
        Label label1 = new Label("Customer Name: ");
        flexTable.setWidget(2, 0, label1);
        flexTable.setWidget(2, 1, customer);
        customer.setMaxLength(25);

        customer.getElement().setPropertyString("placeholder", "Name");
        flexTable.getFlexCellFormatter().setColSpan(2, 1, 4);

        Label label4 = new Label("Start Time: ");
        flexTable.setWidget(3, 0, label4);
        startTime1.setWidth("80px");
        startTime1.setEnabled(false);
        dateBtn = new Anchor("Cal");
        dateBtn.addClickHandler(new AddCalClickHandler());
        datePicker = new DatePicker();
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateStr = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
                startTime1.setText(dateStr);
                datePicker.setVisible(false);
            }
        });
        datePicker.setVisible(false);
        String[] num = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
        for (String n : num) {
            startTime2a.addItem(n);
        }
        for (int i = 10; i < 13; i++) {
            startTime2a.addItem(String.valueOf(i));
        }
        for (String n : num) {
            startTime2b.addItem(n);
        }
        for (int j = 10; j < 60; j++) {
            startTime2b.addItem(String.valueOf(j));
        }

        startTime3.addItem("AM");
        startTime3.addItem("PM");
        flexTable.setWidget(3, 1, startTime1);
        flexTable.setWidget(3, 2, dateBtn);
        flexTable.setWidget(4, 1, datePicker);
        flexTable.setWidget(3, 3, startTime2a);
        flexTable.setWidget(3, 4, startTime2b);
        flexTable.setWidget(3, 5, startTime3);


        Label label5 = new Label("End Time: ");
        flexTable.setWidget(5, 0, label5);
        endTime1.setWidth("80px");
        endTime1.setEnabled(false);
        startTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        endTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        dateBtn2 = new Anchor("Cal");
        dateBtn2.addClickHandler(new AddCal2ClickHandler());
        datePicker2 = new DatePicker();
        datePicker2.addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateStr = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
                endTime1.setText(dateStr);
                datePicker2.setVisible(false);
            }
        });
        datePicker2.setVisible(false);

        for (String n : num) {
            endTime2a.addItem(n);
        }
        for (int i = 10; i < 13; i++) {
            endTime2a.addItem(String.valueOf(i));
        }
        for (String n : num) {
            endTime2b.addItem(n);
        }
        for (int j = 10; j < 60; j++) {
            endTime2b.addItem(String.valueOf(j));
        }
        endTime3.addItem("AM");
        endTime3.addItem("PM");
        flexTable.setWidget(5, 1, endTime1);
        flexTable.setWidget(5, 2, dateBtn2);
        flexTable.setWidget(6, 1, datePicker2);
        flexTable.setWidget(5, 3, endTime2a);
        flexTable.setWidget(5, 4, endTime2b);
        flexTable.setWidget(5, 5, endTime3);


        flexTable.setWidget(8, 1, reqLabel);
        Button searchButton = new Button("Search Call");
        flexTable.setWidget(9, 1, searchButton);
        searchButton.addClickHandler(new SearchButtonClickHandler());

        flexTable.getFlexCellFormatter().setColSpan(9, 1, 5);
        this.verticalPanel.add(flexTable);
    }

    /**
     * This class defines handler for search calls button which takes all the inputs from the
     * text boxes and widgets and search the calls in the Phone bill.
     * Also performs some client side validation for fields.
     */
    private class SearchButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent clickEvent) {

            String customerName = customer.getText();
            String start = startTime1.getText() + " " + startTime2a.getSelectedValue() + ":" + startTime2b.getSelectedValue() + " " + startTime3.getSelectedValue();
            String end = endTime1.getText() + " " + endTime2a.getSelectedValue() + ":" + endTime2b.getSelectedValue() + " " + endTime3.getSelectedValue();
            final Date startDate = validateTime(start);
            Date endDate = validateTime(end);
            if (customerName == null|| customerName.equals("")) {
                Window.alert("Invalid Customer Name");
            } else {
                mainPage.openBillPage(customerName, startDate, endDate);
            }

        }

        private Date validateTime(String time) {
            Date newDate = null;
            try {
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
                newDate = dateTimeFormat.parse(time);
            } catch (Exception e) {
                Window.alert("Date Parsing Error" + e);
            }
            return newDate;
        }
    }

    /**
     * This class is for calander widget in UI
     */
    private class AddCalClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent clickEvent) {
            datePicker.setVisible(true);
        }
    }

    /**
     * This class is for calander widget in UI
     */
    private class AddCal2ClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent clickEvent) {
            datePicker2.setVisible(true);
        }
    }
}
