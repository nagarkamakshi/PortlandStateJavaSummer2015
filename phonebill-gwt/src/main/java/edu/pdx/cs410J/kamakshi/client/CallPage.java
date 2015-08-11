package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.Date;

/**
 * This class defines the UI for placing a phone call and
 * also method to add the call to the Phone bill in server.
 *
 * @author Kamakshi Nagar
 */
public class CallPage extends Composite {

    private VerticalPanel verticalPanel;
    private FlexTable flexTable;
    MainPage mainPage;
    TextBox customer;
    TextBox caller1;
    TextBox caller2;
    TextBox caller3;
    TextBox callee1;
    TextBox callee2;
    TextBox callee3;
    TextBox startTime1;
    DatePicker datePicker;
    Anchor dateBtn;
    ListBox startTime2a;
    ListBox startTime2b;
    ListBox startTime3;
    TextBox endTime1;
    DatePicker datePicker2;
    Anchor dateBtn2;
    ListBox endTime2a;
    ListBox endTime2b;
    ListBox endTime3;
    Button addCallBtn;
    boolean flag = false;

    /**
     * This constructor defines the call Page User Interface.
     * It has text boxes, buttons, date picker and list boxes to enter a phone call.
     *
     * @param main where the UI for call should be.
     */
    public CallPage(MainPage main) {
        verticalPanel = new VerticalPanel();
        initWidget(this.verticalPanel);
        this.mainPage = main;

        Label label = new HTML("<h2>Make A New Call</h2>");
        flexTable = new FlexTable();
        flexTable.setCellPadding(5);
        flexTable.setCellSpacing(5);
        flexTable.setWidget(0, 1, label);
        flexTable.getFlexCellFormatter().setColSpan(0, 1, 5);

        Label label1 = new Label("Customer Name: ");
        customer = new TextBox();
        customer.getElement().setPropertyString("placeholder", "Name");
        customer.setMaxLength(25);
        flexTable.setWidget(2, 0, label1);
        flexTable.setWidget(2, 1, customer);
        flexTable.getFlexCellFormatter().setColSpan(2, 1, 4);

        Label label2 = new Label("Caller Number: ");
        caller1 = new TextBox();
        caller1.addKeyPressHandler(new AddKeyPressHandler());
        caller1.setWidth("50px");
        caller1.setMaxLength(3);
        caller1.getElement().setPropertyString("placeholder", "000");
        caller2 = new TextBox();
        caller2.addKeyPressHandler(new AddKeyPressHandler());
        caller2.setWidth("50px");
        caller2.setMaxLength(3);
        caller2.getElement().setPropertyString("placeholder", "000");
        caller3 = new TextBox();
        caller3.addKeyPressHandler(new AddKeyPressHandler());
        caller3.setWidth("50px");
        caller3.setMaxLength(4);
        caller3.getElement().setPropertyString("placeholder", "0000");
        flexTable.setWidget(3, 0, label2);
        flexTable.setWidget(3, 1, caller1);
        flexTable.setWidget(3, 2, caller2);
        flexTable.setWidget(3, 3, caller3);

        Label label3 = new Label("Callee Number: ");
        callee1 = new TextBox();
        callee1.addKeyPressHandler(new AddKeyPressHandler());
        callee1.setWidth("50px");
        callee1.setMaxLength(3);
        callee1.getElement().setPropertyString("placeholder", "000");
        callee2 = new TextBox();
        callee2.addKeyPressHandler(new AddKeyPressHandler());
        callee2.setWidth("50px");
        callee2.setMaxLength(3);
        callee2.getElement().setPropertyString("placeholder", "000");
        callee3 = new TextBox();
        callee3.addKeyPressHandler(new AddKeyPressHandler());
        callee3.setWidth("50px");
        callee3.setMaxLength(4);
        callee3.getElement().setPropertyString("placeholder", "0000");
        flexTable.setWidget(4, 0, label3);
        flexTable.setWidget(4, 1, callee1);
        flexTable.setWidget(4, 2, callee2);
        flexTable.setWidget(4, 3, callee3);

        Label label4 = new Label("Start Time: ");
        flexTable.setWidget(5, 0, label4);
        startTime1 = new TextBox();
        startTime1.setWidth("80px");
        startTime1.setEnabled(false);
        startTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        dateBtn = new Anchor("Date");
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

        String[] num = {"01", "02", "03", "04", "05", "06", "07", "08", "09"};

        startTime2a = new ListBox();
        for (String n : num) {
            startTime2a.addItem(n);
        }
        for (int i = 10; i < 13; i++) {
            startTime2a.addItem(String.valueOf(i));
        }

        startTime2b = new ListBox();
        startTime2b.addItem("00");
        for (String n : num) {
            startTime2b.addItem(n);
        }
        for (int j = 10; j < 60; j++) {
            startTime2b.addItem(String.valueOf(j));
        }

        startTime3 = new ListBox();
        startTime3.addItem("AM");
        startTime3.addItem("PM");
        flexTable.setWidget(5, 1, startTime1);
        flexTable.setWidget(5, 2, dateBtn);
        flexTable.setWidget(6, 1, datePicker);
        flexTable.setWidget(5, 3, startTime2a);
        flexTable.setWidget(5, 4, startTime2b);
        flexTable.setWidget(5, 5, startTime3);


        Label label5 = new Label("End Time: ");
        flexTable.setWidget(7, 0, label5);
        endTime1 = new TextBox();
        endTime1.setWidth("80px");
        endTime1.setEnabled(false);
        endTime1.setText(DateTimeFormat.getFormat("MM/dd/yyyy").format(new Date()));
        dateBtn2 = new Anchor("Date");
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

        endTime2a = new ListBox();
        for (String n : num) {
            endTime2a.addItem(n);
        }
        for (int i = 10; i < 13; i++) {
            endTime2a.addItem(String.valueOf(i));
        }
        endTime2b = new ListBox();
        endTime2b.addItem("00");
        for (String n : num) {
            endTime2b.addItem(n);
        }
        for (int j = 10; j < 60; j++) {
            endTime2b.addItem(String.valueOf(j));
        }
        endTime3 = new ListBox();
        endTime3.addItem("AM");
        endTime3.addItem("PM");
        flexTable.setWidget(7, 1, endTime1);
        flexTable.setWidget(7, 2, dateBtn2);
        flexTable.setWidget(8, 1, datePicker2);
        flexTable.setWidget(7, 3, endTime2a);
        flexTable.setWidget(7, 4, endTime2b);
        flexTable.setWidget(7, 5, endTime3);

        Label reqLabel = new Label("* All fields are required");
        reqLabel.setStyleName("red_label");
        flexTable.setWidget(10, 1, reqLabel);
        addCallBtn = new Button("Add Call");
        flexTable.setWidget(11, 1, addCallBtn);
        addCallBtn.addClickHandler(new AddCallClickHandler());
        flexTable.getFlexCellFormatter().setColSpan(11, 1, 5);

        this.verticalPanel.add(flexTable);

    }

    /**
     * This class prevents character other than numbers to be typed in the
     * text box.
     */
    private class AddKeyPressHandler implements KeyPressHandler {
        @Override
        public void onKeyPress(KeyPressEvent event) {
            if (!Character.isDigit(event.getCharCode())) {
                ((TextBox) event.getSource()).cancelKey();
            }
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

    /**
     * This class defines handler for Add call button which takes all the inputs from the
     * text boxes and widgets and add the call to the Phone bill.
     * Also performs some client side validation for fields.
     */
    private class AddCallClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent clickEvent) {
            final String customerName = customer.getText();
            String start = startTime1.getText() + " " + startTime2a.getSelectedValue() + ":" + startTime2b.getSelectedValue() + " " + startTime3.getSelectedValue();
            String end = endTime1.getText() + " " + endTime2a.getSelectedValue() + ":" + endTime2b.getSelectedValue() + " " + endTime3.getSelectedValue();
            String callerNumber = validatePhoneNumbers(caller1.getText() + "-" + caller2.getText() + "-" + caller3.getText());
            String calleeNumber = validatePhoneNumbers(callee1.getText() + "-" + callee2.getText() + "-" + callee3.getText());

            if (customerName == null|| customerName.equals("")) {
                Window.alert("Invalid Customer Name");
            }
            if (flag) {
                flag = false;
                Window.alert("Please fill Phone number again");
            } else {
                PingServiceAsync async = GWT.create(PingService.class);
                try {
                    async.addPhoneCall(customerName, callerNumber, calleeNumber, start, end, getBill());
                } catch (PhoneBillGwtException e) {
                    Window.alert(e.getMessage());
                }
            }

        }

        private String validatePhoneNumbers(String number) {

            String phoneNumberPattern = "\\d{3}-\\d{3}-\\d{4}";
            if (number == null || "".equals(number) || !number.matches(phoneNumberPattern)) {
                flag = true;
                Window.alert("Invalid Phone number" + number);
            }
            return number;
        }


    }

    private AsyncCallback<AbstractPhoneBill> getBill() {
        return new AsyncCallback<AbstractPhoneBill>() {
            @Override
            public void onFailure(Throwable throwable) {

                Window.alert(throwable.toString());
            }

            @Override
            public void onSuccess(AbstractPhoneBill phonebill) {
                History.newItem("bill");
                Window.setTitle("A Phone Bill in GWT - Bill");
                mainPage.openBillPage();
            }
        };
    }

}
