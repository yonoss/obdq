/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.serial.utils;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class OBDQLogger 
{
    static final Logger logger = Logger.getLogger(OBDQLogger.class.getName());
    
    static
    {
        try 
        {
            FileHandler handler = new FileHandler(ObdqProperties.logPath, ObdqProperties.logFileSize, ObdqProperties.logFilesNumber, true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
        } 
        catch (IOException e) 
        {
            logger.warning("Failed to initialize logger handler.");
        }
    }
    public static void log(String message)
    {  
       // logg to console
       Globals.receivedMessages.appendText(message+"\n");
       logger.info(message);  
    }
}
