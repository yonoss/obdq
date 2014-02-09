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
public class VoltageReadingCommands
{
    public VoltageReadingCommands()
    {
    }
    /**Calibrate the Voltage to dd.dd volts**/
      public String calibrateVoltage(String dddd)
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CV "+dddd);
    }
      /**restore CV value to factory setting**/
      public String restoreCalibrationVoltageToFactorySettings()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT CV 0000");
    }
      /**Read the input Voltage**/
      public String readInputVoltage()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT RV");
    }

}
