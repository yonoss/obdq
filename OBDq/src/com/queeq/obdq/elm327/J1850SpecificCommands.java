/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.queeq.obdq.elm327;
import com.queeq.serial.utils.SerialUtils;
/**
 *
 * @author admin
 */
public class J1850SpecificCommands
{
    public J1850SpecificCommands ()
    {}

      /**IFRs off**/
      public String setIFROff()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IFR0");
    }
       /**IFRs auto**/
      public String setIFRAuto()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IFR1");
    }
       /**IFRs on**/
      public String setIFROn()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IFR2");
    }
       /**IFR value from Header**/
      public String setIFRHeader()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IFR H");
    }
       /**IFR value from Source**/
      public String setIFRSource()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IFR S");
    }
}
