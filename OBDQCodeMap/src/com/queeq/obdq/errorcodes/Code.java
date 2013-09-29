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

@XmlRootElement(namespace = "com.queeq.odbq.errorcodes")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Code
{   
    @XmlAttribute(name="id")
    private String id="";
    private Meanings meanings=new Meanings();
    private Causes causes=new Causes();
    private Descriptions descriptions=new Descriptions();
    private Solutions solutions=new Solutions();
    private References references=new References();
    private Symptoms symptoms=new Symptoms();
    @XmlTransient
    private String addedBy="";
    @XmlTransient
    private String validateBy="";
    @XmlTransient
    private String language="";
    @XmlAttribute(name="cars")
    private String codeForCar="";
     public void setId(String val)
    {
       id=val; 
    }
    public String getId()
    {
       return id; 
    }
    public void setMeanings(Meanings meanings) 
    {
	this.meanings = meanings;
    }
    public Meanings getMeanings() 
    {
            return meanings;
    }
    public void setCauses(Causes causes) 
    {
	this.causes = causes;
    }
    public Causes getCauses() 
    {
            return causes;
    }
     public void setDescriptions(Descriptions descriptions) 
    {
	this.descriptions = descriptions;
    }
    public Descriptions getDescriptions() 
    {
            return descriptions;
    }
     public void setSolutions(Solutions solutions) 
    {
	this.solutions = solutions;
    }
    public Solutions getSolutions() 
    {
            return solutions;
    }
    public void setSymptoms(Symptoms symptoms) 
    {
	this.symptoms = symptoms;
    }
    public Symptoms getSymptoms() 
    {
            return symptoms;
    }
     public void setReferences(References references) 
    {
	this.references = references;
    }
    public References getReferences() 
    {
            return references;
    }
     public void setAddBy(String val) 
    {
	this.addedBy = val;
    }
    public String getAddBy() 
    {
            return addedBy;
    }
     public void setValidateBy(String val) 
    {
	this.validateBy = val;
    }
    public String getValidateBy() 
    {
            return validateBy;
    }
     public void setLanguage(String val) 
    {
	this.language = val;
    }
    public String getLanguage() 
    {
            return language;
    }
    public void setCodeForCar(String val) 
    {
	this.codeForCar = val;
    }
    public String getCodeForCar() 
    {
            return codeForCar;
    }
}