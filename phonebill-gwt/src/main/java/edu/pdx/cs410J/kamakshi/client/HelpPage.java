package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Created by vaio on 01-08-2015.
 */
public class HelpPage extends Composite {

    private VerticalPanel verticalPanel= new VerticalPanel();

    public HelpPage(){
        initWidget(this.verticalPanel);

        Label label= new Label("This is a ReadMe Page");
        label.setWidth("3");

        this.verticalPanel.add(label);
    }
}
