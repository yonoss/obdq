/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.dials.scenes.controller;

import com.queeq.obdq.elm327.iso15031.ISO15031;
import com.queeq.obdq.dials.scenes.view.DialsPage;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.ObdqProperties;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author admin
 */
public class DialPageControler 
{
    public static double startScaleSpeed=0;
    public static double endScaleSpeed=400;
    
    public static double startAngleSpeed=211;
    public static double endAngleSpeed=512;
    
    public static double cAngleSpeed=211;
    public static double degreePerUnitSpeed=1;
    
    public static double startScaleRPM=0;
    public static double endScaleRPM=12750;
    
    public static double startAngleRPM=211;
    public static double endAngleRPM=402.25;
    
    public static double cAngleRPM=211;
    public static double degreePerUnitRPM=1;
    
    
    private static boolean initialized=false;
    
    public DialPageControler()
    {}
    /**
     * Initialize the dial settings
     **/
    public static void initDialSettings()
    {
        if(!initialized)
        {
            degreePerUnitSpeed=(endAngleSpeed-startAngleSpeed)/(endScaleSpeed-startScaleSpeed);
            degreePerUnitRPM=(endAngleRPM-startAngleRPM)/(endScaleRPM-startScaleRPM);
            initialized=true;
        }
    }
    public static void readSpeed()
    {
         Task task = new Task<Void>() 
        {
            @Override public Void call() throws InterruptedException 
            {
                while(Globals.currentPage.equals(DialsPage.class))
                {
                    //speed logic
                    double readSpeed=0;
                    if(ObdqProperties.defaultMeasurmentSystem==ObdqProperties.Imperial)
                        readSpeed=ISO15031.getVehicleSpeedSensor()*0.621371;
                    else
                        readSpeed=ISO15031.getVehicleSpeedSensor();
                       

                    double angleSpeed=readSpeed*degreePerUnitSpeed;
                    double rotateAngleSpeed=(startAngleSpeed+angleSpeed)-cAngleSpeed;
                    rotateSpeedPin(rotateAngleSpeed);
                    cAngleSpeed=startAngleSpeed+angleSpeed;
                    
                    //rpm logic
                    double rpm=0;
                    rpm=ISO15031.getEngineRPM();
                    double angleRPM=rpm*degreePerUnitRPM;
                    double rotationAngle=(startAngleRPM+angleRPM)-cAngleRPM;
                    rotateRPMPin(rotationAngle);
                    cAngleRPM=startAngleRPM+angleRPM;
                }
                return null;
            }
        };
        new Thread(task).start();
    }
    /**
     * Rotate the speed pin with and angle 
     * @param angleStart - the rotation angle
     ***/
    public static void rotateSpeedPin(double angle)
    {
        rotate(DialsPage.speedPin,angle,30,214,0.5);
    }
    public static void rotateSpeedPin(double angle,double duration)
    {
        rotate(DialsPage.speedPin,angle,30,214,duration);
    }
     public static void rotateRPMPin(double angle)
    {
        rotate(DialsPage.rpmPin,angle,30,214,0.5);
    }
    public static void rotateRPMPin(double angle,double duration)
    {
        rotate(DialsPage.rpmPin,angle,30,214,duration);
    }
    /**
     * Rotate the given ImageView object with an angle on the point defined by X and Y
     * @param Object - ImageView object to be rotated
     * @param angle - the angle of rotation
     * @param onXPonint  - x coordinate of the rotation axes
     * @param onYPonint  - y coordinate of the rotation axes
     * @param duration - the animation duration value in seconds 
     ***/
    public static void rotate(ImageView Object,double angle,int onXPonint,int onYPonint,double duration)
    {
   //     Rotate rotate = new Rotate(0,onXPonint,onYPonint);
  //      Object.getTransforms().clear();
   //     Object.getTransforms().add(rotate);
        final Rotate rotationTransform = new Rotate(0, onXPonint, onYPonint);
        Object.getTransforms().add(rotationTransform);

        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
          .add(
            new KeyFrame(Duration.seconds(duration),new KeyValue(rotationTransform.angleProperty(),angle)
            )
          );
        rotationAnimation.play();
    }
}
