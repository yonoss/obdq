/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.scenes.controller.ScanPageController;
import com.queeq.obdq.scenes.utils.PID;
import com.queeq.obdq.scenes.utils.ScanObject;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.serial.utils.SerialUtils;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 *
 * @author admin
 */
public class ScanPage extends EntryPageTemplate
{
    private final TextField modeFromValue=new TextField();
    private final TextField modeToValue= new TextField();
    private final TextField PIDFromValue= new TextField();
    private final TextField PIDToValue= new TextField();
    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
            //page body
                VBox centralContainer = new VBox();
                centralContainer.setPadding(new Insets(10,10,10,10));
                centralContainer.setSpacing(10);
                    
                    final TableColumn col1 = new TableColumn(LN.getString("scanPage.modePID"));
                    col1.setCellValueFactory(new PropertyValueFactory<PID,String>("sensorLable"));
                    //col1.prefWidthProperty().bind(Globals.dataView.widthProperty().divide(3));
                    
                    final TableColumn col2 = new TableColumn(LN.getString("scanPage.response"));
                    //col2.prefWidthProperty().bind(Globals.dataView.widthProperty().divide(3));
                    col2.setCellValueFactory(new PropertyValueFactory<PID,String>("sensorValue"));
 
                            
                    Globals.scanResults.getColumns().addAll(col1,col2);
                    Globals.scanResults.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    Globals.scanResults.setItems(Globals.scanData.getScanDataObject());                  
                    Globals.scanResults.prefHeightProperty().bind(pane.heightProperty());
                    Globals.scanResults.prefWidthProperty().bind(pane.widthProperty());
                   // Globals.scanResults.
                    //Globals.scanResults.sTableViewSelectionModel();
                    
                    
                    VBox scanConfigurationContainer = new VBox();
                    StackPane scanConfigurationLableContainer = new StackPane();
                    scanConfigurationLableContainer.setId("scanConfigurationLableContainer");
                        HBox scanConfigurationLableBackground = new HBox();
                        scanConfigurationLableBackground.setId("scanConfigurationLableBackground");

                        Label scanConfigurationLable = new Label(LN.getString("scanPage.scanConfiguration"));
                        scanConfigurationLable.setId("scanConfigurationLableText");
                        scanConfigurationLable.setContentDisplay(ContentDisplay.LEFT);
                    scanConfigurationLableContainer.getChildren().addAll(scanConfigurationLableBackground,scanConfigurationLable);

                    GridPane scanConfigurationGrid = new GridPane();
                        scanConfigurationGrid.setId("scanConfigurationGrid");
                        scanConfigurationGrid.setPadding(new Insets(5,10,10,10));
                        //scanConfigurationGrid.setGridLinesVisible(true);
                        scanConfigurationGrid.setHgap(20);
                        scanConfigurationGrid.setVgap(1);
                        scanConfigurationGrid.prefHeightProperty().bind(pane.heightProperty().divide(2));

                        Label modeFrom =new Label(LN.getString("scanPage.modeFrom"));
                        modeFrom.setId("modeFrom");
                        modeFrom.setContentDisplay(ContentDisplay.LEFT);
                        modeFromValue.setId("modeFromValue");
                        modeFromValue.setMinWidth(200);
                        modeFromValue.setText("00");
                        modeFromValue.setOnKeyReleased(new EventHandler<KeyEvent>() 
                        {
                            @Override
                            public void handle(KeyEvent arg0) 
                            {
                               OnFieldEvent(modeFromValue);
                            }
                        });

                        Label modeTo =new Label(LN.getString("scanPage.modeTo"));
                        modeTo.setId("modeTo");
                        modeTo.setContentDisplay(ContentDisplay.LEFT);
                        
                        modeToValue.setId("modeFromValue");
                        modeToValue.setMinWidth(200);
                        modeToValue.setText("00");
                        modeToValue.setOnKeyReleased(new EventHandler<KeyEvent>() 
                        {
                            @Override
                            public void handle(KeyEvent arg0) 
                            {
                               OnFieldEvent(modeToValue);
                            }
                        });
                        
                        
                        Label PIDFrom =new Label(LN.getString("scanPage.PIDFrom"));
                        PIDFrom.setId("PIDFrom");
                        PIDFrom.setContentDisplay(ContentDisplay.LEFT);
                        PIDFromValue.setId("PIDFromValue");
                        PIDFromValue.setMinWidth(200);
                        PIDFromValue.setText("00");
                        PIDFromValue.setOnKeyReleased(new EventHandler<KeyEvent>() 
                        {
                            @Override
                            public void handle(KeyEvent arg0) 
                            {
                               OnFieldEvent(PIDFromValue);
                            }
                        });
                        
                        Label PIDTo =new Label(LN.getString("scanPage.PIDTo"));
                        PIDTo.setId("PIDTo");
                        PIDTo.setContentDisplay(ContentDisplay.LEFT);
                        PIDToValue.setId("PIDToValue");
                        PIDToValue.setMinWidth(200);
                        PIDToValue.setText("20");
                        PIDToValue.setOnKeyReleased(new EventHandler<KeyEvent>() 
                        {
                            @Override
                            public void handle(KeyEvent arg0) 
                            {
                               OnFieldEvent(PIDToValue);
                            }
                        });
                        scanConfigurationGrid.add(modeFrom, 0,2);
                        scanConfigurationGrid.add(modeFromValue, 0,3);
                        scanConfigurationGrid.add(modeTo, 0,6);
                        scanConfigurationGrid.add(modeToValue, 0,7);
                        
                        scanConfigurationGrid.add(PIDFrom, 3,2);
                        scanConfigurationGrid.add(PIDFromValue, 3,3);
                        scanConfigurationGrid.add(PIDTo, 3,6);
                        scanConfigurationGrid.add(PIDToValue, 3,7);
                        
                        Globals.scanStatus =new Label(LN.getString("scanPage.scanStatusDefault"));
                        Globals.scanStatus.setId("scanStatus");
                        Globals.scanStatus.setContentDisplay(ContentDisplay.LEFT);
                        scanConfigurationGrid.add(Globals.scanStatus, 6,2);
                        
                    scanConfigurationContainer.getChildren().addAll(scanConfigurationLableContainer,scanConfigurationGrid);
                    
                    Globals.scan.setText(LN.getString("scanPage.scanStart"));
                    Globals.scan.setId("scanStart");
                    Globals.scan.setCursor(Cursor.HAND);
                    Globals.scan.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        public void handle(ActionEvent e) 
                        {
                            if(Globals.scan.getId().equals("scanStart")&&!Globals.scanButtonClicked)
                            {
                                //Globals.scanButtonClicked=true;
                                if(PrepareScanning())
                                {
                                /*if(!SerialUtils.isSerialPortOpened())
                                    ModalWindowController.openModalWindow(LN.getString("sensorsPage.noCarConnection.title"),LN.getString("sensorsPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
                                else
                                {*/
                                    //InitializePage();
                                     Globals.scanData=new ScanObject();
                                    Globals.scan.setId("scanStop");
                                    Globals.scan.setText(LN.getString("scanPage.scanStop"));
                                    ScanPageController.Scan(modeFromValue.getText(), modeToValue.getText(), PIDFromValue.getText(),PIDToValue.getText());
                               // }
                                }
                            }
                            else if(Globals.scan.getId().equals("scanStop")&&!Globals.scanButtonClicked)
                            {
                                Globals.scanButtonClicked=true;
                                Platform.runLater(new Runnable() 
                                {
                                    @Override
                                    public void run() 
                                    {
                                        Globals.scan.setId("scanStart");
                                        Globals.scan.setText(LN.getString("scanPage.scanStart"));
                                    }
                                });
                            }
                                
                               
                        }
                    });
                    Button exportScanResult= new Button();
                    exportScanResult.setText(LN.getString("scanPage.exportScanResult"));
                    exportScanResult.setId("exportScanResult");
                    exportScanResult.setCursor(Cursor.HAND);
                    exportScanResult.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        public void handle(ActionEvent e) 
                        {
                            ScanPageController.saveScanFile();
                        }
                    });
 
                    Button loadScanResult= new Button();
                    loadScanResult.setText(LN.getString("scanPage.loadScanResult"));
                    loadScanResult.setId("loadScanResult");
                    loadScanResult.setCursor(Cursor.HAND);
                    loadScanResult.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        public void handle(ActionEvent e) 
                        {
                            ScanPageController.loadScanFile();
                        }
                    });
                    
                    
                    
                    HBox buttonsContainer = new HBox();
                    buttonsContainer.setId("scanPageButtonsContainer");
                    buttonsContainer.setPadding(new Insets(0,0,0,0));
                    buttonsContainer.setSpacing(15);
                    buttonsContainer.getChildren().addAll(Globals.scan,exportScanResult,loadScanResult);

                    
                    
                    centralContainer.getChildren().addAll(Globals.scanResults,scanConfigurationContainer,buttonsContainer);
            pane.setCenter(centralContainer); 
        if(!SerialUtils.isSerialPortOpened())
        {
           ModalWindowController.openModalWindow(LN.getString("scanPage.noCarConnection.title"),LN.getString("scanPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
        }
    }
    /**Validates the text boxes content**/
    private void OnFieldEvent(TextField field)
    {
        if(ScanPageController.validate(field.getText()))
        {
            field.setId(field.getId().replaceAll("_e", ""));
        }
        else
            field.setId(field.getId().replaceAll("_e", "")+"_e");
    }
    /****/
    private boolean PrepareScanning()
    {
        boolean go=true;
        String  modeFromVal=modeFromValue.getText();
        if(ScanPageController.validate(modeFromVal))
        {
            modeFromValue.setId(modeFromValue.getId().replaceAll("_e", ""));
        }
        else
        {
            modeFromValue.setId(modeFromValue.getId().replaceAll("_e", "")+"_e");
            go=false;
        }
        String  modeToVal=modeToValue.getText();
        if(ScanPageController.validate(modeToVal))
        {
            modeToValue.setId(modeToValue.getId().replaceAll("_e", ""));
        }
        else
        {
            modeToValue.setId(modeToValue.getId().replaceAll("_e", "")+"_e");
            go=false;
        }
        String  PIDFromVal=PIDFromValue.getText();
        if(ScanPageController.validate(PIDFromVal))
        {
            PIDFromValue.setId(PIDFromValue.getId().replaceAll("_e", ""));
        }
        else
        {
            PIDFromValue.setId(PIDFromValue.getId().replaceAll("_e", "")+"_e");
            go=false;
        }
        String  PIDToVal=PIDToValue.getText();
        if(ScanPageController.validate(PIDToVal))
        {
            PIDToValue.setId(PIDToValue.getId().replaceAll("_e", ""));
        }
        else
        {
            PIDToValue.setId(PIDToValue.getId().replaceAll("_e", "")+"_e");
            go=false;
        }
        return go;
    }
    /**Initialize the scan page content**/
    private void InitializePage()
    {
        Globals.scanResults = new TableView();
        Globals.scanData=new ScanObject();

    }
     /**
     * generates the connection not available modal window buttons 
     ***/
    private ArrayList<Button> getConnectionNotAvailableModalWindowButton()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonSettings = new Button(LN.getString("scanPage.noCarConnection.settingsButton"));
            buttonSettings.setId("buttonSettingsScanPage");
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

    @Override
    public ImageView getEntryIcon() 
    {
        ImageView ScanButton = new ImageView();
        ScanButton.setId("ScanButtonLandingPage");
        ScanButton.setCursor(Cursor.HAND);
        ScanButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                SceneDispatcher.getScene(ScanPage.class,Globals.root,Globals.pane);
            }
        });
        return ScanButton;
    }
    
}
