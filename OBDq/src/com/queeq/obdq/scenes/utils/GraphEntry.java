/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;

import java.util.HashMap;

/**
 *
 * @author admin
 */
public class GraphEntry 
{
    private HashMap entry=new HashMap();
    public GraphEntry()
    {}
    public HashMap getEntry(double time, int timeIndex, double value, int valueIndex)
    {
        entry.put(timeIndex, time);
        entry.put(valueIndex, value);
        return entry;
    }
    public HashMap getEntry(String entryline)
    {
        String[] entries=entryline.split(",");
        for(int i=0;i<entries.length;i++)
        {
            entry.put(i, Double.parseDouble(entries[i]));
        }
        return entry;
    }
    
    
}
