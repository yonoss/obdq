/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.queeq.obdq.elm327;

import com.queeq.serial.utils.SerialUtils;
import java.io.IOException;

/**
 *
 * @author admin
 */
 public class GeneralCommands
{
   public GeneralCommands()
    {}
    /**repeat the last command**/
   public static String repeatCommand()
    {
        return SerialUtils.getSyncSerialResponseForCommand("AT");  //review this
    }
    /**try Baud Rate Divisor hh**/
   public static String tryBaudRateDivisionHH(long hh) throws IOException
    {
         if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT BRD "+hh);
         else
            throw new IOException("tryBaudRateDivisionHH(int hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
    /**set Baud Rate Timeout**/
    public  static String setBaudRateTimeoutHH(long hh) throws IOException
    {
         if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT BRT "+hh);
         else
            throw new IOException("setBaudRateTimeoutHH(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
    /**set all to Defaults**/
     public  static String setAllToDefault()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT D");
    }
       /**Echo off**/
     public  static String echoOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT E0");
    }
       /**Echo on**/
     public  static String echoOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT E1");
    }
       /**Forget Events**/
     public  static String forgetEvent()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT FE");
    }
       /**print the version ID**/
     public  static String printTheVersionID()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT I");
    }
       /**Linefeeds off**/
     public  static String linefeedsOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT L0");
    }
       /**Linefeeds on**/
     public  static String linefeedsOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT L1");
    }
       /**go to Low Power mode**/
     public  static String goToLowPowerMode()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT LP");
    }
       /**Memory off**/
     public  static String mempryOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT M0");
    }
       /**Memory on**/
     public  static String mempryOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT M1");
    }
       /**Read the stored Data**/
     public  static String readTheStoredData()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT RD");
    }
       /**Save Data byte hh**/
     public  static String saveDataByteHH(long hh) throws IOException
    {
        if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT SD "+hh);
         else
            throw new IOException("saveDataByteHH(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**Warm Start (quick software reset)**/
     public  static String warmStart()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT WS");
    }
       /**reset all**/
     public  static String resetAll()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT Z");
    }
       /**display the device description**/
     public  static String dysplayDeviceDescription()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT @1");
    }
       /**display the device identifier**/
     public  static String dysplayDeviceIdentifier()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT @2");
    }
       /**store the @2 identifier**/
     public  static String storeDeviceIdentifier(String value)
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT @3 "+value);
    }
}

