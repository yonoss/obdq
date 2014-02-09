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
public class ISOSpecificCommands
{
    public ISOSpecificCommands ()
    {
    }

     /**perform a Fast Initiation**/
      public String performFastInitiation()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT FI");
    }
       /**set the ISO Baud rate to 10400***/
      public String setISOBaudRate10400()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IB 10");
    }
       /**set the ISO Baud rate to 4800**/
      public String setISOBaudRate4800()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IB 48");
    }
       /**set the ISO Baud rate to 9600**/
      public String setISOBaudRate9600()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IB 96");
    }
       /**set ISO (slow) Init Address to hh**/
      public String setISOSlowInitAddress(long hh) throws IOException
    {
         if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT IIA "+hh);
         else
            throw new IOException("setISOSlowInitAddress(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**display the Key Words**/
      public String displayKeyWords()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT KW");
    }
       /**Key Word checking off***/
      public String setKeyWordCheckingOff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT KW0");
    }
       /**Key Word checking on**/
      public String  setKeyWordCheckingOn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT KW1");
    }
       /**perform a Slow (5 baud) Initiation**/
      public String performSlowInitiation()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT SI");
    }
       /**Set Wakeup interval to hh x 20 msec**/
      public String setWakeupInterval(long hh) throws IOException
    {
          if(Utils.isOfSize(hh, (long)0x0, (long)0xFF))
                return SerialUtils.getSyncSerialResponseForCommand("AT SW "+hh);
          else
                throw new IOException("setWakeupInterval(long hh) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**set the Wakeup Message**/
      public String setWakeupMessage(String message)
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT WM "+message);
    }
}
