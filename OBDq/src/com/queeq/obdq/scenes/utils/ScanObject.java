/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author admin
 */
@XmlRootElement(namespace = "com.queeq.obdq.scenes.utils")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScanObject 
{
    @XmlAttribute(name = "VIN")
    private String VIN="fsdf";
    @XmlElement(name = "result")
    private ArrayList<PID> scanData=new ArrayList<PID>();
    public ScanObject()
    {}
    public ObservableList<PID> getScanDataObject()
    { 
        return FXCollections.observableArrayList(this.scanData);
    }
    public void setVIN(String value)
    {
        VIN=value;
    }
    public String getVIN(String value)
    {
        return VIN;
    }
    
     /**
     * sets the sensor read data 
     * @param String sensorID 
     * @param String sensor read value
     **/
    public void setSensorData(String ID,String value)
    {
        try
        {
            int index=getScanIndexFromID(ID);
            scanData.get(index).setId(ID);
            scanData.get(index).setSensorValue(value);
        }
        catch(Exception e)
        {
            scanData.add(new PID(ID,"",true,true,value)); 
        }
    }
    /**
     * Returns the sensor index in the sensorData list.
     * @param String sensorID
     **/

     private int getScanIndexFromID(String ID)
    {
        int size=scanData.size();
        int result=size;
        for(int i=0;i<size;i++)
        {
            if(scanData.get(i).getId().equals(ID))
            {
                result=i;
                break;
            }
        }
        return result;
    }
}
