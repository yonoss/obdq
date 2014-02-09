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
public class CANSpecificCommands
{
    public CANSpecificCommands ()
    {}
      /**turn off CAN Extended Addressing**/
      public String setCANExtendedAddressingOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CEA");
    }
     /**use CAN Extended Address hh**/
      public String setCANExtendedAddress2h(long hh) throws IOException
    {
          if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CEA "+hh);
          else
              throw new IOException("setCANExtendedAddress2h(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**Automatic Formatting off**/
      public String setAutomaticFormattingOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CAF0");
    }
       /**Automatic Formatting on**/
      public String setAutomaticFormattingOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CAF1");
    }
       /**set the ID Filter to  hhh**/
      public String setIDFilter3h(int hhh) throws IOException
    {
          if(Utils.isOfSize(hhh, (long)0x0, (long)0xFFF))
             return SerialUtils.getSyncSerialResponseForCommand("AT CF "+hhh);
          else
              throw new IOException("setIDFilter3h(int hhh) - Invalid argument exception. A value in between 0x0 and 0xFFF is expected");
    }
       /**set the ID Filter to  hhhhhhhh**/
      public String setIDFilter8h(long hhhhhhhh) throws IOException
    {
        if(Utils.isOfSize(hhhhhhhh, (long)0x0, (long)0xFFFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CF "+hhhhhhhh);
        else
            throw new IOException("setIDFilter8h(long hhhhhhhh) - Invalid argument exception. A value in between 0x0 and 0xFFFFFFFF is expected");
    }
       /**Flow Controls off**/
      public String setFlowControlsOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CFC0");
    }
       /**Flow Controls on**/
      public String setFlowControlsOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CFC1");
    }
       /**set the ID Mask to hhh**/
      public String setIDMask3h(long hhh) throws IOException
    {
        if(Utils.isOfSize(hhh, (long)0x0, (long)0xFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CM "+hhh);
        else
            throw new IOException("setIDMask3h(long hhh) - Invalid argument exception. A value in between 0x0 and 0xFFF is expected");
    }
       /**set the ID Mask to hhhhhhhh**/
      public String setIDMask8h(long hhhhhhhh) throws IOException
    {
        if(Utils.isOfSize(hhhhhhhh, (long)0x0, (long)0xFFFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CM "+hhhhhhhh);
        else
            throw new IOException("setIDMask8h(long hhhhhhhh) - Invalid argument exception. A value in between 0x0 and 0xFFFFFFFF is expected");
    }
       /**set CAN Priority to hh (29 bit)**/
      public String setCANPriority2h(long hh) throws IOException
    {
        if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CP "+hh);
        else
            throw new IOException("setCANPriority2h(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**reset the Receive Address filters**/
      public String resetReceiveAddressFilters()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CRA");
    }
       /**set CAN Receive Address to hhh**/
      public String setCANReceiveAddress3h(long hhh) throws IOException
    {
        if(Utils.isOfSize(hhh, (long)0x0, (long)0xFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CRA hhh");
        else
            throw new IOException("setCANReceiveAddress3h(long hhh) - Invalid argument exception. A value in between 0x0 and 0xFFF is expected");
    }
       /**set the Rx Address to hhhhhhhh**/
      public String setCANReceiveAddress8h(long hhhhhhhh) throws IOException
    {
        if(Utils.isOfSize(hhhhhhhh, (long)0x0, (long)0xFFFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT CRA "+hhhhhhhh);
        else
            throw new IOException("setCANReceiveAddress8h(long hhhhhhhh) - Invalid argument exception. A value in between 0x0 and 0xFFFFFFFF is expected");
    }
       /**show the CAN Status counts**/
      public String getCANStatusCounts()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CS");
    }
       /**Silent Monitoring off**/
      public String setSilentMonitoringOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CSM0");
    }
       /**Silent Monitoring on**/
      public String setSilentMonitoringOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CSM1");
    }
       /**display of the DLC off**/
      public String setDisplayDLCOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT D0");
    }
       /**display of the DLC on**/
      public String setDisplayDLCOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT D1");
    }
       /**Flow Control, Set the Mode to h**/
      public String setFlowControlMode1h(long h) throws IOException
    {
        if(Utils.isOfSize(h, (long)0x0, (long)0xF))
            return SerialUtils.getSyncSerialResponseForCommand("AT FC SM "+h);
        else
            throw new IOException("setFlowControlMode1h(long h) - Invalid argument exception. A value in between 0x0 and 0xF is expected");
    }
       /**FC, Set the Header to hhh**/
      public String setFlowControlHeader3h(long hhh) throws IOException
    {
        if(Utils.isOfSize(hhh, (long)0x0, (long)0xFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT FC SH "+hhh);
        else
            throw new IOException("setFlowControlHeader3h(long hhh) - Invalid argument exception. A value in between 0x0 and 0xFFF is expected");
    }
 /**FC, Set Data to [1 - 5 bytes]**/       /**Set the Header to hhhhhhhh**/
      public String setFlowControlHeader8h(long hhhhhhhh) throws IOException
    {
        if(Utils.isOfSize(hhhhhhhh, (long)0x0, (long)0xFFFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT FC SH "+hhhhhhhh);
         else
            throw new IOException("setFlowControlHeader8h(long hhhhhhhh) - Invalid argument exception. A value in between 0x0 and 0xFFFFFFFF is expected");
    }
    /**set flow control data**/
      public String setFlowControlData(String data)
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT FC SD "+data);
    }
    /**Protocol B options and baud rate**/
      public String setProtocolBParameters(long xx, long yy) throws IOException
    {
        if(Utils.isOfSize(xx, (long)0x0, (long)0xFF))
        {
            if(Utils.isOfSize(yy, (long)0x0, (long)0xFF))
            {
                return SerialUtils.getSyncSerialResponseForCommand("AT PB "+xx+" "+yy);
            }
            else
                throw new IOException("setProtocolBParameters(long xx, long yy) - Invalid argument exception. A value in between 0x0 and 0xFF is expected for the yy argument");
        }
        else
            throw new IOException("setProtocolBParameters(long xx, long yy) - Invalid argument exception. A value in between 0x0 and 0xFF is expected for the xx argument");
    }
    /**send an RTR message**/
      public String sendRTRMessage()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT RTR");
    }
    /**use of Variable DLC off**/
      public String setDLCOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT V0");
    }
    /**use of Variable DLC on**/
      public String setDLCOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT V1");
    }
}