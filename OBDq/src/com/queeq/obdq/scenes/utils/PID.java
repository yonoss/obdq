/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.settings.ObdqProperties;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.*;

/**
 *
 * @author admin
 */
@XmlRootElement(name = "PID")
public class PID implements Serializable 
    {   
        private String id;
        private boolean isAvailable;
        private boolean isSelectedOnReadCodesPage;
        private boolean isSelectedOnGraphsCodesPage;
        private String responseKey;
        private String label;
        private ArrayList<String> units=new ArrayList();
        private String displayValue;
        private String sensorValue;
        private double sensorDoubleValue;
        
        private boolean onReadCodesPage;
        private boolean onGraphsPage;


      /*  public PID(String id,String responseKey,boolean isAvailable,boolean isSelectedOnReadCodesPage,String sensorValue,String unit)
        {
            this.id=id;
            this.responseKey=responseKey;
            this.isAvailable = isAvailable;
            this.isSelectedOnReadCodesPage = isSelectedOnReadCodesPage;
            this.sensorValue=sensorValue;
            this.unit=unit;
        }*/
        public PID(String id,String responseKey,boolean isAvailable,boolean isSelectedOnReadCodesPage,String sensorValue)
        {
            this.id=id;
            this.responseKey=responseKey;
            this.isAvailable = isAvailable;
            this.isSelectedOnReadCodesPage = isSelectedOnReadCodesPage;
            this.sensorValue=sensorValue;
        }
        public PID(String id,String responseKey,boolean isSelectedOnReadCodesPage,String sensorValue)
        {
            this.id=id;
            this.responseKey=responseKey;
            this.isSelectedOnReadCodesPage = isSelectedOnReadCodesPage;
            this.sensorValue=sensorValue;
        }
        public PID(String id,boolean isSelectedOnReadCodesPage)
        {
            this.id=id;
            this.isSelectedOnReadCodesPage = isSelectedOnReadCodesPage;
        }

        public PID() 
        {
        }
        public void setId(String id) 
        { 
            this.id = id; 
        }
        public String getId() 
        { 
            return this.id; 
        }
        public SimpleStringProperty sensorLableProperty() 
        { 
            return new SimpleStringProperty(LN.getString("sensor.PID"+id)); 
        }
        @XmlTransient
        public void setSensorValue(String sensorValue) 
        { 
            this.sensorValue =sensorValue; 
        }
        public String getSensorValue() 
        { 
            return this.sensorValue; 
        }
        public SimpleStringProperty sensorValueProperty() 
        { 
            return new SimpleStringProperty(this.sensorValue); 
        }
         public void setIsAvailable(boolean isAvailable)
        {
            this.isAvailable= isAvailable; 
        }
        public boolean getIsAvailable()
        {
            return isAvailable; 
        }
         public void setIsSelectedOnGraphsCodesPage(boolean isSelected)
        {
            this.isSelectedOnGraphsCodesPage= isSelected; 
        }
        public boolean getIsSelectedOnGraphsCodesPage()
        {
            return isSelectedOnGraphsCodesPage; 
        }
         public void setIsSelectedOnReadCodesPage(boolean isSelected)
        {
            this.isSelectedOnReadCodesPage= isSelected; 
        }
        public boolean getIsSelectedOnReadCodesPage()
        {
            return isSelectedOnReadCodesPage; 
        }
        
        public void setResponseKey(String responseKey) 
        { 
            this.responseKey =responseKey; 
        }
        public String getResponseKey() 
        { 
            return this.responseKey; 
        }
        
        @XmlTransient
        public String getLable() 
        { 
            if(responseKey.equals("")){
                return LN.getString("sensor.PID"+id).replace(",", "");
            } else {
                return LN.getString("sensor.PID"+id+"."+responseKey).replace(",", "");
            }
        }
        public void setUnits(ArrayList<String> unists)
        {
            this.units=units;
        }
        public ArrayList<String> getUnits()
        {
            return this.units;
        }
        @XmlTransient
        public String getUnit()
        {
            try
            {
                return this.units.get(ObdqProperties.defaultMeasurmentSystem);
            }
            catch(Exception e)
            {
            }
            return "";
        }
        public String getDisplayValue()
        {
            return this.sensorValue+" "+getUnit();
        }
        @XmlTransient
        public void setDoubleValue(double value)
        {
            this.sensorDoubleValue=value;
        }
        public double getDoubleValue()
        {
            return this.sensorDoubleValue;
        }
        public void setOnReadCodesPage(boolean value)
        {
            this.onReadCodesPage=value;
        }
        public boolean getOnReadCodesPage()
        {
            return this.onReadCodesPage;
        }
        public void setOnGraphsPage(boolean value)
        {
            this.onGraphsPage=value;
        }
        public boolean getOnGraphsPage()
        {
            return this.onGraphsPage;
        }
    }
