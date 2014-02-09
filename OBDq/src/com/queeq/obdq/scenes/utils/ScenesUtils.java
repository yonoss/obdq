/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.utils;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author admin
 */
public class ScenesUtils 
{
    private static final NumberFormat twoDp = new DecimalFormat("0");
    
    public static String getIntegerValuec(double value)
    {
        return twoDp.format(value);
    }
}
