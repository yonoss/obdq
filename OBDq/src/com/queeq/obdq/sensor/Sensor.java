/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.sensor;

/**
 *
 * @author admin
 */
public class Sensor 
{
    private String label="";
    private String pid="";
    private int resultIndex=-1;
    private String unit="";
    private boolean read=false;
    public Sensor()
    {}
    
    public void setLabel(String lable)
    {
        this.label=lable;
    }
    public String getLabel()
    {
        return this.label;
    }
     public void setPID(String pid)
    {
        this.pid=pid;
    }
    public String getPID()
    {
        return this.pid;
    }
     public void setResultIndex(int resultIndex)
    {
        this.resultIndex=resultIndex;
    }
    public int getResultIndex()
    {
        return this.resultIndex;
    }
    public void setUnit(String unit)
    {
        this.unit=unit;
    }
    public String getUnit()
    {
        return this.unit;
    }
     public void setRead(boolean read)
    {
        this.read=read;
    }
    public boolean getRead()
    {
        return this.read;
    }
}
