/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq;

import com.queeq.obdq.internationalization.InternationalizationUtils;
import com.queeq.obdq.plugins.utils.ClassHelper;
import com.queeq.obdq.scenes.LandingPage;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.obdq.settings.SettingsUtils;
import com.queeq.serial.utils.SerialUtils;
import java.awt.SplashScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;

/**
 *
 * @author admin
 */
public class OBDQ extends Application 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage pStage) 
    {  
        Globals.primaryStage=pStage;
        Globals.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event) 
            {
                destruct();
            }
        });
//        Globals.primaryStage.widthProperty().addListener(new ChangeListener<Number>() 
//        {
//            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) 
//            {
//                if(newSceneWidth.intValue()>ObdqProperties.MaxWidth)
//                     Globals.primaryStage.setWidth(ObdqProperties.MaxWidth);
//            }
//          });
        
        
        initialize();
        
            SettingsUtils.setPageHistory(LandingPage.class);
            Globals.Page=new Scene(Globals.root , ObdqProperties.defaultWindowWidth, ObdqProperties.defaultWindowHeight);
            Globals.Page.getStylesheets().addAll(SettingsUtils.getTheamFolderLocation()+SettingsUtils.cssLocation);
            Globals.currentPage=LandingPage.class;
            new LandingPage().getPageContent(Globals.root,Globals.pane);     

        Globals.primaryStage.setScene(Globals.Page);
        Globals.primaryStage.show();
        SplashScreen splash = SplashScreen.getSplashScreen();
        if(splash!=null)
        {
            splash.close();
            splash=null;
        }

    }
    /**
     * Application window initialization method. Contains all the initialization methods  
     * @return void
     **/
    private void initialize()
    {
        //get the available plugins
        Globals.EntryPageListContent=ClassHelper.getClassListForType(EntryPageTemplate.class);
        //order the availble plugins by name &/| by order number
        Globals.EntryPageListContent=ClassHelper.sortEntryPageList(Globals.EntryPageListContent);
        
       
        try
        {
            ObdqProperties.getPropertiesFromFile();
        }
        catch(Exception e)
        {
            ObdqProperties.storePropertiesToFile();
            ObdqProperties.getPropertiesFromFile();
        }
        SettingsUtils.initializeStageDimensions();
        Globals.primaryStage.getIcons().add(new Image("resources/CheckEngine.png"));
        Globals.primaryStage.setTitle(ObdqProperties.windowTitle); 
        Globals.primaryStage.setResizable(ObdqProperties.resizable);
        //do not replaint the screen yet
        InternationalizationUtils.changeLanguage(ObdqProperties.defaultLanguage, ObdqProperties.defaultCountry,false,false);
    }
    /**
     * Application window destruction method. Contains all the destruction methods  
     * @return void
     **/
    private void destruct()
    {
        Globals.isApplicationTerminated=true;
        SerialUtils.disconnect();
        if(Globals.carConnectionMonitor!=null)
        {
            Globals.carConnectionMonitor.interrupt();
        }
    }
}
