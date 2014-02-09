/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes;

import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.SettingsUtils;
import java.lang.reflect.Method;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author admin
 */
public class SceneDispatcher 
{
    /**
     *SceneSidpatched default constructor 
     **/
    public SceneDispatcher()
    {}
    /**
     *Dispatcher method for the scene content.
     * @param scene : the scene Class
     * @param root  : The main scene StackPane object 
     * @return void
     **/
    public static void getScene(Class scene, StackPane root,BorderPane pane)
    {
        pane.setCenter(null);
        SettingsUtils.setPageHistory(scene);
        try
        {
            getPageContentFromClass(scene,root,pane);    
        }
        catch(Exception e)
        {
        }
        Globals.currentPage=scene;  
        
         Task task = new Task<Void>() 
        {
            @Override public Void call() throws InterruptedException 
            {
                System.gc();
                return null;
             }
        };
        new Thread(task).start();
    }
    /**
     * Returns the specified page content
     * @param tmpCls - the the class for which the content will be returned
     * @param root - the application root StackPane
     * @param pane - the application main pane
     **/
    private static void getPageContentFromClass(Class tmpCls,StackPane root,BorderPane pane) throws Exception
    {
       // Class tmpCls=(Class)toSort.get(i);
        Object tmpObj = tmpCls.newInstance();
        Method method = tmpCls.getMethod("getPageContent", StackPane.class,BorderPane.class);
        Object temp= method.invoke(tmpObj, root,pane);
        temp=null;
        tmpObj=null;
        method=null;
    }
   /**
    * Clears the application root object; Memory leaks were observed prior of implementing this method 
    * @param root - the application root StackPane 
    **/
  /*  public static void CleanRoot(StackPane root) 
    {
        int size=root.getChildren().size();
        for (int i = size-1; i >=0; i--) 
        {
            Node g = (Node)root.getChildren().get(i);
            root.getChildren().remove(g);
            g=null;
        }
        root.getChildren().clear();  
    }*/
}