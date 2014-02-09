/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.car.Car;
import com.queeq.obdq.internationalization.InternationalizationUtils;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.SettingsUtils;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author admin
 */
public class CarEditPageController 
{
    /**
     *Save car edit page changes
     * @param carNameValue 
     * @param vinValue 
     * @param makerValue 
     * @param modelValue 
     * @param colorValue 
     * @param bodyTypeValue 
     * @param manufactureYearValue 
     * @param fuelTypeValue 
     * @param transmissionTypeValue 
     * @param enginePowerValue 
     **/
    public static void saveChanges(TextField carNameValue,
                                    TextField vinValue,
                                    ChoiceBox makerValue,
                                    ChoiceBox modelValue,
                                    ChoiceBox colorValue,
                                    ChoiceBox bodyTypeValue,
                                    ChoiceBox manufactureYearValue,
                                    ChoiceBox fuelTypeValue,
                                    ChoiceBox transmissionTypeValue,
                                    ChoiceBox enginePowerValue) 
    {
        if(!carNameValue.getText().equals(""))
         {
            carNameValue.setId("carNameValue");
            if(!vinValue.getText().equals(""))
            {
                vinValue.setId("vinValue");
                if(!makerValue.valueProperty().getValue().equals(LN.getString("general.selectBrand")))
                {
                    makerValue.setId("makerValue");
                    if(!modelValue.valueProperty().getValue().equals(LN.getString("general.selectModel")))
                    {
                        modelValue.setId("modelValue");
                        if(!colorValue.valueProperty().getValue().equals(LN.getString("general.selectColor")))
                        {
                            colorValue.setId("colorValue");
                            if(!bodyTypeValue.valueProperty().getValue().equals(LN.getString("general.selectBodyType")))
                            {
                                bodyTypeValue.setId("bodyTypeValue");
                                if(!manufactureYearValue.valueProperty().getValue().equals(LN.getString("general.selectYear")))
                                {
                                    manufactureYearValue.setId("manufactureYearValue");
                                    if(!fuelTypeValue.valueProperty().getValue().equals(LN.getString("general.selectFuelType")))
                                    {
                                        fuelTypeValue.setId("fuelTypeValue");
                                        if(!transmissionTypeValue.valueProperty().getValue().equals(LN.getString("general.selectTransmissionType")))
                                        {
                                            transmissionTypeValue.setId("transsmisionTypeValue");
                                            if(!enginePowerValue.valueProperty().getValue().equals(LN.getString("general.selectEnginePower")))
                                            {
                                                enginePowerValue.setId("enginePowerValue");
                                                Car updatedCar=new Car(carNameValue.getText().toString(),
                                                                vinValue.getText().toString(),
                                                                makerValue.valueProperty().getValue().toString(),
                                                                modelValue.valueProperty().getValue().toString(),
                                                                InternationalizationUtils.getKeyForString(colorValue.valueProperty().getValue().toString()),
                                                                InternationalizationUtils.getKeyForString(transmissionTypeValue.valueProperty().getValue().toString()),
                                                                enginePowerValue.valueProperty().getValue().toString().replace(LN.getString("general.horsepower"), "").trim(),
                                                                InternationalizationUtils.getKeyForString(bodyTypeValue.valueProperty().getValue().toString()),
                                                                InternationalizationUtils.getKeyForString(fuelTypeValue.valueProperty().getValue().toString()),
                                                                "",
                                                                "",
                                                                "",
                                                                "",
                                                                "",
                                                                "",
                                                                manufactureYearValue.valueProperty().getValue().toString());
                                                Globals.carsList.updateCarToList(updatedCar, true);
                                                SceneDispatcher.getScene(SettingsUtils.getPreviousPage(),Globals.root,Globals.pane);
                                            }
                                            else
                                            {
                                                enginePowerValue.setId("Error");
                                            } 
                                        }
                                        else
                                        {
                                            transmissionTypeValue.setId("Error");
                                        }        
                                    }
                                    else
                                    {
                                        fuelTypeValue.setId("Error");
                                    }
                                }
                                else
                                {
                                    manufactureYearValue.setId("Error");
                                }
                            }
                            else
                            {
                                bodyTypeValue.setId("Error");
                            }
                            }
                        else
                        {
                            colorValue.setId("Error");
                        }
                    }
                    else
                    {
                        modelValue.setId("Error");
                    }     
                }
                else
                {  
                    makerValue.setId("Error");
                } 
            }
            else
            { 
                vinValue.setId("Error");
            }
         }
         else
         {  
             carNameValue.setId("Error");
         }   
     }
    
}
