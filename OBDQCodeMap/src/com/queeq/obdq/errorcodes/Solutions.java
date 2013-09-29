/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.errorcodes;

import com.queeq.obdq.errorcodes.Solution;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author admin
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Solutions
{   
    @XmlElement(name = "solution")
    private ArrayList<Solution> solutions=new ArrayList();
    
    public void setSolutions(ArrayList<Solution> solutions) 
    {
	this.solutions = solutions;
    }
    public ArrayList<Solution> getSolutions() 
    {
            return solutions;
    }
    
  
}
