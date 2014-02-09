/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@XmlRootElement(namespace = "com.queeq.odbq.scenes.utils")
@XmlAccessorType(XmlAccessType.FIELD)
public class PIDList 
{
     @XmlElementWrapper(name = "PIDList")
	// XmlElement sets the name of the entities
    @XmlElement(name = "PID")
    private ArrayList<PID> PIDList=new ArrayList();
    
    public void setPIDList(ArrayList<PID> PIDList) 
    {
	this.PIDList = PIDList;
    }
    public ArrayList<PID> getPIDList() 
    {
            return PIDList;
    }
}
