/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.serial.utils;

import com.queeq.obdq.car.Car;
import com.queeq.obdq.elm327.GeneralCommands;
import com.queeq.obdq.elm327.Others;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.utils.CommunicationUtils;
import java.io.IOException;
import javafx.application.Platform;
import jssc.*;


/**
 *
 * @author admin
 */
public class SerialUtils 
{
    public SerialUtils()
    {}
    public static String[] getOpenCOMPorts() 
    {
       return SerialPortList.getPortNames();
    }
   
    public static synchronized String getSyncSerialResponseForCommand(String command)
    {
        OBDQLogger.log(LN.getString("consolePage.Client")+command);
                                    
        String result="";
        String serialCommand=command+"\r";
        if(SerialUtils.isSerialPortOpened()&&Globals.serialPortStatusFlag==1)
        {
            try
            {
                Globals.serialPortStatusFlag=2;
                Globals.serialPort.purgePort(SerialPort.PURGE_RXCLEAR | SerialPort.PURGE_TXCLEAR);
                Globals.serialPort.writeBytes(serialCommand.getBytes());
                
                while(Globals.serialPortStatusFlag!=3)
                {
                    Thread.sleep(10);
                }
                
                result=cleanSerialResponse(Globals.serialMessage,command);
                Globals.serialMessage="";
                Globals.serialPortStatusFlag=1;
            }
            catch(SerialPortException ex)
            {
                Globals.serialPortStatusFlag=1;
                Globals.serialMessage="";
                //System.out.println(ex);
            }
            catch(InterruptedException ex)
            {
                Globals.serialPortStatusFlag=1;
                Globals.serialMessage="";
                //System.out.println(ex);
            }
            
        }
        OBDQLogger.log(LN.getString("consolePage.ECU")+result);
        return result;
    }
    private static String cleanSerialResponse(String message,String command)
    {
       return message.trim().replaceAll(">", "").replace(command, "");
    }        
    public static synchronized boolean connect(String COM, int baudRate)
    {
        try 
        {
            if(!isSerialPortOpened())
            {
                CommunicationUtils.setStatus(LN.getString("settingsPage.connecting"));
                Thread.sleep(50);
                Globals.serialPort = new SerialPort(COM);
                Globals.serialPort.openPort();
                Globals.serialPort.setParams(baudRate, 8, 1, 0);//Set params 38400
                int mask = SerialPort.MASK_RXCHAR;//Prepare mask
                Globals.serialPort.setEventsMask(mask);//Set mask
                Globals.serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
                String resetELM=GeneralCommands.resetAll().toLowerCase();
                if(resetELM.indexOf("elm")!=-1)
                {
                    if(!Others.initializeELM())
                        throw new IOException(LN.getString("ELMConnectionError"));
                    else
                    { 
                        CommunicationUtils.setStatus(LN.getString("settingsPage.getCarSetting"));     
                        String VIN=Others.getVIN();
                        Globals.currentConnectionVIN=VIN;
                        Car newCar=new Car(VIN, VIN,
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"),
                                           LN.getString("general.notavailable"));
                         Globals.carsList.addCarToList(newCar, true);
                         CommunicationUtils.onConnectionEstablished();
                         
                    }
                }
                else
                    throw new IOException(String.format(LN.getString("ELMNotDetected"),resetELM));
                isSerialPortOpened();
            }
            return true;
        }
        catch (Exception ex) 	
        {
            final String exMessage=ex.getMessage();
            CommunicationUtils.setStatus(LN.getString("settingsPage.error")+":"+ex.getMessage());
            disconnect(); 
            return false;
        }
        
    }
    /**
     * Serial channel disconnect 
     **/
    public static boolean disconnect()
    {
        boolean result=true;
        try 
        {
            result=Globals.serialPort.closePort();
        }
        catch (Exception ex) 	
        {   
            //System.out.println(ex);
        }
        CommunicationUtils.onConnectionClosed();
        isSerialPortOpened();
        return result;
    }
     /**
     *Returns the status of the serial port.
     * @return boolean
     **/
    public static boolean isSerialPortOpened()
    {
        try
        {
            if(Globals.serialPort!=null)
            {
                if(Globals.serialPort.isOpened())
                {
                    if(Globals.serialPort.getInputBufferBytesCount()!=-1)
                    {    
                        Platform.runLater(new Runnable() 
                        {
                            @Override
                            public void run() 
                            {
                                Globals.CarStatus.setId("ComputerCarStatusButtonOK");
                                Globals.connectButton.setId("disconnectButton");
                                Globals.connectButton.setText(LN.getString("settingsPage.disconnect"));
                                Globals.serialPortStatus.setText("");
                            }
                        });
                       
                        return true;
                    }
                    else
                    {
                        Platform.runLater(new Runnable() 
                        {
                            @Override
                            public void run() 
                            {
                                Globals.connectButton.setId("connectButton");
                                Globals.connectButton.setText(LN.getString("settingsPage.connect"));
                                Globals.CarStatus.setId("ComputerCarStatusButtonError");
                            }
                        });
                        return false;
                    }
                }
                else
                {
                    Platform.runLater(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                            Globals.connectButton.setId("connectButton");
                            Globals.connectButton.setText(LN.getString("settingsPage.connect"));
                            Globals.CarStatus.setId("ComputerCarStatusButtonError");
                        }
                    });
                    return false;
                }
            }
            else
            {
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        Globals.connectButton.setId("connectButton");
                        Globals.connectButton.setText(LN.getString("settingsPage.connect"));
                        Globals.CarStatus.setId("ComputerCarStatusButtonError");
                    }
                });
                return false;
            }
        }
        catch(Exception e)
        {
            disconnect();
            Globals.connectButton.setId("connectButton");
            Globals.connectButton.setText(LN.getString("settingsPage.connect"));
            Globals.CarStatus.setId("ComputerCarStatusButtonError");
            return false;
        }
    }
    /**
     * Read serial message
     **/
    static class SerialPortReader implements SerialPortEventListener 
     {
        private String tempMessage="";
        @Override
        public void serialEvent(SerialPortEvent event) 
        {
            if(event.isRXCHAR())
            {//If data is available
                int size=event.getEventValue();
                if( size>= 1)
                {
                    try 
                    {
                        byte buffer[] = Globals.serialPort.readBytes();
                        tempMessage=tempMessage+new String(buffer);
                        if(tempMessage.indexOf(">")!=-1)
                        {
                            Globals.serialMessage=tempMessage;
                            tempMessage="";
                            Globals.serialPortStatusFlag=3;
                        }
                    }
                    catch (SerialPortException ex) 
                    {
                        //System.out.println(ex);
                    }
                }
            }
        }
    }
}
