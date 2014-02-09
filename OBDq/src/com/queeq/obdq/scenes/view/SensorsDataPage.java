/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.scenes.controller.SensorsDataPageController;
import com.queeq.obdq.scenes.impementation.support.PageFooter;
import com.queeq.obdq.scenes.impementation.support.PageHeader;
import com.queeq.obdq.scenes.utils.DataObject;
import com.queeq.obdq.scenes.utils.PID;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.serial.utils.SerialUtils;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
/**
 *
 * @author admin
 */
public class SensorsDataPage extends EntryPageTemplate
{
     public SensorsDataPage ()
    {
    }

    
     @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
        InitializePage();
            //page body
                VBox centralContainer = new VBox();
                centralContainer.setPadding(new Insets(10,10,10,10));
                    
                    final TableColumn col1 = new TableColumn(LN.getString("sensorsPage.sensor"));
                    col1.setCellValueFactory(new PropertyValueFactory<PID,String>("lable"));
                    col1.prefWidthProperty().bind(Globals.dataView.widthProperty().divide(10).multiply(4.5f));
                    
                    final TableColumn col2 = new TableColumn(LN.getString("sensorsPage.value"));
                    col2.prefWidthProperty().bind(Globals.dataView.widthProperty().divide(10).multiply(4.5f));
                    col2.setCellValueFactory(new PropertyValueFactory<PID,String>("displayValue"));
                   
                    Callback<TableColumn, TableCell> cellFactoryCol3 = new Callback<TableColumn, TableCell>() {
                        @Override
                        public TableCell call(final TableColumn param) 
                        {
                            final TableCell cell = new TableCell()
                            {
                                @Override
                                public void updateItem(Object item, boolean empty) 
                                {
                                    if (item!=null) 
                                    {
                                        if((Boolean)item)
                                        {
                                            final TableCell c = this;
                                            CheckBox checkBox = new CheckBox();
                                            checkBox.setSelected(false);
                                            checkBox.setOnAction(new EventHandler<ActionEvent>() 
                                            {
                                                public void handle(ActionEvent e) 
                                                {
                                                    TableRow tableRow = c.getTableRow();
                                                    PID curentS= (PID) tableRow.getTableView().getItems().get(tableRow.getIndex());
                                                    Globals.data.toggleSensor(curentS);
                                                    try
                                                    {
                                                        SensorsDataPageController.updateTableContent();
                                                    }
                                                    catch(Exception ex)
                                                    {}
                                                }
                                            });
                                            setGraphic(checkBox);
                                            setAlignment(Pos.CENTER);
                                        }
                                    }
                                }
                            };
                            return cell;
                        }
                    };
                    
                    
                    
                    final TableColumn col3 = new TableColumn(LN.getString("sensorsPage.remove"));
                    col3.prefWidthProperty().bind(Globals.dataView.widthProperty().divide(10));
                    col3.setCellValueFactory(new PropertyValueFactory<PID,String>("isSelectedOnReadCodesPage"));
                    col3.setCellFactory(cellFactoryCol3);
                            
                    Globals.dataView.getColumns().addAll(col1,col2, col3);
                    Globals.dataView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    Globals.dataView.setItems(Globals.data.getSensorDataObject());                  
                    Globals.dataView.prefHeightProperty().bind(pane.heightProperty());
                    

                    final TableColumn sensorName = new TableColumn(LN.getString("sensorsPage.sensorAvailable"));
                    sensorName.setCellValueFactory(new PropertyValueFactory<PID,String>("lable"));
                    sensorName.prefWidthProperty().bind(Globals.sensorsView.widthProperty().divide(10).multiply(9));
                   // sensorName.setCellFactory(cellFactorySensorNameColumn);
                    
                        Callback<TableColumn, TableCell> cellFactorySelectColumn = new Callback<TableColumn, TableCell>() {
                        @Override
                        public TableCell call(final TableColumn param) 
                        {
                            final TableCell cell = new TableCell()
                            {
                                @Override
                                public void updateItem(Object item, boolean empty) 
                                {
                                    if (item!=null) 
                                    {
                                        if(!(Boolean)item)
                                        {
                                            final TableCell c = this;
                                            CheckBox checkBox = new CheckBox();
                                            checkBox.setSelected(false);
                                            checkBox.setOnAction(new EventHandler<ActionEvent>() 
                                            {
                                                public void handle(ActionEvent e) 
                                                {
                                                    TableRow tableRow = c.getTableRow();
                                                    PID curentS= (PID) tableRow.getTableView().getItems().get(tableRow.getIndex());
                                                    Globals.data.toggleSensor(curentS);
                                                    try
                                                    {
                                                        SensorsDataPageController.updateTableContent();
                                                    }
                                                    catch(Exception ex)
                                                    {}
                                                }
                                            });
                                            setGraphic(checkBox);
                                            setAlignment(Pos.CENTER);
                                        }
                                    }
                                }
                            };
                            return cell;
                        }
                    };
                    
                    
                    final TableColumn select = new TableColumn(LN.getString("sensorsPage.add"));
                    select.setCellValueFactory(new PropertyValueFactory<PID,String>("isSelectedOnReadCodesPage"));
                    select.prefWidthProperty().bind(Globals.sensorsView.widthProperty().divide(10));   
                    select.setCellFactory(cellFactorySelectColumn);
                    
                    Globals.sensorsView.getColumns().addAll(sensorName,select);
                    Globals.sensorsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    Globals.sensorsView.setItems(Globals.data.getsensorIDsObject());                  
                    Globals.sensorsView.prefHeightProperty().bind(pane.heightProperty());
                    
                   
                 
                    Globals.readPID.setText(LN.getString("sensorsPage.readPID"));
                    Globals.readPID.setId("readPID");
                    Globals.readPID.setCursor(Cursor.HAND);
                    Globals.readPID.setPrefWidth(250);
                    Globals.readPID.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        public void handle(ActionEvent e) 
                        {
                           /* if(!SerialUtils.isSerialPortOpened())
                                ModalWindowController.openModalWindow(LN.getString("sensorsPage.noCarConnection.title"),LN.getString("sensorsPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
                            else
                            {*/
                                SensorsDataPageController.readSensorData();
                           // }
                        }
                    });
                    
                centralContainer.getChildren().addAll(Globals.dataView,Globals.sensorsView,Globals.readPID);
            pane.setCenter(centralContainer); 
        if(!SerialUtils.isSerialPortOpened())
        {
           ModalWindowController.openModalWindow(LN.getString("sensorsPage.noCarConnection.title"),LN.getString("sensorsPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
        }
    }
    
    /*Test methode*/
    public void getData()
    {
        Globals.data=new DataObject();
        Globals.data.setNewSensor("0101","DTCNumber","",false);
        Globals.data.setNewSensor("0101","MILStatus","",false);
        Globals.data.setNewSensor("0101","MisfireMonitoringSupported","",false);
        Globals.data.setNewSensor("0102","","",true);
        Globals.data.setNewSensor("0103","FuelSystem1Status","",false);
        Globals.data.setNewSensor("0103","FuelSystem2Status","",false);
        
      /*  PIDList temp=new PIDList();
        temp.setPIDList(Globals.data.getData());
        try{
            PIDManager.storePIDList(temp);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        
    }
    private void InitializePage()
    {
        Globals.dataView = new TableView();
        Globals.sensorsView=new TableView();
       // getData();
    }
    /**
     * generates the connection not available modal window buttons 
     ***/
    private ArrayList<Button> getConnectionNotAvailableModalWindowButton()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonSettings = new Button(LN.getString("sensorsPage.noCarConnection.settingsButton"));
            buttonSettings.setId("buttonSettingsSensorsDataPage");
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
        ImageView SensorsDataButton = new ImageView();
        SensorsDataButton.setId("SensorsDataButtonLandingPage");
        SensorsDataButton.setCursor(Cursor.HAND);
        SensorsDataButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                SceneDispatcher.getScene(SensorsDataPage.class,Globals.root,Globals.pane);
            }
        });
        return SensorsDataButton;
    }
}
