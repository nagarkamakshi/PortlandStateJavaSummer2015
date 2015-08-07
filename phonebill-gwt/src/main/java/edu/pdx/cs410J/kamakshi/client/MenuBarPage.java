package edu.pdx.cs410J.kamakshi.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Composite;



/**
 * Created by vaio on 01-08-2015.
 */
public class MenuBarPage extends Composite {


    private HorizontalPanel horizontalPanel = new HorizontalPanel();
    private FlexTable flexTable = new FlexTable();
    private MenuBar menuBar = new MenuBar();
    Button btn1 = new Button("Call");
    Button btn2 = new Button("Bill");
    Button btn3 = new Button("Search");
   // Button btn4 = new Button("Help");
    MainPage mainPage;

    public MenuBarPage(final MainPage mainPage){
        initWidget(this.horizontalPanel);
        this.mainPage = mainPage;
        flexTable.setCellPadding(5);
        flexTable.setCellSpacing(5);

      //  btn1.setWidth("60px");btn2.setWidth("60px");btn3.setWidth("60px");btn4.setWidth("60px");
        flexTable.setWidget(1,1,btn1);
        btn1.addClickHandler(new CallClickHandler());

        flexTable.setWidget(1,2,btn2);
        btn2.addClickHandler(new BillClickHandler());

        flexTable.setWidget(1,3,btn3);
        btn3.addClickHandler(new SearchClickHandler());

        flexTable.setWidget(1,15,menuBar);
       // btn4.addClickHandler(new HelpClickHandler());

        MenuBar helpmenu = new MenuBar(true);
       // helpmenu.setSize("60px","50px");
        menuBar.addItem(" Help ",helpmenu);
       // menuBar.getElement().getStyle().setBackgroundColor("#E6E6FA");
       // menuBar.getElement().getStyle().setBorderStyle(Style.BorderStyle.NONE);
        menuBar.setWidth("0");
      //  menuBar.setSize("60px", "25px");
        Command cmd = new Command() {
            public void execute() {
              mainPage.openHelpPage();
            }
        };
        helpmenu.addItem("ReadMe",cmd);


        this.horizontalPanel.add(flexTable);
    }


    private class CallClickHandler implements ClickHandler{
        @Override
        public void onClick(ClickEvent clickEvent) {
            mainPage.openCallPage();
        }
    }
    private class BillClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
                mainPage.openBillPage();
        }
    }
    private class SearchClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
        mainPage.openSearchPage();
        }
    }
    private class HelpClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent clickEvent) {
        mainPage.openHelpPage();
        }
    }
}
