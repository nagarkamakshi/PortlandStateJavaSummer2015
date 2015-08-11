package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This is ReadME Page for the user, explain the details about Project.
 *
 * @author Kamakshi Nagar
 */
public class HelpPage extends Composite {

    private VerticalPanel verticalPanel= new VerticalPanel();

    public HelpPage(){
        initWidget(this.verticalPanel);

        Label label= new HTML("<h2><p>This is a README for java Project 5 : A Rich Internet Application for a Phone Bill at Portland State University Summer 2015, created by Kamakshi Nagar.</br></br>" +
                "The project is to generate a phone bill for the customers by maintaining the record of calls at given time.</br></br>" +
                "Enter customer name, phone number and callee's phone number for a given time, to add a new call to the Bill.</br></br>" +
                "Phone Bill has Customer name for whom we are generating the Bill and also callerNumber, calleeNumber, startTime and endTime of call with call duration.</br></br>" +
                "Search Phone calls for a certain time period using serach menu.</br></p></h2>");

        this.verticalPanel.add(label);
    }
}
