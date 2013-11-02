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
public final class Causes
{   
    @XmlElement(name = "cause")
    private ArrayList<Cause> causes=new ArrayList();
    
    public void setCauses(ArrayList<Cause> causes) 
    {
	this.causes = causes;
    }
    public ArrayList<Cause> getCauses() 
    {
            return causes;
    }
}