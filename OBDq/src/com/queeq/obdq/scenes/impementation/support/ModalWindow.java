/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.impementation.support;

import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.settings.Globals;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author admin
 */
public class ModalWindow 
{
    public ModalWindow()
    {}
    /**
     * generates the modal window content. The close button will be enabled
     * @param title String of the modal window
     * @param message String of the modal window
     * @param buttons ArrayList<Buttons> list to be displayed on the modal window.
     * @return StackPane the modal window content
     ***/
    public static StackPane getModalWindow(String title,String message,ArrayList<Button> buttons)
    {
        return getModalWindow(title,message,buttons,true);
    }
    /**
     * generates the modal window content
     * @param title String of the modal window
     * @param message String of the modal window
     * @param buttons ArrayList<Buttons> list to be displayed on the modal window.
     * @param showCloseButton boolean 
     * @return StackPane the modal window content
     ***/
    public static StackPane getModalWindow(String title,String message,ArrayList<Button> buttons,boolean showCloseButton)
    {
         StackPane modalWindow = new StackPane();
         modalWindow.setOpacity(0);
         modalWindow.setVisible(false);
         modalWindow.setId("modalWindow");
            HBox background = new HBox();
            background.setId("modalWindowBackground");
            
                VBox ModalWindowContainer= new VBox();
                ModalWindowContainer.setPadding(new Insets(200,50,10,50));
                ModalWindowContainer.setSpacing(10);
                ModalWindowContainer.setId("ModalWindowContainer");

                   
                    //Internet connection settings
                    VBox contentContainer = new VBox();
                    StackPane modalWindowLableContainer = new StackPane();
                    modalWindowLableContainer.setId("modalWindowLableContainer");
                        HBox modalWindowLableBackground = new HBox();
                        modalWindowLableBackground.setId("modalWindowLableBackground");

                        Label modalWindowLable = new Label(title);
                        modalWindowLable.setId("modalWindowLable");
                        modalWindowLable.setContentDisplay(ContentDisplay.LEFT);
                    modalWindowLableContainer.getChildren().addAll(modalWindowLableBackground,modalWindowLable);
                    
                            VBox messageButtonsContainer = new VBox();
                            messageButtonsContainer.setId("modalWindowMessageButtonsContainer");
                            messageButtonsContainer.setPadding(new Insets(10,10,10,10));
                            messageButtonsContainer.setSpacing(10);
                            messageButtonsContainer.prefHeightProperty().bind(modalWindow.heightProperty().divide(3));
                                HBox messageContainer = new HBox();
                                messageContainer.setId("modalWindowMessageContainer");
                                    TextArea modalWindowMessageTextArea=new TextArea();
                                    modalWindowMessageTextArea.setEditable(false);
                                    modalWindowMessageTextArea.setWrapText(true);
                                    modalWindowMessageTextArea.setFocusTraversable(false);
                                    modalWindowMessageTextArea.prefHeightProperty().bind(messageButtonsContainer.heightProperty().divide(3/2));
                                    modalWindowMessageTextArea.prefWidthProperty().bind(modalWindow.widthProperty());
                                    modalWindowMessageTextArea.setId("modalWindowMessageTextArea");
                                    modalWindowMessageTextArea.setText(message);
                                messageContainer.getChildren().addAll(modalWindowMessageTextArea);
                                
                                HBox modalWindowButtonsContainer = new HBox();
                                modalWindowButtonsContainer.setId("modalWindowButtonsContainer");
                                modalWindowButtonsContainer.setSpacing(10);
                                modalWindowButtonsContainer.setAlignment(Pos.CENTER);

                                if(showCloseButton)
                                    modalWindowButtonsContainer.getChildren().addAll(ModalWindowController.addCloseButton(buttons,modalWindow));
                                else
                                    modalWindowButtonsContainer.getChildren().addAll(buttons);
                            messageButtonsContainer.getChildren().addAll(messageContainer,modalWindowButtonsContainer);
                       contentContainer.getChildren().addAll(modalWindowLableContainer,messageButtonsContainer);             
                    ModalWindowContainer.getChildren().addAll(contentContainer);
         modalWindow.getChildren().addAll(background,ModalWindowContainer);
         Globals.modalWindow=modalWindow;
         return modalWindow;
    }
      
}
