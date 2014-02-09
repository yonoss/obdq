/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.elm327.ResponseObject;
import com.queeq.obdq.elm327.iso15031.ISO15031;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.utils.DataObject;
import com.queeq.obdq.scenes.utils.PID;
import com.queeq.obdq.scenes.view.SensorsDataPage;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.serial.utils.SerialUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
 * @author admin
 */
public class SensorsDataPageController 
{
     public SensorsDataPageController()
     {}
     /**
      * Update PIDs table method
      * @param page on which the method will be triggered
      **/
     public static void updateTable(Class page) throws Exception
     {
        //read PID as long as we are on the SensorsDataPage
        if(!Globals.isApplicationTerminated&&page==Globals.currentPage&&!Globals.readPID.getText().equals(LN.getString("sensorsPage.readPID")))
        {
            Platform.runLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                    Globals.dataView.setItems(null); 
                    Globals.dataView.layout(); 
                    ObservableList<PID> tdata= Globals.dataView.getSortOrder();
                    Globals.dataView.setItems(Globals.data.getSensorDataObject());   
                    Globals.dataView.getSortOrder().addAll(tdata); 
                    
                    
                   /* Globals.sensorsView.setItems(null); 
                    Globals.sensorsView.layout(); 
                    ObservableList<PID> tsensors= Globals.sensorsView.getSortOrder();
                    Globals.sensorsView.setItems(Globals.data.getsensorIDsObject());   
                    Globals.sensorsView.getSortOrder().addAll(tsensors); */
                }
            });   
        }
        else
        {
            throw new Exception("Stop PID reading");
        }
    }
     public static void updateTableContent() throws Exception
     {
         Platform.runLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                    Globals.dataView.setItems(null); 
                    Globals.dataView.layout(); 
                    ObservableList<PID> tdata= Globals.dataView.getSortOrder();
                    Globals.dataView.setItems(Globals.data.getSensorDataObject());   
                    Globals.dataView.getSortOrder().addAll(tdata); 
                    
                    
                    Globals.sensorsView.setItems(null); 
                    Globals.sensorsView.layout(); 
                    ObservableList<PID> tsensors= Globals.sensorsView.getSortOrder();
                    Globals.sensorsView.setItems(Globals.data.getsensorIDsObject());   
                    Globals.sensorsView.getSortOrder().addAll(tsensors); 
                }
            });
     }
     /**
      * read sensor data method. Will be executed as long as the user will stay on the SensorsDataPage or a cancel operation will be issued.
      **/
    public static void readSensorData()
    {
        if(SerialUtils.isSerialPortOpened()&&Globals.readPID.getText().equals(LN.getString("sensorsPage.readPID")))
        {
            Globals.readPID.setText(LN.getString("sensorsPage.stopReadingPID"));
            Task task = new Task<Void>() 
            {
                @Override public Void call() throws InterruptedException 
                {
                    try
                    {
                        Class[] parameterTypes = new Class[1];
                        parameterTypes[0] =Class.class;
                        Method method1 = SensorsDataPageController.class.getMethod("updateTable", parameterTypes);

                        Object[] param=new Object[1];
                        param[0]=SensorsDataPage.class;

                      //  SensorsDataPageController classInstance=new SensorsDataPageController();
                    //    int cnt=0;
                        while(true)
                        {
                            readPIDs(Globals.data,method1,null,param); 
                        //    cnt++;
                           // Thread.sleep(500);
                        }  
                    }
                    catch(Exception e)
                    {
                        //stop PID read process
                         Globals.readPID.setText(LN.getString("sensorsPage.readPID"));
                         Globals.data=new DataObject();
                    }
                    return null;
                }
            };
            new Thread(task).start(); 
        }
        else if(Globals.readPID.getText().equals(LN.getString("sensorsPage.stopReadingPID")))
        {
            Globals.readPID.setText(LN.getString("sensorsPage.readPID"));
        }
    }
    
     public static void readPIDs(DataObject data,Method refreshGrid,Object classInstance,Object[] args) throws Exception
        {
            ArrayList<PID> dataToRead= Globals.data.getData();
            for(int i=0;i<dataToRead.size();i++)
            {
                PID tempPID=dataToRead.get(i);
                if(tempPID.getIsSelectedOnReadCodesPage()){
                    ResponseObject result=new ResponseObject();
                    try
                    {
                        Class[] parameterTypes = new Class[3];
                        parameterTypes[0]=String.class;
                        parameterTypes[1]=int.class;
                        parameterTypes[2]=String.class;
                        Method getPIDValue = ISO15031.class.getMethod("PID"+tempPID.getId(), parameterTypes);

                        Object[] param=new Object[3];
                        param[0]=tempPID.getResponseKey();
                        param[1]=ObdqProperties.defaultMeasurmentSystem;
                        param[2]=tempPID.getUnit();
                        result=(ResponseObject)getPIDValue.invoke(null, param);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    Globals.data.setSensorData(tempPID.getId(),result);
                    refreshGrid.invoke(classInstance, args);
                }
            }
        }
       /* public static void initSenesorsReader() throws Exception
        {
           Globals.data.setData(PIDManager.getPIDList());
        }*/
       
}
