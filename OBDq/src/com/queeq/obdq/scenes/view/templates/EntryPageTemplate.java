/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view.templates;

import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author admin
 */
public abstract class EntryPageTemplate 
{
    private int order=-1;
    public EntryPageTemplate(){}
     public abstract void getPageContent(StackPane root,BorderPane pane);
     public abstract ImageView getEntryIcon();
     public int getOrder()
     {
         return order;
     }
     public void setOrder(int val)
     {
         order=val;
     }

}
