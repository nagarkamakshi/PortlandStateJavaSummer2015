package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;



/**
 *This class creates a Menu For UI.
 * It has three buttons and one Help menu with ReadMe Menu-Item.
 * @author Kamakshi Nagar
 */
public class MenuBarPage extends Composite {


    private HorizontalPanel horizontalPanel = new HorizontalPanel();
    private FlexTable flexTable = new FlexTable();
    private MenuBar menuBar = new MenuBar();
    Button btn1 = new Button("CALL");
    Button btn2 = new Button("BILL");
    Button btn3 = new Button("SEARCH");
    MainPage mainPage;

    public MenuBarPage(final MainPage mainPage){
        initWidget(this.horizontalPanel);
        this.mainPage = mainPage;
        flexTable.setCellPadding(5);
        flexTable.setCellSpacing(5);

        flexTable.setWidget(1,1,btn1);
        btn1.addClickHandler(new CallClickHandler());

        flexTable.setWidget(1,2,btn2);
        btn2.addClickHandler(new BillClickHandler());

        flexTable.setWidget(1,3,btn3);
        btn3.addClickHandler(new SearchClickHandler());

        flexTable.setWidget(1,4,menuBar);

        MenuBar helpmenu = new MenuBar(true);
        menuBar.addItem(" HELP ",helpmenu);
        menuBar.setWidth("0");
        Command cmd = new Command() {
            public void execute() {
                History.newItem("help");
                Window.setTitle("A Phone Bill in GWT - Call");
                mainPage.openHelpPage();
            }
        };
        helpmenu.addItem("ReadMe",cmd);

        History.fireCurrentHistoryState();

        this.horizontalPanel.add(flexTable);
    }


    private class CallClickHandler implements ClickHandler{
        @Override
        public void onClick(ClickEvent clickEvent) {
            History.newItem("call");
           Window.setTitle("A Phone Bill in GWT - Call");
            mainPage.openCallPage();
        }
    }
    private class BillClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {

            History.newItem("bill");
           Window.setTitle("A Phone Bill in GWT - Bill");
            mainPage.openBillPage();
        }
    }
    private class SearchClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
            History.newItem("search");
           Window.setTitle("A Phone Bill in GWT - Search");
            mainPage.openSearchPage();
        }
    }
}
