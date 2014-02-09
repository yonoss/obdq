/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import com.queeq.obdq.elm327.iso15031.ISO15031;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author admin
 */
public class PIDManager 
{
    public PIDManager()
    {}
    /**
     *returns the available PIDs list
     * @return ArrayList
     **/
    public static ArrayList<PID> getPIDList() throws Exception
    {
        ArrayList<PID> result=new ArrayList(); 
        if(Globals.currentCar!=null)
        {
            result=Globals.currentCar.getPIDList();
            if(result.size()<=0)
            {
                result=getPIDListFromCar();
                Globals.currentCar.setPIDList(result);
                Globals.carsList.updateCarToList(Globals.currentCar, true);
            }     
        }
        return result;
    }
    /**
     * Fetches the PID list from the car
     * @return ArrayList
     **/
    private static ArrayList<PID> getPIDListFromCar() throws Exception
    {
        ArrayList<PID> result=new ArrayList(); 
        HashMap pids=ISO15031.getAvailablePIDList();
        ArrayList<PID> pidList=loadPIDList().getPIDList();
        
        for(int i=0;i<pidList.size();i++)
        {
            try
            {
                PID tmpPID=pidList.get(i);
                if((Boolean)pids.get(tmpPID.getId().toLowerCase()))
                {
                    result.add(tmpPID);
                }
            }catch(Exception e){
            System.out.println(e.getMessage());
            }
        }
        return result;
    }    
     /**
     * Serialize and stores the PIDList into the data file
     **/
    public static void storePIDList(PIDList Obj) throws Exception
    {        
        JAXBContext context = JAXBContext.newInstance(PIDList.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Writer w = null;
        try
        {
            w = new FileWriter(ObdqProperties.workingDirectoryPath+ObdqProperties.pidListBinPath,false);
            m.marshal(Obj, w);
        } finally
        {
            try
            {
                w.close();
            }
            catch (Exception e)
            {
            }
        }
    }
    /**
     * Retrieves the carsList from the data file
     **/
    public static PIDList loadPIDList() throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(PIDList.class);
        Unmarshaller um = context.createUnmarshaller();
        PIDList result = (PIDList) um.unmarshal(new FileReader(ObdqProperties.workingDirectoryPath+ObdqProperties.pidListBinPath));

        return result;
    }    
    
}
