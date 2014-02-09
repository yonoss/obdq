/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;

import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.controller.HistoryPageController;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.*;

/**
 *
 * @author admin
 */
public class HistoryPage extends EntryPageTemplate
{
     private final TilePane selectedCarArea = new TilePane();
     private final ListView<String> auditListView = new ListView<String>();
     private final ChoiceBox selectCar = new ChoiceBox();
     private final TextArea eventDescriptionTextArea=new TextArea();

     public HistoryPage()
     {
         //setOrder(2);
     }
     @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
            //page body
                VBox centralContainer = new VBox();         
                centralContainer.setPadding(new Insets(10,10,10,10));
                centralContainer.setSpacing(10);
                centralContainer.setId("mainContainer");
            
                /*Car selector*/
                    VBox carSelectorContainer = new VBox();
                    StackPane carSelectorLableContainer = new StackPane();
                    carSelectorLableContainer.setId("carSelectorLableContainer");
                        HBox currentCodeLableBackground = new HBox();
                        currentCodeLableBackground.setId("carSelectorLableBackground");

                        Label carSelectorLable = new Label(LN.getString("historyPage.carSelector"));
                        carSelectorLable.setId("carSelectorLableText");
                        carSelectorLable.setContentDisplay(ContentDisplay.LEFT);
                    carSelectorLableContainer.getChildren().addAll(currentCodeLableBackground,carSelectorLable);
                        VBox carSelectorTab = new VBox();
                        carSelectorTab.setId("carSelectorTab");
                        
                            TilePane carControlsArea = new TilePane();
                            carControlsArea.setHgap(10);
                            carControlsArea.setVgap(10);
                            carControlsArea.setId("carControlsArea");
                            carControlsArea.setPadding(new Insets(10,10,10,10));
                            carControlsArea.setTileAlignment(Pos.CENTER);    
                           
                            HistoryPageController.getSelectCarEntries(selectCar);         
                            selectCar.setId("selectCar");
                            carControlsArea.getChildren().add(selectCar);
                            
                            selectCar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
                            {
                                @Override
                                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) 
                                {
                                    if(arg2!=null)
                                        HistoryPageController.carSelection(arg2, selectedCarArea, auditListView);
                                }
                            
                            });
                            
                            Button editCar = new Button(LN.getString("historyPage.editCar"));
                            editCar.setId("editCar");
                            carControlsArea.getChildren().add(editCar); 
                            editCar.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                { 
                                    if(Globals.selectedCar!=null)
                                        SceneDispatcher.getScene(CarEditPage.class,Globals.root,Globals.pane);
                                }
                            });
                            
                            Button removeCar = new Button(LN.getString("historyPage.removeCar"));
                            removeCar.setId("removeCar");
                            carControlsArea.getChildren().add(removeCar); 
                            removeCar.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                { 
                                    if(Globals.selectedCar!=null)
                                         HistoryPageController.removeCar(Globals.selectedCar,selectedCarArea,selectCar);
                                }
                            });
                            
                           /* Button addNewCar = new Button(LN.getString("historyPage.addNewCar"));
                            addNewCar.setId("addNewCar");
                            carControlsArea.getChildren().add(addNewCar); 
                            addNewCar.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                { 
                                        SceneDispatcher.getScene(CarAddPage.class,Globals.root,Globals.pane);
                                }
                            });*/
                            
                            selectedCarArea.setHgap(10);
                            selectedCarArea.setVgap(10);
                            selectedCarArea.setId("selectedCarArea");
                            selectedCarArea.setPadding(new Insets(10,10,10,10));
                            selectedCarArea.setTileAlignment(Pos.CENTER);                       
                            HistoryPageController.setCarDescriptionArea(selectedCarArea);                     
                         carSelectorTab.getChildren().addAll(carControlsArea,selectedCarArea);

                    carSelectorContainer.getChildren().addAll(carSelectorLableContainer,carSelectorTab);
                    /*Bottom container*/
                    HBox bottomContainer = new HBox();
                    bottomContainer.setPadding(new Insets(10,0,10,0));
                    bottomContainer.setSpacing(20);
                    bottomContainer.setId("bottomContainer");
                        /*Audit container*/
                        VBox auditContainer = new VBox();
                            StackPane auditLableContainer = new StackPane();
                            auditLableContainer.setId("auditLableContainer");
                                HBox auditLableBackground = new HBox();
                                auditLableBackground.setId("auditLableBackground");

                                Label auditLable = new Label(LN.getString("historyPage.carAudit"));
                                auditLable.setId("auditLableText");
                                auditLable.setContentDisplay(ContentDisplay.LEFT);
                            auditLableContainer.getChildren().addAll(auditLableBackground,auditLable);

                            auditListView.setId("auditListView");
                            auditListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                            auditListView.prefHeightProperty().bind(pane.heightProperty());
                            auditListView.prefWidthProperty().bind(pane.widthProperty());
                            HistoryPageController.setAuditArea(auditListView);
                            auditListView.setOnMouseReleased(new EventHandler<MouseEvent>()
                            {
                                @Override
                                public void handle(MouseEvent event) 
                                {
                                    int selectedIndex=auditListView.getSelectionModel().getSelectedIndex();
                                    HistoryPageController.updateDescriptionArea(selectedIndex,eventDescriptionTextArea);
                                }
                            
                            });
                           
                           /* auditListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 
                            {
                                public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) 
                                {
                                        int selectedIndex=auditListView.getSelectionModel().getSelectedIndex();
                                        HistoryPageController.updateDescriptionArea(selectedIndex,eventDescriptionTextArea);
                                }
                            });*/

                        auditContainer.getChildren().addAll(auditLableContainer,auditListView);
                        /*Event Description*/                        
                        VBox eventDescriptionContainer = new VBox();
                            StackPane eventDescriptionLableContainer = new StackPane();
                            eventDescriptionLableContainer.setId("eventDescriptionLableContainer");
                                HBox eventDescriptionLableBackground = new HBox();
                                eventDescriptionLableBackground.setId("eventDescriptionLableBackground");

                                Label eventDescriptionLableText = new Label(LN.getString("historyPage.eventDescription"));
                                eventDescriptionLableText.setId("eventDescriptionLableText");
                                eventDescriptionLableText.setContentDisplay(ContentDisplay.LEFT);
                            eventDescriptionLableContainer.getChildren().addAll(eventDescriptionLableBackground,eventDescriptionLableText);
                            
                            eventDescriptionTextArea.setEditable(false);
                            eventDescriptionTextArea.setWrapText(true);
                            eventDescriptionTextArea.setFocusTraversable(false);
                            eventDescriptionTextArea.prefHeightProperty().bind(pane.heightProperty());
                            eventDescriptionTextArea.prefWidthProperty().bind(pane.widthProperty().multiply(1.30));
                            eventDescriptionTextArea.setId("eventDescriptionTextArea");
                        eventDescriptionContainer.getChildren().addAll(eventDescriptionLableContainer,eventDescriptionTextArea);                 
                   bottomContainer.getChildren().addAll(auditContainer,eventDescriptionContainer);         
                centralContainer.getChildren().addAll(carSelectorContainer,bottomContainer);           
            pane.setCenter(centralContainer); 
    }

    @Override
    public ImageView getEntryIcon()
    {
         ImageView HistoryButton = new ImageView();
        HistoryButton.setId("HistoryButtonLandingPage");
        HistoryButton.setCursor(Cursor.HAND);
        HistoryButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                SceneDispatcher.getScene(HistoryPage.class,Globals.root,Globals.pane);
            }
        });
        return HistoryButton;
    }
    
}
