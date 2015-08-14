package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;


/**
 * This class is the main page for the UI.
 * It has two panel one for menu and other for content.
 * In content panel it opens CallPage, BillPage, SearchPage and HelpPage.
 *
 * @author Kamakshi Nagar
 */
public class MainPage extends Composite {


    private VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel contentPanel = new VerticalPanel();

    public MainPage() {
        initWidget(this.vPanel);
        this.vPanel.setBorderWidth(0);
        this.vPanel.getElement().getStyle().setBackgroundColor("#E6E6FA");
        this.vPanel.getElement().setAttribute("align", "center");
        this.vPanel.setWidth("600px");


        MenuBarPage menuBarPage = new MenuBarPage(this);
        this.vPanel.add(menuBarPage);

        this.vPanel.add(this.contentPanel);
        maintainHistory();

        Image img = new Image("/Images/payphone-bill-harrison.jpg");
        img.setWidth("600px");

        this.contentPanel.add(img);
    }

    private void maintainHistory() {
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                String token = event.getValue();
                if ("call".equals(token)) {
                    Window.setTitle("A Phone Bill in GWT - Call");
                    openCallPage();

                } else if ("bill".equals(token)) {
                    Window.setTitle("A Phone Bill in GWT - Bill");
                    openBillPage();

                } else if ("search".equals(token)) {
                    Window.setTitle("A Phone Bill in GWT - Search");
                    openSearchPage();
                } else {
                    Window.setTitle("A Phone Bill in GWT - Help");
                    openHelpPage();
                }

            }
        });
    }

    public void openCallPage() {
        this.contentPanel.clear();
        CallPage callPage = new CallPage(this);
        this.contentPanel.add(callPage);
    }

    public void openBillPage() {
        this.contentPanel.clear();
        BillPage billPage = new BillPage();
        this.contentPanel.add(billPage);
    }

    public void openBillPage(String customerName, Date start, Date end) {
        this.contentPanel.clear();
        // Window.alert(phoneBill.toString());
        BillPage billPage = new BillPage(customerName, start, end);
        //billPage.displayPhoneBill(phoneBill);
        this.contentPanel.add(billPage);
    }

    public void openSearchPage() {
        this.contentPanel.clear();
        SearchPage searchPage = new SearchPage(this);
        this.contentPanel.add(searchPage);
    }

    public void openHelpPage() {
        this.contentPanel.clear();
        HelpPage helpPage = new HelpPage();
        this.contentPanel.add(helpPage);
    }

}
