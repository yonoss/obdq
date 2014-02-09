/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.car;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;



/**
 *
 * @author admin
 */
@XmlRootElement(namespace = "com.queeq.odbq.car")
@XmlAccessorType(XmlAccessType.FIELD)
public final class CarsList
{   
    @XmlElementWrapper(name = "carsList")
    @XmlElement(name = "car")
    private ArrayList<Car> carsList=new ArrayList();
    
    public void setCarsList(ArrayList<Car> carsList) 
    {
	this.carsList = carsList;
    }
    public ArrayList<Car> getCarsList() 
    {
            return carsList;
    }
    
  
}
