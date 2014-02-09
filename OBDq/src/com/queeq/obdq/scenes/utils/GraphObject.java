/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import com.queeq.obdq.utils.SystemUtils;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class GraphObject 
{
    private ArrayList<String> headers=new ArrayList<String>();
    private ArrayList<String> units=new ArrayList<String>();
    private HashMap checkboxStatus=new HashMap();
    private ArrayList<HashMap> series=new ArrayList<HashMap>();
    
    public GraphObject()
    {
    }
    public GraphObject(DataObject data)
    {
        ArrayList<PID> tmpData=data.getData();
        for(int i=0;i<tmpData.size();i++)
        {
            PID tmpPID=tmpData.get(i);
            if(tmpPID.getOnGraphsPage())
            {
                setHeader(tmpPID.getLable());
                setUnit(tmpPID.getUnit());
                setCheckboxStatus(tmpPID.getId(),tmpPID.getIsSelectedOnGraphsCodesPage());
            }
        }
    }
    public GraphObject(String filePath)
    {
        
        try
            {
                FileInputStream fstream = new FileInputStream(filePath);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                int cnt=0;
                while ((strLine = br.readLine()) != null)   
                {
                    if(cnt==0)
                    {
                        setHeaders(strLine);
                        setCheckboxesStatus(strLine);
                    }
                    else if(cnt==1)
                    {
                        setUnits(strLine);
                    }
                    else
                    {
                        setSeriesEntry(strLine);
                    }
                    cnt++;
                }
                in.close();
            }
            catch (Exception e)
            {
                System.err.println("Error in GraphObject(String filePath) constructor: " + e.getMessage());
            }

    }
    public void setCheckboxesStatus(String headersLine)
    {
        String[] hArray=headersLine.split(",");
        for(int i=1;i<hArray.length;i++)
        {
            checkboxStatus.put(hArray[i], Boolean.TRUE);
        }
    }
    public void setCheckboxStatus(String header,boolean status)
    {
        checkboxStatus.put(header,status);
    }
    public HashMap getCheckboxesStatus()
    {
        return checkboxStatus;
    }
    public ArrayList<HashMap> getSeries()
    {
        return series;
    }

    public void setSeriesEntry(String entryLine)
    {
        GraphEntry entry=new GraphEntry();
        series.add(entry.getEntry(entryLine));
    }
    public void setHeaders(String headersLine)
    {
        String[] hArray=headersLine.split(",");
        headers=new ArrayList(Arrays.asList(hArray)); 
    }
    public void setHeader(String header)
    {
        headers.add(header);
    }
    public ArrayList<String> getHeaders()
    {
        return headers;
    }
    public void setUnits(String unitsLine)
    {
         String[] uArray=unitsLine.split(",");
         units=new ArrayList(Arrays.asList(uArray)); 
    }
    public void setUnit(String unit)
    {
        units.add(unit);
    }
    public ArrayList<String> getUnits()
    {
        return units;
    }
    public int getHeaderIndexForID(String ID)
    {
        for(int i=0;i<headers.size();i++)
        {
            if(headers.get(i).equals(ID))
            {
                return i;
            }
        }
        return -1;
    }
    public String getHeaderIDForIndex(int index)
    {
        return headers.get(index);
    }
     public int getUnitIndexForID(String ID)
    {
        for(int i=0;i<units.size();i++)
        {
            if(units.get(i).equals(ID))
            {
                return i;
            }
        }
        return -1;
    }
    public String getUnitIDForIndex(int index)
    {
        return headers.get(index);
    }
    /***
     *save graph content to file
     * @param String filePath
     * @param String data 
     **/
    public void saveToFile(String filePath)
    {
        if(filePath.toLowerCase().endsWith(".csv"))
            SystemUtils.writeToFile(filePath, this.serializeForSaveing());
        else
            SystemUtils.writeToFile(filePath+".csv", this.serializeForSaveing());
    }
    public String serializeForSaveing()
    {
        String result="";
        result=serializeHeadersList(headers);
        result=result+"\n"+serializeHeadersList(units);
        result=result+"\n"+serializeSeries(series);
        return result;
    }
     private String serializeSeries(ArrayList<HashMap> list)
    {
        String result="";
        for(int i=0;i<list.size();i++)
        {
            if(i==0)
                result=serializeSerie(list.get(i));
            else
                result=result+"\n"+serializeSerie(list.get(i)); 
        }    
        return result;
    }
     private String serializeSerie(HashMap serie)
     {
         String result="";
         for(int i=0;i<serie.size();i++)
         {
             if(i==0)
                result=""+serie.get(i);
             else
                result=result+","+serie.get(i); 
         }
         return result;
     }
    private String serializeHeadersList(ArrayList<String> list)
    {
        String result="";
        for(int i=0;i<list.size();i++)
        {
            if(i==0)
                result=result+list.get(i);
            else
                result=result+","+list.get(i);
        }
        return result;
    
    }
}
