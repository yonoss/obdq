/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.impementation.support.ModalWindow;
import com.queeq.obdq.settings.Globals;
import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author admin
 */
public class ModalWindowController 
{
    public static void closeModalWindow(StackPane root,StackPane modalWindow)
    {
        hideModalMessage(root,modalWindow);

    }
    public static void openModalWindow(String title,String message,StackPane root)
    {
        StackPane modalWindow=ModalWindow.getModalWindow(title,message,new ArrayList());
        root.getChildren().addAll(modalWindow);
        showModalMessage(modalWindow);
    }
    public static void openModalWindow(String title,String message,ArrayList<Button> buttons,StackPane root)
    {
        StackPane modalWindow=ModalWindow.getModalWindow(title,message,buttons);
        root.getChildren().addAll(modalWindow);
        showModalMessage(modalWindow);
    }
    public static void openModalWindow(String title,String message,ArrayList<Button> buttons,StackPane root,boolean showCloseButton)
    {
        StackPane modalWindow=ModalWindow.getModalWindow(title,message,buttons,showCloseButton);
        root.getChildren().addAll(modalWindow);
        showModalMessage(modalWindow);
    }
    /**
     *generate the modal window buttons
     * @param ArrayList<Buttons> buttons list to be displayed on the modal window.
     * @return ArrayList<Button> the modal window buttons list containing the close button.
     * 
     **/
    public static ArrayList<Button> addCloseButton(ArrayList<Button> buttons,final StackPane modalWindow)
    {
       // ArrayList<Button> buttons = new ArrayList();     
        
            Button buttonNo = new Button(LN.getString("modalWindow.button.close"));
            buttonNo.setId("modalWindowCloseButton");
            buttonNo.setCursor(Cursor.HAND);
            buttonNo.setOnAction(new EventHandler<ActionEvent>() 
            {
                public void handle(ActionEvent e) 
                { 
                    ModalWindowController.closeModalWindow(Globals.root, modalWindow);
                }
            });
            buttons.add(buttonNo);
            return buttons;
    }
    public static void showModalMessage(final StackPane modalWindow) 
      {
        modalWindow.setVisible(true);
        modalWindow.setCache(true);
        TimelineBuilder.create().keyFrames(
            new KeyFrame(Duration.seconds(1), 
                new EventHandler<ActionEvent>() 
                {
                    public void handle(ActionEvent t) 
                    {
                        modalWindow.setCache(false);
                    }
                },
                new KeyValue(modalWindow.opacityProperty(),1, Interpolator.EASE_BOTH)
        )).build().play();
    }
    public static void hideModalMessage(final StackPane root,final StackPane modalWindow) 
    {
        modalWindow.setCache(true);
        TimelineBuilder.create().keyFrames(
            new KeyFrame(Duration.seconds(1), 
                new EventHandler<ActionEvent>() 
                {
                    public void handle(ActionEvent t)
                    {
                        modalWindow.setCache(false);
                        modalWindow.setVisible(false);
                         int size=root.getChildren().size();
                         root.getChildren().remove(size-1);
                         Globals.modalWindow=null;
                    }
                },
                new KeyValue(modalWindow.opacityProperty(),0, Interpolator.EASE_BOTH)
        )).build().play();
    }
}
