/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.impementation.support;



import com.queeq.obdq.utils.SystemUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 *
 * @author admin
 */
public class PageFooter 
{
    public PageFooter()
    {
    }
    public static StackPane getPageFooter()
    {
        StackPane footer = new StackPane(); 
            HBox FooterHbox = new HBox();
            FooterHbox.setPadding(new Insets(0,10,10,10));
            FooterHbox.setSpacing(0);
            FooterHbox.setId("footerContentHBox");
            FooterHbox.setAlignment(Pos.CENTER);
                GridPane gridHeaderLeft= new GridPane();
               // gridHeaderLeft.setGridLinesVisible(true);
                gridHeaderLeft.setHgap(1);
                gridHeaderLeft.setVgap(1);
                gridHeaderLeft.setAlignment(Pos.CENTER_LEFT);
                gridHeaderLeft.setPadding(new Insets(2,2,2,2));
                        Text catr = new Text("© queeq");                            
                        catr.setFont(Font.font(java.awt.Font.DIALOG, 10));
                        catr.setId("footerContentText");
                        catr.setCursor(Cursor.HAND);
                        catr.setOnMouseClicked(new EventHandler<MouseEvent>() 
                        {
                            @Override
                            public void handle(MouseEvent e) 
                            { 
                                try
                                {
                                    SystemUtils.openLink("http://www.queeq.com");
                                }
                                catch(Exception ex)
                                {
                                    ex.printStackTrace();
                                }
                            }
                        });
  
                gridHeaderLeft.add(catr, 1, 1);
            FooterHbox.getChildren().add(gridHeaderLeft);  
        
        footer.getChildren().addAll(FooterHbox);
        return footer;
    }
}
