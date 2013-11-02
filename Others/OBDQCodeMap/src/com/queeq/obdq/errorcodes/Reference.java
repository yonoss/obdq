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
@XmlRootElement(name = "reference")
public class Reference extends Entry 
{
     public Reference()
    {
       super();     
    }
    public Reference(String messVal,String ratPlsVal, String ratMinVal, String ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Reference(String messVal,int ratPlsVal, int ratMinVal, int ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Reference(String messVal)
    {
        super(messVal);
    } 
}
