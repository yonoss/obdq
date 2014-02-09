/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.elm327.Others;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.view.templates.StandardPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.SettingsUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author admin
 */
public class ConfirmClearCodePage extends StandardPageTemplate
{
     @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
                //page body
                VBox settingPageContainer= new VBox();
                settingPageContainer.setPadding(new Insets(200,50,10,50));
                settingPageContainer.setSpacing(10);
                settingPageContainer.setId("ConfirmClearCodePageContainer");

                   
                    //Internet connection settings
                    VBox internetConnectionContainer = new VBox();
                    StackPane internetConnectionLableContainer = new StackPane();
                    internetConnectionLableContainer.setId("confirmClearCodeLableContainer");
                        HBox internetConnectionLableBackground = new HBox();
                        internetConnectionLableBackground.setId("confirmClearCodeLableBackground");

                        Label internetConnectionLable = new Label(LN.getString("confirmClearCodePage.confirmClearCode"));
                        internetConnectionLable.setId("confirmClearCodeLableText");
                        internetConnectionLable.setContentDisplay(ContentDisplay.LEFT);
                    internetConnectionLableContainer.getChildren().addAll(internetConnectionLableBackground,internetConnectionLable);
                        
                            VBox messageButtonsContainer = new VBox();
                            messageButtonsContainer.setId("confirmClearCodeContainer");
                            messageButtonsContainer.setPadding(new Insets(10,10,10,10));
                            messageButtonsContainer.prefHeightProperty().bind(pane.heightProperty().divide(3));
                                HBox messageContainer = new HBox();
                                messageContainer.setId("confirmClearCodeMessageContainer");
                                    TextArea confirmClearCodeMessageTextArea=new TextArea();
                                    confirmClearCodeMessageTextArea.setEditable(false);
                                    confirmClearCodeMessageTextArea.setWrapText(true);
                                    confirmClearCodeMessageTextArea.setFocusTraversable(false);
                                    confirmClearCodeMessageTextArea.prefHeightProperty().bind(messageButtonsContainer.heightProperty().divide(3/2));
                                    confirmClearCodeMessageTextArea.prefWidthProperty().bind(pane.widthProperty().divide(5).multiply(4));
                                    confirmClearCodeMessageTextArea.setId("confirmClearCodeMessageTextArea");
                                    confirmClearCodeMessageTextArea.setText(LN.getString("confirmClearCodePage.confirmClearCodeMessage"));
                                messageContainer.getChildren().addAll(confirmClearCodeMessageTextArea);
                                HBox buttonsContainer = new HBox();
                                buttonsContainer.setId("confirmClearCodeButtonsContainer");
                                buttonsContainer.setSpacing(10);
                                buttonsContainer.setAlignment(Pos.CENTER);
                                    Button buttonYes = new Button(LN.getString("confirmClearCodePage.clear"));
                                    buttonYes.setId("clearCodeYes");
                                    buttonYes.setOnAction(new EventHandler<ActionEvent>() 
                                    {
                                        public void handle(ActionEvent e) 
                                        { 
                                           Others.resetTroubleCodes();
                                           SceneDispatcher.getScene(SettingsUtils.getPreviousPage(),Globals.root,Globals.pane);
                                        }
                                    });
                                    Button buttonNo = new Button(LN.getString("confirmClearCodePage.cancel"));
                                    buttonNo.setId("clearCodeNo");
                                    buttonNo.setOnAction(new EventHandler<ActionEvent>() 
                                    {
                                        public void handle(ActionEvent e) 
                                        { 
                                           SceneDispatcher.getScene(SettingsUtils.getPreviousPage(),Globals.root,Globals.pane);
                                        }
                                    });
                                buttonsContainer.getChildren().addAll(buttonYes,buttonNo);
                            messageButtonsContainer.getChildren().addAll(messageContainer,buttonsContainer);
                    internetConnectionContainer.getChildren().addAll(internetConnectionLableContainer,messageButtonsContainer);
                    
                    
                    
                    settingPageContainer.getChildren().addAll(internetConnectionContainer);
            pane.setCenter(settingPageContainer);
    }
}
