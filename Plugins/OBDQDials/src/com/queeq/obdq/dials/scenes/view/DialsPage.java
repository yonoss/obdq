/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.dials.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.dials.scenes.controller.DialPageControler;
import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.scenes.view.SettingsPage;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.serial.utils.SerialUtils;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author admin
 */
public class DialsPage extends EntryPageTemplate
{

    public static ImageView rpmPin=null;
    public static ImageView speedPin=null;

     @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
                StackPane dialPageContainer = new StackPane();
                dialPageContainer.setId("dialPageContainer");
                    HBox backgroundGrid = new HBox();
                    
//                    backgroundGrid.setStyle("-fx-background-repeat:repeat;"+
//                                            "-fx-opacity:0.5;"+
//                                            "-fx-background-image:url(\"" + getClass().getResource("img/hex.png").toExternalForm() + "\");");
                   backgroundGrid.setId("dialPageBackgroundGrid"); 
                    Group dials=new Group();
                        
                        final ImageView imageViewDial = new ImageView();
                        final ImageView imageViewBaseSpeed = new ImageView();
                        final ImageView imageViewBaseRPM = new ImageView();
                        rpmPin = new ImageView();
                        speedPin = new ImageView();
                        Task task = new Task<Void>() 
                        {
                            @Override public Void call() throws InterruptedException 
                                    
                            {
                                if(ObdqProperties.defaultMeasurmentSystem==ObdqProperties.Imperial)
                                {
                                    Image mphDials=new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme+"/img/mphDials.png");
                                    if(!mphDials.isError())
                                        imageViewDial.setImage(mphDials);
                                    else
                                        imageViewDial.setImage(new Image(getClass().getResource("theme/img/mphDials.png").toExternalForm()));
                                }
                                else
                                {
                                    Image kmhDials=new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme+"/img/kmhDials.png");
                                    if(!kmhDials.isError())
                                        imageViewDial.setImage(kmhDials);
                                    else
                                        imageViewDial.setImage(new Image(getClass().getResource("theme/img/kmhDials.png").toExternalForm()));   
                                } 
                                
                                Image rpmPinSmall=new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme+"/img/pinSmall.png");
                                if(!rpmPinSmall.isError())
                                    rpmPin.setImage(rpmPinSmall);
                                else
                                    rpmPin.setImage(new Image(getClass().getResource("theme/img/pinSmall.png").toExternalForm()));
                                rpmPin.setLayoutX(235);
                                rpmPin.setLayoutY(50);
                                rpmPin.setCache(true);
                                DialPageControler.rotateRPMPin(DialPageControler.startAngleRPM,0.1); 

                                Image rpmPinbaseSmall=new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme+"/img/pinbaseSmall.png");
                                if(!rpmPinbaseSmall.isError())
                                    imageViewBaseRPM.setImage(rpmPinbaseSmall);
                                else
                                    imageViewBaseRPM.setImage(new Image(getClass().getResource("theme/img/pinbaseSmall.png").toExternalForm()));
                                imageViewBaseRPM.setLayoutX(220);
                                imageViewBaseRPM.setLayoutY(220);
                                imageViewBaseRPM.setCache(true);
                                
                                
                                Image speedPinSmall=new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme+"/img/pinSmall.png");
                                if(!speedPinSmall.isError())
                                    speedPin.setImage(speedPinSmall);
                                else
                                    speedPin.setImage(new Image(getClass().getResource("theme/img/pinSmall.png").toExternalForm()));
                                speedPin.setLayoutX(605);
                                speedPin.setLayoutY(50);
                                speedPin.setCache(true);
                                DialPageControler.rotateSpeedPin(DialPageControler.startAngleSpeed,0.1); 

                                Image speedPinbaseSmall=new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme+"/img/pinbaseSmall.png");
                                if(!speedPinbaseSmall.isError())
                                    imageViewBaseRPM.setImage(speedPinbaseSmall);
                                else
                                    imageViewBaseSpeed.setImage(new Image(getClass().getResource("theme/img/pinbaseSmall.png").toExternalForm()));
                                imageViewBaseSpeed.setLayoutX(590);
                                imageViewBaseSpeed.setLayoutY(220);
                                imageViewBaseSpeed.setCache(true);
                                
                                return null;
                             }
                        };
                        new Thread(task).start();
                        
                       /* Button up=new Button("up");
                        up.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            { 
                              //  DialPageControler.rotateSpeedPin(100);
                                DialPageControler.rotateRPMPin(180);
                            }
                        });
                        Button down=new Button("down");
                        down.setLayoutX(500);
                        down.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            { 
                                // DialPageControler.rotateSpeedPin(-100);
                                 DialPageControler.rotateRPMPin(-180);
                            }
                        });*/
                        
                        dials.getChildren().addAll(imageViewDial,rpmPin,imageViewBaseRPM,speedPin,imageViewBaseSpeed);
     
                  //152 149 211 
         
                dialPageContainer.getChildren().addAll(backgroundGrid,dials);                  
            pane.setCenter(dialPageContainer);

        if(!SerialUtils.isSerialPortOpened())
        {
           ModalWindowController.openModalWindow(LN.getString("dialPage.noCarConnection.title"),LN.getString("dialPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
        }
        else
        {
            DialPageControler.initDialSettings();
            DialPageControler.readSpeed();
        }
    }

    @Override
    public ImageView getEntryIcon() 
    {
        String cssPath=getClass().getResource("theme/dials.css").toExternalForm();
        if(cssPath!=null)
        {
            if(!Globals.Page.getStylesheets().contains(cssPath))
                Globals.Page.getStylesheets().addAll(cssPath);
        }
        ImageView DialsButton = new ImageView();
        DialsButton.setId("DialsButtonLandingPage");
        DialsButton.setCursor(Cursor.HAND);
        DialsButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                //System.out.println("Start:"+System.currentTimeMillis());
                SceneDispatcher.getScene(DialsPage.class,Globals.root,Globals.pane);
            }
        });
        return DialsButton;
    }
    /**
     * generates the connection not available modal window buttons 
     ***/
    private ArrayList<Button> getConnectionNotAvailableModalWindowButton()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonSettings = new Button(LN.getString("dialPage.noCarConnection.settingsButton"));
            buttonSettings.setId("buttonSettingsDialPage");
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
}


