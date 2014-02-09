/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.settings;

import com.queeq.obdq.utils.SystemUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Properties;


/**
 *
 * @author admin
 */
public class ObdqProperties 
{
    private static boolean retry=true;
    public static final int Imperial=0;
    public static final int Metric=1;
    public static String[] Standards={"Imperial","Metric"};
    
    public static String workingDirectoryPath=System.getProperty("user.dir");
    public static String errorCodesPath=workingDirectoryPath+File.separator+"data"+File.separator+"errorCodes"+File.separator;
    public static String saveGraphPath=workingDirectoryPath+File.separator+"save"+File.separator+"graph"+File.separator;
    public static String saveScanPath=workingDirectoryPath+File.separator+"save"+File.separator+"scan"+File.separator;
    public static String imagesPath=workingDirectoryPath+"";
    public static String carHistoryBinPath=File.separator+"data"+File.separator+"history"+File.separator;
    public static String carListBinPath=File.separator+"data"+File.separator+"cars"+File.separator+"cars.xml";
    public static String pidListBinPath=File.separator+"data"+File.separator+"pid"+File.separator+"pids.xml";
    public static String carManufacturersPath=File.separator+"data"+File.separator+"miscellaneous"+File.separator+"brandsAndModels"+File.separator;
    public static String carColorsPath=File.separator+"data"+File.separator+"miscellaneous"+File.separator+"others"+File.separator+"colors.csv";
    public static String carBodyTypesPath=File.separator+"data"+File.separator+"miscellaneous"+File.separator+"others"+File.separator+"bodyType.csv";
    public static String carFuelTypesPath=File.separator+"data"+File.separator+"miscellaneous"+File.separator+"others"+File.separator+"fuelType.csv";
    public static String carTransmissionTypesPath=File.separator+"data"+File.separator+"miscellaneous"+File.separator+"others"+File.separator+"transmissionType.csv";
    public static String carEnginePowerPath=File.separator+"data"+File.separator+"miscellaneous"+File.separator+"others"+File.separator+"enginePower.csv"; 
    public static String pluginsFolde=workingDirectoryPath+File.separator+"plugins"+File.separator;
    public static String logPath=workingDirectoryPath+File.separator+"logs"+File.separator+"log.log";
    public static int logFileSize=10000;
    public static int logFilesNumber=10;
    
    public static int MaxHeight=600;
    public static int MaxWidth=800;
    
    
    public static Format historyAuditFormatter = new SimpleDateFormat("dd MMM yyyy  ");
    public static Format historyDescriptionFormatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss  ");

    /*************************************************************************
     * Properties
     *************************************************************************/
    public static String unifiedMACAddress=SystemUtils.getUnifiedMacAddress();
    public static int defaultMeasurmentSystem=Imperial;
    public static String windowTitle="OBDq";
    public static String defaultTheme="blueq"; 
    public static int defaultWindowWidth=1024;
    public static int defaultWindowHeight=678;
    public static String defaultLanguage="en";
    public static String defaultCountry="US";
    public static boolean resizable=true;
    public static String baudRates="9600,19200,38400,57600,115200";
    public static String defaultBaudRate="38400";
    /**
     *ObdqProperties default constructor 
     **/
    public ObdqProperties()
    {
        //storePropertiesToFile();
        getPropertiesFromFile();
    }
    /**
     *Get the application configurations from properties file
     * @return void. All the properties will be loaded in to the ObdqProperties object
     **/
    public static void getPropertiesFromFile()
    {
        Properties prop = new Properties();
        try 
        {
               //load a properties file
    		prop.load(new FileInputStream(workingDirectoryPath+File.separator+"properties"+File.separator+"config.properties"));
               //get the property value and print it out
                defaultMeasurmentSystem=getStandardFromString(prop.getProperty("defaultMeasurmentSystem"));
                windowTitle=prop.getProperty("windowTitle");
                defaultTheme=prop.getProperty("defaultTheme");
    		defaultWindowWidth=Integer.valueOf(prop.getProperty("defaultWindowWidth"));
    		defaultWindowHeight=Integer.valueOf(prop.getProperty("defaultWindowHeight"));
                defaultLanguage=prop.getProperty("defaultLanguage");
                defaultCountry=prop.getProperty("defaultCountry");
                resizable=Boolean.valueOf(prop.getProperty("resizable"));
                baudRates=prop.getProperty("baudRates");
                defaultBaudRate=prop.getProperty("defaultBaudRate");
                unifiedMACAddress=prop.getProperty("unifiedMACAddress");
    	} 
        catch (IOException ex) 
        {
    		ex.printStackTrace();
                storePropertiesToFile();
                if(retry)
                {
                    retry=false;
                    getPropertiesFromFile();
                }
        }
        retry=true;
    }
    /**
     *Saves the application configurations into the properties file.
     * @return void
     **/
    public static void storePropertiesToFile()
    {
        Properties prop = new Properties();
    	try 
        {
    		//set the properties value
                prop.setProperty("unifiedMACAddress", unifiedMACAddress);
                prop.setProperty("defaultMeasurmentSystem", Standards[defaultMeasurmentSystem]);
                prop.setProperty("windowTitle", windowTitle);
    		prop.setProperty("defaultTheme", defaultTheme);
    		prop.setProperty("defaultWindowWidth", String.valueOf(defaultWindowWidth));
    		prop.setProperty("defaultWindowHeight", String.valueOf(defaultWindowHeight));
                prop.setProperty("defaultLanguage", defaultLanguage);
    		prop.setProperty("defaultCountry", defaultCountry);
                prop.setProperty("resizable", String.valueOf(resizable));
                prop.setProperty("baudRates", baudRates);
                prop.setProperty("defaultBaudRate", defaultBaudRate);
    		//save properties to project root folder
    		prop.store(new FileOutputStream(workingDirectoryPath+File.separator+"properties"+File.separator+"config.properties"), null);
 
    	} 
        catch (IOException ex) 
        {
    		ex.printStackTrace();
        }
    }
    private static int getStandardFromString(String standard)
    {
        for(int i=0;i<Standards.length;i++)
        {
            if(Standards[i].equals(standard))
                return i;
        }
        return 0;
    }
    
}
