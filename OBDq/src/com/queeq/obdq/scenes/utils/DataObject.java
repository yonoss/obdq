/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import com.queeq.obdq.elm327.ResponseObject;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class DataObject 
{
    private ArrayList<PID> sensorData=new ArrayList<PID>();

    public DataObject ()
    {
    }
    
    public void setData(ArrayList<PID> data)
    {
        //sensorData=data;
        sensorData=data;
    }
    public ArrayList<PID> getData()
    {
        return sensorData;
    }
    public ObservableList<PID> getSensorDataObject()
    { 
        ArrayList<PID> tempData=new ArrayList<PID>();
        for(int i=0;i<sensorData.size();i++)
        {
            PID tmpPID=sensorData.get(i);
            if(tmpPID.getIsAvailable())
            {
                if(tmpPID.getIsSelectedOnReadCodesPage())
                    tempData.add(tmpPID);
            }
        }
        
        return FXCollections.observableArrayList(tempData);
    }
    public ObservableList<PID> getsensorIDsObject()
    { 
        ArrayList<PID> tempData=new ArrayList<PID>();
        for(int i=0;i<sensorData.size();i++)
        {
            PID tmpPID=sensorData.get(i);
            if(tmpPID.getIsAvailable())
            {
                if(!tmpPID.getIsSelectedOnReadCodesPage())
                    tempData.add(tmpPID);
            }
        }
        return FXCollections.observableArrayList(tempData);
    }
    
    /**
     * toggle sensors collection appartnance
     * @param PID
     **/
    public void toggleSensor(PID data)
    {
        int index=getSensorDataIndexFromSensorID(data.getId(),data.getResponseKey());
        if(index>-1)
        {
            sensorData.get(index).setIsSelectedOnReadCodesPage(!data.getIsSelectedOnReadCodesPage());
        }
    }
    /**
     * sets the sensor read data 
     * @param String sensorID 
     * @param String sensor read value
     **/
    public void setSensorData(String sensorID, ResponseObject value)
    {
        try
        {
            int index=getSensorDataIndexFromSensorID(sensorID,value.getKey());
            if(index>-1)
            {
                sensorData.get(index).setSensorValue(value.getStringValue());
                //sensorData.get(index).setUnit(value.getUnit());
            }
        }
        catch(Exception e)
        {
        }
    }
    /**
     * Sets the sensor data. Depending of the value provided for the the read parameter, the value will be set as sensor data if readSensor=y or as sensor ID if readSensor=n
     * @param String sensorLable
     * @param String sensorID
     * @param String value
     * @param String readSensor.
     **/
    public void setNewSensor(String sensorID,String responseKey, String value, boolean isSelectedOnReadCodesPage)
    {
        try
        {
            int index=getOrCreateSensorDataIndexFromSensorIDs(sensorID,responseKey);
            sensorData.get(index).setId(sensorID);
            sensorData.get(index).setResponseKey(responseKey);
            sensorData.get(index).setSensorValue(value);
            sensorData.get(index).setIsSelectedOnReadCodesPage(isSelectedOnReadCodesPage);
            sensorData.get(index).setIsAvailable(true);
        }
        catch(Exception e)
        {
            sensorData.add(new PID(sensorID,responseKey,true,isSelectedOnReadCodesPage,value)); 
        }
    }
    /**
     * Returns the sensor index in the sensorData list.
     * @param String sensorID
     **/

     private int getOrCreateSensorDataIndexFromSensorIDs(String sensorID,String responseKey)
    {
        int size=sensorData.size();
        int result=size;
        for(int i=0;i<size;i++)
        {
            if(sensorData.get(i).getId().equals(sensorID)&&sensorData.get(i).getResponseKey().equals(responseKey))
            {
                result=i;
                continue;
            }
        }
        return result;
    }
     private int getSensorDataIndexFromSensorID(String sensorID,String responseKey)
    {
        int size=sensorData.size();
        int result=-1;
        for(int i=0;i<size;i++)
        {
            if(sensorData.get(i).getId().equals(sensorID)&&sensorData.get(i).getResponseKey().equals(responseKey))
            {
                result=i;
                continue;
            }
        }
        return result;
    }
}