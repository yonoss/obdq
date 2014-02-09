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
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.obdq.settings.SettingsUtils;
import com.queeq.obdq.utils.SystemUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author admin
 */
public class CarAddPageController 
{
    
     public static void getSelectBrandEntries(ChoiceBox selectBrand)
    {
        String[] brands = getAvailableCarManufacturers();
        int brandsSize=brands.length;
        selectBrand.getItems().add(LN.getString("general.selectBrand"));
        for(int i=0;i<brandsSize;i++)
        {
            selectBrand.getItems().add(brands[i]);
        }
        selectBrand.setValue(LN.getString("general.selectBrand"));

   }
     public static void getSelectModelEntries(ChoiceBox selectModel,String brandName)
    {     
        selectModel.getItems().clear();
        selectModel.getItems().add(LN.getString("general.selectModel"));
        if(!brandName.equals(LN.getString("general.selectBrand"))&&!brandName.equals(LN.getString("general.notavailable")))
        {
            String[] models = sortArray(getAvailableEntries(ObdqProperties.workingDirectoryPath+ObdqProperties.carManufacturersPath+brandName+".csv" ));
            int modelsSize=models.length; 
            for(int i=0;i<modelsSize;i++)
            {
                selectModel.getItems().add(models[i]);
            }
            selectModel.setDisable(false);
        }
        else
        {
            selectModel.setDisable(true);
        }
        selectModel.setValue(LN.getString("general.selectModel"));

   }
    
    private static String[] getAvailableCarManufacturers()
    {
        File dir = new File(ObdqProperties.workingDirectoryPath+ObdqProperties.carManufacturersPath);
        
        ArrayList<String> result=new ArrayList();
        // This filter only returns directories
        FilenameFilter fileFilterManufacturers = new FilenameFilter() 
        {
            @Override
            public boolean accept(File dir, String name) 
            {
                return name.endsWith(".csv");
            }
        };      
        String[] manufacturers= dir.list(fileFilterManufacturers);
        
        for(int i=0;i<manufacturers.length;i++)
        {
            manufacturers[i]=manufacturers[i].substring(0,manufacturers[i].indexOf("."));
        }
        
        return manufacturers;
    }
     private static String[] getAvailableEntries(String path)
    {
        return SystemUtils.readFromFile(path).split(",");     
    }
     
    public static void getColorEntries(ChoiceBox colorBox)
    {     
        colorBox.getItems().add(LN.getString("general.selectColor"));

            String[] colors = sortArray(getAvailableEntries(ObdqProperties.workingDirectoryPath+ObdqProperties.carColorsPath));
            int colorsSize=colors.length; 
            for(int i=0;i<colorsSize;i++)
            {
                colorBox.getItems().add(LN.getString("general."+colors[i]));
            }
            colorBox.getItems().add(LN.getString("general.other"));
        colorBox.setValue(LN.getString("general.selectColor"));
   }
      public static void getBodyTypesEntries(ChoiceBox bodyTypeBox)
    {     
        bodyTypeBox.getItems().add(LN.getString("general.selectBodyType"));

            String[] bodyTypes = sortArray(getAvailableEntries(ObdqProperties.workingDirectoryPath+ObdqProperties.carBodyTypesPath));
            int bodyTypesSize=bodyTypes.length; 
            for(int i=0;i<bodyTypesSize;i++)
            {
                bodyTypeBox.getItems().add(LN.getString("general."+bodyTypes[i]));
            }
            bodyTypeBox.getItems().add(LN.getString("general.other"));
        bodyTypeBox.setValue(LN.getString("general.selectBodyType"));
   }
       public static void getFuelTypesEntries(ChoiceBox fuelTypeBox)
    {     
        fuelTypeBox.getItems().add(LN.getString("general.selectFuelType"));

            String[] bodyTypes = sortArray(getAvailableEntries(ObdqProperties.workingDirectoryPath+ObdqProperties.carFuelTypesPath));
            int bodyTypesSize=bodyTypes.length; 
            for(int i=0;i<bodyTypesSize;i++)
            {
                fuelTypeBox.getItems().add(LN.getString("general."+bodyTypes[i]));
            }
            fuelTypeBox.getItems().add(LN.getString("general.other"));
        fuelTypeBox.setValue(LN.getString("general.selectFuelType"));
   }
          public static void getYearEntries(ChoiceBox yearBox)
    {     
            yearBox.getItems().add(LN.getString("general.selectYear"));
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            for(int i=year;i>=1940;i--)
            {
                yearBox.getItems().add(String.valueOf(i));
            }

        yearBox.setValue(LN.getString("general.selectYear"));
   }
      public static void getTransmissionTypesEntries(ChoiceBox transmissionTypeBox)
    {     
        transmissionTypeBox.getItems().add(LN.getString("general.selectTransmissionType"));

            String[] transmissionTypes = sortArray(getAvailableEntries(ObdqProperties.workingDirectoryPath+ObdqProperties.carTransmissionTypesPath));
            int transmissionTypesSize=transmissionTypes.length; 
            for(int i=0;i<transmissionTypesSize;i++)
            {
                transmissionTypeBox.getItems().add(LN.getString("general."+transmissionTypes[i]));
            }
            transmissionTypeBox.getItems().add(LN.getString("general.other"));
        transmissionTypeBox.setValue(LN.getString("general.selectTransmissionType"));
   }
     public static void getEnginePowerEntries(ChoiceBox enginePowerBox)
    {     
        enginePowerBox.getItems().add(LN.getString("general.selectEnginePower"));

            String[] enginePower = getAvailableEntries(ObdqProperties.workingDirectoryPath+ObdqProperties.carEnginePowerPath);
            int enginePowerSize=enginePower.length; 
            for(int i=0;i<enginePowerSize;i++)
            {
                enginePowerBox.getItems().add(enginePower[i]+" "+LN.getString("general.horsepower"));
            }
            enginePowerBox.getItems().add(LN.getString("general.other"));
        enginePowerBox.setValue(LN.getString("general.selectEnginePower"));
   }
    
       
     private static String[] sortArray(String[] input)
     {
         ArrayList<String> temp=new ArrayList<String>();
         for(int i=0;i<input.length;i++)
         {
             int tmpsize=temp.size();
             
             if(tmpsize<=0)
                 temp.add(input[i]);
             else
             {
                for(int j=0;j<tmpsize;j++)
                {
                    if(input[i].compareTo(temp.get(j))<0)
                    {
                        temp.add(j, input[i]);
                        break;
                    }
                    else if(input[i].compareTo(temp.get(j))==0)
                    {}
                    else if(j+1==tmpsize)
                    {
                        temp.add(input[i]);
                        break; 
                    }
                }
             }
         }
         return temp.toArray(new String[temp.size()]);
     }
     
     public static void addCar(TextField carNameValue,TextField vinValue,ChoiceBox makerValue,ChoiceBox modelValue,ChoiceBox colorValue,ChoiceBox bodyTypeValue,ChoiceBox manufactureYearValue,ChoiceBox fuelTypeValue,ChoiceBox transsmisionTypeValue,ChoiceBox enginePowerValue) 
     {
         if(!carNameValue.getText().equals("")&&Globals.carsList.getCarByName(carNameValue.getText())==null)
         {
            carNameValue.setId("carNameValue");
            if(!vinValue.getText().equals("")&&Globals.carsList.getCarByVIN(vinValue.getText())==null)
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
                                        if(!transsmisionTypeValue.valueProperty().getValue().equals(LN.getString("general.selectTransmissionType")))
                                        {
                                            transsmisionTypeValue.setId("transsmisionTypeValue");
                                            if(!enginePowerValue.valueProperty().getValue().equals(LN.getString("general.selectEnginePower")))
                                            {
                                                enginePowerValue.setId("enginePowerValue");
                                                Car newCar=new Car(carNameValue.getText().toString(),
                                                                vinValue.getText().toString(),
                                                                makerValue.valueProperty().getValue().toString(),
                                                                modelValue.valueProperty().getValue().toString(),
                                                                InternationalizationUtils.getKeyForString(colorValue.valueProperty().getValue().toString()),
                                                                InternationalizationUtils.getKeyForString(transsmisionTypeValue.valueProperty().getValue().toString()),
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
                                                Globals.carsList.addCarToList(newCar, true);
                                                SceneDispatcher.getScene(SettingsUtils.getPreviousPage(),Globals.root,Globals.pane);
                                            }
                                            else
                                            {
                                                enginePowerValue.setId("Error");
                                            } 
                                        }
                                        else
                                        {
                                            transsmisionTypeValue.setId("Error");
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
