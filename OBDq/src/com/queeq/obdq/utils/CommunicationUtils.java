/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.utils;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.utils.DataObject;
import com.queeq.obdq.scenes.utils.PIDManager;
import com.queeq.obdq.settings.Globals;
import javafx.application.Platform;

/**
 *
 * @author admin
 */
public class CommunicationUtils {
    public static void onConnectionEstablished() throws Exception{
        Globals.currentCar=Globals.selectedCar;
        setStatus(LN.getString("getPIDsList"));
        Globals.data.setData(PIDManager.getPIDList());
        setStatus(LN.getString("systemReady"));
    }
    public static void onConnectionClosed()
    {
        Globals.serialPort=null;
        Globals.currentCar=null;
        Globals.currentConnectionVIN="";
        Globals.data=new DataObject();
    }
    public static void setStatus(final String status)
    {
        Platform.runLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                Globals.serialPortStatus.setText(status);
            }
        });
    }
}
