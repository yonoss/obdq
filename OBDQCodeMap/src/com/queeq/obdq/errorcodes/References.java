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
public final class References
{   
    @XmlElement(name = "reference")
    private ArrayList<Reference> reference=new ArrayList();
    
    public void setReferences(ArrayList<Reference> reference) 
    {
	this.reference = reference;
    }
    public ArrayList<Reference> getReferences() 
    {
            return reference;
    }
}