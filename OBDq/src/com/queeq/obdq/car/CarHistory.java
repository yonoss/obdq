/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author admin
 */
@XmlRootElement(namespace = "com.queeq.odbq.car")
@XmlAccessorType(XmlAccessType.FIELD)
public final class CarHistory 
{
     @XmlElementWrapper(name = "history")
     @XmlElement(name = "carHistoryEntry")
    private ArrayList<CarHistoryEntry> history=new ArrayList();

    public void setHistory(ArrayList<CarHistoryEntry> history) 
    {
	this.history = history;
    }
    public ArrayList<CarHistoryEntry> getHistory() 
    {
            return history;
    }
    
}
