/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author admin
 */
public final class CarsManager 
{
    private CarsList carsList =new CarsList();
    public CarsManager()
    {
        try
        {
            carsList=loadCarsList();
        }
        catch(Exception e)
        {
            try
            {

                storeCarsList(carsList);
                carsList=loadCarsList();
            }
            catch(Exception f)
            {
                f.printStackTrace();
            }
        }
    }
    public int getSize()
    {
        int size=0;
        try
        {
            size=carsList.getCarsList().size();
        }
        catch(Exception e)
        {
        }

        return size;
    }
    public Car getListEntry(int index)
    {
        return carsList.getCarsList().get(index);
    }
    /**
     * Add a new car to the list
     * @param newCar : the car which will be added to the list
     * @param saveData : saves  the carsList object onto the file system
     **/
    public void addCarToList(Car newCar, boolean saveData)
    {
        try
        {
            int carListIndex=getCarListIndex(newCar);
            if(carListIndex==-1)
            {
                carsList.getCarsList().add(newCar); 
                Globals.historyManager=new HistoryManager(newCar);
                if(saveData)
                    storeCarsList(carsList);
            }
            else
            {
               newCar=getCarByVIN(newCar.getVIN());
            }
            Globals.selectedCar=newCar;       
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Removes car from the list
     * @param car : the car which will be removed from the list
     * @param saveData : saves  the carsList object onto the file system
     **/
    public void removeCarFromList(Car car, boolean saveData)
    {
        try
        {
            int carListIndex=getCarListIndex(car);
            if(carListIndex!=-1)
            {
                carsList.getCarsList().remove(carListIndex); 
            }
            if(saveData)
                storeCarsList(carsList);
            if(Globals.selectedCar==car)
                Globals.selectedCar=null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * @param newCar : the car which will be updated
     * @param saveData : saves  the carsList object onto the file system
     **/
    public void updateCarToList(Car updatedCar, boolean saveData)
    {
        try
        {
            int carListIndex=getCarListIndex(updatedCar);
            if(carListIndex!=-1)
            {
                carsList.getCarsList().remove(carListIndex); 
                carsList.getCarsList().add(carListIndex,updatedCar);  
                
            }
            if(saveData)
                storeCarsList(carsList);
           
            Globals.selectedCar=updatedCar;
            Globals.historyEvenManager.CarEditedEvent(updatedCar);     

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Checks for the existence of the specified car in the carsList 
     * @param car  : the car that it will search for
     **/
    private int getCarListIndex(Car car)
    {
        int result=-1;
        for(int i=0;i<getSize();i++)
        {
                if(carsList.getCarsList().get(i).getVIN().equals(car.getVIN()))
                {
                    result=i;
                    break;
                }
        } 
        return result;
    }
     /**
     * Returns the car based on its name 
     * @param carName  : the car name
     * @return car the requested car
     **/
    public Car getCarByName(String carName)
    {
        for(int i=0;i<getSize();i++)
        {
                if(carsList.getCarsList().get(i).getCarName().equals(carName))
                {
                    return carsList.getCarsList().get(i);
                }
        } 
        return null;
    }
    /**
     * Returns the car based on its name 
     * @param carVIN  : the car name
     * @return car the requested car
     **/
    public Car getCarByVIN(String carVIN)
    {
        for(int i=0;i<getSize();i++)
        {
                if(carsList.getCarsList().get(i).getVIN().equals(carVIN))
                {
                    return carsList.getCarsList().get(i);
                }
        } 
        return null;
    }
    /**
     * Serialize and stores the carsList into the data file
     **/
    public void storeCarsList(CarsList Obj) throws Exception
    {        
                JAXBContext context = JAXBContext.newInstance(CarsList.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		Writer w = null;
		try
                {
                    w = new FileWriter(ObdqProperties.workingDirectoryPath+ObdqProperties.carListBinPath,false);
                    m.marshal(Obj, w);
		} finally
                {
                    try
                    {
                        w.close();
                    }
                    catch (Exception e)
                    {
                    }
		}
    }
    /**
     * Retrieves the carsList from the data file
     **/
    public CarsList loadCarsList() throws Exception
    {
		JAXBContext context = JAXBContext.newInstance(CarsList.class);
		Unmarshaller um = context.createUnmarshaller();
		CarsList result = (CarsList) um.unmarshal(new FileReader(ObdqProperties.workingDirectoryPath+ObdqProperties.carListBinPath));

                return result;
    }    
}
