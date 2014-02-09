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
public class ProgrammableParameterCommands
{
    public ProgrammableParameterCommands()
    {}

     /**disable Prog Parameter xx**/
      public String disableProgramableParameter(long xx) throws IOException
    {
         if(Utils.isOfSize(xx, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT PP "+xx+" OFF");
         else
            throw new IOException("disableProgramableParameter(long xx) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**all Prog Parameters disabled**/
      public String disableAllProgramableParameters()
    {
         return SerialUtils.getSyncSerialResponseForCommand("PP FF OFF");
    }
       /**enable Prog Parameter xx**/
      public String enableProgramableParameter(long xx) throws IOException
    {
         if(Utils.isOfSize(xx, (long)0x0, (long)0xFF))
            return SerialUtils.getSyncSerialResponseForCommand("AT PP "+xx+" ON");
         else
            throw new IOException("enableProgramableParameter(long xx) - Invalid argument exception. A value in between 0x0 and 0xFF is expected");
    }
       /**all Prog Parameters enabled**/
      public String enableAllProgramableParameters()
    {
         return SerialUtils.getSyncSerialResponseForCommand("PP FF ON");
    }
       /**for PP xx, Set the Value to yy**/
      public String setValueForProgramableParameters(String pp, String value)
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT PP "+pp+" SV "+value);
    }
       /**print a Programable Parameters Summary**/
      public String printProgramableParametersSumary()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT PPS");
    }
}
