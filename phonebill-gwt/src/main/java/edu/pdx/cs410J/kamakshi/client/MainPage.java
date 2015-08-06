package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.pdx.cs410J.AbstractPhoneBill;

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
        this.vPanel.getElement().setAttribute("align", "center");
        this.vPanel.setWidth("600px");


        MenuBar menuBar= new MenuBar(this);
        this.vPanel.add(menuBar);

        this.vPanel.add(this.contentPanel);

       Image img = new Image("/Images/Vintage_valentine_cards.jpg");
        img.setWidth("600px");

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

}
