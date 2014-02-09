/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.InternationalizationUtils;
import com.queeq.obdq.scenes.view.templates.StandardPageTemplate;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.obdq.settings.SettingsUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 *
 * @author admin
 */
public class LanguagePage extends StandardPageTemplate 
{

    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
            //page body
                TilePane centralContainer = new TilePane();
                centralContainer.setHgap(20);
                centralContainer.setVgap(5);
                centralContainer.setPadding(new Insets(10,20,10,20));
                centralContainer.setTileAlignment(Pos.CENTER);
                String[] availableLanguages=InternationalizationUtils.getAvailableLanguages();
                for(int i=0;i<availableLanguages.length;i++)
                {
                    final String[] data = availableLanguages[i].split("_");
                    final VBox languageButton = new VBox();
                    if(ObdqProperties.defaultLanguage.equals(data[1])&&ObdqProperties.defaultCountry.equals(data[2]))
                        languageButton.setId("flagSelected");
                    else
                        languageButton.setId("flag");

                    
                        ImageView flag = new ImageView();
                            Image flagImag=new Image(SettingsUtils.getLanguageFolderLocation()+"/"+availableLanguages[i]+".png");
                        flag.setImage(flagImag);

                        Label flagLable = new Label(data[0]);
                        flagLable.setId("flagLable");
                        flagLable.setContentDisplay(ContentDisplay.CENTER);
                    languageButton.getChildren().addAll(flag,flagLable);
                    languageButton.setAlignment(Pos.CENTER);  

                    languageButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
                    {
                        @Override
                        public void handle(MouseEvent event) 
                        {
                            InternationalizationUtils.changeLanguage(data[1], data[2],true,false);
                        }
                    });
                            
                            
                    centralContainer.getChildren().add(languageButton);
                }    
                
            pane.setCenter(centralContainer); 
    }
    
}
