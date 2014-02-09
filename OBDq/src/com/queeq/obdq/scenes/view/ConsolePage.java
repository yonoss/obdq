/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.serial.utils.SerialUtils;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author admin
 */
public class ConsolePage extends EntryPageTemplate  
{

    public ConsolePage()
    {
        setOrder(2);
    }
    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {  
            //page body
              VBox Container = new VBox();
              Container.setPadding(new Insets(10,10,10,10));
              Container.setSpacing(10);

                    Globals.receivedMessages.setEditable(false);
                    Globals.receivedMessages.setWrapText(true);
                    Globals.receivedMessages.setFocusTraversable(false);
                    Globals.receivedMessages.prefHeightProperty().bind(pane.heightProperty().subtract(100));
                    Globals.receivedMessages.setId("receivedMessagesConsolePage");
 
                    HBox commanFieldContainer=new HBox();
                    commanFieldContainer.setId("commanField");
                    commanFieldContainer.setPadding(new Insets(0,0,0,0));
                    commanFieldContainer.setSpacing(10);
                        final TextField commandField =new TextField();
                        commandField.setPrefColumnCount(40);
                        commandField.setFocusTraversable(true);
                        commandField.requestFocus();
                        commandField.setId("commandFieldConsolePage");
                        commandField.prefWidthProperty().bind(pane.widthProperty());
                        commandField.setOnKeyReleased(new EventHandler<KeyEvent>() 
                        {
                            @Override
                            public void handle(KeyEvent arg0) 
                            {
                                if(arg0.getCode()==KeyCode.ENTER)
                                {
                                    if(!SerialUtils.isSerialPortOpened())
                                    {
                                        ModalWindowController.openModalWindow(LN.getString("sensorsPage.noCarConnection.title"),LN.getString("sensorsPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
                                    }
                                    else
                                    {
                                        SerialUtils.getSyncSerialResponseForCommand(commandField.getText());
                                        commandField.clear();
                                    }
                                }
                            }
                        });
                        
                        Button sendCommand = new Button(LN.getString("consolePage.sendCommand"));
                        sendCommand.setId("sendCommand");
                        sendCommand.setPrefWidth(250);
                        sendCommand.setCursor(Cursor.HAND);
                        sendCommand.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            { 
                                 if(!SerialUtils.isSerialPortOpened())
                                {
                                    ModalWindowController.openModalWindow(LN.getString("sensorsPage.noCarConnection.title"),LN.getString("sensorsPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
                                }
                                else
                                {
                                    SerialUtils.getSyncSerialResponseForCommand(commandField.getText());   
                                    commandField.clear();
                                }
                            }
                        });
                    commanFieldContainer.getChildren().addAll(commandField,sendCommand);   
                Container.getChildren().addAll(Globals.receivedMessages,commanFieldContainer);     
            pane.setCenter(Container);    
         if(!SerialUtils.isSerialPortOpened())
        {
           ModalWindowController.openModalWindow(LN.getString("consolePage.noCarConnection.title"),LN.getString("consolePage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
        }
    }
     /**
     * generates the connection not available modal window buttons 
     ***/
    private ArrayList<Button> getConnectionNotAvailableModalWindowButton()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonSettings = new Button(LN.getString("consolePage.noCarConnection.settingsButton"));
            buttonSettings.setId("buttonSettingsConsolePage");
            buttonSettings.setOnAction(new EventHandler<ActionEvent>() 
            {
                public void handle(ActionEvent e) 
                { 
                    ModalWindowController.closeModalWindow(Globals.root, Globals.modalWindow);
                    SceneDispatcher.getScene(SettingsPage.class,Globals.root,Globals.pane);
                }
            });
        buttons.add(buttonSettings);
        return buttons;
    }

    @Override
    public ImageView getEntryIcon() 
    {
        ImageView ConsoleButton = new ImageView();
        ConsoleButton.setId("ConsoleButtonLandingPage");
        ConsoleButton.setCursor(Cursor.HAND);
        ConsoleButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                SceneDispatcher.getScene(ConsolePage.class,Globals.root,Globals.pane);
            }
        });
        return ConsoleButton;
    }
}
