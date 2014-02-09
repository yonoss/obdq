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
public class OBDCommands
{
     public OBDCommands()
     {
     }
      /**Allow Long (>7 byte) messages**/
      public static String allowLongMessages()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT AL");
    }
       /**Automatically Receive**/
      public static String automaticReceive()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT AR");
    }
       /**Adaptive Timing off,**/
      public static String setAdaptiveTimingOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT AT0");
    }
       /**Adaptive Timing auto1**/
      public static String setAdaptiveTimingAuto1()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT AT1");
    }
       /**Adaptive Timing auto2**/
      public static String setAdaptiveTimingAuto2()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT AT2");
    }
       /**perform a Buffer Dump**/
      public static String performBufferDump()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT BD");
    }
       /**Bypass the Initialization sequence**/
      public static String bypassInitializationSequence()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT BI");
    }
       /**Describe the current Protocol**/
      public static String dDescribeCurrentProtocol()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT DP");
    }
       /**Describe the Protocol by Number**/
      public static String describeProtocolNumber()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT DPN");
    }
       /**Headers off**/
      public static String setHeadersOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT H0");
    }
       /**Headers on**/
      public static String setHeadersOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT H1");
    }
       /**Monitor All**/
      public static String monitorAll()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT MA");
    }

       /**Monitor for Receiver = hh**/
      public static String monitorForReceiver(long hh) throws IOException
    {
          if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT MR "+hh);
          else
            throw new IOException("monitorForReceiver(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }

      /**Monitor for Transmitter = hh**/
      public static String monitorForTransmiter(long hh) throws IOException
    {
          if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT MT "+hh);
          else
            throw new IOException("monitorForTransmiter(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
      /**Normal Length messages***/
      public static String setNormalLengthMessages()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT NL");
    }
      /**Protocol Close**/
      public static String closeProtocol()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT PC");
    }
      /**Responses off**/
      public static String setResponseOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT R0");
    }
      /**Response on**/
      public static String setResponseOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT R1");
    }
      /**set the Receive Address to hh**/
      public static String setReceiveAddressRA(long hh) throws IOException
    {
         if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT RA "+hh);
         else
            throw new IOException("setReceiveAddressRA(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
      /**printing of Spaces off**/
      public static String setPrintingSpacesOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT S0");
    }
      /**printing of Spaces on**/
      public static String setPrintingSpacesOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT S1");
    }
      /**Set Header to xyz**/
      public static String setHeaderXYZ(long xyz) throws IOException
    {
         if(Utils.isOfSize(xyz, (long)0x0, (long)0xFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT SH "+xyz);
         else
            throw new IOException("setHeaderXYZ(long xyz) - Invalid argument exception. A value in between 0x0 and 0xFFF is expected");
    }
      /**Set Header to xxyyzz**/
      public static String setHeaderXXYYZZ(long xxyyzz) throws IOException
    {
         if(Utils.isOfSize(xxyyzz, (long)0x0, (long)0xFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT SH "+xxyyzz);
         else
            throw new IOException("setHeaderXXYYZZ(long xxyyzz) - Invalid argument exception. A value in between 0x0 and 0xFFFFFF is expected");
    }
      /**Set Protocol to h and save it**/
      public static String setProtocolAndSaveIt(long h) throws IOException
    {
          if(Utils.isOfSize(h, (long)0x0, (long)0xF))
            return SerialUtils.getSyncSerialResponseForCommand("AT SP "+h);
          else
            throw new IOException("setProtocolAndSaveIt(long h) - Invalid argument exception. A value in between 0x0 and 0xF is expected");
    }
      /**Set Protocol to Auto, h and save it**/
      public static String  setProtocolToAutoAndSaveIt(long h) throws IOException
    {
         if(Utils.isOfSize(h, (long)0x0, (long)0xF))
            return SerialUtils.getSyncSerialResponseForCommand("AT SP A"+h);
          else
            throw new IOException("setProtocolToAutoAndSaveIt(long h) - Invalid argument exception. A value in between 0x0 and 0xF is expected");
    }
      /**Set the Receive address to hh**/
      public static String setReceiveAddressSR(long hh) throws IOException
    {
          if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT SR "+hh);
          else
            throw new IOException("setReceiveAddressSR(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
      /**use Standard Search order (J1978) **/
      public static String useStandardSearchOrder()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT SS");
    }
      /**Set Timeout to hh x 4 msec**/
      public static String setTimeout(long hh) throws IOException
    {
         if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT ST "+hh);
         else
            throw new IOException("setTimeout(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
      /**set Tester Address to hh**/
      public static String setTesterAddress(long hh) throws IOException
    {
         if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT TA "+hh);
         else
            throw new IOException("setTesterAddress(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
      /**Try Protocol h**/
      public static String tryProtocol(long h) throws IOException
    {
         if(Utils.isOfSize(h, (long)0x0, (long)0xF))
            return SerialUtils.getSyncSerialResponseForCommand("AT TP "+h);
         else
            throw new IOException("tryProtocol(String h) - Invalid argument exception. A value in between 0x0 and 0xF is expected");
    }
      /**Try Protocol h with Auto search**/
      public static String tryProtocolWithautoSearch(long h) throws IOException
    {
         if(Utils.isOfSize(h, (long)0x0, (long)0xF))
            return SerialUtils.getSyncSerialResponseForCommand("AT TP A"+h);
         else
            throw new IOException("tryProtocolWithautoSearch(String h) - Invalid argument exception. A value in between 0x0 and 0xF is expected");
    }
}
