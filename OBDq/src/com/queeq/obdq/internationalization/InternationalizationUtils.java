/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.internationalization;

import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 *
 * @author admin
 */
public class InternationalizationUtils 
{
    private static Locale locale;
    public InternationalizationUtils()
    {}
    /**
     *Change current language
     * @param language : String containing the language code
     * @param country  : String containing the country code
     * @param repaintScene  : boolean value which flags the repaint of the current scene
     * @param saveSettings  : boolean value which flags the repaint of the current scene
     * @return void
     **/
    public static void changeLanguage(String language, String country,boolean repaintScene,boolean saveSettings) 
    {
        try
        {
            locale = new Locale(language, country);
            //Load language bundel from file system
            File folder = new File(ObdqProperties.workingDirectoryPath+"/languages");
            URL[] urls = {folder.toURI().toURL()};  
            ClassLoader loader = new URLClassLoader(urls);  
            Globals.curentrb = ResourceBundle.getBundle("Messages", locale, loader);
            //set language as default
            ObdqProperties.defaultLanguage=language;
            ObdqProperties.defaultCountry=country;
            
            //Repaint the curent page
            if(repaintScene)
                SceneDispatcher.getScene(Globals.currentPage,Globals.root,Globals.pane);
            if(saveSettings)   
                ObdqProperties.storePropertiesToFile();
        }
        catch(Exception e)
        {
        }

    }
    /**
     *Returns the list of all the available language packages. In order for a package to be valid it has to contain the following 2 files under the /languages folder: <language_code>_<country_code>.ico and Messages_<language_code>_<country_code>.properties
     * @return String[] : the list of the available packages as <language_code>_<country_code> string
     **/
    public static String[] getAvailableLanguages()
    {
        File dir = new File(ObdqProperties.workingDirectoryPath+"/languages");
        
        ArrayList<String> result=new ArrayList();
        // This filter only returns directories
       FilenameFilter fileFilterFlags = new FilenameFilter() 
       {
            @Override
            public boolean accept(File dir, String name) 
            {
                return name.endsWith(".png");
            }
        };
       FilenameFilter fileFilterLang = new FilenameFilter() 
       {
            @Override
            public boolean accept(File dir, String name) 
            {
                return name.endsWith(".properties");
            }
        };

      
       String[] flags= dir.list(fileFilterFlags);
       String[] langs= dir.list(fileFilterLang);
       
       for(int i=0; i<flags.length;i++)
       {
           for(int j=0;j<langs.length;j++)
           {
               String base=flags[i].substring(flags[i].indexOf("_")+1, flags[i].indexOf("."));
               String langName="Messages_"+base+".properties";
               if(langName.equals(langs[j]))
               {
                   result.add(flags[i].substring(0, flags[i].indexOf(".")));
                   break;
               }
           }
       }
       return result.toArray(new String[result.size()]);

    } 
    public static String getKeyForString(String value)
    {
        String result=null;
        Enumeration keySet= Globals.curentrb.getKeys();
        
        while(keySet.hasMoreElements())
        {
           String key=(String)keySet.nextElement();
           if(Globals.curentrb.getString(key).equals(value))
           {
               result=key;
               break;
           }
        }
        return result;
    }

}
