/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.settings;

import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.utils.SystemUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author admin
 */
public class SettingsUtils 
{
    public static final String cssLocation="/css/stylesheet.css";
    public static final String cssFolderLocation="/css/";
     /**
     *SettingsUtils default constructor 
     **/
    public SettingsUtils()
    {}
    /**
     *Change the application's current  style
     * @param theme : String containing the new style name as available in the /themes folder
     * @param saveSetting : boolean value which triggers the save of the application configurations into the properties file 
     * @return void
     **/
    public static void changeStyle(String theme,boolean saveSetting)
    {
        ObdqProperties.defaultTheme=theme;
        Globals.Page.getStylesheets().clear();    
        Globals.Page.getStylesheets().add(SettingsUtils.getTheamFolderLocation()+"/css/stylesheet.css");
        SceneDispatcher.getScene(Globals.currentPage,Globals.root,Globals.pane);
        if(saveSetting)
        {   
            ObdqProperties.storePropertiesToFile();
        }
        initializeStageDimensions();
    }
     /**
     * Return the themes folder location
     * @return String 
     **/
    public static String getFileSystemTheamFolderLocation()
    {
        return ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme;
    }
     /**
     * Return the themes folder location
     * @return String 
     **/
    public static String getTheamFolderLocation()
    {
        return "file:///"+ObdqProperties.workingDirectoryPath+"/themes/"+ObdqProperties.defaultTheme;
    }
     /**
     * Return the languages folder location
     * @return String 
     **/
    public static String getLanguageFolderLocation()
    {
        return "file:///"+ObdqProperties.workingDirectoryPath+"/languages";
    }    
    /**
     * Get the least of the available themes folders 
     * @return File[] 
     **/
    public static File[] getAvailableThemesFolders()
    {
        File dir = new File(ObdqProperties.workingDirectoryPath+"/themes");
        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() 
        {
            @Override
            public boolean accept(File file) 
            {
                return file.isDirectory();
            }
        };
      
        return dir.listFiles(fileFilter);
    }
    /**Record the page history
     *@param page : the current page
     **/
    public static void setPageHistory(Class page)
    {
        int found=-1;
        for(int i=0;i<Globals.pageHistory.size();i++)
        {
           if(Globals.pageHistory.get(i)== page)
           {
               found=i;
               break;
           }
        }
        if(found==-1)
            Globals.pageHistory.add(page);
        else
        {
            for(int i=found+1;i<Globals.pageHistory.size();i++)
            {
              Globals.pageHistory.remove(i);  
            }
        }
    }
    /**Get previous page
     *Returns the previous page
     **/
    public static Class getPreviousPage()
    {
      if(Globals.pageHistory.size()==1)
      {
          return (Class) Globals.pageHistory.get(0) ; 
      }
      else
        return (Class) Globals.pageHistory.get(Globals.pageHistory.size()-2) ; 
    }
    /**
     * Get the maximum with and height for the application window
     *
     **/
    public static void getWindowSize()
    {
        try
        {
            String path=getBackgroundImageLocations();
            if(!path.equals(""))
            {
                BufferedImage bimg = ImageIO.read(new File(getFileSystemTheamFolderLocation()+cssFolderLocation+path));
                int width          = bimg.getWidth();
                int height         = bimg.getHeight();
                ObdqProperties.MaxHeight=height;
                ObdqProperties.MaxWidth=width;
            }
        }
        catch(Exception e)
        {
        }
    }
    /**
     * Returns the background image location
     **/
    public static String getBackgroundImageLocations()
    {
        String location;
        String CSSContent=SystemUtils.readFromFile(getFileSystemTheamFolderLocation()+cssLocation);
        int startBKTag=CSSContent.indexOf("backgroundImageLandingPage");
        String bkTag=CSSContent.substring(startBKTag, CSSContent.indexOf("}", startBKTag));
        int startimg=bkTag.indexOf("-fx-image");
        String fxImageTag=bkTag.substring(startimg, bkTag.indexOf(")", startimg));
        int start=fxImageTag.indexOf("\"");
        int end=fxImageTag.lastIndexOf("\"");
        location=fxImageTag.substring(start+1,end);
        return location;
    }
    /**
     * Check to see if the license was accepted
     **/
    public static boolean isLicenseAccepted()
    {
        if(ObdqProperties.unifiedMACAddress!=null)
        {
            if(ObdqProperties.unifiedMACAddress.indexOf(Globals.accepted)!=-1&&
               ObdqProperties.unifiedMACAddress.indexOf(SystemUtils.getUnifiedMacAddress())!=-1)
            {
                return true;
            }
        }
        
        return false;
    }
    /**
     * accept the license terms for the current MAC configuration 
     **/
    public static void acceptLicese()
    {
        ObdqProperties.unifiedMACAddress=SystemUtils.getUnifiedMacAddress()+Globals.accepted;
        ObdqProperties.storePropertiesToFile();
    }
    /**
     * Initialize the page dimensions
     **/
    public static void initializeStageDimensions()
    {
        SettingsUtils.getWindowSize();
        Globals.primaryStage.setMaxHeight(ObdqProperties.MaxHeight);
        Globals.primaryStage.setMaxWidth(ObdqProperties.MaxWidth);
    }
}
