/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@XmlRootElement(name = "carHistoryEntry")
public class CarHistoryEntry 
{
    
    private String eventTitle="";
    private String eventDescription="";
    private long eventDate=0;
    public CarHistoryEntry()
    {
    }
     public CarHistoryEntry(String EventTitleValue,
                       String EventDescriptionValue,
                       long EventDateValue)
    {
        eventTitle=EventTitleValue;
        eventDescription=EventDescriptionValue;
        eventDate=EventDateValue;
    }
   @XmlElement(name = "eventTitle")
     public void setEventTitle(String eventTitleValue)
    {
       eventTitle=eventTitleValue; 
    }
    public String getEventTitle()
    {
       return eventTitle; 
    }
     public void setEventDescription(String eventDescriptionValue)
    {
       eventDescription=eventDescriptionValue; 
    }
    public String getEventDescription()
    {
       return eventDescription; 
    }
     public void setEventDate(long eventDateValue)
    {
       eventDate=eventDateValue; 
    }
    public long getEventDate()
    {
       return eventDate; 
    }
     
     
}
