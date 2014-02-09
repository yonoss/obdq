/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.elm327;

/**
 *
 * @author admin
 */
public class ResponseObject {
    private String stringValue="";
    private double doubleValue;
    private String unit="";
    private String key="";
    
    public ResponseObject(){
    }
    public void setStringValue(String value){
        this.stringValue=value;
    }
    public String getStringValue(){
        return this.stringValue;
    }
    public void setDoubleValue(double value){
        this.doubleValue=value;
    }
    public double getDoubleValue(){
        return this.doubleValue;
    }
    public void setUnit(String value){
        this.unit=value;
    }
    public String getUnit(){
        return this.unit;
    }
    public void setKey(String value){
        this.key=value;
    }
    public String getKey(){
        return this.key;
    }
}
