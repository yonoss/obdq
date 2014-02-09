/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.elm327.iso15031;

import com.queeq.obdq.elm327.ResponseObject;
import com.queeq.obdq.elm327.Utils;
import com.queeq.serial.utils.SerialUtils;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author admin
 */
public class ISO15031 
{
    public String[] PID0100={"",""};
    
    public ISO15031()
    {
    }
    public static HashMap getAvailablePIDList()
    {
        HashMap result=new HashMap();
        result.putAll(PID0100());
        result.putAll(PID0120());
       // result.putAll(PID0140());
        return result;
    }
    
    /**
     * Return PID0100 - PIDs supported in between the interval [01 - 20] as a hash map containing PID-boolean pairs. The key is the PID number
     **/
    public static HashMap PID0100()
    {
        int start=0x01;
        int stop=0x20;
        int interval=stop-start;
        
        HashMap result=new HashMap();
        String responseHeader="4100";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 00"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
        {
            return null;
        }
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        String binVal= Utils.convertHexToBinaryString(Response,false);
        for(int i=0;i<interval;i++)
        {
            String pid=Integer.toHexString(start+i);
            pid=(pid.length()==1?pid="0"+pid:pid);
            try
            {
                if(binVal.charAt(i)=='1')
                {
                    result.put("01"+pid, true);
                }
                else
                {
                     result.put("01"+pid, false);
                }
            }
            catch(Exception e)
            {
                result.put("01"+Integer.toHexString(start+i), false);
            }
        }
        return result;
    }
    /**
     * Returns PID0101 values as a hashmap
     *@returns HashMap
     * keys / values
     * DTCNumber - number of DTC codes
     * MILStatus - 1 = on; 0 = off
     * MisfireMonitoringSupported - 1 = true; 0 = false
     * FuelSystemMonitoringSupported - 1 = true; 0 = false
     * ComprehensiveComponentMonitoringSupported - 1 = true; 0 = false
     * MisfireMonitoringReady - 1 = true; 0 = false
     * FuelSystemMonitoringReady - 1 = true; 0 = false
     * ComprehensiveComponentMonitoringReady - 1 = true; 0 = false
     * 
     * CatalystMonitoringSupported - 1 = true; 0 = false
     * HeatedCatalystMonitoringSupported - 1 = true; 0 = false
     * EvaporativeSystemMonitoringSupported - 1 = true; 0 = false
     * SecondaryAirSystemMonitoringSupported - 1 = true; 0 = false
     * ACSystemRefrigerantMonitoringSupported - 1 = true; 0 = false
     * OxygenSensorMonitoringSupported - 1 = true; 0 = false
     * OxygenSensorHeaterMonitoringSupported - 1 = true; 0 = false
     * EGRSystemMonitoringSupported - 1 = true; 0 = false
     * 
     * CatalystMonitoringReady - 1 = true; 0 = false
     * HeatedCatalystMonitoringReady - 1 = true; 0 = false
     * EvaporativeSystemMonitoringReady - 1 = true; 0 = false
     * SecondaryAirSystemMonitoringReady - 1 = true; 0 = false
     * ACSystemRefrigerantMonitoringReady - 1 = true; 0 = false
     * OxygenSensorMonitoringReady - 1 = true; 0 = false
     * OxygenSensorHeaterMonitoringReady - 1 = true; 0 = false
     * EGRSystemMonitoringReady - 1 = true; 0 = false
     **/
    public static HashMap PID0101()
    {
        HashMap result=new HashMap();
        String responseHeader="4101";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 01"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
          
       String binaryStringA=Utils.convertHexToBinaryString(Response.substring(0, 2),true);
       result.put("DTCNumber",Utils.BinStringToInt(binaryStringA.substring(1)));
       result.put("MILStatus",binaryStringA.charAt(0)-48);

       String binaryStringB=Utils.convertHexToBinaryString(Response.substring(2, 4),true);      
       result.put("MisfireMonitoringSupported",binaryStringB.charAt(7)-48);
       result.put("FuelSystemMonitoringSupported",binaryStringB.charAt(6)-48);
       result.put("ComprehensiveComponentMonitoringSupported",binaryStringB.charAt(5)-48);
       result.put("MisfireMonitoringReady",binaryStringB.charAt(3)-48);
       result.put("FuelSystemMonitoringReady",binaryStringB.charAt(2)-48);
       result.put("ComprehensiveComponentMonitoringReady",binaryStringB.charAt(1)-48);
    
       String binaryStringC=Utils.convertHexToBinaryString(Response.substring(4, 6),true);
       result.put("CatalystMonitoringSupported",binaryStringC.charAt(7)-48);
       result.put("HeatedCatalystMonitoringSupported",binaryStringC.charAt(6)-48);
       result.put("EvaporativeSystemMonitoringSupported",binaryStringC.charAt(5)-48);
       result.put("SecondaryAirSystemMonitoringSupported",binaryStringC.charAt(4)-48);
       result.put("ACSystemRefrigerantMonitoringSupported",binaryStringC.charAt(3)-48);
       result.put("OxygenSensorMonitoringSupported",binaryStringC.charAt(2)-48);
       result.put("OxygenSensorHeaterMonitoringSupported",binaryStringC.charAt(1)-48);
       result.put("EGRSystemMonitoringSupported",binaryStringC.charAt(0)-48);

       String binaryStringD=Utils.convertHexToBinaryString(Response.substring(6),true);
       result.put("CatalystMonitoringReady",binaryStringC.charAt(7)-48);
       result.put("HeatedCatalystMonitoringReady",binaryStringC.charAt(6)-48);
       result.put("EvaporativeSystemMonitoringReady",binaryStringC.charAt(5)-48);
       result.put("SecondaryAirSystemMonitoringReady",binaryStringC.charAt(4)-48);
       result.put("ACSystemRefrigerantMonitoringReady",binaryStringC.charAt(3)-48);
       result.put("OxygenSensorMonitoringReady",binaryStringC.charAt(2)-48);
       result.put("OxygenSensorHeaterMonitoringReady",binaryStringC.charAt(1)-48);
       result.put("EGRSystemMonitoringReady",binaryStringC.charAt(0)-48);
       
        return result;
    }
    public static HashMap getCarStatus()
    {
       return PID0101();
    }
    public static ResponseObject PID0101(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            result.setKey(key);
            result.setStringValue(String.valueOf(PID0101().get(key)));
        }catch(Exception e){
        }
        return result;
    }
    /**
     *DTC that caused required freeze frame data storage PID0102
     *@return String DTC that caused required freeze frame data storage
     **/
    public static String PID0102()
    {
        String responseHeader="4102";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 02"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        
        String msb=Response.substring(0,1);
        String codeTail=Response.substring(1,4);
        return Utils.getCodePrefix(msb) +codeTail;
    }
    public static String getDTCThatCausedRequiredFreezeFrameDataStorage()
    {
        return PID0102();
    }
     public static ResponseObject PID0102(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try
        {
            result.setKey(key);
            result.setStringValue(PID0102());
        }
        catch(Exception e){}
        return result;
    }
    /**
     *Returns Fuel system status PID0103
     * @return HashMap
     * Keys/values
     * FuelSystem1Status:
     * FuelSystem2Status:
     **/
    public static HashMap PID0103()
    {

        HashMap values = new HashMap();
        values.put("01", "OL");
        values.put("02", "CL");
        values.put("04", "OL-Drive");
        values.put("08", "OL-Fault");
        values.put("10", "CL-Fault");
        HashMap result = new HashMap();
        String responseHeader="4103";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 03"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("FuelSystem1Status", values.get(Response.substring(0, 2)));
        result.put("FuelSystem2Status", values.get(Response.substring(2)));
        return result;
    }
     public static HashMap getFueslSystemStatus()
    {
        return PID0103();
    }
      public static ResponseObject PID0103(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try
        {
            result.setKey(key);
            result.setStringValue((String)PID0103().get(key));
        }catch(Exception e){
        }
        return result;
    }
    /**
      * Calculated Load Value.PID0104
      * @return double value in percentage. Min value 0% Max value 100%
      **/
    public static double PID0104()
    {
        String responseHeader="4104";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 04"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*100/255;
    }
    public static double getCalculatedLoadValue()
    {
        return PID0104();
    }
     public static ResponseObject PID0104(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try
        {
            //double value=PID0104();
             Random randomGenerator = new Random();
            double value = randomGenerator.nextInt(100);
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }
        catch(Exception e){}
        return result;
    }
    
    /**
     *Engine Coolant Temperature PID0105
     * @return Engine Coolant Temperature in Celsius degrees. Min value − 40 °C. Max value +215 °C
     **/
     public static int PID0105()
    {
        String responseHeader="4105";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 05"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -41;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)-40;
    }
      public static int getEngineCoolantTemperature()
    {
        return PID0105();
    }
       public static ResponseObject PID0105(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try
        {
            double value=PID0105();           
            if(system==0)
            {
                double iValue=Utils.convertCelsisuToFahrenheit(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
     /**
     *Short term fuel trim PID0106
     * @return returns Short Term Fuel Trim . Min value -100%. Max value +99.22% 
     * ShortTermFuelTrimBank1
     * ShortTermFuelTrimBank3
     **/
      public static HashMap PID0106()
    {
        HashMap result = new HashMap();
        
        String responseHeader="4106";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 06"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("ShortTermFuelTrimBank1", Utils.HexStringToInt(Response.substring(0, 2))*100/128-100);
        result.put("ShortTermFuelTrimBank3", Utils.HexStringToInt(Response.substring(2))*100/128-100);

        return result;
    }
        public static HashMap getShortTermFuelTrimPID0106()
    {
        return PID0106();
    }
         public static ResponseObject PID0106(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0106();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue(Double.valueOf((Integer)value.get(key)));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        
      /**
     *Long Term Fuel Trim PID0107
     * @return returns Long Term Fuel Trim. Min value -100%. Max value +99.22% 
     * LongTermFuelTrimBank1
     * LongTermFuelTrimBank3
     **/
       public static HashMap PID0107()
    {
        HashMap result = new HashMap();
        
        String responseHeader="4107";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 07"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");

        result.put("LongTermFuelTrimBank1", Utils.HexStringToInt(Response.substring(0, 2))*100/128-100);
        result.put("LongTermFuelTrimBank3", Utils.HexStringToInt(Response.substring(2))*100/128-100);

        return result;
    }
        public static HashMap getLongTermFuelTrimPID0107()
    {
        return PID0107();
    }
        public static ResponseObject PID0107(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0107();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue(Double.valueOf((Integer)value.get(key)));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
     /**
     *Short term fuel trim PID0108
     * @return returns Short Term Fuel Trim . Min value -100%. Max value +99.22% 
     * ShortTermFuelTrimBank2
     * ShortTermFuelTrimBank4
     **/
      public static HashMap PID0108()
    {
        HashMap result = new HashMap();
        
        String responseHeader="4108";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 08"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("ShortTermFuelTrimBank2", Utils.HexStringToInt(Response.substring(0, 2))*100/128-100);
        result.put("ShortTermFuelTrimBank4", Utils.HexStringToInt(Response.substring(2))*100/128-100);

        return result;
    }
       public static HashMap getShortTermFuelTrimPID0108()
    {
        return PID0108();
    }
       public static ResponseObject PID0108(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0108();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue(Double.valueOf((Integer)value.get(key)));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
      /**
     *Long Term Fuel Trim PID0109
      @return returns Long Term Fuel Trim. Min value -100%. Max value +99.22% 
     * LongTermFuelTrimBank2
     * LongTermFuelTrimBank4
     **/
      public static HashMap PID0109()
    {
        HashMap result = new HashMap();
        
        String responseHeader="4109";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 09"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("LongTermFuelTrimBank2", Utils.HexStringToInt(Response.substring(0, 2))*100/128-100);
        result.put("LongTermFuelTrimBank4", Utils.HexStringToInt(Response.substring(2))*100/128-100);

        return result;
    }
      public static HashMap getLongTermFuelTrimPID09()
    {
        return PID0109();
    }
      public static ResponseObject PID0109(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0109();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue(Double.valueOf((Integer)value.get(key)));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
      /**
       *Fuel Rail Pressure (gauge) PID010A
       * @return Fuel Rail Pressure in kPa. Min value 0 kPa. Max value 765 kPa.
       **/
      public static int PID010A()
    {
        String responseHeader="410A";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 0A"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*3;
    }
      public static int getFuelRailPressurePID0A()
    {
        return PID010A();
    }
      public static ResponseObject PID010A(String key,int system,String unit)
    {
       ResponseObject result=new ResponseObject();
        try
        {
            int value=PID010A();           
            if(system==0)
            {
                double iValue=Utils.convertkPAtoPSI(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
    /**
     *Intake Manifold Absolute Pressure PID010B
     * @return Intake Manifold Absolute Pressure in kPA . Min value 0 kPa. Max value 255 kPa
     **/  
    public static int PID010B()
    {
        String responseHeader="410B";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 0B"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response);
    }
    public static int getIntakeManifoldAbsolutePressure()
    {
        return PID010B();
    }
     public static ResponseObject PID010B(String key,int system,String unit)
    {
       ResponseObject result=new ResponseObject();
        try
        {
            int value=PID010B();           
            if(system==0)
            {
                double iValue=Utils.convertkPAtoPSI(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
    /**
    *Engine RPM PID010C
    * @return Engine in rpm. Min value 0. Max value 16383.75 
    **/
    public static double PID010C()
    {
        String responseHeader="410C";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 0C"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)/4;
    }
     public static double getEngineRPM()
    {
        return PID010C();
    }
      public static ResponseObject PID010C(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID010C();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
    *Vehicle Speed Sensor  PID010D
    * VSS shall display vehicle road speed, if utilized by the control module strategy. 
    * Vehicle speed may be derived from a vehicle speed sensor, calculated by the PCM using other speed sensors, or obtained from the vehicle serial data communication bus.
    * @return Vehicle Speed Sensor in Km/h. Min value 0. Max value 255.
    **/
     public static int PID010D()
    {
        String responseHeader="410D";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 0D"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response);
    }
     public static int getVehicleSpeedSensor()
    {
        return PID010D();
    }
     public static ResponseObject PID010D(String key,int system,String unit)
    {
       ResponseObject result=new ResponseObject();
        try
        {
            int value=PID010D();           
            if(system==0)
            {
                double iValue=Utils.convertKmToMiles(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
     /**
      *Ignition Timing Advance for #1 Cylinder PID010E
      * @return Ignition Timing Advance for #1 Cylinder in degrees(angle). Min value -64. Max value 63.5 
      **/
      public static double PID010E()
    {
        String responseHeader="410E";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 0E"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -65;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)/2-64;
    }
      public static double getIgnitionTimingAdvanceForNo1Cylinder()
    {
        return PID010E();
    }
      public static ResponseObject PID010E(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID010E();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
      /**
       *Intake Air Temperature PID010F
       * The standard is for CANADA -50 is the minimum value. The minimum value for standard is -40
       * @return Intake Air Temperature in Celsius.Min value -50. Max value +205.
       **/
       public static double PID010F()
    {
        String responseHeader="410F";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 0F"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -51;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)-50;
    }
       public static double getIntakeAirTemperature()
    {
        return PID010F();
    }
        public static ResponseObject PID010F(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try
        {
            double value=PID010F();           
            if(system==0)
            {
                double iValue=Utils.convertCelsisuToFahrenheit(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
       /**
       *Air Flow Rate from Mass Air Flow Sensor PID0110
       * @return Air Flow Rate from Mass Air Flow Sensor in g/s .Min value 0. Max value 655,35.
       **/
       public static double PID0110()
    {
        String responseHeader="4110";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 10"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)/100;
    }
        public static double getAirFlowRateFromMassAirFlowSensor()
    {
        return PID0110();
    }
        public static ResponseObject PID0110(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try
        {
            double value=PID0110();           
            if(system==0)
            {
                double iValue=Utils.convertGtoOZ(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
       /**
       *Absolute Throttle Position PID0111
       * @return Absolute Throttle Position in % .Min value 0. Max value 100.
       **/
       public static double PID0111()
    {
        String responseHeader="4111";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 11"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*100/255;
    }
        public static double getAbsoluteThrottlePosition()
    {
        return PID0111();
    }
        public static ResponseObject PID0111(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID0111();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Commanded Secondary Air Status PID0112
       * @return AIR_STAT. Possible values :UPS (upstream of first catalytic converter); DNS (downstream of first catalytic converter inlet) ; OFF ; N/A  
       **/
       public static String PID0112()
    {
        String[] values={"UPS","DNS","OFF"};
        String responseHeader="4112";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 12"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return "N/A";
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        Response=Utils.convertHexToBinaryString(Response,true);
        if(Response.charAt(7)-48==1)
          return values[0];
        else if(Response.charAt(6)-48==1)
          return values[1];
        else if(Response.charAt(5)-48==1)
          return values[2];
        else
          return "N/A";  
    }
       public static String getCommandedSecondaryAirStatus()
    {
        return PID0112();
    }
       public static ResponseObject PID0112(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            result.setKey(key);
            result.setStringValue(PID0112());
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
      /**
       *Oxygen Sensors Present
       * @return HashMap containing the oxygen sensors location. 
       * possible sensors/keys: 
       * O2S11
       * O2S12
       * O2S13
       * O2S14
       * O2S21
       * O2S22
       * O2S23
       * O2S24
       **/
       public static HashMap PID0113()
    { 
        HashMap result=new HashMap();
        String responseHeader="4113";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 13"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        Response=Utils.convertHexToBinaryString(Response,true);
        result.put("O2S11", String.valueOf(Response.charAt(7)-48));
        result.put("O2S12", String.valueOf(Response.charAt(6)-48));
        result.put("O2S13", String.valueOf(Response.charAt(5)-48));
        result.put("O2S14", String.valueOf(Response.charAt(4)-48));
        result.put("O2S21", String.valueOf(Response.charAt(3)-48));
        result.put("O2S22", String.valueOf(Response.charAt(2)-48));
        result.put("O2S23", String.valueOf(Response.charAt(1)-48));
        result.put("O2S24", String.valueOf(Response.charAt(0)-48));
        return result;
    } 
       public static ResponseObject PID0113(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0113();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
       
     /**
       *Bank 1 – oxygen sensor Sensor 1
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID0114()
    { 
        HashMap result=new HashMap();
        String responseHeader="4114";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 14"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
         public static ResponseObject PID0114(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0114();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
       /**
       *Bank 1 – oxygen sensor Sensor 2
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID0115()
    { 
        HashMap result=new HashMap();
        String responseHeader="4115";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 15"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
       public static ResponseObject PID0115(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0115();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
       /**
       *Bank 1 – oxygen sensor Sensor 3
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID0116()
    { 
        HashMap result=new HashMap();
        String responseHeader="4116";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 16"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
       public static ResponseObject PID0116(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0116();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
       /**
       *Bank 1 – oxygen sensor Sensor 4
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID0117()
    { 
        HashMap result=new HashMap();
        String responseHeader="4117";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 17"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
       public static ResponseObject PID0117(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0117();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Bank 2 – oxygen sensor Sensor 1
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID0118()
    { 
        HashMap result=new HashMap();
        String responseHeader="4118";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 18"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
        public static ResponseObject PID0118(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0118();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Bank 2 – oxygen sensor Sensor 2
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID0119()
    { 
        HashMap result=new HashMap();
        String responseHeader="4119";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 19"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
       public static ResponseObject PID0119(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0119();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Bank 2 – oxygen sensor Sensor 3
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID011A()
    { 
        HashMap result=new HashMap();
        String responseHeader="411A";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 1A"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
       public static ResponseObject PID011A(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID011A();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Bank 2 – oxygen sensor Sensor 4
       * @return HashMap. possible keys:
       * "O2Sxy" - Oxygen Sensor Output Voltage (Bx-Sy). Min val=0 Max val=1.275 (Volts)
       * "SHRTFTxy" - Short Term Fuel Trim (Bx-Sy). Min val=-100 Max val=99.22 (Percentage)
       **/
       public static HashMap PID011B()
    { 
        HashMap result=new HashMap();
        String responseHeader="411B";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 1B"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("O2Sxy", String.valueOf(Utils.HexStringToInt(Response.substring(0, 2))*5/1000));
        result.put("SHRTFTxy", String.valueOf(Utils.HexStringToInt(Response.substring(2))*100/128-100));
        return result;
    }
       public static ResponseObject PID011B(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID011B();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *OBD requirements to which vehicle is designed PID011C
       * @return string = OBD requirements to which vehicle is designed (OBDSUP)
       **/
       public static String PID011C()
    { 
        HashMap values=new HashMap();
        values.put("01", "OBD II");
        values.put("02", "OBD");
        values.put("03", "OBD and OBD II");
        values.put("04", "OBD I");
        values.put("05", "NO OBD");
        values.put("06", "EOBD");
        values.put("07", "EOBD and OBD II");
        values.put("08", "EOBD and OBD");
        values.put("09", "EOBD, OBD and OBD II");
        values.put("0A", "JOBD");
        values.put("0B", "JOBD and OBD II");
        values.put("0C", "JOBD and EOBD");
        values.put("0D", "JOBD, EOBD, and OBD II");
        values.put("0E", "EURO IV B1");
        values.put("0F", "EURO V B2");
        values.put("10", "EURO C");
        values.put("11", "EMD"); 
        String responseHeader="411C";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 1C"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        
        return (String)values.get(Response);
    }
       public static String getOBDRequirementsToWhichVehicleIsDesigned()
    { 
        return PID011C();
    }
       public static ResponseObject PID011C(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            String value=PID011C();
            result.setKey(key);
            result.setStringValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Location of oxygen sensors
       *(Where sensor 1 is closest to the engine. Each bit indicates the presence or absence of an oxygen sensor at the following location.)
       * @return HashMap containing the oxygen sensors location. 
       * possible sensors/keys: 
       * O2S11
       * O2S12
       * O2S21
       * O2S22
       * O2S31
       * O2S32
       * O2S41
       * O2S42
       **/
       public static HashMap PID011D()
    { 
        HashMap result=new HashMap();
        String responseHeader="411D";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 1D"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        Response=Utils.convertHexToBinaryString(Response,true);
        result.put("O2S11", String.valueOf(Response.charAt(7)-48));
        result.put("O2S12", String.valueOf(Response.charAt(6)-48));
        result.put("O2S21", String.valueOf(Response.charAt(5)-48));
        result.put("O2S22", String.valueOf(Response.charAt(4)-48));
        result.put("O2S31", String.valueOf(Response.charAt(3)-48));
        result.put("O2S32", String.valueOf(Response.charAt(2)-48));
        result.put("O2S41", String.valueOf(Response.charAt(1)-48));
        result.put("O2S42", String.valueOf(Response.charAt(0)-48));
        return result;
    }  
        public static ResponseObject PID011D(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID011D();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        /**
       *Auxiliary Input Status : Power Take Off (PTO) Status
       * @return Power Take Off (PTO) Status. Possible values 0 or 1 
       * 0 = PTO not active (OFF);
       * 1 = PTO active (ON).
       **/
       public static String PID011E()
    { 
        HashMap result=new HashMap();
        String responseHeader="411E";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 1E"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return null;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        Response=Utils.convertHexToBinaryString(Response,true);
        return String.valueOf(Response.charAt(7)-48);
    }
        public static ResponseObject PID011E(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            String value=PID011E();
            result.setKey(key);
            result.setStringValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
       /**
       *Time Since Engine Start in seconds PID011F
       * RUNTM shall increment while the engine is running. It shall freeze if the engine stalls. RUNTM shall be reset to zero during every control module power-up and when entering the key-on, engine off position. RUNTM is limited to 65,535 seconds and shall not wrap around to zero.
       * @return Time Since Engine Start in seconds . Min value 0 sec. Max value 65,535 sec.
       **/
       public static double PID011F()
    { 
        String responseHeader="411F";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 1F"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response);
    }
        public static double getTimeSinceEngineStart()
    { 
        return PID011F();
    }
        public static ResponseObject PID011F(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID011F();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
        
        /**
     * Return PID0120 - PIDs supported in between the interval [21 - 40] as a hash map containing PID-boolean pairs. The key is the PID number
     **/
    public static HashMap PID0120()
    {
        int start=0x21;
        int stop=0x40;
        int interval=stop-start;
        
        HashMap result=new HashMap();
        String responseHeader="4120";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 20"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
        {
            return null;
        }
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        String binVal= Utils.convertHexToBinaryString(Response,false);
        for(int i=0;i<interval;i++)
        {
            try
            {
                if(binVal.charAt(i)=='1')
                {
                    result.put("01"+Integer.toHexString(start+i), true);
                }
                else
                {
                     result.put("01"+Integer.toHexString(start+i), false);
                }
            }
            catch(Exception e)
            {
                result.put("01"+Integer.toHexString(start+i), false);
            }
        }
        return result;
    }
       /**
       * Distance Traveled While MIL is Activated PID0121
       * Conditions for “Distance travelled” counter:
       * -reset to $0000 when MIL state changes from deactivated to activated by this ECU;
       * -accumulate counts in km if MIL is activated (ON);
       * -do not change value while MIL is not activated (OFF);
       *- reset to $0000 if diagnostic information is cleared either by service $04 or at least 40 warm-up cycles without MIL activated;
       * -do not wrap to $0000 if value is $FFFF.
       * @return Distance Traveled While MIL Activated . Min value 0 km. Max value 65,535 km.
       **/
       public static double PID0121()
    { 
        String responseHeader="4121";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 21"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response);
    }
        public static double getDistanceTraveledWhileMILActivated()
    { 
        return PID0121();
    }
         public static ResponseObject PID0121(String key,int system,String unit)
    {
       ResponseObject result=new ResponseObject();
        try
        {
            double value=PID0121();           
            if(system==0)
            {
                double iValue=Utils.convertKmToMiles(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
       /**
        *Fuel Rail Pressure relative to manifold vacuum PID0122
        *FRP shall display fuel rail pressure at the engine when the reading is referenced to manifold vacuum (relativepressure).For systems supporting a fuel pressure sensor, one of the following three PIDs shall be used: $0A, $22, or $23.There shall be no support for more than one of these PIDs.
        *@return Fuel Rail Pressure relative to manifold vacuum in kPa. Min value 0 kPa Max value 5177,27 kPa 
        **/
           public static double PID0122()
    { 
        String responseHeader="4122";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 22"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*5178/65535;
    }
            public static double getFuelRailPressureRelativeToManifoldVacuum()
    { 
        return PID0122();
    }
            public static ResponseObject PID0122(String key,int system,String unit)
    {
       ResponseObject result=new ResponseObject();
        try
        {
            double value=PID0122();           
            if(system==0)
            {
                double iValue=Utils.convertkPAtoPSI(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
    /**
     *Fuel Rail Pressure PID0123
     * FRP shall display fuel rail pressure at the engine when the reading is referenced to atmosphere (gage pressure). Diesel fuel pressure and gasoline direct injection systems have a higher pressure range than FRPPID $0A.
     * For systems supporting a fuel pressure sensor, one of the following three PIDs shall be used: $0A, $22, or $23.
     * There shall be no support for more than one of these PIDs.
     * @return Fuel Rail Pressure in kPa Min value 0 kPa Max value 655350kPa
     **/       
    public static double PID0123()
    { 
        String responseHeader="4123";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 23"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*10;
    }
    public static double getFuelRailPressure()
    {
        return PID0123();
    }
    public static ResponseObject PID0123(String key,int system,String unit)
    {
       ResponseObject result=new ResponseObject();
        try
        {
            double value=PID0123();           
            if(system==0)
            {
                double iValue=Utils.convertkPAtoPSI(value);
                result.setStringValue(String.valueOf(iValue));
                result.setDoubleValue(iValue);
            }
            else
            {
                result.setStringValue(String.valueOf(value));
                result.setDoubleValue(value);
            }
            result.setUnit(unit);
            result.setKey(key);           
        }catch(Exception e){}
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 1 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID0124()
    { 
        HashMap result=new HashMap();
        String responseHeader="4124";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 24"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0124(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0124();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 1 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID0125()
    { 
        HashMap result=new HashMap();
        String responseHeader="4125";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 25"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0125(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0125();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 1 – Sensor 3 (wide range O2S) or Bank 2 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID0126()
    { 
        HashMap result=new HashMap();
        String responseHeader="4126";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 26"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID0126(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0126();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 1 – Sensor 4 (wide range O2S) or Bank 2 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID0127()
    { 
        HashMap result=new HashMap();
        String responseHeader="4127";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 27"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID0127(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0127();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
     /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 2 – Sensor 1 (wide range O2S) or Bank 3 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID0128()
    { 
        HashMap result=new HashMap();
        String responseHeader="4128";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 28"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID0128(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0128();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 2 – Sensor 2 (wide range O2S) or Bank 3 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID0129()
    { 
        HashMap result=new HashMap();
        String responseHeader="4129";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 29"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID0129(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0129();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 2 – Sensor 3 (wide range O2S) or Bank 4 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID012A()
    { 
        HashMap result=new HashMap();
        String responseHeader="412A";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 2A"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID012A(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID012A();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $24 to $2B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and voltage are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Voltage, the external test equipment shall use the scaling values included in this table for those values. If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.
     *@return Bank 2 – Sensor 4 (wide range O2S) or Bank 4 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Voltage (Bx-Sy)in volts:Min value:0 Max value: 7,999
     **/
    public static HashMap PID012B()
    { 
        HashMap result=new HashMap();
        String responseHeader="412B";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 2B"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID012B(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID012B();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * Commanded EGR : PID012C
     * Commanded EGR displayed as a percent. EGR_PCT shall be normalized to the maximum EGR commanded output control parameter. EGR systems use a variety of methods to control the amount of EGR delivered to the engine.
     * If an on/off solenoid is used – EGR_PCT shall display 0 % when the EGR is commanded off, 100 % when the EGR system is commanded on.
     * If a vacuum solenoid is duty cycled, the EGR duty cycle from 0 to 100 % shall be displayed.
     * If a linear or stepper motor valve is used, the fully closed position shall be displayed as 0 %, the fully open position shall be displayed as 100 %. Intermediate positions shall be displayed as a percent of the full-open position. For example, a stepper-motor EGR valve that moves from 0 to 128 counts shall display 0 % at zero counts, 100 % at 128 counts and 50 % at 64 counts.
     * Any other actuation method shall be normalized to display 0 % when no EGR is commanded and 100 % at the maximum commanded EGR position.
     * @return  Commanded EGR in percentage.Min value 0 Max value 100
     **/
    public static double PID012C()
    { 
        String responseHeader="412C";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 2C"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*100/255;
    }
    /**
     * Commanded EGR : PID012C
     * Commanded EGR displayed as a percent. EGR_PCT shall be normalized to the maximum EGR commanded output control parameter. EGR systems use a variety of methods to control the amount of EGR delivered to the engine.
     * If an on/off solenoid is used – EGR_PCT shall display 0 % when the EGR is commanded off, 100 % when the EGR system is commanded on.
     * If a vacuum solenoid is duty cycled, the EGR duty cycle from 0 to 100 % shall be displayed.
     * If a linear or stepper motor valve is used, the fully closed position shall be displayed as 0 %, the fully open position shall be displayed as 100 %. Intermediate positions shall be displayed as a percent of the full-open position. For example, a stepper-motor EGR valve that moves from 0 to 128 counts shall display 0 % at zero counts, 100 % at 128 counts and 50 % at 64 counts.
     * Any other actuation method shall be normalized to display 0 % when no EGR is commanded and 100 % at the maximum commanded EGR position.
     * @return  Commanded EGR in percentage.Min value 0 Max value 100
     **/
    public static double getCommandedEGR()
    {
        return PID012C();
    }
     public static ResponseObject PID012C(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID012C();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    
    
    
   /**
    *EGR Error : PID012D
    * EGR error is a percent of commanded EGR. Often, EGR valve control outputs are not in the same engineering units as the EGR feedback input sensors. For example, an EGR valve can be controlled using a duty-cycled vacuum solenoid; however, the feedback input sensor is a position sensor. This makes it impossible to display “actual” versus “commanded” in the same engineering units. EGR error solved this problem by displaying a normalized (non-dimensional) EGR system feedback parameter. EGR error is defined to be
    * ((EGR actual - EGR commanded) / EGR commanded) * 100 %
    * For example, if 10 % EGR is commanded and 5 % is delivered to the engine, the EGR_ERR is
    * ((5 % - 10 %) / 10 %) * 100 % = -50 % error.
    * EGR_ERR may be computed using various control parameters such as position, steps, counts, etc. All EGR systems must react to quickly changing conditions in the engine; therefore, EGR_ERR will generally show errors during transient conditions. Under steady condition, the error will be minimized (not necessarily zero, however) if the EGR system is under control.
    * If the control system does not use closed loop control, EGR_ERR shall not be supported.
    * When commanded EGR is 0 %, EGR error is technically undefined. In this case EGR error should be set to 0 %
    * when actual EGR = 0 % or EGR error should be set to 99,2 % when actual EGR > 0 %.
    *@return 
    **/
    public static double PID012D()
    { 
        String responseHeader="412D";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 2D"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*100/128-100;
    }
    /**
    *EGR Error : PID012D
    * EGR error is a percent of commanded EGR. Often, EGR valve control outputs are not in the same engineering units as the EGR feedback input sensors. For example, an EGR valve can be controlled using a duty-cycled vacuum solenoid; however, the feedback input sensor is a position sensor. This makes it impossible to display “actual” versus “commanded” in the same engineering units. EGR error solved this problem by displaying a normalized (non-dimensional) EGR system feedback parameter. EGR error is defined to be
    * ((EGR actual - EGR commanded) / EGR commanded) * 100 %
    * For example, if 10 % EGR is commanded and 5 % is delivered to the engine, the EGR_ERR is
    * ((5 % - 10 %) / 10 %) * 100 % = -50 % error.
    * EGR_ERR may be computed using various control parameters such as position, steps, counts, etc. All EGR systems must react to quickly changing conditions in the engine; therefore, EGR_ERR will generally show errors during transient conditions. Under steady condition, the error will be minimized (not necessarily zero, however) if the EGR system is under control.
    * If the control system does not use closed loop control, EGR_ERR shall not be supported.
    * When commanded EGR is 0 %, EGR error is technically undefined. In this case EGR error should be set to 0 %
    * when actual EGR = 0 % or EGR error should be set to 99,2 % when actual EGR > 0 %.
    *@return 
    **/
    public static double getEGRError()
    { 
        return PID012D();
    }
    public static ResponseObject PID012D(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID012D();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     *Commanded Evaporative Purge :PID012E
     * EGR Error
     * Commanded evaporative purge control valve displayed as a percent. EVAP_PCT shall be normalized to the maximum EVAP purge commanded output control parameter.
     * If an on/off solenoid is used, EVAP_PCT shall display 0 % when purge is commanded off, 100 % when purge is commanded on.
     * If a vacuum solenoid is duty-cycled, the EVAP purge valve duty cycle from 0 to 100 % shall be displayed.
     * If a linear or stepper motor valve is used, the fully closed position shall be displayed as 0 %, and the fully open position shall be displayed as 100 %. Intermediate positions shall be displayed as a percent of the full-open position. For example, a stepper-motor EVAP purge valve that moves from 0 to 128 counts shall display 0 % at 0 counts, 100 % at 128 counts and 50 % at 64 counts.
     * Any other actuation method shall be normalized to display 0 % when no purge is commanded and 100 % at the maximum commanded purge position/flow.
     *@return Commanded Evaporative Purge, in percentage. Min value:0% Max value 100%
     **/
     public static double PID012E()
    { 
        String responseHeader="412E";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 2E"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*100/255;
    }
     /**
     *Commanded Evaporative Purge :PID012E
     * Commanded evaporative purge control valve displayed as a percent. EVAP_PCT shall be normalized to the maximum EVAP purge commanded output control parameter.
     * If an on/off solenoid is used, EVAP_PCT shall display 0 % when purge is commanded off, 100 % when purge is commanded on.
     * If a vacuum solenoid is duty-cycled, the EVAP purge valve duty cycle from 0 to 100 % shall be displayed.
     * If a linear or stepper motor valve is used, the fully closed position shall be displayed as 0 %, and the fully open position shall be displayed as 100 %. Intermediate positions shall be displayed as a percent of the full-open position. For example, a stepper-motor EVAP purge valve that moves from 0 to 128 counts shall display 0 % at 0 counts, 100 % at 128 counts and 50 % at 64 counts.
     * Any other actuation method shall be normalized to display 0 % when no purge is commanded and 100 % at the maximum commanded purge position/flow.
     *@return Commanded Evaporative Purge, in percentage. Min value:0% Max value 100%
     **/
     public static double getCommandedEvaporativePurge()
    { 
        return PID012E();
    }
      public static ResponseObject PID012E(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID012E();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
     /**
      * Fuel Level Input PID012F
      * FLI shall indicate nominal fuel tank liquid fill capacity as a percent of maximum, if utilized by the control module for OBD monitoring. 
      * FLI may be obtained directly from a sensor, may be obtained indirectly via the vehicle serial data communication bus, or may be inferred by the control strategy using other sensor inputs. 
      * Vehicles that use gaseous fuels shall display the percent of useable fuel capacity. 
      * If there are two tanks in a bi-fuel car, one for each fuel type, the Fuel Level Input reported shall be from the tank, which contains the fuel type the engine is running on.
      * @return Fuel Level Input, in percentage.Min value 0% Max value 100%
      * 
      **/
      public static double PID012F()
    { 
        String responseHeader="412F";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 2F"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response)*100/255;
    }
       public static double getFuelLevelInput()
    { 
        return PID012F();
    }
        public static ResponseObject PID012F(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID012F();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
      /**
       *Number of warm-ups since diagnostic trouble codes cleared PID0130
       * Number of OBD warm-up cycles since all DTCs were cleared (via an external test equipment or possibly, a battery disconnect). 
       * A warm-up is defined in the OBD regulations to be sufficient vehicle operation such that coolant temperature rises by at least 22 °C (40 °F) from engine starting and reaches a minimum temperature of 70 °C (160 °F) (60 °C (140 °F) for diesels).
       * This PID is not associated with any particular DTC.
       * It is simply an indication for I/M, of the last time an external test equipment was used to clear DTCs. 
       * If greater than 255 warm-ups have occurred, WARM_UPS shall remain at 255 and not wrap to zero.
       * @return Number of warm-ups since diagnostic trouble codes cleared . Min value 0, Max value 255.
       **/
       public static int PID0130()
    { 
        String responseHeader="4130";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 30"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return -1;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        return Utils.HexStringToInt(Response);
    }
        public static int getNumberOfWarmUpsSinceDiagnosticTroubleCodesCleared()
    { 
        return PID0130();
    }
        public static ResponseObject PID0130(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            double value=PID0130();
            result.setKey(key);
            result.setStringValue(String.valueOf(value));
            result.setDoubleValue(value);
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
       /**
        *Distance since diagnostic trouble codes cleared PID0131
        * This is distance accumulated since DTCs were cleared (via an external test equipment or possibly, a battery disconnect). 
        * This PID is not associated with any particular DTC. 
        * It is simply an indication for I/M (Inspection/Maintenance), of the last time an external test equipment was used to clear DTCs. 
        * If greater than 65,535 km have occurred, CLR_DIST shall remain at 65,535 km and not wrap to zero.
        * @return Distance since diagnostic trouble codes cleared in Km. Min value 0 Km, Max value 65535Km 
        **/
          public static int PID0131()
        { 
            String responseHeader="4131";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 31"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response);
        }
          public static int getDistanceSinceDiagnosticTroubleCodesCleared()
        {
            return PID0131();
        }
          public static ResponseObject PID0131(String key,int system,String unit)
        {
           ResponseObject result=new ResponseObject();
            try
            {
                int value=PID0131();           
                if(system==0)
                {
                    double iValue=Utils.convertKmToMiles(value);
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
          /**
           *Evap System Vapour Pressure - PID0132
           * This is evaporative system vapour pressure, if utilized by the control module. 
           * The pressure signal is normally obtained from a sensor located in the fuel tank (FTP – Fuel Tank Pressure) or a sensor in an evaporative system vapour line. 
           * If a wider pressure range is required, PID $54 scaling allows for a wider pressure range than PID $32.
           * For systems supporting Evap System Vapour Pressure, one of the following two PIDs shall be used: $32 or $54.
           * There shall be support for no more than one of these PIDs.
           *@return Evap System Vapour Pressure in Pa. Min value −8192 Pa. Max value 8191.75 Pa
           **/
           public static double PID0132()
        { 
            String responseHeader="4132";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 32"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)/4-8192;
        }
            public static double getEvapSystemVapourPressurePID32()
        { 
            return PID0132();
        }
            public static ResponseObject PID0132(String key,int system,String unit)
        {
           ResponseObject result=new ResponseObject();
            try
            {
                double value=PID0132();           
                if(system==0)
                {
                    double iValue=Utils.convertkPAtoPSI(value)/1000;
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
           /**
            *Barometric Pressure -PID0133
            * Barometric pressure utilized by the control module. 
            * BARO is normally obtained from a dedicated BARO sensor, from a MAP sensor at key-on and during certain modes of driving, or inferred from a MAF sensor and other inputs during certain modes of driving.
            * The control module shall report BARO from whatever source it is derived from.
            * NOTE 1 Some weather services report local BARO values adjusted to sea level. In these cases, the reported value may not match the displayed value on the external test equipment.
            * NOTE 2 If BARO is inferred while driving and stored in non-volatile RAM or Keep-alive RAM, BARO may not be accurate after a battery disconnect or total memory clear.
            *@return Barometric Pressure in kPa. Min value 0 kPa, Max value 255kPa
            **/
             public static int PID0133()
        { 
            String responseHeader="4133";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 33"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response);
        }
              public static int getBarometricPressure()
        { 
            return PID0133();
        }
               public static ResponseObject PID0133(String key,int system,String unit)
        {
           ResponseObject result=new ResponseObject();
            try
            {
                int value=PID0133();           
                if(system==0)
                {
                    double iValue=Utils.convertkPAtoPSI(value);
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
     /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 1 – Sensor 1 (wide range O2S) 
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID0134()
    { 
        HashMap result=new HashMap();
        String responseHeader="4134";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 34"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
    public static ResponseObject PID0134(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0134();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 1 – Sensor 2 (wide range O2S) 
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID0135()
    { 
        HashMap result=new HashMap();
        String responseHeader="4135";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 35"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0135(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0135();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 1 – Sensor 3 (wide range O2S) or Bank 2 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID0136()
    { 
        HashMap result=new HashMap();
        String responseHeader="4136";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 36"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0136(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0136();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 1 – Sensor 4 (wide range O2S) or Bank 2 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID0137()
    { 
        HashMap result=new HashMap();
        String responseHeader="4137";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 37"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0137(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0137();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 2 – Sensor 1 (wide range O2S) or Bank 3 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID0138()
    { 
        HashMap result=new HashMap();
        String responseHeader="4138";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 38"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0138(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0138();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 2 – Sensor 2 (wide range O2S) or Bank 3 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID0139()
    { 
        HashMap result=new HashMap();
        String responseHeader="4139";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 39"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID0139(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID0139();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 2 – Sensor 3 (wide range O2S) or Bank 4 – Sensor 1 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID013A()
    { 
        HashMap result=new HashMap();
        String responseHeader="413A";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 3A"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID013A(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID013A();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * PIDs $34 to $3B shall be used for linear or wide-ratio Oxygen Sensors when equivalence ratio and current are displayed.
     * If PID $4F is not supported for this ECU, or if PID $4F is supported and includes $00 for either Equivalence Ratio or Maximum Oxygen Sensor Current, the external test equipment shall use the scaling values included in this table for those values. 
     * If PID $4F is supported for this ECU, the external test equipment shall calculate scaling and range for these PIDs as explained in the PID $4F definition.   
     *@return Bank 4 – Sensor 4 (wide range O2S) or Bank 4 – Sensor 2 (wide range O2S)
     *  possible keys: 
     * EQ_RATxy - double - Equivalence Ratio (lambda) (Bx-Sy):Min value: 0 Max value: 1,999
     * O2Sxy - double - Oxygen Sensor Current (Bx-Sy)in milli amper :Min  value:-128 Max value: 127.99 mA
     **/
    public static HashMap PID013B()
    { 
        HashMap result=new HashMap();
        String responseHeader="413B";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 3B"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
            return result;
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        result.put("EQ_RATxy", Utils.HexStringToInt(Response.substring(0, 4)));
        result.put("O2Sxy", Utils.HexStringToInt(Response.substring(0)));
        return result;
    }
     public static ResponseObject PID013B(String key,int system,String unit)
    {
        ResponseObject result=new ResponseObject();
        try{
            HashMap value=PID013B();
            result.setKey(key);
            result.setStringValue((String)value.get(key));
            result.setDoubleValue((Double)value.get(key));
            result.setUnit(unit);
        }catch(Exception e){
        }
        return result;
    }
    /**
     * Catalyst Temperature Bank 1, Sensor 1 PID013C
     * CATEMP11 shall display catalyst substrate temperature for a bank 1 catalyst, if utilized by the control module strategy for OBD monitoring, or the Bank 1, Sensor 1 catalyst temperature sensor. 
     * CATEMP11 may be obtained directly from a sensor, or may be inferred by the control strategy using other sensor inputs.
     * @return Catalyst Temperature Bank 1, Sensor 1 in Celsius. Min value: -40 °C. Max value 6513,5 °C
     **/
        public static double PID013C()
        { 
            String responseHeader="413C";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 3C"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)/10-40;
        }
        public static double getCatalystTemperatureBank1Sensor1()
        {
            return PID013C();
        }
        public static ResponseObject PID013C(String key,int system,String unit)
        {
            ResponseObject result=new ResponseObject();
            try
            {
                double value=PID013C();           
                if(system==0)
                {
                    double iValue=Utils.convertCelsisuToFahrenheit(value);
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
        
      /**
     * Catalyst Temperature Bank 2, Sensor 1 PID013D
     * CATEMP21 shall display catalyst substrate temperature for a bank 2 catalyst, if utilized by the control module strategy for OBD monitoring, or the Bank 2, Sensor 1 catalyst temperature sensor. 
     * CATEMP21 may be obtained directly from a sensor, or may be inferred by the control strategy using other sensor inputs.
     * @return Catalyst Temperature Bank 2, Sensor 1 in Celsius. Min value: -40 °C. Max value 6513,5 °C
     **/
        public static double PID013D()
        { 
            String responseHeader="413D";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 3D"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)/10-40;
        }
        public static double getCatalystTemperatureBank2Sensor1()
        {
            return PID013D();
        }
        public static ResponseObject PID013D(String key,int system,String unit)
        {
            ResponseObject result=new ResponseObject();
            try
            {
                double value=PID013D();           
                if(system==0)
                {
                    double iValue=Utils.convertCelsisuToFahrenheit(value);
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
        /**
     * Catalyst Temperature Bank 1, Sensor 2 PID3E
     * CATEMP12 shall display catalyst substrate temperature for a bank 1 catalyst, if utilized by the control module strategy for OBD monitoring, or the Bank 1, Sensor 2 catalyst temperature sensor. 
     * CATEMP12 may be obtained directly from a sensor, or may be inferred by the control strategy using other sensor inputs.
     * @return Catalyst Temperature Bank 1, Sensor 2 in Celsius. Min value: -40 °C. Max value 6513,5 °C
     **/
        public static double PID013E()
        { 
            String responseHeader="413E";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 3E"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)/10-40;
        }
        public static double getCatalystTemperatureBank1Sensor2()
        {
            return PID013E();
        }
         public static ResponseObject PID013E(String key,int system,String unit)
        {
            ResponseObject result=new ResponseObject();
            try
            {
                double value=PID013E();           
                if(system==0)
                {
                    double iValue=Utils.convertCelsisuToFahrenheit(value);
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
        /**
     * Catalyst Temperature Bank 2, Sensor 2 PID3F
     * CATEMP22 shall display catalyst substrate temperature for a bank 1 catalyst, if utilized by the control module strategy for OBD monitoring, or the Bank 2, Sensor 2 catalyst temperature sensor. 
     * CATEMP22 may be obtained directly from a sensor, or may be inferred by the control strategy using other sensor inputs.
     * @return Catalyst Temperature Bank 2, Sensor 2 in Celsius. Min value: -40 °C. Max value 6513,5 °C
     **/
        public static double PID013F()
        { 
            String responseHeader="413F";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 3F"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)/10-40;
        }
        public static double getCatalystTemperatureBank2Sensor2()
        {
            return PID013F();
        }
         public static ResponseObject PID013F(String key,int system,String unit)
        {
            ResponseObject result=new ResponseObject();
            try
            {
                double value=PID013F();           
                if(system==0)
                {
                    double iValue=Utils.convertCelsisuToFahrenheit(value);
                    result.setStringValue(String.valueOf(iValue));
                    result.setDoubleValue(iValue);
                }
                else
                {
                    result.setStringValue(String.valueOf(value));
                    result.setDoubleValue(value);
                }
                result.setUnit(unit);
                result.setKey(key);           
            }catch(Exception e){}
            return result;
        }
        /**
     * Return PID0140 - PIDs supported in between the interval [41 - 60] as a hash map containing PID-boolean pairs. The key is the PID number
     **/
    public static HashMap PID0140()
    {
        int start=0x41;
        int stop=0x60;
        int interval=stop-start;
        
        HashMap result=new HashMap();
        String responseHeader="4140";
        String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 40"));
        int header=Response.indexOf(responseHeader);
        if(header==-1)
        {
            return null;
        }
        Response=Response.substring(header);
        Response=Response.replace(responseHeader, "");
        String binVal= Utils.convertHexToBinaryString(Response,false);
        for(int i=0;i<interval;i++)
        {
            try
            {
                if(binVal.charAt(i)=='1')
                {
                    result.put("01"+Integer.toHexString(start+i), true);
                }
                else
                {
                     result.put("01"+Integer.toHexString(start+i), false);
                }
            }
            catch(Exception e)
            {
                result.put("01"+Integer.toHexString(start+i), false);
            }
        }
        return result;
    }
        public static String PID0141()
        {
            return "To be implemented";
        }
        /**
         * Control module voltage PID0142
         * VPWR – power input to the control module. VPWR is normally battery voltage, less any voltage drop in the circuit between the battery and the control module.
         * NOTE 42-volts vehicles may utilize multiple voltages for different systems on the vehicle. VPWR represents the voltage at the control module; it may be significantly different than battery voltage.
         * @return  Control module voltage in volts. Min value 0 V, Max value 65,535 V
         **/
         public static double PID0142()
        {
            String responseHeader="4142";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 42"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)/1000;
        }
          public static double getControlModuleVoltage()
        {
            return PID0142();
        }
         
         /**
          * Absolute Load Value PID0143
          * @return Absolute Load Value. Min value 0% Max value 25700 %
          **/
        public static double PID0143()
        {
            String responseHeader="4143";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 43"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
        public static double getAbsoluteLoadValue()
        {
            return PID0143();
        }
        /**
         *Commanded Equivalence Ratio PID0144
         * @return Commanded Equivalence Ratio. Min value 0 Max value 1,999
         **/
        public static double getCommandedEquivalenceRatio()
        {
            String responseHeader="4144";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 44"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*2/65535;
        }
            /**
             * Relative Throttle Position PID0145
             * Relative or “learned” throttle position shall be displayed as a normalized value, scaled from 0 to 100 %. 
             * TP_R should display a value of 0 % at the “learned” closed-throttle position. For example, if a 0 to 5,0 volt sensor is used (uses a 5,0 volt reference voltage), and the closed-throttle position is at 1,0 volts, TP shall display (1,0 – 1,0 / 5,0) = 0 % at closed throttle and 30 % at 2,5 volts. 
             * Because of the closed-throttle offset, wide-open throttle will usually indicate substantially less than 100 %.
             * For systems where the output is proportional to the input voltage, this value is the percent of maximum input reference voltage. 
             * For systems where the output is inversely proportional to the input voltage, this value is 100 % minus the percent of maximum input reference voltage. 
             * See PID $11 for a definition of Absolute Throttle Position.
             * @return Relative Throttle Position. Min value 0% Max value 100%
             **/
        public static double getRelativeThrottlePosition()
        {
            String responseHeader="4145";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 45"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
        /**
         * Ambient air temperature PID0146
         * AAT shall display ambient air temperature, if utilized by the control module strategy for OBD monitoring. 
         * AAT may be obtained directly from a sensor, may be obtained indirectly via the vehicle serial data communication bus, or may be inferred by the control strategy using other sensor inputs.
         * @return Ambient air temperature in Celsius. Min val -40°C Max value 215 °C
         **/
        public static int getAmbientAirTemperature()
        {
            String responseHeader="4146";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 46"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)-40;
        }
        
        /**
         *Absolute Throttle Position B PID0147
         * @return Absolute Throttle Position B. Min value 0% Max value 100%
         **/
         public static double getAbsoluteThrottlePositionB()
        {
            String responseHeader="4147";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 47"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
          /**
         *Absolute Throttle Position C PID0148
         * @return Absolute Throttle Position C. Min value 0% Max value 100%
         **/
         public static double getAbsoluteThrottlePositionC()
        {
            String responseHeader="4148";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 48"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
         /**
         *Accelerator Pedal Position D PID0149
         * @return Accelerator Pedal Position D. Min value 0% Max value 100%
         **/
         public static double getAcceleratorPedalPositionD()
        {
            String responseHeader="4149";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 49"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
          /**
         *Accelerator Pedal Position E PID014A
         * @return Accelerator Pedal Position E. Min value 0% Max value 100%
         **/
         public static double getAcceleratorPedalPositionE()
        {
            String responseHeader="414A";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 4A"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
        /**
         *Accelerator Pedal Position F PID014B
         * @return Accelerator Pedal Position F. Min value 0% Max value 100%
         **/
         public static double getAcceleratorPedalPositionF()
        {
            String responseHeader="414B";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 4B"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
         /**
         *Commanded Throttle Actuator Control PID014C
         * Commanded TAC displayed as a percent. TAC_PCT shall be normalized to the maximum TAC commanded output control parameter. TAC systems use a variety of methods to control the amount of throttle opening:
         * 1) If a linear or stepper motor is used, the fully closed throttle position shall be displayed as 0 %, and the fully open throttle position shall be displayed as 100 %. Intermediate positions shall be displayed as a percent of the full-open throttle position. For example, a stepper-motor TAC that moves the throttle from 0 to 128 counts shall display 0 % at 0 counts, 100 % at 128 counts and 50 % at 64 counts.
         * 2) Any other actuation method shall be normalized to display 0 % when the throttle is commanded closed and 100 % when the throttle is commanded open.
         * @return Commanded Throttle Actuator Control. Min value 0% Max value 100%
         **/
         public static double getCommandedThrottleActuatorControl()
        {
            String responseHeader="414C";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 4C"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -41;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response)*100/255;
        }
         /**
          *Time run by the engine while MIL is activated PID014D
          * Conditions for “Time run by the engine while MIL is activated” counter:
          * - reset to $0000 when MIL state changes from deactivated to activated by this ECU;
          * - accumulate counts in minutes if MIL is activated (ON);
          * - do not change value while MIL is not activated (OFF);
          * - reset to $0000 if diagnostic information is cleared either by service $04 or at least 40 warm-up cycles without MIL activated;
          * - do not wrap to $0000 if value is $FFFF.
          * @return Time run by the engine while MIL is activated in minutes. Min value 0 min Max value 65535 min
          **/
          public static int getTimeRunByEngineWhileMILActivated()
        {
            String responseHeader="414D";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 4D"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response);
        }
           /**
          *Time since diagnostic trouble codes cleared PID014E
          * Time accumulated since DTCs were cleared (via an external test equipment or possibly a battery disconnect).
          * This PID is not associated with any particular DTC. It is simply an indication for I/M (Inspection/Maintenance), of the last time an external test equipment was used to clear DTCs. 
          * If greater than 65 535 min have occurred, CLR_TIME shall remain at 65 535 min and not wrap to zero.
          * @return Time since diagnostic trouble codes cleared in minutes. Min value 0 min Max value 65535 min
          **/
          public static int getTimeSinceDiagnosticTroubleCodesCleared()
        {
            String responseHeader="414E";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 4E"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            return Utils.HexStringToInt(Response);
        }
           /**
          * PID4F
          * 
          * @return PID4F as a hash map
          * Keys/Values
          * MaximumValueForEquivalenceRatio - Min value 0 V Max value 255 V
          * MaximumValueForOxygenSensorVoltage - Min value 0 V Max value 255 V
          * MaximumValueForOxygenSensorCurrent - Min value 0 V Max value 255 V
          * MaximumValueForIntakeManifoldAbsolutePressure - Min value 0 kPa Max value 2550 kPa
          **/
          public static HashMap getPID014F()
        {
            HashMap result=new HashMap();
            String responseHeader="414F";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 4F"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return result;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            result.put("MaximumValueForEquivalenceRatio", Utils.HexStringToInt(Response.substring(0, 2)));
            result.put("MaximumValueForOxygenSensorVoltage", Utils.HexStringToInt(Response.substring(2,4)));
            result.put("MaximumValueForOxygenSensorCurrent", Utils.HexStringToInt(Response.substring(4,6)));
            result.put("MaximumValueForIntakeManifoldAbsolutePressure", Utils.HexStringToInt(Response.substring(6))*10);
            
            
            return result;

        }
          
          /**
           *PID50
           *@return  PID0150
           * Keys/Values
           * MaximumValueForAirFlowRateFromMassAirFlowSensor - Min value 0 g/s Max value 2550 g/s
           **/
          public static HashMap getPID0150()
        {
            HashMap result=new HashMap();
            String responseHeader="4150";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 50"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return result;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            result.put("MaximumValueForIntakeManifoldAbsolutePressure", Utils.HexStringToInt(Response)*10);
            return result;
        }
          /**
           *Type of fuel currently being utilized by the vehicle PID0151
           * @return Type of fuel currently being utilized by the vehicle
           **/
           public static String getTypeOfFuelCurrentlyBeingUtilizedByVehicle()
        {
             HashMap values=new HashMap();
            values.put("01", "GAS");
            values.put("02", "METH");
            values.put("03", "ETH");
            values.put("04", "DSL");
            values.put("05", "LPG");
            values.put("06", "CNG");
            values.put("07", "PROP");
            values.put("08", "ELEC");
            values.put("09", "BI_GAS");
            values.put("0A", "BI_METH");
            values.put("0B", "BI_ETH");
            values.put("0C", "BI_LPG");
            values.put("0D", "BI_CNG");
            values.put("0E", "BI_PROP");
            values.put("0F", "BI_ELEC");
            String responseHeader="4151";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 51"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return null;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
 
            return (String)values.get(Response);
        }
           /**
            * Alcohol Fuel Percentage PID0152
            * ALCH_PCT shall indicate the percentage of alcohol contained in ethanol or methanol fuels, if utilized. For example, ethanol fuel (E85) normally contains 85 % ethanol, in which case ALCH_PCT shall display 85,0 %.
            * Alcohol percentage can be determined using a sensor or can be inferred by the fuel control software.
            * @return AlcoholFuelPercentage in percentage. Min value 0% Max value 100%
            **/
          public static double getAlcoholFuelPercentage()
        {
            String responseHeader="4152";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 52"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
           
            return Utils.HexStringToInt(Response)*100/255;
        }
          /**
           *Absolute Evap System Vapour Pressure PID 0153
           * Absolute evaporative system vapour pressure, if utilized by the control module. 
           * The pressure signal is normally obtained from a sensor located in the fuel tank (FTP – Fuel Tank Pressure) or a sensor in an evaporative system vapour line.
           * @return Absolute Evap System Vapour Pressure. Min value 0 kPa Max value 327,675 kPa
           **/
            public static double getAbsoluteEvapSystemVapourPressure ()
        {
            String responseHeader="4153";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 53"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
           
            return Utils.HexStringToInt(Response)/200;
        }
             /**
           * Evap System Vapour Pressure PID0154
           * Evaporative system vapour pressure, if utilized by the control module. 
           * The pressure signal is normally obtained from a sensor located in the fuel tank (FTP – Fuel Tank Pressure) or a sensor in an evaporative system vapour line. 
           * PID $54 scaling allows for a wider pressure range than PID $32.
           * For systems supporting Evap System Vapour Pressure, one of the following 2 PIDs is required: $32, or $54.
           * Support for more than one of these PIDs is not allowed.
           * @return Evap System Vapour Pressure. Min value − 32767 Pa Max value 32768 Pa
           **/
            public static double getEvapSystemVapourPressurePID0154()
        {
            String responseHeader="4154";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 54"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -32768;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
           
            return Utils.HexStringToInt(Response)-32767;
        }
        /**
         *PID0155
         *@return PID0155
         * Keys/values
         * ShortTermSecondaryO2SensorFuelTrimBank1 - Min value -100% Max value + 99.22 %
         * ShortTermSecondaryO2SensorFuelTrimBank3 - Min value -100% Max value + 99.22 %
         **/
        public static HashMap getPID0155()
        {
            HashMap result=new HashMap();
            String responseHeader="4155";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 55"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return result;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            result.put("ShortTermSecondaryO2SensorFuelTrimBank1", Utils.HexStringToInt(Response.substring(0,2))*100/128-100);
            result.put("ShortTermSecondaryO2SensorFuelTrimBank3", Utils.HexStringToInt(Response.substring(2))*100/128-100);
            return result;
        }
        /**
         *PID0156
         *@return PID0156
         * Keys/values
         * LongTermSecondaryO2SensorFuelTrimBank1 - Min value -100% Max value + 99.22 %
         * LongTermSecondaryO2SensorFuelTrimBank3 - Min value -100% Max value + 99.22 %
         **/
        public static HashMap getPID0156()
        {
            HashMap result=new HashMap();
            String responseHeader="4156";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 56"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return result;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            result.put("LongTermSecondaryO2SensorFuelTrimBank1", Utils.HexStringToInt(Response.substring(0,2))*100/128-100);
            result.put("LongTermSecondaryO2SensorFuelTrimBank3", Utils.HexStringToInt(Response.substring(2))*100/128-100);
            return result;
        }
         /**
         *PID0157
         *@return PID0157
         * Keys/values
         * ShortTermSecondaryO2SensorFuelTrimBank2 - Min value -100% Max value + 99.22 %
         * ShortTermSecondaryO2SensorFuelTrimBank4 - Min value -100% Max value + 99.22 %
         **/
        public static HashMap getPID0157()
        {
            HashMap result=new HashMap();
            String responseHeader="4157";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 57"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return result;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            result.put("ShortTermSecondaryO2SensorFuelTrimBank2", Utils.HexStringToInt(Response.substring(0,2))*100/128-100);
            result.put("ShortTermSecondaryO2SensorFuelTrimBank4", Utils.HexStringToInt(Response.substring(2))*100/128-100);
            return result;
        }
        /**
         *PID0158
         *@return PID0158
         * Keys/values
         * LongTermSecondaryO2SensorFuelTrimBank2 - Min value -100% Max value + 99.22 %
         * LongTermSecondaryO2SensorFuelTrimBank4 - Min value -100% Max value + 99.22 %
         **/
        public static HashMap getPID0158()
        {
            HashMap result=new HashMap();
            String responseHeader="4158";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 58"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return result;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");
            result.put("LongTermSecondaryO2SensorFuelTrimBank2", Utils.HexStringToInt(Response.substring(0,2))*100/128-100);
            result.put("LongTermSecondaryO2SensorFuelTrimBank4", Utils.HexStringToInt(Response.substring(2))*100/128-100);
            return result;
        }
         /**
         * Fuel Rail Pressure - PID0159
         * FRP shall display fuel rail pressure at the engine when the reading is absolute. 
         * Diesel fuel-pressure and gasoline direct-injection systems have a higher pressure range than FRP PID $0A.
         * For systems supporting a fuel pressure sensor, one of the following 4 PIDs shall be used: $0A, $22, $23 or $59.
         * There shall be no support for more than one of these PIDs.
         
         *@return Fuel Rail Pressure in kPa. Min value 0 kPa Max value 655350 kPa
         **/
        public static int getFuelRailPressurePID0159()
        {
            HashMap result=new HashMap();
            String responseHeader="4159";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 59"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");

            return  Utils.HexStringToInt(Response)*10;
        }
        /**
         * Relative Accelerator Pedal Position - PID015A
        *@return Relative Accelerator Pedal Position Min value 0 % Max value 100%
         **/
        public static double getRelativeAcceleratorPedalPosition()
        {
            HashMap result=new HashMap();
            String responseHeader="415A";
            String Response=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 5A"));
            int header=Response.indexOf(responseHeader);
            if(header==-1)
                return -1;
            Response=Response.substring(header);
            Response=Response.replace(responseHeader, "");

            return  Utils.HexStringToInt(Response)*100/255;
        }
       
}
