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
@XmlRootElement(name = "solution")
public class Solution extends Entry
{
     public Solution()
    {
       super();     
    }
    public Solution(String messVal, String ratPlsVal, String ratMinVal, String ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Solution(String messVal,int ratPlsVal, int ratMinVal, int ratNeuVal)
    {
        super(messVal,ratPlsVal,ratMinVal,ratNeuVal);
    }
    public Solution(String messVal)
    {
        super(messVal);
    } 
}
