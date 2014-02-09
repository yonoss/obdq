/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import com.queeq.obdq.scenes.utils.PID;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@XmlRootElement(name = "car")
public class Car implements Serializable
{
    private String carName="";
    
    private String VIN="";
    private String manufacturerName="";
    private String modelName="";
    private String exteriorColor="";
    private String transmissionType="";
    private String power="";

    private String bodyType="";
    private String fuelType="";
    private String climatisation="";
    private String interiorType="";
    private String interiorColour="";
    private String numberOfDoors="";
    private String engineSize="";
    private String numberOfSeats="";
    private String manufacturingYear="";
    private String test="";
    private ArrayList<PID> PIDList=new ArrayList();
     public Car(){}
     public Car(String carNameValue,
                String VINValue,
                String manufacturerNameValue,
                String modelNameValue,
                String exteriorColorValue,
                String transmissionTypeValue,
                String powerValue,
                String bodyTypeValue,
                String fuelTypeValue,
                String climatisationValue,
                String interiorTypeValue,
                String interiorColourValue,
                String numberOfDoorsValue,
                String engineSizeValue,
                String numberOfSeatsValue,
                String manufacturingYearValue)
    {
        carName=carNameValue;
        VIN=VINValue;
        manufacturerName=manufacturerNameValue;
        modelName=modelNameValue;
        exteriorColor=exteriorColorValue;
        transmissionType=transmissionTypeValue;
        power=powerValue;

        bodyType=bodyTypeValue;
        fuelType=fuelTypeValue;
        climatisation=climatisationValue;
        interiorType=interiorTypeValue;
        interiorColour=interiorColourValue;
        numberOfDoors=numberOfDoorsValue;
        engineSize=engineSizeValue;
        numberOfSeats=numberOfSeatsValue;
        manufacturingYear=manufacturingYearValue;
        
    }
     @XmlElement(name = "carName")
     public void setCarName(String carNameValue)
    {
       carName=carNameValue; 
    }
    public String getCarName()
    {
       return carName; 
    }
    public void setVIN(String VINValue)
    {
       VIN=VINValue; 
    }
    public String getVIN()
    {
       return VIN; 
    }
    public void setManufacturerName(String manufacturerNameValue)
    {
       manufacturerName=manufacturerNameValue; 
    }
    public String getManufacturerName()
    {
       return manufacturerName; 
    }
     public void setModelName(String modelNameValue)
    {
       modelName=modelNameValue; 
    }
    public String getModelName()
    {
       return modelName; 
    }
     public void setExteriorColor(String colorValue)
    {
       exteriorColor=colorValue; 
    }
    public String getExteriorColor()
    {
       return exteriorColor; 
    }
      public void setEngineSize(String engineSizeValue)
    {
       engineSize=engineSizeValue; 
    }
    public String getEngineSize()
    {
       return engineSize; 
    }
      public void setTransmissionType(String transmissionTypeValue)
    {
       transmissionType=transmissionTypeValue; 
    }
    public String getTransmissionType()
    {
       return transmissionType; 
    }
      public void setBodyType(String bodyTypeValue)
    {
       bodyType=bodyTypeValue; 
    }
    public String getBodyType()
    {
       return bodyType; 
    }
      public void setFuelType(String fuelTypeValue)
    {
       fuelType=fuelTypeValue; 
    }
    public String getFuelType()
    {
       return fuelType; 
    }
     public void setPower(String powerValue)
    {
       power=powerValue; 
    }
    public String getPower()
    {
       return power; 
    }
    
      public void setClimatisation(String climatisationValue)
    {
       climatisation=climatisationValue; 
    }
    public String getClimatisation()
    {
       return climatisation; 
    }
      public void setInteriorType(String interiorTypeValue)
    {
       interiorType=interiorTypeValue; 
    }
    public String getInteriorType()
    {
       return interiorType; 
    }
      public void setInteriorColour(String interiorColourValue)
    {
       interiorColour=interiorColourValue; 
    }
    public String getInteriorColour()
    {
       return interiorColour; 
    }
      public void setNumberOfDoors(String numberOfDoorsValue)
    {
       numberOfDoors=numberOfDoorsValue; 
    }
    public String getNumberOfDoors()
    {
       return numberOfDoors; 
    }
      public void setNumberOfSeats(String numberOfSeatsValue)
    {
       numberOfSeats=numberOfSeatsValue; 
    }
    public String getNumberOfSeats()
    {
       return numberOfSeats; 
    }
      public void setManufacturingYear(String manufacturingYearValue)
    {
       manufacturingYear=manufacturingYearValue; 
    }
    public String getManufacturingYear()
    {
       return manufacturingYear; 
    }
    public void setTest(String testValue)
    {
       test=testValue; 
    }
    public String getTest()
    {
       return test; 
    }
    @XmlElementWrapper(name = "PIDList")
    @XmlElement(name = "PID")
      public void setPIDList(ArrayList<PID> PIDList) 
    {
	this.PIDList = PIDList;
    }
    public ArrayList<PID> getPIDList() 
    {
            return PIDList;
    }
}
