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
public final class Descriptions
{   
    @XmlElement(name = "description")
    private ArrayList<Description> descriptions=new ArrayList();
    
    public void setDecriptions(ArrayList<Description> descriptions) 
    {
	this.descriptions = descriptions;
    }
    public ArrayList<Description> getDecriptions() 
    {
            return descriptions;
    }
    
  
}
