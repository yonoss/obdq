/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.utils;

import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.Date;
import java.util.TimeZone;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author admin
 */
public class SystemUtils 
{
    /***
     * write String to the specified file
     * @param file - String representing the file name
     * @param  data - String content to be written in to the file
     **/
    public static void writeToFile(String file, String data)
    {
		try
		{
			  // Create file 
			  FileWriter fstream = new FileWriter(file,true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(data);
			  //Close the output stream
			  out.close();
		}
		catch (Exception e)
		{//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		}
     }
    /**
     * Get the file content
     * @param file - name of the file
     * @return String file content
     **/
    public static String readFromFile(String file)
    {
            String result="";

            try
            {
                        // Open the file that is the first 
                        // command line parameter
                        FileInputStream fstream = new FileInputStream(file);
                        // Get the object of DataInputStream
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String strLine;
                        //Read File Line By Line
                        while ((strLine = br.readLine()) != null)   
                        {
                                result+=strLine;
                        }
                        //Close the input stream
                        in.close();
            }
            catch (Exception e)
            {//Catch exception if any
                        System.err.println("Error: " + e.getMessage());
            }	
            return result;
    }
    /**
     * Converts current time stamp to GMT
     * @param date - the current date
     * @return Date GMT value
     **/
    public static Date cvtToGmt( Date date )
    {
        TimeZone tz = TimeZone.getDefault();
        Date ret = new Date( date.getTime() - tz.getRawOffset() );

        // if we are now in DST, back off by the delta.  Note that we are checking the GMT date, this is the KEY.
        if ( tz.inDaylightTime( ret ))
        {
            Date dstDate = new Date( ret.getTime() - tz.getDSTSavings() );

            // check to make sure we have not crossed back into standard time
            // this happens when we are on the cusp of DST (7pm the day before the change for PDT)
            if ( tz.inDaylightTime( dstDate ))
            {
                ret = dstDate;
            }
        }

        return ret;
    }
     /**
	 * Returns the list of the file names having the extension specified, available in the folder mentioned in the directoryPath.
	 * @param directoryPath - the queried directory
	 * @param extension - the file extension
	 * @return files name list
	 * **/
	public static String[] getFolderFilesNames(String directoryPath,String extension)
	{
		final String ext=extension; 
		File dir = new File(directoryPath);

		String[] children = null;

		FilenameFilter filter = new FilenameFilter() 
		{
		    public boolean accept(File dir, String name) 
		    {
		        return name.endsWith(ext);
		    }
		};
		children = dir.list(filter);
		if(children==null)
                    return new String[0];
                else
                    return children;
	}
    /**
     * Open a browser view
     * @param url - the  String URL to be opened in the browser view
     **/    
    public static void openLink(String url)
    {
        TextField urlVal=new TextField();
        urlVal.setText(url);
        urlVal.setPrefHeight(20);
        urlVal.setEditable(false);
        urlVal.setId("urlValue");
        
        final WebView browser = new WebView();
        browser.setContextMenuEnabled(true);
        final WebEngine webEngine = browser.getEngine();
       
        
        VBox br=new VBox();
        br.getChildren().addAll(urlVal,browser);
        
        Stage browserStage=new Stage();
            Scene brScene=new Scene(br);
        browserStage.setScene(brScene);
        browserStage.show();
        webEngine.load(url);
    }
    /**
     * Returns the unified hardware address (usually MAC) of the interfaces if it has one and if it can be accessed given the current privileges.
     **/
    public static String getUnifiedMacAddress()
    {
         String result="";
         try 
         {
            Enumeration<NetworkInterface> nwInterface = NetworkInterface.getNetworkInterfaces();
            while (nwInterface.hasMoreElements()) 
            {
                NetworkInterface nis = nwInterface.nextElement();
                if (nis != null) 
                {
                    byte[] mac = nis.getHardwareAddress();
                    if (mac != null) 
                    {
                        
                        result+= new BigInteger(1, mac).toString(16);
                    }        
                } 
            }
        } 
        catch (Exception ex) 
        {
        }
         return result;
    }
    /**
     * Get internet connection status
     **/
    public static boolean isInternetConnectionAvailable()
    {
        try
        {
           // String ipAddress = "www.google.com";
           // InetAddress inet = InetAddress.getByName(ipAddress);
          //  return inet.isReachable(5000);
        }
        catch(Exception e)
        {}
        return false;
    }
}
