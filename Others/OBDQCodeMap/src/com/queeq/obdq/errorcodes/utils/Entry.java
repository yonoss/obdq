/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.errorcodes.utils;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author admin
 */
public class Entry implements Serializable 
{
    private String value="";   
    private String ratingPlus="";  
    private String ratingMinus="";
    private String ratingNeutral="";
    
    public Entry()
    {}
    public Entry(String messVal,String ratPlsVal, String ratMinVal, String ratNeuVal)
    {
        value=messVal;;
        ratingPlus=ratPlsVal;
        ratingMinus=ratMinVal;
        ratingNeutral=ratNeuVal;
    }
    public Entry(String messVal, int ratPlsVal, int ratMinVal, int ratNeuVal)
    {
        value=messVal;
        ratingPlus=String.valueOf(ratPlsVal);
        ratingMinus=String.valueOf(ratMinVal);
        ratingNeutral=String.valueOf(ratNeuVal);
    }
    public Entry(String messVal)
    {
        value=messVal;
        ratingPlus="0";
        ratingMinus="0";
        ratingNeutral="0";
    }
    @XmlValue
    public void setValue(String val)
    {
       value=val; 
    }
    public String getValue()
    {
       return value; 
    }
    @XmlTransient//(name="rtPlus")
      public void setRatingPlus(String val)
    {
       ratingPlus=val; 
    }
    public String getRatingPlus()
    {
       return ratingPlus; 
    }
    @XmlTransient//(name="rtMinus")
      public void setRatingMinus(String val)
    {
       ratingMinus=val; 
    } 
    public String getRatingMinus()
    {
       return ratingMinus; 
    }
    @XmlTransient//(name="rtNeutral")
    public void setRatingNeutral(String val)
    {
       ratingNeutral=val; 
    } 
    public String getRatingNeutral()
    {
       return ratingNeutral; 
    }

}