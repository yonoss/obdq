/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.utils.GraphObject;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;

/**
 *
 * @author admin
 */
public class GraphPageController 
{
    public static void startReadingLiveData()
    {
        Globals.graphObj=new GraphObject(Globals.data);
        handleChartChangeEvent(true);
        clearMenuCheckBoxes();
        createMenuCheckBoxes(Globals.graphObj.getHeaders(),Globals.graphObj.getUnits(),Globals.graphObj.getCheckboxesStatus());
    }
    
   // private static int[][] test={{-100,-100},{-50,50},{0,0},{50,-50},{150,50}};
    /**
     * Handles the events responsible of changing the chart view
     * @param boolean initiateSliders instructing the system to initiate the sliders
     **/
    public static void handleChartChangeEvent(boolean initiateSliders )
    {
        try
        {
            removeAllAreaChartSeries(Globals.areaChart);
            int seriesLength=Globals.graphObj.getHeaders().size();
            String time=Globals.graphObj.getHeaders().get(0);
            for(int s=1;s<seriesLength;s++)
            {
                String sensorName=Globals.graphObj.getHeaders().get(s);

                if((Boolean)Globals.graphObj.getCheckboxesStatus().get(sensorName))
                {
                    String sensorUnit=Globals.graphObj.getUnits().get(s);
                    String seriesNameLong=builSeriesName(sensorName, sensorUnit);


                    int timeIndex=Globals.graphObj.getHeaderIndexForID(time); 
                    int sensorIndex=Globals.graphObj.getHeaderIndexForID(sensorName); 

                    ArrayList<HashMap> seriesData=Globals.graphObj.getSeries();
                    AreaChart.Series<Number,Number> series = new AreaChart.Series<Number,Number>();
                    series.setName(LN.getString(seriesNameLong));

                    for(int i=0;i<seriesData.size();i++)
                    {
                        double yVal =(Double)seriesData.get(i).get(sensorIndex);
                        double xVal =(Double)seriesData.get(i).get(timeIndex);
                        if(initiateSliders)
                        {
                        series.getData().add(new AreaChart.Data<Number,Number>(xVal, yVal));
                        updateSlidersLimits((int)xVal,(int)yVal); 
                        }
                        else if(xVal>=Globals.xFirst&&xVal<=Globals.xLast&&yVal>=Globals.yFirst&&yVal<=Globals.yLast)
                            series.getData().add(new AreaChart.Data<Number,Number>(xVal, yVal));
                    }               
                    addAreaChartSeries(Globals.areaChart,series);
                }
            }
        }
        catch(Exception e)
        {}
    }
    private static String builSeriesName(String sensorName, String sensorUnit )
    {
        return sensorName+" "+sensorUnit;
    }
    /**
     * Generate chart menu check box
     * @param String the sensor ID
     * @param boolean checked the status of the check box (checked or unchecked )
     **/
    public static CheckBox getSensorControlAsCheckbox(final String sensorName, String sensorUnit, boolean checked)
    {
        final String sensorNameLong=builSeriesName(sensorName,sensorUnit);
        final CheckBox control = new CheckBox (LN.getString(sensorName));
        control.setId("GraphsMenuCheckBox");
        
        if(checked)
        {
            control.setSelected(checked);
        }
        
        control.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            { 
                //System.out.println(sensorNameLong+"/"+control.isSelected());
                Globals.graphObj.getCheckboxesStatus().put(sensorName, control.isSelected());
                if(control.isSelected())
                {
                    GraphPageController.handleChartChangeEvent(false);
                }
                else
                    GraphPageController.removeAreaChartSeries(Globals.areaChart,sensorNameLong);
            }
        });
        
        return control;
    }
    /**
     * Clear the chart menu 
     **/
    public static void clearMenuCheckBoxes()
    {
         Globals.checkboxContainer.getChildren().clear();
    }
    /**
     *Generate the menu check boxes
     * @param ArrayList<String> sensors list
     **/
    public static void createMenuCheckBoxes(ArrayList<String> sensors,ArrayList<String> units)
    {
        for(int i=1;i<sensors.size();i++)
        {
            Globals.checkboxContainer.getChildren().add(getSensorControlAsCheckbox(sensors.get(i), units.get(i),true));
        }
    }
    public static void createMenuCheckBoxes(ArrayList<String> sensors,ArrayList<String> units,HashMap checkboxStatus)
    {
        for(int i=1;i<sensors.size();i++)
        {
            Globals.checkboxContainer.getChildren().add(getSensorControlAsCheckbox(sensors.get(i), units.get(i),(Boolean)checkboxStatus.get(sensors.get(i))));
        }
    }
    /**Calculates the sliders max and min
     *@param int x value
     *@param int y value
     **/
    public static void updateSlidersLimits(int x,int y)
    {
            if(x<Globals.xFirst)
                Globals.xFirst=x;
            if(x>Globals.xLast)
                Globals.xLast=x;

            if(y<Globals.yFirst)
                Globals.yFirst=y;
            if(y>Globals.yLast)
                Globals.yLast=y;        
    }
    /**Update the sliders size
     **/
    private static void updateSliders()
    {
        Globals.doubleSliderXAxis.setMax(Globals.xLast+1);
        Globals.doubleSliderXAxis.setValue2(Globals.xLast+1);
        Globals.doubleSliderXAxis.setMin(Globals.xFirst-1);
        Globals.doubleSliderXAxis.setValue1(Globals.xFirst-1);
        Globals.doubleSliderXAxis.setMajorTickUnit(getMajorTickUnit(Globals.xFirst-1,Globals.xLast+1));
        
        
        Globals.doubleSliderYAxis.setMax(Globals.yLast+1);
        Globals.doubleSliderYAxis.setValue2(Globals.yLast+1);
        Globals.doubleSliderYAxis.setMin(Globals.yFirst-1);
        Globals.doubleSliderYAxis.setValue1(Globals.yFirst-1);
        Globals.doubleSliderYAxis.setMajorTickUnit(getMajorTickUnit(Globals.yFirst-1,Globals.yLast+1));
    }
    /**Calculate the major tick unit
     *@param int start value
     *@param int stop value 
     **/
    private static double getMajorTickUnit(int start, int stop)
    {
        int interval=stop-start;
        
        if(interval>0)
        {
            return (interval*5)/100;
        }
        else
            return 10;
    }
     /**Clears the chart area
     *@param AreaChart<Number,Number> to be cleared
     *@return void
     **/
    public static void clearAreaChart(AreaChart<Number,Number> ac) 
    {   
        if (!ac.getData().isEmpty()) 
         {
            ac.getData().clear();
         }
    }
    public static void removeAllAreaChartSeries(AreaChart<Number,Number> ac)
    {
        ac.getData().clear();
    }
    /**Removes the specified series from the specified chart area
     *@param AreaChart<Number,Number> from which the series will be removed
     *@param String sensor
     *@return void
     **/
    public static void removeAreaChartSeries(AreaChart<Number,Number> ac,String sensor)
    {
        if (!ac.getData().isEmpty()) 
        {
            ObservableList data= ac.getData();
            for(int i=0;i<data.size();i++)
            {
                if(ac.getData().get(i).getName().equals(LN.getString(sensor)))
                    ac.getData().remove(i);  
            }     
        }
    }
    /**Adds new data series to the specified area chart, on the specified index  
     *@param AreaChart<Number,Number> from which the series will be removed
     *@paramAreaChart.Series<Number,Number> the data series
     *@return void
     **/
    public static void addAreaChartSeries(AreaChart<Number,Number> ac,AreaChart.Series<Number,Number> series)
    {
        ac.getData().add(series);
    }
    /**Add date item to chart
     *@param AreaChart<Number,Number>
     *@param String the sensor id
     **/
    public static void addDataItem(AreaChart<Number,Number> ac, String sensor)
    {
        ObservableList<XYChart.Series<Number, Number>> data = ac.getData();
        if (!data.isEmpty()) 
        {
            for(int i=0;i<data.size();i++)
            {
                XYChart.Series<Number, Number> s = data.get(i);
                if(s.getName().equals(sensor))
                {
                   if(s!=null)
                    //not implemented yet
                    break;
                }
            }
        }
    }
    /**Load saved data**/
    public static void loadSavedData()
    {
        FileChooser fileChooser = new FileChooser();       
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(ObdqProperties.saveGraphPath));
        //Show save file dialog
        File file = fileChooser.showOpenDialog(Globals.primaryStage);
        if(file != null)
        {
            //System.out.println(file.getPath());
            Globals.graphObj=new GraphObject(file.getPath());
            handleChartChangeEvent(true);
            clearMenuCheckBoxes();
            createMenuCheckBoxes(Globals.graphObj.getHeaders(),Globals.graphObj.getUnits());
            
            try
            {
                //Globals.scanData=loadErrorCode(file.getPath());
                Platform.runLater(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                       updateSliders();
                    }
                });       
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    /**Load saved data**/
    public static void saveData()
    {
        FileChooser fileChooser = new FileChooser();       
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(ObdqProperties.saveGraphPath));
        //Show save file dialog
        File file = fileChooser.showSaveDialog(Globals.primaryStage);
        if(file != null)
        {
            try
            {
                Globals.graphObj.saveToFile(file.getPath());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            // textArea.setText("test");
        }
    }
    private static void initializeSliders()
    {
        Globals.xLast=0;
        Globals.yLast=0;
        Globals.xFirst=0;
        Globals.yFirst=0;
        Globals.doubleSliderXAxis.setMax(Globals.xLast);
        Globals.doubleSliderXAxis.setValue2(Globals.xLast);
        Globals.doubleSliderXAxis.setMin(Globals.xFirst);
        Globals.doubleSliderXAxis.setValue1(Globals.xFirst);
       // Globals.doubleSliderXAxis.setMajorTickUnit(getMajorTickUnit(Globals.xFirst-1,Globals.xLast+1));
        
        
        Globals.doubleSliderYAxis.setMax(Globals.yLast);
        Globals.doubleSliderYAxis.setValue2(Globals.yLast);
        Globals.doubleSliderYAxis.setMin(Globals.yFirst);
        Globals.doubleSliderYAxis.setValue1(Globals.yFirst);
      //  Globals.doubleSliderYAxis.setMajorTickUnit(getMajorTickUnit(Globals.yFirst-1,Globals.yLast+1));
    }
    public static void clearGraph()
    {
        removeAllAreaChartSeries(Globals.areaChart);
        clearMenuCheckBoxes();
        initializeSliders();
    }
}
