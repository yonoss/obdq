/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import com.queeq.obdq.settings.Globals;

/**
 *
 * @author admin
 */
public class HistoryEvenManager 
{
    public HistoryEvenManager()
    {
    }
    public void CarAddedEvent(Car carAdded)
    {
         Globals.historyManager.addToCarHistory(new CarHistoryEntry("history.event.carAddedTitle", "history.event.carAddedDescription", System.currentTimeMillis()),carAdded.getVIN(),true);
    }
    public void CarEditedEvent(Car carEdited)
    {
        Globals.historyManager.addToCarHistory(new CarHistoryEntry("history.event.carEditedTitle", "history.event.carEditedDescription", System.currentTimeMillis()),carEdited.getVIN(),true);
            
    }
    
    
}
