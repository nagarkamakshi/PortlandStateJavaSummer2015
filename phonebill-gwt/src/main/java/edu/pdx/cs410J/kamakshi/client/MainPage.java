package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;


/**
 * Created by vaio on 01-08-2015.
 */
public class MainPage extends Composite {


    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel contentPanel = new VerticalPanel();;

    public MainPage(){
        initWidget(this.vPanel);
        this.vPanel.setBorderWidth(0);
        this.vPanel.getElement().getStyle().setBackgroundColor("#E6E6FA");
        this.vPanel.getElement().setAttribute("align", "center");
        this.vPanel.setWidth("600px");


        MenuBarPage menuBarPage = new MenuBarPage(this);
        this.vPanel.add(menuBarPage);

        this.vPanel.add(this.contentPanel);

       Image img = new Image("/Images/payphone-bill-harrison.jpg");
        img.setWidth("550px");

        this.contentPanel.add(img);
    }

    public  void openCallPage(){
        this.contentPanel.clear();
        CallPage callPage = new CallPage(this);
        this.contentPanel.add(callPage);
    }
    public void openBillPage() {
        this.contentPanel.clear();
       // Window.alert(phoneBill.toString());
        BillPage billPage = new BillPage();
        //billPage.displayPhoneBill(phoneBill);
        this.contentPanel.add(billPage);
    }
    public void openBillPage(Date start,Date end) {
        this.contentPanel.clear();
        // Window.alert(phoneBill.toString());
        BillPage billPage = new BillPage(start,end);
        //billPage.displayPhoneBill(phoneBill);
        this.contentPanel.add(billPage);
    }
    public  void openSearchPage(){
        this.contentPanel.clear();
        SearchPage searchPage = new SearchPage(this);
        this.contentPanel.add(searchPage);
    }
    public  void openHelpPage(){
        this.contentPanel.clear();
        HelpPage helpPage = new HelpPage();
        this.contentPanel.add(helpPage);
    }

    public void addSelectionHandler(SelectionHandler<Integer> selectionHandler) {


    }
}
