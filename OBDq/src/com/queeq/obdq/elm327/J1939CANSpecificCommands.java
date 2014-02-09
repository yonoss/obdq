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
public class J1939CANSpecificCommands
{
    public J1939CANSpecificCommands()
    {}
           /**monitor for DM1 messages**/
      public String monitorDorDm1Messages()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT DM1");
    }
       /**use J1939 Elm data format***/
      public String useJ1939ELMDataFormat()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT JE");
    }
      /**Header Formatting off**/
      public String setHeaderFormationOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT JHF0");
    }
      /****/
      public String setHeaderFormationOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT JHF1");
    }
      /**use J1939 SAE data format**/
      public String useJ1939SAEDataFormat()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT JS");
    }
      /**set Timer Multiplier to 1**/
      public String setTimerMultiplierTo1()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT JTM1");
    }
      /**set Timer Multiplier to 5**/
      public String setTimerMultiplierTo5()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT JTM5");
    }
      /**Monitor for PGN 0hhhh**/
      public String monitorForPGN4h(long hhhh) throws IOException
    {
        if(Utils.isOfSize(hhhh, (long)0x0, (long)0xFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT MP "+hhhh);
        else
            throw new IOException("monitorForPGN4h(long hhhh) - Invalid argument exception. A value in between 0x0 and 0xFFFF is expected");
    }
      /**Monitor for PGN 0hhhh and get n messages**/
      public String  monitorForPGN4hAndGetN(long hhhh, int n) throws IOException
    {
          if(Utils.isOfSize(hhhh, (long)0x0, (long)0xFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT MP "+hhhh+" "+n);
          else
            throw new IOException("monitorForPGN4hAndGetN(long hhhh, int n) - Invalid argument exception. A value in between 0x0 and 0xFFFF is expected");
    }
        /**Monitor for PGN hhhhhh**/
      public String monitorForPGN6h(long hhhhhh) throws IOException
    {
          if(Utils.isOfSize(hhhhhh, (long)0x0, (long)0xFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT MP "+hhhhhh);
          else
            throw new IOException("monitorForPGN6h(long hhhhhh) - Invalid argument exception. A value in between 0x0 and 0xFFFFFF is expected");
    }
      /**Monitor for PGN 0hhhh and get n messages**/
      public String  monitorForPGN6hAndGetN(long hhhhhh, int n) throws IOException
    {
          if(Utils.isOfSize(hhhhhh, (long)0x0, (long)0xFFFFFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT MP "+hhhhhh+" "+n);
          else
            throw new IOException("monitorForPGN6hAndGetN(long hhhhhh, int n) - Invalid argument exception. A value in between 0x0 and 0xFFFFFF is expected");
    }
}
