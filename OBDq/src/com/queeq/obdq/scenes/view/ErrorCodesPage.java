/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.view;


import com.queeq.obdq.elm327.Others;
import com.queeq.obdq.elm327.iso15031.ISO15031;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.SceneDispatcher;
import com.queeq.obdq.scenes.controller.ErrorCodesPageController;
import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.scenes.view.templates.EntryPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.serial.utils.SerialUtils;
import java.util.ArrayList;
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
public class ErrorCodesPage extends EntryPageTemplate 
{
    private final TextArea currentCodeDescriptionTextArea=new TextArea();
    private final TextArea currentCodeSolutionTextArea=new TextArea();
    private final TextArea currentCodeReasonTextArea=new TextArea();
    private final TextArea currentCodeMeaningTextArea=new TextArea();
    private final TextArea currentCodeTextArea=new TextArea();
   // private final TextArea carStatusTextArea=new TextArea();
    
    private final TextArea currentCodeSymptomTextArea=new TextArea();
    private final ListView<String> errorCodesListView = new ListView<String>();
    
    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
                //page body
                HBox Container = new HBox();         
                Container.setPadding(new Insets(10,10,10,10));
                Container.setSpacing(10);
                //left container
                    VBox leftConteiner = new VBox();
                    leftConteiner.setSpacing(10);
                        //curent code area
                        VBox currentCodeContainer = new VBox();
                        StackPane currentCodeLableContainer = new StackPane();
                        currentCodeLableContainer.setId("currentCodeLableContainer");
                            HBox currentCodeLableBackground = new HBox();
                            currentCodeLableBackground.setId("currentCodeLableBackground");

                            Label currentCodeLable = new Label(LN.getString("errorCodesPage.CurrentCode"));
                            currentCodeLable.setId("currentCodeLableText");
                            currentCodeLable.setContentDisplay(ContentDisplay.LEFT);
                        currentCodeLableContainer.getChildren().addAll(currentCodeLableBackground,currentCodeLable);

                        currentCodeTextArea.setEditable(true);
                        currentCodeTextArea.setWrapText(true);
                        currentCodeTextArea.setId("currentCodeTextArea");
                        currentCodeTextArea.setCursor(Cursor.NONE);
                        currentCodeTextArea.setEditable(false);
                        currentCodeTextArea.prefHeightProperty().bind(pane.heightProperty().divide(4));
                        currentCodeContainer.getChildren().addAll(currentCodeLableContainer,currentCodeTextArea);

                        // error codes list
                        VBox errorCodesList = new VBox();
                            StackPane errorCodesLableContainer = new StackPane();
                            errorCodesLableContainer.setId("errorCodesLableContainer");
                                HBox errorCodesLableBackground = new HBox();
                                errorCodesLableBackground.setId("errorCodesLableBackground");

                                Label errorCodesLable = new Label(LN.getString("errorCodesPage.DTCCodes"));
                                errorCodesLable.setId("errorCodesLableText");
                                errorCodesLable.setContentDisplay(ContentDisplay.LEFT);
                            errorCodesLableContainer.getChildren().addAll(errorCodesLableBackground,errorCodesLable);


                            errorCodesListView.setId("errorCodesListView");
                            errorCodesListView.prefHeightProperty().bind(pane.heightProperty().divide(3));
                            ErrorCodesPageController.setErrorCodesList(errorCodesListView, ErrorCodesPageController.getCodesArray(),currentCodeTextArea,currentCodeDescriptionTextArea,currentCodeReasonTextArea,currentCodeMeaningTextArea,currentCodeSolutionTextArea,currentCodeSymptomTextArea);
                            errorCodesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                            errorCodesListView.setOnMouseReleased(new EventHandler<MouseEvent>()
                                {
                                    @Override
                                    public void handle(MouseEvent event) 
                                    {
                                        String selectedItem=errorCodesListView.getSelectionModel().getSelectedItem();
                                        ErrorCodesPageController.updateDescriptionAreas(selectedItem,currentCodeTextArea,currentCodeDescriptionTextArea,currentCodeReasonTextArea,currentCodeMeaningTextArea,currentCodeSolutionTextArea,currentCodeSymptomTextArea);
                                    }

                                });

                        errorCodesList.getChildren().addAll(errorCodesLableContainer,errorCodesListView);

                        //Car status
                        VBox carStatusContainer = new VBox();
                        StackPane carStatusLableContainer = new StackPane();
                        carStatusLableContainer.setId("carStatusLableContainer");
                            HBox carStatusLableBackground = new HBox();
                            carStatusLableBackground.setId("carStatusLableBackground");

                            Label carStatusLable = new Label(LN.getString("errorCodesPage.carStatus"));
                            carStatusLable.setId("carStatusLableText");
                            carStatusLable.setContentDisplay(ContentDisplay.LEFT);
                        carStatusLableContainer.getChildren().addAll(carStatusLableBackground,carStatusLable);

                            GridPane carStatusGrid = new GridPane();
                            carStatusGrid.setId("carStatusGrid");
                            carStatusGrid.setPadding(new Insets(0,0,0,0));
                            carStatusGrid.setHgap(10);
                            carStatusGrid.setVgap(10);
                        // carStatusGrid.setGridLinesVisible(true);
                            carStatusGrid.prefHeightProperty().bind(pane.heightProperty().divide(4));

                            Label timeSinceDiagnosticTroubleCodesCleared = new Label(LN.getString("errorCodesPage.timeSinceDiagnosticTroubleCodesCleared"));
                            carStatusGrid.add(timeSinceDiagnosticTroubleCodesCleared,1,1);
                            Label timeSinceDiagnosticTroubleCodesClearedValue = new Label(LN.getString("errorCodesPage.minutes").replace("@@@", String.valueOf(ISO15031.getTimeSinceDiagnosticTroubleCodesCleared())));
                            carStatusGrid.add(timeSinceDiagnosticTroubleCodesClearedValue,1,2);


                            Label timeRunByEngineWhileMILActivated = new Label(LN.getString("errorCodesPage.timeRunByEngineWhileMILActivated"));
                            carStatusGrid.add(timeRunByEngineWhileMILActivated, 1,4);
                            Label timeRunByEngineWhileMILActivatedValue = new Label(LN.getString("errorCodesPage.minutes").replace("@@@", String.valueOf(ISO15031.getTimeRunByEngineWhileMILActivated())));
                            carStatusGrid.add(timeRunByEngineWhileMILActivatedValue,1,5);

                        carStatusContainer.getChildren().addAll(carStatusLableContainer,carStatusGrid);

                    leftConteiner.getChildren().addAll(currentCodeContainer,errorCodesList,carStatusContainer);
                    leftConteiner.prefWidthProperty().bind(pane.widthProperty().divide(4));
                    leftConteiner.prefHeightProperty().bind(pane.heightProperty());
                    //right container
                    VBox rightContainer = new VBox();
                    rightContainer.setPadding(new Insets(0,0,0,0));
                    rightContainer.setSpacing(10);
                        //Code Description
                        VBox currentCodeDescriptionContainer = new VBox();
                            StackPane currentCodeDescriptionLableContainer = new StackPane();
                            currentCodeDescriptionLableContainer.setId("currentCodeDescriptionLableContainer");
                                HBox currentCodeDescriptionLableBackground = new HBox();
                                currentCodeDescriptionLableBackground.setId("currentCodeDescriptionLableBackground");

                                Label currentCodeDescriptionLableText = new Label(LN.getString("errorCodesPage.CurrentCodeDescription"));
                                currentCodeDescriptionLableText.setId("currentCodeDescriptionLableText");
                                currentCodeDescriptionLableText.setContentDisplay(ContentDisplay.LEFT);
                            currentCodeDescriptionLableContainer.getChildren().addAll(currentCodeDescriptionLableBackground,currentCodeDescriptionLableText);
                            currentCodeDescriptionTextArea.setEditable(false);
                            currentCodeDescriptionTextArea.setWrapText(true);
                            currentCodeDescriptionTextArea.setFocusTraversable(false);
                            currentCodeDescriptionTextArea.prefHeightProperty().bind(pane.heightProperty().divide(9));
                            currentCodeDescriptionTextArea.prefWidthProperty().bind(pane.widthProperty().divide(5).multiply(4));
                            currentCodeDescriptionTextArea.setId("currentCodeDescriptionTextArea");
                        currentCodeDescriptionContainer.getChildren().addAll(currentCodeDescriptionLableContainer,currentCodeDescriptionTextArea);

                        //Code Meaning
                        VBox currentCodeMeaningContainer = new VBox();
                            StackPane currentCodeMeaningLableContainer = new StackPane();
                            currentCodeMeaningLableContainer.setId("currentCodeMeaningLableContainer");
                                HBox currentCodeMeaningLableBackground = new HBox();
                                currentCodeMeaningLableBackground.setId("currentCodeMeaningLableBackground");

                                Label currentCodeMeaningLableText = new Label(LN.getString("errorCodesPage.CurrentCodeMeaning"));
                                currentCodeMeaningLableText.setId("currentCodeMeaningLableText");
                                currentCodeMeaningLableText.setContentDisplay(ContentDisplay.LEFT);
                            currentCodeMeaningLableContainer.getChildren().addAll(currentCodeMeaningLableBackground,currentCodeMeaningLableText);
                            currentCodeMeaningTextArea.setEditable(false);
                            currentCodeMeaningTextArea.setWrapText(true);
                            currentCodeMeaningTextArea.setFocusTraversable(false);
                            currentCodeMeaningTextArea.prefHeightProperty().bind(pane.heightProperty().divide(9));
                            currentCodeMeaningTextArea.setId("currentCodeMeaningTextArea");
                        currentCodeMeaningContainer.getChildren().addAll(currentCodeMeaningLableContainer,currentCodeMeaningTextArea);

                        //Code Symptom
                        VBox currentCodeSymptomContainer = new VBox();
                            StackPane currentCodeSymptomLableContainer = new StackPane();
                            currentCodeSymptomContainer.setId("currentCodeSymptomLableContainer");
                                HBox currentCodeSymptomLableBackground = new HBox();
                                currentCodeSymptomLableBackground.setId("currentCodeSymptomLableBackground");

                                Label currentCodeSymptomLableText = new Label(LN.getString("errorCodesPage.CurrentCodeSymptom"));
                                currentCodeSymptomLableText.setId("currentCodeSymptomLableText");
                                currentCodeSymptomLableText.setContentDisplay(ContentDisplay.LEFT);
                            currentCodeSymptomLableContainer.getChildren().addAll(currentCodeSymptomLableBackground,currentCodeSymptomLableText);
                            currentCodeSymptomTextArea.setEditable(false);
                            currentCodeSymptomTextArea.setWrapText(true);
                            currentCodeSymptomTextArea.setFocusTraversable(false);
                            currentCodeSymptomTextArea.prefHeightProperty().bind(pane.heightProperty().divide(9));
                            currentCodeSymptomTextArea.setId("currentCodeSymptomTextArea");
                        currentCodeSymptomContainer.getChildren().addAll(currentCodeSymptomLableContainer,currentCodeSymptomTextArea);

                        //Code Reason
                        VBox currentCodeReasonContainer = new VBox();
                            StackPane currentCodeReasonLableContainer = new StackPane();
                            currentCodeReasonLableContainer.setId("currentCodeReasonLableContainer");
                                HBox currentCodeReasonLableBackground = new HBox();
                                currentCodeReasonLableBackground.setId("currentCodeReasonLableBackground");

                                Label currentCodeReasonLableText = new Label(LN.getString("errorCodesPage.CurrentCodeReason"));
                                currentCodeReasonLableText.setId("currentCodeReasonLableText");
                                currentCodeReasonLableText.setContentDisplay(ContentDisplay.LEFT);
                            currentCodeReasonLableContainer.getChildren().addAll(currentCodeReasonLableBackground,currentCodeReasonLableText);
                            currentCodeReasonTextArea.setEditable(false);
                            currentCodeReasonTextArea.setWrapText(true);
                            currentCodeReasonTextArea.setFocusTraversable(false);
                            currentCodeReasonTextArea.prefHeightProperty().bind(pane.heightProperty().divide(6));
                            currentCodeReasonTextArea.setId("currentCodeReasonTextArea");
                        currentCodeReasonContainer.getChildren().addAll(currentCodeReasonLableContainer,currentCodeReasonTextArea);



                        //Available Solutions   
                        VBox currentCodeSolutionContainer = new VBox();
                            StackPane currentCodeSolutionLableContainer = new StackPane();
                            currentCodeSolutionLableContainer.setId("currentCodeSolutionLableContainer");
                                HBox currentCodeSolutionLableBackground = new HBox();
                                currentCodeSolutionLableBackground.setId("currentCodeSolutionLableBackground");

                                Label currentCodeSolutionLableText = new Label(LN.getString("errorCodesPage.CurrentCodeSolution"));
                                currentCodeSolutionLableText.setId("currentCodeSolutionLableText");
                                currentCodeSolutionLableText.setContentDisplay(ContentDisplay.LEFT);
                            currentCodeSolutionLableContainer.getChildren().addAll(currentCodeSolutionLableBackground,currentCodeSolutionLableText);               


                            currentCodeSolutionTextArea.setEditable(false);
                            currentCodeSolutionTextArea.setWrapText(true);
                            currentCodeSolutionTextArea.setFocusTraversable(false);
                            currentCodeSolutionTextArea.prefHeightProperty().bind(pane.heightProperty().divide(6).multiply(1.35));
                            currentCodeSolutionTextArea.setId("currentCodeSolutionTextArea");
                        currentCodeSolutionContainer.getChildren().addAll(currentCodeSolutionLableContainer,currentCodeSolutionTextArea);

                        HBox buttonsContainer = new HBox();
                        buttonsContainer.setSpacing(10);
                        buttonsContainer.setId("buttonsContainer");
                            Button clearButton = new Button(LN.getString("errorCodesPage.clearButton"));
                            clearButton.setId("clearButton");
                           // if(!LN.getString("errorCodesPage.NoCode").equals(currentCodeTextArea.getText()))
                           // {
                            clearButton.setCursor(Cursor.HAND);
                            clearButton.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                { 
                                    if(!LN.getString("errorCodesPage.NoCode").equals(currentCodeTextArea.getText()))
                                    {
                                         ModalWindowController.openModalWindow(LN.getString("errorCodesPage.confirmClearCode.title"),LN.getString("errorCodesPage.confirmClearCode.message"),getClearCodesModalWindowButtons(),Globals.root);      
                                    }
                                    else
                                    {
                                        ModalWindowController.openModalWindow(LN.getString("errorCodesPage.noCodeAvailable.title"),LN.getString("errorCodesPage.noCodeAvailable.message"),Globals.root);                           
                                    }
                                }
                            });
                          //  }
                            Button readCodesButton = new Button(LN.getString("errorCodesPage.readCodesButton"));
                            readCodesButton.setId("readCodesButton");
                            readCodesButton.setCursor(Cursor.HAND);
                            readCodesButton.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                {  
                                    if(!SerialUtils.isSerialPortOpened())
                                    {
                                       ModalWindowController.openModalWindow(LN.getString("errorCodesPage.noCarConnection.title"),LN.getString("errorCodesPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
                                    }
                                    else
                                    {
                                        ErrorCodesPageController.setErrorCodesList(errorCodesListView, ErrorCodesPageController.getCodesArray(),currentCodeTextArea,currentCodeDescriptionTextArea,currentCodeReasonTextArea,currentCodeMeaningTextArea,currentCodeSolutionTextArea,currentCodeSymptomTextArea);
                                    }
                                }
                            });
                            Button readMore = new Button(LN.getString("errorCodesPage.readMoreButton"));
                            readMore.setId("readMoreButton");
                            readMore.setCursor(Cursor.HAND);
                            readMore.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                public void handle(ActionEvent e) 
                                { 
                                    if(!currentCodeTextArea.getText().equals(LN.getString("errorCodesPage.NoCode")))
                                    {
                                        ErrorCodesPageController.readMore(currentCodeTextArea.getText());
                                    }
                                    else
                                    {
                                        ModalWindowController.openModalWindow(LN.getString("errorCodesPage.noCodeAvailable.title"),LN.getString("errorCodesPage.noCodeAvailable.message"),Globals.root);                           
                                    }
                                }
                            });
                        buttonsContainer.setAlignment(Pos.CENTER_RIGHT); 
                        buttonsContainer.getChildren().addAll(readMore,clearButton,readCodesButton);

                    rightContainer.getChildren().addAll(currentCodeDescriptionContainer,currentCodeMeaningContainer,currentCodeSymptomContainer,currentCodeReasonContainer,currentCodeSolutionContainer,buttonsContainer);
                    rightContainer.prefHeightProperty().bind(pane.heightProperty());
                Container.getChildren().addAll(leftConteiner,rightContainer);     
                pane.setCenter(Container); 

        if(!SerialUtils.isSerialPortOpened())
        {
           ModalWindowController.openModalWindow(LN.getString("errorCodesPage.noCarConnection.title"),LN.getString("errorCodesPage.noCarConnection.message"),getConnectionNotAvailableModalWindowButton(),Globals.root);                           
        }
    }
    /**
     * generates the clear codes modal window buttons
     **/
    private ArrayList<Button> getClearCodesModalWindowButtons()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonClearCodes = new Button(LN.getString("errorCodesPage.confirmClearCode.clearButton"));
            buttonClearCodes.setId("buttonClearCodes");
            buttonClearCodes.setOnAction(new EventHandler<ActionEvent>() 
            {
                public void handle(ActionEvent e) 
                { 
                    Others.resetTroubleCodes();
                    SceneDispatcher.getScene(ErrorCodesPage.class,Globals.root,Globals.pane);
                }
            });
        buttons.add(buttonClearCodes);
        return buttons;
    }
    /**
     * generates the connection not available modal window buttons 
     ***/
    private ArrayList<Button> getConnectionNotAvailableModalWindowButton()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonSettings = new Button(LN.getString("errorCodesPage.noCarConnection.settingsButton"));
            buttonSettings.setId("buttonSettingsErrorCodesModal");
            buttonSettings.setOnAction(new EventHandler<ActionEvent>() 
            {
                public void handle(ActionEvent e) 
                { 
                    ModalWindowController.closeModalWindow(Globals.root, Globals.modalWindow);
                    SceneDispatcher.getScene(SettingsPage.class,Globals.root,Globals.pane);
                }
            });
        buttons.add(buttonSettings);
        return buttons;
    }

    @Override
    public ImageView getEntryIcon() 
    {
        ImageView CheckEngineButton = new ImageView();
        CheckEngineButton.setId("CheckEngineButtonLandingPage");
        CheckEngineButton.setCursor(Cursor.HAND);
        CheckEngineButton.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                SceneDispatcher.getScene(ErrorCodesPage.class,Globals.root,Globals.pane);
            }
        });
        return CheckEngineButton;
    }
}
