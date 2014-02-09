/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.controller.SettingPageController;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.obdq.settings.SettingsUtils;
import com.queeq.serial.utils.SerialUtils;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author admin
 */
public class SettingsPage extends EntryPageTemplate
{
    private final ChoiceBox SelectCOMPorts= new ChoiceBox();
    private final ChoiceBox SelectBaudRate= new ChoiceBox();
    
    public SettingsPage()
    {
        setOrder(1);
    }
    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {     
                //page body
                VBox settingPageContainer= new VBox();
                settingPageContainer.setPadding(new Insets(10,10,10,10));
                settingPageContainer.setSpacing(10);
                settingPageContainer.setId("settingPageContainer");

                    //Car computer connection settings
                    VBox carComputerConnectionContainer = new VBox();
                    StackPane carComputerConnectionLableContainer = new StackPane();
                    carComputerConnectionLableContainer.setId("carComputerConnectionLableContainer");
                        HBox carComputerConnectionLableBackground = new HBox();
                        carComputerConnectionLableBackground.setId("carComputerConnectionLableBackground");

                        Label carComputerConnectionLable = new Label(LN.getString("settingsPage.carComputerConnectionConfiguration"));
                        carComputerConnectionLable.setId("carComputerConnectionLableText");
                        carComputerConnectionLable.setContentDisplay(ContentDisplay.LEFT);
                    carComputerConnectionLableContainer.getChildren().addAll(carComputerConnectionLableBackground,carComputerConnectionLable);

                       GridPane carComputerConnectionGrid = new GridPane();
                        carComputerConnectionGrid.setId("carComputerConnectionGrid");
                        carComputerConnectionGrid.setPadding(new Insets(0,0,0,0));
                        carComputerConnectionGrid.setHgap(10);
                        carComputerConnectionGrid.setVgap(10);
                        carComputerConnectionGrid.prefHeightProperty().bind(pane.heightProperty().divide(4));
                        

                        SettingPageController.getSelectCOMPorts(SelectCOMPorts); 
                        SelectCOMPorts.setMinWidth(250);
                        SelectCOMPorts.setId("SelectCOMPorts");
                        SelectCOMPorts.setCursor(Cursor.HAND);
                        carComputerConnectionGrid.add(SelectCOMPorts, 1,1);
                        //COMPorts.setValue(ObdqProperties.selectedCar.getManufacturerName());
                        
                        
                        SettingPageController.getSelectBaudRate(SelectBaudRate); 
                        SelectBaudRate.setMinWidth(250);
                        SelectBaudRate.setId("SelectBaudRate");
                        SelectBaudRate.setCursor(Cursor.HAND);
                        carComputerConnectionGrid.add(SelectBaudRate, 1,3);
                        
                        Button refreshButton = new Button(LN.getString("settingsPage.refresh"));
                        refreshButton.setId("refreshButton");
                        refreshButton.setCursor(Cursor.HAND);
                        refreshButton.setPrefWidth(250);
                        refreshButton.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            {
                                new SceneDispatcher().getScene(SettingsPage.class,Globals.root,Globals.pane);
                            }
                        });
                        carComputerConnectionGrid.add(refreshButton, 1,5);
                        

                        Globals.connectButton.setPrefWidth(250);
                        if(!SerialUtils.isSerialPortOpened())
                        {  
                            Globals.connectButton.setText(LN.getString("settingsPage.connect"));
                            if(!SelectCOMPorts.valueProperty().getValue().toString().equals(LN.getString("settingsPage.selectCOMPort"))&&
                               !SelectCOMPorts.valueProperty().getValue().toString().equals(LN.getString("settingsPage.noCOMPort"))&&
                               !SelectBaudRate.valueProperty().getValue().toString().equals(LN.getString("settingsPage.noBaudRate"))&&
                               !SelectBaudRate.valueProperty().getValue().toString().equals(LN.getString("settingsPage.selectBaudRate")))
                            {
                                Globals.connectButton.setId("connectButton");
                                Globals.connectButton.setCursor(Cursor.HAND);
                            }
                            else
                            {
                                Globals.connectButton.setId("connectButtonInactive");
                            }
                        }
                        else
                        {
                            Globals.connectButton.setId("disconnectButton");
                            Globals.connectButton.setText(LN.getString("settingsPage.disconnect"));
                            Globals.connectButton.setCursor(Cursor.HAND);
                        }
                        Globals.connectButton.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            {  
                                if( !Globals.connectButtonClicked)
                                {
                                     Globals.connectButtonClicked=true;
                                     SettingPageController.connectButtonClick(Globals.connectButton.getId(),SelectCOMPorts.valueProperty().getValue().toString(),SelectBaudRate.valueProperty().getValue().toString());                      
                                }               
                            }
                        });
                        carComputerConnectionGrid.add(Globals.connectButton, 3,5);
                        //ObdqProperties.serialPortStatus.setText("");
                        Globals.serialPortStatus.setId("serialPortStatus");
                        Globals.serialPortStatus.setContentDisplay(ContentDisplay.LEFT);
                        carComputerConnectionGrid.add(Globals.serialPortStatus, 1,6,4,6);
                    carComputerConnectionContainer.getChildren().addAll(carComputerConnectionLableContainer,carComputerConnectionGrid);
                    
                    //Internet connection settings
                   /* VBox internetConnectionContainer = new VBox();
                    StackPane internetConnectionLableContainer = new StackPane();
                    internetConnectionLableContainer.setId("internetConnectionLableContainer");
                        HBox internetConnectionLableBackground = new HBox();
                        internetConnectionLableBackground.setId("internetConnectionLableBackground");

                        Label internetConnectionLable = new Label(LN.getString("settingsPage.internetConnectionConfiguration"));
                        internetConnectionLable.setId("internetConnectionLableText");
                        internetConnectionLable.setContentDisplay(ContentDisplay.LEFT);
                    internetConnectionLableContainer.getChildren().addAll(internetConnectionLableBackground,internetConnectionLable);

                       GridPane internetConnectionGrid = new GridPane();
                        internetConnectionGrid.setId("internetConnectionGrid");
                        internetConnectionGrid.setPadding(new Insets(10,10,10,10));
                        internetConnectionGrid.prefHeightProperty().bind(pane.heightProperty().divide(4));
                        
                        Button buttona = new Button("test");
                        buttona.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            {
                                    //System.out.println(Others.getVIN());
                                    //new ISO15031().methTest();
                                }
                            });
                        internetConnectionGrid.add(buttona, 1,2);
                        Button buttonq = new Button("getCode");
                         buttonq.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                {
                                   // System.out.println(Others.getTroubleCodes());
                                }
                            });
                        internetConnectionGrid.add(buttonq, 2,3);
                    internetConnectionContainer.getChildren().addAll(internetConnectionLableContainer,internetConnectionGrid);
                    */
                     //Theme connection settings
                    VBox themeContainer = new VBox();
                    StackPane themeLableContainer = new StackPane();
                    themeLableContainer.setId("themeLableContainer");
                        HBox themeLableBackground = new HBox();
                        themeLableBackground.setId("themeLableBackground");

                        Label themeLable = new Label(LN.getString("settingsPage.themeConfiguration"));
                        themeLable.setId("themeLableText");
                        themeLable.setContentDisplay(ContentDisplay.LEFT);
                    themeLableContainer.getChildren().addAll(themeLableBackground,themeLable);

                        ScrollPane themeScrollPane = new ScrollPane();
                        themeScrollPane.setId("themeScrollPane");
                        themeScrollPane.prefHeightProperty().bind(pane.heightProperty().divide(3));
                            TilePane tilePane = new TilePane();
                            tilePane.setPadding(new Insets(10,10,10,10));
                            tilePane.prefWidthProperty().bind(themeScrollPane.widthProperty().subtract(10));
                           // tilePane.setPrefColumns(3); //preferred columns
                            tilePane.setHgap(10);
                            tilePane.setVgap(10);
                            File[] themeList=SettingsUtils.getAvailableThemesFolders();
                            for(int i=0;i<themeList.length;i++)
                            {
                                tilePane.getChildren().addAll(getThemeButton(themeList[i].getName()));
                            }
                        themeScrollPane.setContent(tilePane); 
                        
                    themeContainer.getChildren().addAll(themeLableContainer,themeScrollPane);
                   
                     //Misc settings
                    VBox othersContainer = new VBox();
                    StackPane othersLableContainer = new StackPane();
                    othersLableContainer.setId("othersLableContainer");
                        HBox othersLableBackground = new HBox();
                        othersLableBackground.setId("othersLableBackground");

                        Label othersLable = new Label(LN.getString("settingsPage.others"));
                        othersLable.setId("othersLableText");
                        othersLable.setContentDisplay(ContentDisplay.LEFT);
                    othersLableContainer.getChildren().addAll(othersLableBackground,othersLable);

                       GridPane othersGrid = new GridPane();
                        othersGrid.setId("othersGrid");
                        othersGrid.setPadding(new Insets(0,0,0,0));
                        othersGrid.setHgap(10);
                        othersGrid.setVgap(10);
                        //othersGrid.setGridLinesVisible(true);
                        othersGrid.prefHeightProperty().bind(pane.heightProperty().divide(4));
                        
                        Label systemOfMeasurements = new Label(LN.getString("settingsPage.systemOfMeasurements"));
                        othersGrid.add(systemOfMeasurements, 1,1,2,1);
                        
                        ToggleGroup standard = new ToggleGroup();
                        RadioButton metric = new RadioButton(LN.getString("settingsPage.metric"));
                        metric.setToggleGroup(standard);
                        metric.setSelected(true);
                        RadioButton imperial = new RadioButton(LN.getString("settingsPage.imperial"));
                        imperial.setToggleGroup(standard);
                        
                        if(ObdqProperties.defaultMeasurmentSystem==ObdqProperties.Imperial)
                        {
                            imperial.setSelected(true);
                        }
                        else
                            metric.setSelected(true);
                            
                         metric.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                {
                                    SettingPageController.onMetricSelected();
                                }
                            });
                         imperial.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                {
                                    SettingPageController.onImperialSelected();
                                }
                            });
                        
                        othersGrid.add(metric, 1,2);
                        othersGrid.add(imperial, 2,2);
                    othersContainer.getChildren().addAll(othersLableContainer,othersGrid);
                    
                    settingPageContainer.getChildren().addAll(carComputerConnectionContainer,themeContainer,othersContainer);
            pane.setCenter(settingPageContainer);
    }

    @Override
    public ImageView getEntryIcon() 
    {
        ImageView SettingsButton = new ImageView();
        SettingsButton.setId("SettingsButtonLandingPage");
        SettingsButton.setCursor(Cursor.HAND);
        SettingsButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                SceneDispatcher.getScene(SettingsPage.class,Globals.root,Globals.pane);
            }
        });
        return SettingsButton;
    }
    private VBox getThemeButton(final String theme)
    {
        VBox button =new VBox(); 
        if(theme.equals(ObdqProperties.defaultTheme))
        {
            button.setId("themeIconSelected");
        }
        else
        {
            button.setId("themeIcon");
            button.setOnMouseClicked(new EventHandler<MouseEvent>() 
            {
                @Override
                public void handle(MouseEvent event) 
                {
                    SettingsUtils.changeStyle(theme,true);
                }
            });
        }
            ImageView themeButtonImage = new ImageView();
            themeButtonImage.setId("themeIconImage");
                 Image image = new Image("file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+theme+"/themeIcon.png");
            themeButtonImage.setImage(image);
            //Mask Shape
            Rectangle mask = new Rectangle();
             mask.setWidth(75);
             mask.setHeight(75);
             mask.setArcWidth(20);
             mask.setArcHeight(20);
            themeButtonImage.setClip(mask);
            Bloom bloom = new Bloom();
            bloom.setThreshold(0.7);
            DropShadow dropShadow2 = new DropShadow();
            dropShadow2.setOffsetX(6.0);
            dropShadow2.setOffsetY(4.0);
            themeButtonImage.setEffect(dropShadow2);

            Label name=new Label(theme);
          // name.setContentDisplay(ContentDisplay.CENTER);
            name.setId("themeIconLable");
            name.setEffect(bloom);
        
        button.getChildren().addAll(themeButtonImage,name);
        button.setAlignment(Pos.CENTER);
        
        return button;
    }
}
