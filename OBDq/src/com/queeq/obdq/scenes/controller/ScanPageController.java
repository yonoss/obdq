/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;
import com.queeq.obdq.elm327.Utils;
import com.queeq.obdq.errorcodes.Code;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.utils.PID;
import com.queeq.obdq.scenes.utils.ScanObject;
import com.queeq.obdq.scenes.view.ScanPage;
import com.queeq.obdq.settings.Globals;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author admin
 */
public class ScanPageController 
{
    private static Matcher matcher;
    private static final String HEX_PATTERN = "^[0-9a-fA-F]{2,2}$";
    private static Pattern pattern=null;
    
    public ScanPageController()
    {
        pattern = Pattern.compile(HEX_PATTERN);
    }
    public static boolean validate(String hex) 
    {
        matcher = getPattern().matcher(hex);
        return matcher.matches();
    }
    private static Pattern getPattern()
    {
        if(pattern==null)
            pattern=Pattern.compile(HEX_PATTERN);
        return pattern;
    }
    public static void Scan(String modeFrom,String modeTo,String PIDFrom,String PIDTo)
    {
        final int mFrom=Utils.HexStringToInt(modeFrom);
        final int mTo=Utils.HexStringToInt(modeTo);
        final int PFrom=Utils.HexStringToInt(PIDFrom);
        final int PTo=Utils.HexStringToInt(PIDTo);

        Task task = new Task<Void>() 
        {
            @Override public Void call() throws InterruptedException 
            {
                for(int i=mFrom;i<=mTo;i++)
                {
                for(int j=PFrom;j<=PTo;j++)
                    {
                        if(Globals.currentPage==ScanPage.class&&!Globals.scan.getText().equals(LN.getString("scanPage.scanStart"))&&!Globals.scanButtonClicked)
                        {
                            final String mode=Utils.IntToHexString(i,2);
                            final String PID=Utils.IntToHexString(j,2);
                            Platform.runLater(new Runnable() 
                            {
                                @Override
                                public void run() 
                                {
                                    Globals.scanStatus.setText(LN.getString("scanPage.scanStatusScanning").replaceAll("@mode@", mode).replaceAll("@PID@", PID));
                                }
                            }); 
                            Globals.scanData.setSensorData(mode+" "+PID, i+""+j);
                            Thread.sleep(1000);
                            Platform.runLater(new Runnable() 
                            {
                                @Override
                                public void run() 
                                {
                                    Globals.scanResults.setItems(null); 
                                    Globals.scanResults.layout(); 
                                    ObservableList<PID> tdata= Globals.scanResults.getSortOrder();
                                    Globals.scanResults.setItems(Globals.scanData.getScanDataObject());   
                                    Globals.scanResults.getSortOrder().addAll(tdata); 

                                }
                            }); 
                        }
                        else
                        {
                            Globals.scanButtonClicked=false;
                            j=PTo+1;
                            i=mTo+1;
                        }
                    }  
                }
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        Globals.scan.setText(LN.getString("scanPage.scanStart"));
                        Globals.scan.setId("scanStart");
                        Globals.scanButtonClicked=false;
                        Globals.scanStatus.setText(LN.getString("scanPage.scanStatusDefault"));
                               
                    }
                }); 
                return null;
            }   
        };
        new Thread(task).start();
    }
    public static void loadScanFile()
    {
        FileChooser fileChooser = new FileChooser();       
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showOpenDialog(Globals.primaryStage);
        if(file != null)
        {
            //System.out.println(file.getPath());
            try
            {
                Globals.scanData=loadErrorCode(file.getPath());
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        Globals.scanResults.setItems(null); 
                        Globals.scanResults.layout(); 
                        ObservableList<PID> tdata= Globals.scanResults.getSortOrder();
                        Globals.scanResults.setItems(Globals.scanData.getScanDataObject());   
                        Globals.scanResults.getSortOrder().addAll(tdata); 
                    }
                });        
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            // textArea.setText("test");
        }
    }
    public static void saveScanFile()
    {
        FileChooser fileChooser = new FileChooser();       
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showSaveDialog(Globals.primaryStage);
        if(file != null)
        {
            try
            {
                storeErrorCode(Globals.scanData, file.getPath());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            // textArea.setText("test");
        }
    }
    /**
     * Store the ScanObject to the file
     **/
    public static void storeErrorCode(ScanObject Obj, String FileLocation) throws Exception
    {        
                JAXBContext context = JAXBContext.newInstance(ScanObject.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//m.marshal(Ads, System.out);
                File f = new File(FileLocation);
               
		Writer w = null;
		try
                {
                    f.mkdir();
                    w = new FileWriter(FileLocation+".xml",false);
                    m.marshal(Obj, w);
		}
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        w.close();
                    }
                    catch (Exception e)
                    {
                    }
		}
    }
    /**
     * Retrieves the ScanObject from the data file
     **/
    public static ScanObject loadErrorCode(String FileLocation) throws Exception
    {
		JAXBContext context = JAXBContext.newInstance(ScanObject.class);
		Unmarshaller um = context.createUnmarshaller();
		ScanObject result = (ScanObject) um.unmarshal(new FileReader(FileLocation));

                return result;
    }  
}
