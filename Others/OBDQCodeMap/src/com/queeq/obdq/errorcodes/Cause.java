/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.errorcodes;


import com.queeq.obdq.errorcodes.utils.Entry;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author admin
 */
@XmlRootElement(name = "cause")
public class Cause extends Entry 
{    
    public Cause()
    {
       super();     
    }
    public Cause(String messVal,String ratPlsVal, String ratMinVal, String ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Cause(String messVal,int ratPlsVal, int ratMinVal, int ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Cause(String messVal)
    {
        super(messVal);
    }
}
