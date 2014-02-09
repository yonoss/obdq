/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.controller.CarAddPageController;
import com.queeq.obdq.scenes.controller.CarEditPageController;
import com.queeq.obdq.scenes.view.templates.StandardPageTemplate;
import com.queeq.obdq.settings.Globals;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author admin
 */
public class CarEditPage extends StandardPageTemplate
{
    private final TextField carNameValue =new TextField();
     private final TextField vinValue= new TextField();
     private final ChoiceBox makerValue= new ChoiceBox();
     private final ChoiceBox modelValue= new ChoiceBox();
     private final ChoiceBox colorValue= new ChoiceBox();
     private final ChoiceBox bodyTypeValue= new ChoiceBox();
     private final ChoiceBox manufactureYearValue= new ChoiceBox();
     private final ChoiceBox fuelTypeValue= new ChoiceBox();
     private final ChoiceBox transsmisionTypeValue= new ChoiceBox();
     private  final ChoiceBox enginePowerValue= new ChoiceBox();
    
    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {   
            //page body
                VBox centralContainer = new VBox();         
                centralContainer.setPadding(new Insets(10,10,10,10));
                centralContainer.setSpacing(10);
                centralContainer.setId("carEditMainContainer");
                
                
                    VBox carContainer = new VBox();
                        StackPane carLableContainer = new StackPane();
                        carLableContainer.setId("carEditLableContainer");
                            HBox carLableBackground = new HBox();
                            carLableBackground.setId("carEditLableBackground");

                            Label carLable = new Label(LN.getString("carEditPage.editCar"));
                            carLable.setId("carEditLableText");
                            carLable.setContentDisplay(ContentDisplay.LEFT);
                        carLableContainer.getChildren().addAll(carLableBackground,carLable);
                        VBox carTab = new VBox();
                        carTab.setId("carEditTab");
                        carTab.setPadding(new Insets(10,10,10,10));
                        carTab.setSpacing(10);
                        carTab.prefHeightProperty().bind(pane.heightProperty());
                        
                       
                            HBox carNameContainer = new HBox();
                            carNameContainer.setId("carNameContainer");
                            carNameContainer.setSpacing(10);
                            carNameContainer.prefWidthProperty().bind(pane.widthProperty().divide(2));
                                Label carName = new Label(LN.getString("general.defaultSeparator") + LN.getString("general.carName"));
                                carNameValue.setId("carNameValue");
                                carNameValue.setMinWidth(200);
                                carNameValue.setText(Globals.selectedCar.getCarName());
                            carNameContainer.getChildren().addAll(carNameValue,carName);   
                        carTab.getChildren().add(carNameContainer);
                        
                            HBox carVINContainer = new HBox();
                            carVINContainer.setId("carVINContainer");
                            carVINContainer.setSpacing(10);
                                Label vin = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.vin"));
                                vinValue.setMinWidth(200);
                                vinValue.setId("vinValue");
                                vinValue.setText(Globals.selectedCar.getVIN());
                                vinValue.setDisable(true);
                            carVINContainer.getChildren().addAll(vinValue,vin);   
                        carTab.getChildren().add(carVINContainer);

                            HBox makerContainer = new HBox();
                            makerContainer.setId("makerContainer");
                            makerContainer.setSpacing(10);
                                Label maker = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.maker"));
                                CarAddPageController.getSelectBrandEntries(makerValue); 
                                makerValue.setMinWidth(200);
                                makerValue.setId("makerValue");
                                makerValue.setValue(Globals.selectedCar.getManufacturerName());
                                makerValue.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
                                {
                                    @Override
                                    public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) 
                                    {
                                         CarAddPageController.getSelectModelEntries(modelValue ,arg2); 
                                    }

                                });
                                
                            makerContainer.getChildren().addAll(makerValue,maker);   
                        carTab.getChildren().add(makerContainer);

                        
                            HBox modelContainer = new HBox();
                            modelContainer.setId("modelContainer");
                            modelContainer.setSpacing(10);
                                Label model = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.model"));
                                CarAddPageController.getSelectModelEntries(modelValue ,Globals.selectedCar.getManufacturerName()); 
                                modelValue.setMinWidth(200);
                                modelValue.setId("modelValue");
                                modelValue.setValue(Globals.selectedCar.getModelName());
                            modelContainer.getChildren().addAll(modelValue,model);   
                        carTab.getChildren().add(modelContainer);
                        
                        
                            HBox colorContainer = new HBox();
                            colorContainer.setId("colorContainer");
                            colorContainer.setSpacing(10);
                                Label color = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.color"));
                                CarAddPageController.getColorEntries(colorValue ); 
                                colorValue.setMinWidth(200);
                                colorValue.setId("colorValue");
                                colorValue.setValue(LN.getString(Globals.selectedCar.getExteriorColor()));
                            colorContainer.getChildren().addAll(colorValue,color);   
                        carTab.getChildren().add(colorContainer);

                        
                        
                            HBox bodyTypeContainer = new HBox();
                            bodyTypeContainer.setId("bodyTypeContainer");
                            bodyTypeContainer.setSpacing(10);
                                Label bodyType = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.bodyType"));
                                CarAddPageController.getBodyTypesEntries(bodyTypeValue ); 
                                bodyTypeValue.setMinWidth(200);
                                bodyTypeValue.setId("bodyTypeValue");
                                bodyTypeValue.setValue(LN.getString(Globals.selectedCar.getBodyType()));
                            bodyTypeContainer.getChildren().addAll(bodyTypeValue,bodyType);   
                        carTab.getChildren().add(bodyTypeContainer);

                        
                            HBox manufactureYearContainer = new HBox();
                            manufactureYearContainer.setId("manufactureYearContainer");
                            manufactureYearContainer.setSpacing(10);
                                Label manufactureYear = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.year"));
                                CarAddPageController.getYearEntries(manufactureYearValue ); 
                                manufactureYearValue.setMinWidth(200);
                                manufactureYearValue.setId("manufactureYearValue");
                                manufactureYearValue.setValue(Globals.selectedCar.getManufacturingYear());
                            manufactureYearContainer.getChildren().addAll(manufactureYearValue,manufactureYear);   
                        carTab.getChildren().add(manufactureYearContainer);
                        
                       


                            HBox fuelTypeContainer = new HBox();
                            fuelTypeContainer.setId("fuelTypeContainer");
                            fuelTypeContainer.setSpacing(10);
                                Label fuelType = new Label(LN.getString("general.defaultSeparator")+LN.getString("general.fuelType"));
                                CarAddPageController.getFuelTypesEntries(fuelTypeValue ); 
                                fuelTypeValue.setMinWidth(200);
                                fuelTypeValue.setId("fuelTypeValue");
                                fuelTypeValue.setValue(LN.getString(Globals.selectedCar.getFuelType()));
                            fuelTypeContainer.getChildren().addAll(fuelTypeValue,fuelType);   
                        carTab.getChildren().add(fuelTypeContainer);

                        
                            HBox transmissionTypeContainer = new HBox();
                            transmissionTypeContainer.setId("transmissionContainer");
                            transmissionTypeContainer.setSpacing(10);
                                Label transmissionType =  new Label(LN.getString("general.defaultSeparator")+LN.getString("general.transmission"));
                                CarAddPageController.getTransmissionTypesEntries(transsmisionTypeValue ); 
                                transsmisionTypeValue.setMinWidth(200);
                                transsmisionTypeValue.setId("transmissionTypeValue");
                                transsmisionTypeValue.setValue(LN.getString(Globals.selectedCar.getTransmissionType()));
                            transmissionTypeContainer.getChildren().addAll(transsmisionTypeValue,transmissionType);   
                        carTab.getChildren().add(transmissionTypeContainer);
                       
                                              
                            HBox enginePowerContainer = new HBox();
                            enginePowerContainer.setId("enginePowerContainer");
                            enginePowerContainer.setSpacing(10);
                                Label enginePower =  new Label(LN.getString("general.defaultSeparator")+LN.getString("general.power"));
                                CarAddPageController.getEnginePowerEntries(enginePowerValue ); 
                                enginePowerValue.setMinWidth(200);
                                enginePowerValue.setId("enginePowerValue");
                                enginePowerValue.setValue(Globals.selectedCar.getPower()+" "+LN.getString("general.horsepower"));
                            enginePowerContainer.getChildren().addAll(enginePowerValue,enginePower);   
                        carTab.getChildren().add(enginePowerContainer);
                        
                        
                         
                        Button addCar = new Button(LN.getString("carEditPage.saveChanges"));
                        addCar.setId("saveChanges");
                        addCar.setOnAction(new EventHandler<ActionEvent>() 
                        {
                            public void handle(ActionEvent e) 
                            { 
                                CarEditPageController.saveChanges(carNameValue,vinValue,makerValue,modelValue,colorValue,bodyTypeValue,manufactureYearValue,fuelTypeValue,transsmisionTypeValue,enginePowerValue); 
                            }
                        });
   
                        
                        carTab.setAlignment(Pos.TOP_RIGHT);
                        
                        carTab.getChildren().addAll(addCar);                                
                    carContainer.getChildren().addAll(carLableContainer,carTab);
                    
                 centralContainer.getChildren().addAll(carContainer);     
              pane.setCenter(centralContainer);     
    }
    
}
