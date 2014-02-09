/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.view.SettingsPage;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.serial.utils.SerialUtils;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author admin
 */
public class SettingPageController 
{
    public static void getSelectCOMPorts(ChoiceBox selectPort)
    {
        String[] ports = SerialUtils.getOpenCOMPorts();
        int portsSize=ports.length;
        if (portsSize==0)
        {
            selectPort.getItems().add(LN.getString("settingsPage.noCOMPort"));
            selectPort.setValue(LN.getString("settingsPage.noCOMPort"));
        }
        else if(portsSize==1)
        {
            selectPort.getItems().add(ports[0]);
            selectPort.setValue(ports[0]);
        }
        else
        {
            for(int i=0;i<portsSize;i++)
            {
                selectPort.getItems().add(ports[i]);
            }
            selectPort.getItems().add(LN.getString("settingsPage.selectCOMPort"));
            selectPort.setValue(LN.getString("settingsPage.selectCOMPort"));
        }

    }
    public static void getSelectBaudRate(ChoiceBox selectBaudRate)
    {
        String[] baudRates = ObdqProperties.baudRates.split(",");
        int ratesSize=baudRates.length;
        if (ratesSize==0)
        {
            selectBaudRate.getItems().add(LN.getString("settingsPage.noBaudRate"));
            selectBaudRate.setValue(LN.getString("settingsPage.noBaudRate"));
        }
        else if(ratesSize==1)
        {
            selectBaudRate.getItems().add(baudRates[0]);
            selectBaudRate.setValue(baudRates[0]);
        }
        else
        {
            for(int i=0;i<ratesSize;i++)
            {
                selectBaudRate.getItems().add(baudRates[i]);
            }
            if(!ObdqProperties.defaultBaudRate.equals(""))
                 selectBaudRate.setValue(ObdqProperties.defaultBaudRate);
            else
            {
                selectBaudRate.getItems().add(LN.getString("settingsPage.selectBaudRate"));
                selectBaudRate.setValue(LN.getString("settingsPage.selectBaudRate"));
            }
        }

    }
    public static void connectButtonClick( String connectButtonID,final String COMPort,final String BaudRate)
    {
        if(!COMPort.equals(LN.getString("settingsPage.selectCOMPort"))&&!COMPort.equals(LN.getString("settingsPage.noCOMPort")))
        {
            if(!COMPort.equals(LN.getString("settingsPage.selectBaudRate"))&&!COMPort.equals(LN.getString("settingsPage.noBaudRate")))
            {
                if(connectButtonID.equals("connectButton"))
                {
                    Task task = new Task<Void>() 
                    {
                        @Override public Void call() throws InterruptedException 
                        {
                            SerialUtils.connect(COMPort,Integer.valueOf(BaudRate));
                            Platform.runLater(new Runnable() 
                            {
                                @Override
                                public void run() 
                                {
                                      Globals.connectButtonClicked=false;;
                                }
                            });
                            return null;
                        }

                    };
                    new Thread(task).start();
                }
                else if(connectButtonID.equals("disconnectButton"))
                {
                    SerialUtils.disconnect();
                    SceneDispatcher.getScene(SettingsPage.class,Globals.root,Globals.pane);
                    Globals.connectButtonClicked=false;
                }
            }
            else
            {
                Globals.serialPortStatus.setText(LN.getString("settingsPage.selectBaudRate"));
                  Globals.connectButtonClicked=false;
            }
        }
        else
        {
            Globals.serialPortStatus.setText(LN.getString("settingsPage.selectCOMPort"));
             Globals.connectButtonClicked=false;
        }
      
    }
    public static void onMetricSelected()
    {
         ObdqProperties.defaultMeasurmentSystem=ObdqProperties.Metric;
         ObdqProperties.storePropertiesToFile();
    }
    public static void onImperialSelected()
    {
         ObdqProperties.defaultMeasurmentSystem=ObdqProperties.Imperial;
         ObdqProperties.storePropertiesToFile();
    }
}
