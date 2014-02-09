/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.car.Car;
import com.queeq.obdq.car.CarHistoryEntry;
import com.queeq.obdq.car.CarsManager;
import com.queeq.obdq.car.HistoryManager;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;

/**
 *
 * @author admin
 */
public class HistoryPageController 
{
    /**
     *Remove the provided car from the cars list
     * @param removableCar : cat to be removed from the list
     * @param selectedCarArea : tilePane representing the car description area visible on the History page
     * @param selectCar : Cars list choiceBox
     **/
    public static void removeCar(Car removableCar,TilePane selectedCarArea,ChoiceBox selectCar)
    {
        Globals.carsList.removeCarFromList(removableCar, true);
        Globals.historyManager.removeCarHistory(removableCar.getVIN());
        Globals.carsList=new CarsManager();
        getSelectCarEntries(selectCar); 
        setCarDescriptionArea(selectedCarArea);
    }
    public static void carSelection(String selectedCar,TilePane selectedCarArea,ListView<String> auditListView)
    {
        if(!selectedCar.equals(LN.getString("historyPage.selectCar"))&&!selectedCar.equals(LN.getString("historyPage.noCarsAvailable")))
        {
            Globals.selectedCar=Globals.carsList.getCarByName(selectedCar);
            Globals.historyManager=new HistoryManager(Globals.selectedCar); 
        }
        else
        {
            Globals.selectedCar=null;
            Globals.historyManager=null;
        }
        HistoryPageController.setCarDescriptionArea(selectedCarArea);
        HistoryPageController.setAuditArea(auditListView);
    }
    public static void getSelectCarEntries(ChoiceBox selectCar)
    {
        selectCar.getItems().clear();
        try
        {

            int carSize=Globals.carsList.getSize();
            if(carSize>0)
            {
                selectCar.getItems().add(LN.getString("historyPage.selectCar"));
                for(int i=0;i<carSize;i++)
                {
                    selectCar.getItems().add(Globals.carsList.getListEntry(i).getCarName());
                }
                if(Globals.selectedCar!=null)
                {
                    selectCar.setValue(Globals.selectedCar.getCarName());
                }
                else
                    selectCar.setValue(LN.getString("historyPage.selectCar"));
                
            }
            else
            {
                selectCar.getItems().add(LN.getString("historyPage.noCarsAvailable"));
                selectCar.setValue(LN.getString("historyPage.noCarsAvailable"));
            }
        }
        catch(Exception e)
        {
        }
   }
    
    public static void setCarDescriptionArea( TilePane selectedCarArea)
    {
        selectedCarArea.getChildren().clear();
        
        if(Globals.selectedCar==null)
        {
            Label vin = new Label(LN.getString("general.vin")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(vin);

            Label maker = new Label(LN.getString("general.maker")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(maker);

            Label model = new Label(LN.getString("general.model")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(model);

            Label color = new Label(LN.getString("general.color")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(color);

            Label bodyType = new Label(LN.getString("general.bodyType")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(bodyType);

            Label year = new Label(LN.getString("general.year")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(year);


            Label fuelType = new Label(LN.getString("general.fuelType")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(fuelType);

            Label transmision = new Label(LN.getString("general.transmission")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(transmision);

            /*Label engineSize = new Label(LN.getString("general.engineSize")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(engineSize);*/

            Label power = new Label(LN.getString("general.power")+LN.getString("general.defaultSeparator")+LN.getString("general.defaultPlaceholder"));
            selectedCarArea.getChildren().add(power);
        }
        else
        {
            Label vin = new Label(LN.getString("general.vin")+LN.getString("general.defaultSeparator")+Globals.selectedCar.getVIN());
            selectedCarArea.getChildren().add(vin);

            Label maker = new Label(LN.getString("general.maker")+LN.getString("general.defaultSeparator")+Globals.selectedCar.getManufacturerName());
            selectedCarArea.getChildren().add(maker);

            Label model = new Label(LN.getString("general.model")+LN.getString("general.defaultSeparator")+Globals.selectedCar.getModelName());
            selectedCarArea.getChildren().add(model);

            Label color = new Label(LN.getString("general.color")+LN.getString("general.defaultSeparator")+LN.getString(Globals.selectedCar.getExteriorColor()));
            selectedCarArea.getChildren().add(color);

            Label bodyType = new Label(LN.getString("general.bodyType")+LN.getString("general.defaultSeparator")+LN.getString(Globals.selectedCar.getBodyType()));
            selectedCarArea.getChildren().add(bodyType);

            Label year = new Label(LN.getString("general.year")+LN.getString("general.defaultSeparator")+Globals.selectedCar.getManufacturingYear());
            selectedCarArea.getChildren().add(year);


            Label fuelType = new Label(LN.getString("general.fuelType")+LN.getString("general.defaultSeparator")+LN.getString(Globals.selectedCar.getFuelType()));
            selectedCarArea.getChildren().add(fuelType);

            Label transmision = new Label(LN.getString("general.transmission")+LN.getString("general.defaultSeparator")+LN.getString(Globals.selectedCar.getTransmissionType()));
            selectedCarArea.getChildren().add(transmision);

           /* Label engineSize = new Label(LN.getString("general.engineSize")+LN.getString("general.defaultSeparator")+Globals.selectedCar.getEngineSize());
            selectedCarArea.getChildren().add(engineSize);*/

            Label power = new Label(LN.getString("general.power")+LN.getString("general.defaultSeparator")+Globals.selectedCar.getPower()+" "+LN.getString("general.horsepower"));
            selectedCarArea.getChildren().add(power);  
        }
    }
    
     public static void setAuditArea( ListView<String> auditListView)
    {   
        auditListView.getItems().clear();
        if(Globals.historyManager!=null)
        {
            ObservableList<String> list=FXCollections.observableArrayList();
            for(int i=0; i< Globals.historyManager.getCarHistory().getHistory().size();i++)
            {
                int no=i+1;
                CarHistoryEntry carHistEntry=Globals.historyManager.getCarHistory().getHistory().get(i);
                list.add(no+")  "+ ObdqProperties.historyAuditFormatter.format(carHistEntry.getEventDate()) +LN.getString(carHistEntry.getEventTitle()));
            }
            auditListView.setItems(list);
        }
        
    }
     /**
      *Updates the event description area
      * @param eventDescriptionTextArea : text area to be updated
      **/
    public static void updateDescriptionArea(int selectedEvent, TextArea eventDescriptionTextArea)
    {
        CarHistoryEntry carHistEntry=Globals.historyManager.getCarHistory().getHistory().get(selectedEvent);
        eventDescriptionTextArea.setText(ObdqProperties.historyDescriptionFormatter.format(carHistEntry.getEventDate())+ LN.getString(carHistEntry.getEventDescription()));
    }
}
