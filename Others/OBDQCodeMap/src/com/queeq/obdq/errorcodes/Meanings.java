/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.errorcodes;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Meanings
{   
    @XmlElement(name = "meaning")
    private ArrayList<Meaning> meanings=new ArrayList();
    
    public void setMeanings(ArrayList<Meaning> meanings) 
    {
	this.meanings = meanings;
    }
    public ArrayList<Meaning> getMeanings() 
    {
            return meanings;
    }
}