/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author admin
 */
public class HistoryManager 
{
    private CarHistory carHistory =new CarHistory();
     public HistoryManager(Car car)
    {

        try
        {
            carHistory=loadHistory(car.getVIN());
        }
        catch(Exception e)
        {
            try
            {   
                Globals.historyManager=this; 
                Globals.historyEvenManager.CarAddedEvent(car);
                carHistory=loadHistory(car.getVIN());
            }
            catch(Exception f)
            {
                f.printStackTrace();
            }
        }
        
    }
     public CarHistory getCarHistory()
     {
         return carHistory;
     }
    /**
     *Adds a new history entry into the events audit object 
     * @param historyEntry : the history entry object to be store into the events audit object
     * @param saveData : saves  the carHistory object on the file system
     *
     **/
    public void addToCarHistory(CarHistoryEntry historyEntry,String VIN, boolean saveData) 
    {
        carHistory.getHistory().add(historyEntry);
        if(saveData)
            storeHistory(carHistory,VIN); 
    }
    /**
     * Remove car history
     **/
    public void removeCarHistory(String VIN)
    {
         File file = new File(ObdqProperties.workingDirectoryPath+ObdqProperties.carHistoryBinPath+VIN+".xml");
         file.delete();
    }
            
     /**
     * Serialize and stores the history object into the data file
     **/
    public void storeHistory(CarHistory Obj,String VIN) 
    {
        Writer w = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(CarHistory.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //m.marshal(Ads, System.out);
            w = new FileWriter(ObdqProperties.workingDirectoryPath+ObdqProperties.carHistoryBinPath+VIN+".xml",false);
            m.marshal(Obj, w);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                w.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
   
    }
    /**
     * Retrieves the history object from the data file
     **/
    public CarHistory loadHistory(String VIN) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(CarHistory.class);
        Unmarshaller um = context.createUnmarshaller();
        CarHistory result = (CarHistory) um.unmarshal(new FileReader(ObdqProperties.workingDirectoryPath+ObdqProperties.carHistoryBinPath+VIN+".xml"));

        return result;
    }

}
