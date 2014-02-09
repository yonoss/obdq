/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view.templates;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author admin
 */
public abstract class StandardPageTemplate 
{
    public StandardPageTemplate(){}
     public abstract void getPageContent(StackPane root,BorderPane pane);
    /**
     *@return the type of the page
     **/
}
