/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.queeq.obdq.elm327;
import com.queeq.obdq.internationalization.LN;
import com.queeq.serial.utils.SerialUtils;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Others
{
    int countCodesSubstract=128; //80 HEX
     public Others()
    {
    }
     public static boolean initializeELM() throws Exception
     {
        OBDCommands.setProtocolToAutoAndSaveIt(0);
        if(getSouportedPIDs().length()==8)
            return true;
        else
            return false;
        
     }
      /**read the IgnMon input level**/
      public String readIgnMonInputLevel()
    {
         return SerialUtils.getSyncSerialResponseForCommand("AT IGN");
    }
    /**Returns the number of error codes**/
    public static int countTroubleCodes()
    {
        int countCodesSubstract=128; //80 HEX
        String responseHeader="4101";
        
        String countResponse=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 01"));
        int header=countResponse.indexOf(responseHeader);
        if(header==-1)
            return 0;
        countResponse=countResponse.replace(responseHeader, "");
        countResponse=countResponse.substring(0,2);
        int response=Integer.parseInt(countResponse,16);
        if(response==0)
            return 0;
        else
        {
            int partialResponse=response-countCodesSubstract;
            if(partialResponse<0)
                return response;
            else
                return partialResponse;
        }
    }
    /**Returns the car error codes**/
    public static void getTroubleCodes(ArrayList<String> list)
    {
        int DTCNumber=countTroubleCodes();
        if(DTCNumber>0)
        {
            String responseHeader="43";
            String countResponse=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("03"));
            if(countResponse.indexOf(responseHeader)!=-1)
            {
                countResponse=countResponse.replace(responseHeader, "");

                for(int i=0;i<DTCNumber;i++)
                {
                    String countResponseTmp=countResponse.substring(i*4+0, i*4+4);
                    String msb=countResponseTmp.substring(0,1);
                    String codeTail=countResponseTmp.substring(1,4);
                    list.add(Utils.getCodePrefix(msb) +codeTail);
                }
            }
            else 
                list.add(LN.getString("errorCodesPage.NoCode"));
        }
        else 
            list.add(LN.getString("errorCodesPage.NoCode"));
    }
    /**resets the trouble code**/
    public static boolean resetTroubleCodes()
    {
        String countResponse=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("04"));
        if(countResponse.equals("44"))
            return true;
        else
            return false;
    }
    public static String getSouportedPIDs()
    {
        String responseHeader="4100";
        String countResponse=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("01 00"));
        int header=countResponse.indexOf(responseHeader);
        if(header==-1)
            return "";
        countResponse=countResponse.substring(header);
        countResponse=countResponse.replace(responseHeader, "");
        return countResponse;
    }
     public static String getVIN()
    {
        String responseHeader="4902";
        String countResponse=Utils.cleanString(SerialUtils.getSyncSerialResponseForCommand("09 02"));
         int header=countResponse.indexOf(responseHeader);
        if(header==-1)
            return "";
        countResponse=countResponse.substring(header);
        countResponse=countResponse.replace(responseHeader, "");
        return Utils.convertHexToString(countResponse);
    }
}
