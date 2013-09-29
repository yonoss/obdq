/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.errorcodes;

import com.queeq.obdq.errorcodes.utils.Entry;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author admin
 */
@XmlRootElement(name = "meaning")
public class Meaning extends Entry
{
    public Meaning()
    {
       super();     
    }
    public Meaning(String messVal, String ratPlsVal, String ratMinVal, String ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Meaning(String messVal,int ratPlsVal, int ratMinVal, int ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Meaning(String messVal)
    {
        super(messVal);
    } 
}
