/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.internationalization;

import com.queeq.obdq.settings.Globals;


/**
 *
 * @author admin
 */
public class LN 
{

      /**
     * Get String for key. If the string is not found, the key will be returned 
     * @param String key
     * @return String
     **/
    public static String getString(String key)
    {
        try
        {
            String val=Globals.curentrb.getString(key);
            return new String(val.getBytes("ISO-8859-1"), "UTF-8");
        }
        catch(Exception e)
        {
            return key;
        }
    }

}
