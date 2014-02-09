/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.impementation.support;

import com.queeq.obdq.scenes.LandingPage;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.view.LanguagePage;
import com.queeq.obdq.scenes.view.SettingsPage;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.SettingsUtils;
import com.queeq.serial.utils.SerialUtils;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author admin
 */
public class PageHeader 
{
    public PageHeader()
    {}
    /**
     *@return The page header
     **/
    public static StackPane getPageHeader(BorderPane pane )
    {
        StackPane header = new StackPane(); 
                HBox headerBackgroud = new HBox();
                headerBackgroud.setPadding(new Insets(2,2,2,2));
                headerBackgroud.setSpacing(10);
                headerBackgroud.setId("headerBackgroud");
                
                HBox headerContent= new HBox();
                headerContent.setId("headerContent");
                headerContent.prefWidthProperty().bind(pane.widthProperty());
                    HBox headerHboxLeft = new HBox();
                    headerHboxLeft.setPadding(new Insets(2,2,2,2));
                    headerHboxLeft.setSpacing(2);
                    headerHboxLeft.setId("headerHboxLeft");
                    headerHboxLeft.setAlignment(Pos.CENTER_LEFT);
                        GridPane gridHeaderLeft= new GridPane();
                        //gridHeaderLeft.setGridLinesVisible(false);
                        gridHeaderLeft.setHgap(1);
                        gridHeaderLeft.setVgap(1);
                        gridHeaderLeft.setAlignment(Pos.CENTER_LEFT);
                        gridHeaderLeft.setPadding(new Insets(2,2,2,2));

                            Task task = new Task<Void>() 
                            {
                                @Override public Void call() throws InterruptedException 
                                {
                                    while(!Globals.isApplicationTerminated)
                                    {
                                        SerialUtils.isSerialPortOpened();
                                        //System.out.println(System.currentTimeMillis());
                                        Thread.sleep(15000);
                                    }
                                    return null;
                                 }
                            };
                            Globals.carConnectionMonitor =new Thread(task);
                            Globals.carConnectionMonitor.start();
                            
                            Globals.CarStatus.setCursor(Cursor.HAND);
                            Globals.CarStatus.setOnMouseClicked(new EventHandler<MouseEvent>() 
                            {
                                @Override
                                public void handle(MouseEvent event) 
                                {
                                    SceneDispatcher.getScene(SettingsPage.class,Globals.root,Globals.pane);
                                }
                            });
                        gridHeaderLeft.add(Globals.CarStatus, 1, 1);
                           /* final ImageView InternetStatus = new ImageView();
                            if(SystemUtils.isInternetConnectionAvailable())
                                InternetStatus.setId("InternetStatusButtonOK");
                            else
                                InternetStatus.setId("InternetStatusButtonError");     
                            InternetStatus.setCursor(Cursor.HAND);
                            InternetStatus.setOnMouseClicked(new EventHandler<MouseEvent>() 
                            {
                                @Override
                                public void handle(MouseEvent event) 
                                {
                                     SceneDispatcher.getScene(SettingsPage.class,Globals.root,Globals.pane);
                                }
                            });
                        gridHeaderLeft.add(InternetStatus, 15, 1);*/
                  headerHboxLeft.getChildren().add(gridHeaderLeft);  

                        /*Spacer*/
                        HBox headerSpacer= new HBox();
                        headerSpacer.setId("headerSpacer");
                        headerSpacer.prefWidthProperty().bind(pane.widthProperty());
                        /*Right box*/
                        HBox headerHboxRight = new HBox();
                        headerHboxRight.setPadding(new Insets(2,2,2,2));
                        headerHboxRight.setSpacing(2);
                        headerHboxRight.setId("headerHboxRight");
                        headerHboxRight.setAlignment(Pos.CENTER_RIGHT);
                            GridPane gridHeaderRight= new GridPane();
                            //gridHeaderRight.setGridLinesVisible(true);
                            gridHeaderRight.setHgap(1);
                            gridHeaderRight.setVgap(1);
                            gridHeaderRight.setAlignment(Pos.CENTER_RIGHT);
                            gridHeaderRight.setPadding(new Insets(2,2,2,2));

                                ImageView LanguageButton = new ImageView();
                                LanguageButton.setId("HeaderLanguageButton");
                                LanguageButton.setCursor(Cursor.HAND);
                                LanguageButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
                                {
                                    @Override
                                    public void handle(MouseEvent event) 
                                    {
                                        SceneDispatcher.getScene(LanguagePage.class,Globals.root,Globals.pane);
                                    }
                                });

                            // gridHeaderRight.add(CarStatus, 43, 1);   
                            gridHeaderRight.add(LanguageButton, 29, 1);

                                ImageView HomeButton = new ImageView();
                                HomeButton.setId("HeaderHomeButton");
                                HomeButton.setCursor(Cursor.HAND);
                                HomeButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
                                {
                                    @Override
                                    public void handle(MouseEvent event) 
                                    {
                                        SceneDispatcher.getScene(LandingPage.class,Globals.root,Globals.pane);
                                    }
                                });

                            gridHeaderRight.add(HomeButton, 15, 1);


                            ImageView BackButton = new ImageView();
                                BackButton.setId("BackButton");
                                BackButton.setCursor(Cursor.HAND);
                                BackButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
                                {
                                    @Override
                                    public void handle(MouseEvent event) 
                                    {
                                        SceneDispatcher.getScene(SettingsUtils.getPreviousPage(),Globals.root,Globals.pane);
                                    }
                                });

                            gridHeaderRight.add(BackButton, 1, 1);

                        headerHboxRight.getChildren().add(gridHeaderRight);
                  headerContent.getChildren().addAll(headerHboxLeft,headerSpacer,headerHboxRight);      

            header.getChildren().addAll(headerBackgroud,headerContent);
            return header;
    }
    
}
