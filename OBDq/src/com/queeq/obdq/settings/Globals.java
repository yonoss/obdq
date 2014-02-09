/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.settings;

import com.queeq.obdq.car.Car;
import com.queeq.obdq.car.CarsManager;
import com.queeq.obdq.car.HistoryEvenManager;
import com.queeq.obdq.car.HistoryManager;
import com.queeq.obdq.scenes.impementation.widgets.DoubleSlider;
import com.queeq.obdq.scenes.utils.DataObject;
import com.queeq.obdq.scenes.utils.GraphObject;
import com.queeq.obdq.scenes.utils.ScanObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jssc.SerialPort;

/**
 *
 * @author admin
 */
public class Globals 
{
    public Globals()
    {}
    /****/
    public static Stage primaryStage=null;
    public static List EntryPageListContent=null;
    /**Serial connection**/
    public static SerialPort serialPort=null;
    public static String serialMessage="";
    public static int serialPortStatusFlag=1;//1 - serial channel free; 2- serial channel waiting response ; 3- serial channel message received

    /****/
    /**Headers**/
    public static final ImageView CarStatus=new ImageView();
    /***Settings page**/
    public static Label serialPortStatus = new Label();
    public static boolean connectButtonClicked=false;
    public static Button connectButton = new Button();
    
    /**Graph page**/
    public static AreaChart<Number,Number> areaChart=null;
    public static DoubleSlider doubleSliderXAxis = new DoubleSlider();
    public static int xFirst=0;
    public static int xLast=0;
   
    
    public static DoubleSlider doubleSliderYAxis = new DoubleSlider();
    public static int yFirst=0;
    public static int yLast=0;
    
    public static VBox checkboxContainer=new VBox();
    public static GraphObject graphObj=null;
    /**Console page**/
    public static final TextArea receivedMessages=new TextArea();
    /****/
    /**Scan page**/
    public static TableView scanResults = new TableView();
    public static ScanObject scanData=new ScanObject();
    public static Button scan = new Button();
    public static boolean scanButtonClicked=false;
    public static Label scanStatus=new Label();
    /**sensor data page**/
    public static TableView dataView = new TableView();
    public static TableView sensorsView = new TableView();
    public static DataObject data=new DataObject();
    public static Button readPID = new Button();
    
    
    public static CarsManager carsList = new CarsManager();
    public static HistoryManager historyManager = null;
    public static HistoryEvenManager historyEvenManager= new HistoryEvenManager();
    public static Car selectedCar=null;
    public static Car currentCar=null;
    public static String currentConnectionVIN="";
    
    public static ArrayList<Class> pageHistory=new ArrayList();
    
    public static StackPane root=new StackPane();
    public static BorderPane pane = new BorderPane();
    public static StackPane modalWindow=null;
    public static Scene Page =null;
    
    public static Class currentPage;
    
    public static ResourceBundle curentrb;
    
    public static boolean isApplicationTerminated=false;
    public static Thread carConnectionMonitor=null;
    public static boolean isLicenseAccepted=false;
    /****/
    public static final String accepted="ACCEPTED";
    
}
