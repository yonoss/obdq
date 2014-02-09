/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.queeq.obdq.elm327;

/**
 *
 * @author admin
 */
public class Utils
{
    public static String[][] troubleCodeMap={{"0","P0"},
                                             {"1","P1"},
                                             {"2","P2"},
                                             {"3","P3"},
                                             {"4","C0"},
                                             {"5","C1"},
                                             {"6","C2"},
                                             {"7","C3"},
                                             {"8","B0"},
                                             {"9","B1"},
                                             {"A","B2"},
                                             {"B","B3"},
                                             {"C","U0"},
                                             {"D","U1"},
                                             {"E","U2"},
                                             {"F","U3"}};
    public Utils()
    {}
    /**Check for the size of the input argument**/
    public static boolean isOfSize(long input, long minSize, long maxSize)
    {
        boolean result;
        result = (input >= minSize) && (input <= maxSize);
        return result;
    }
    public static String getCodePrefix(String msb)
    {
        for(int i=0;i<troubleCodeMap.length;i++)
        {
            if(troubleCodeMap[i][0].equals(msb))
                return troubleCodeMap[i][1];
        }
        return "";
    }
    public static String cleanString(String input)
    {
        input=input.replaceAll("\r", "");
        input=input.replaceAll(" ","");
        input=input.replaceAll("\n","");
        return input;
    }
    public static String convertHexToString(String hex)
    {
	  StringBuilder sb = new StringBuilder();
	//  StringBuilder temp = new StringBuilder();
	  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
	  for( int i=0; i<hex.length()-1; i+=2 )
          {
 
	      //grab the hex in pairs
	      String output = hex.substring(i, (i + 2));
	      //convert hex to decimal
	      int decimal = Integer.parseInt(output, 16);
	      //convert the decimal to character
              if(decimal>=32)
                sb.append((char)decimal);
	     // temp.append(decimal);
	  }
	 // System.out.println("Decimal : " + temp.toString());
	  return sb.toString();
    }
    public static String convertHexToBinaryString(String Hex,boolean padding)
    {
        int size=Hex.length()*4;
        long i = Long.parseLong(Hex,16);
        String Bin = Long.toBinaryString(i);
        if(padding)
        {
            return getZeroPadding(size-Bin.length())+Bin;
        }
        else
            return Bin;
    }
    public static int BinStringToInt(String bin)
    {
        int i = Integer.parseInt(bin,2);
        return i;
    }
    public static int HexStringToInt(String bin)
    {
        int i = Integer.parseInt(bin,16);
        return i;
    }
    public static String IntToHexString(int val,int size)
    {
        String hexVal=Integer.toHexString(val);
        while(hexVal.length()<size)
        {
            hexVal="0"+hexVal;
        }
       return hexVal;
    }
    private static String getZeroPadding(int size)
    {
       String padding="";     
       for(int i=0;i<size;i++)
       {
           padding=padding+"0";
       }
       return padding;
    }
    /**
     * Convert Celsius to Fahrenheit
     **/
    public static double convertCelsisuToFahrenheit(double val)
    {
        return (val*9)/5+32;
    }
    /**
     * Convert km to miles
     **/
    public static double convertKmToMiles(double val)
    {
        return val* 0.62137;
    }
    /**
     *Convert kilo Pascals to pounds per square inch
     **/
    public static double convertkPAtoPSI(double val)
    {
        return val*0.14503773773020923;
    }
    /**
     * Convert gram to ounce
     **/
    public static double convertGtoOZ(double val)
    {
        return val*0.035274;
    }
    
}
