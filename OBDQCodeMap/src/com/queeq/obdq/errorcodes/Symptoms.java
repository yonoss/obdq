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
public final class Symptoms
{   
    @XmlElement(name = "symptom")
    private ArrayList<Symptom> symptoms=new ArrayList();
    
    public void setSymptoms(ArrayList<Symptom> symptoms) 
    {
	this.symptoms = symptoms;
    }
    public ArrayList<Symptom> getSymptoms() 
    {
            return symptoms;
    }
}