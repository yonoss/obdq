/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes.controller;

import com.queeq.obdq.elm327.Others;
import com.queeq.obdq.errorcodes.*;
import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.settings.ObdqProperties;
import com.queeq.obdq.utils.SystemUtils;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

/**
 *
 * @author admin
 */
public class ErrorCodesPageController 
{
    /**
     *Generate the trouble codes list
     * @return ArrayList
     **/
    public static ArrayList<String> getCodesArray()
    {   
        ArrayList<String> result=new ArrayList();
        Others.getTroubleCodes(result);
        return result;
    }
    /**
     *Sets the error codes list
     * @param errorCodesListView
     * @param codeList 
     * @param currentCodeTextArea 
     * @param codeDescriptionTextArea 
     * @param codeCauseTextArea 
     * @param codeMeaningTextArea 
     * @param codeSolutionTextArea 
     * @param codeSymptomTextArea 
     **/
    public static void setErrorCodesList(ListView<String> errorCodesListView,
                                        ArrayList<String> codeList,
                                        TextArea currentCodeTextArea,
                                        TextArea codeDescriptionTextArea,
                                        TextArea codeCauseTextArea,
                                        TextArea codeMeaningTextArea,
                                        TextArea codeSolutionTextArea,
                                        TextArea codeSymptomTextArea)
    {
            errorCodesListView.setItems(FXCollections.observableArrayList(codeList));
            errorCodesListView.getSelectionModel().selectFirst();
            String initialSelection= errorCodesListView.getSelectionModel().getSelectedItem();
           
             if(initialSelection!=null)
                            ErrorCodesPageController.updateDescriptionAreas(initialSelection,currentCodeTextArea,codeDescriptionTextArea,codeCauseTextArea,codeMeaningTextArea,codeSolutionTextArea,codeSymptomTextArea);
                        
    }
    /**
     * clears the error codes list
     * @param errorCodesListView 
     * @param currentCodeDescriptionTextArea 
     * @param currentCodeSolutionTextArea 
     * @param currentCodeReasonTextArea 
     * @param currentCodeMeaningTextArea 
     * @param currentCodeTextArea 
     * @param currentCodeSymptomTextArea 
     **/
    public static void clearErrorCodesList(ListView<String> errorCodesListView,
                                           TextArea currentCodeDescriptionTextArea,
                                           TextArea currentCodeSolutionTextArea,
                                           TextArea currentCodeReasonTextArea,
                                           TextArea currentCodeMeaningTextArea,
                                           TextArea currentCodeTextArea,
                                           TextArea currentCodeSymptomTextArea)
    {
            errorCodesListView.setItems(null);
            currentCodeDescriptionTextArea.clear();
            currentCodeSolutionTextArea.clear();
            currentCodeReasonTextArea.clear();
            currentCodeMeaningTextArea.clear();
            currentCodeTextArea.clear();
            currentCodeSymptomTextArea.clear();      
    }
    /**
     * Update the description area
     * @param selectedCode 
     * @param codeDescriptionTextArea 
     **/
    public static void updateDescriptionArea(Code selectedCode, TextArea codeDescriptionTextArea)
    {
        String content="";
        if(selectedCode!=null) 
        {
            Descriptions descriptions=selectedCode.getDescriptions();
            if(descriptions!=null)
            {
                ArrayList<Description> dsc=descriptions.getDecriptions();
                int descSize=dsc.size(); 
                for(int i=0;i<descSize;i++)
                {
                    content=content+"- "+dsc.get(i).getValue()+"\n";
                }
            }
            if(!content.equals(""))
            {
                codeDescriptionTextArea.setText(content);
            }
            else
            {
                codeDescriptionTextArea.setText(LN.getString("errorCodesPage.NA"));    
            }
 
        }
        
        if(!content.equals(""))
        {
            codeDescriptionTextArea.setText(content);
        }
        else
        {
            codeDescriptionTextArea.setText(LN.getString("errorCodesPage.NA"));    
        }
    }
    /**
     * Updates the code cause area
     * @param selectedCode
     * @param currentCodeCauseTextArea
     **/
    public static void updateCodeCauseArea(Code selectedCode, TextArea currentCodeCauseTextArea)
    {
        String content="";
        if(selectedCode!=null) 
        {            
            Causes causes=selectedCode.getCauses();
            if(causes!=null)
            {
                ArrayList<Cause> caus=causes.getCauses();
                int Size=caus.size(); 
                for(int i=0;i<Size;i++)
                {
                    content=content+"- "+caus.get(i).getValue()+"\n";
                }
            }
        }
        if(!content.equals(""))
        {
            currentCodeCauseTextArea.setText(content);
        }
        else
        {
            currentCodeCauseTextArea.setText(LN.getString("errorCodesPage.NA"));    
        }
    }
    /**
     * Updates the code meaning area
     * @param selectedCode
     * @param codeMeaningTextArea
     **/
    public static void updateCodeMeaningArea(Code selectedCode, TextArea codeMeaningTextArea)
    {
        String content="";
        if(selectedCode!=null) 
        {            
            Meanings meanings=selectedCode.getMeanings();
            if(meanings!=null)
            {
                ArrayList<Meaning> mean=meanings.getMeanings();
                int Size=mean.size(); 
                for(int i=0;i<Size;i++)
                {
                    content=content+"- "+mean.get(i).getValue()+"\n";
                }
            }
        }
        if(!content.equals(""))
        {
            codeMeaningTextArea.setText(content);
        }
        else
        {
            codeMeaningTextArea.setText(LN.getString("errorCodesPage.NA"));    
        }
    }
    /***
     * Updates the solution area
     * @param selectedCode
     * @param codeSolutionTextArea
     **/
    public static void updateSolutionArea(Code selectedCode, TextArea codeSolutionTextArea)
    {
        String content="";
        if(selectedCode!=null) 
        {            
            Solutions solutions=selectedCode.getSolutions();
            if(solutions!=null)
            {
                ArrayList<Solution> sol=solutions.getSolutions();
                int Size=sol.size(); 
                for(int i=0;i<Size;i++)
                {
                    content=content+"- "+sol.get(i).getValue()+"\n";
                }
            }
        }
        if(!content.equals(""))
        {
            codeSolutionTextArea.setText(content);
        }
        else
        {
            codeSolutionTextArea.setText(LN.getString("errorCodesPage.NA"));    
        }
    }
    /***
     * Update the contempt on the Symptom text area
     * @param selectedCode - the selected code
     * @param codeSymptomTextArea - text area to be updated 
     **/
    public static void updateCodeSymptomArea(Code selectedCode, TextArea codeSymptomTextArea)
    {
        String content="";
        if(selectedCode!=null) 
        {            
            Symptoms symptoms=selectedCode.getSymptoms();
            if(symptoms!=null)
            {
                ArrayList<Symptom> sympt=symptoms.getSymptoms();
                int Size=sympt.size(); 
                for(int i=0;i<Size;i++)
                {
                    content=content+"- "+sympt.get(i).getValue()+"\n";
                }
            }
        }
        if(!content.equals(""))
        {
            codeSymptomTextArea.setText(content);
        }
        else
        {
            codeSymptomTextArea.setText(LN.getString("errorCodesPage.NA"));    
        }
    }
    /**
     * updates the current code area 
     * @param selectedCode
     * @param currentCodeTextArea
     **/
    public static void updateCurrentCodeArea(String selectedCode, TextArea currentCodeTextArea)
    {
            currentCodeTextArea.setText(selectedCode);

    }
    /**
     *Update description areas
     * @param selectedCode
     * @param currentCodeTextArea
     * @param codeDescriptionTextArea
     * @param codeCauseTextArea
     * @param codeMeaningTextArea
     * @param codeSolutionTextArea
     * @param codeSymptomTextArea
     **/
    public static void updateDescriptionAreas(String selectedCode,
                                              TextArea currentCodeTextArea,
                                              TextArea codeDescriptionTextArea,
                                              TextArea codeCauseTextArea, 
                                              TextArea codeMeaningTextArea,
                                              TextArea codeSolutionTextArea,
                                              TextArea codeSymptomTextArea)
    {
        Code code=null;
        try
        {
            code =new ErrorCodeManager().loadErrorCode(selectedCode,ObdqProperties.errorCodesPath+ObdqProperties.defaultLanguage+"/");
        }
        catch(Exception e)
        {
            try
            {
                code =new ErrorCodeManager().loadErrorCode(selectedCode,ObdqProperties.errorCodesPath+"en/");
            }
            catch(Exception f)
            {}
        }
        updateCurrentCodeArea(selectedCode, currentCodeTextArea);
        updateDescriptionArea(code, codeDescriptionTextArea);
        updateSolutionArea(code,codeSolutionTextArea);
        updateCodeCauseArea(code,codeCauseTextArea);
        updateCodeMeaningArea(code,codeMeaningTextArea);
        updateCodeSymptomArea(code,codeSymptomTextArea);
    }
    /**
     * Opens a google query for the selected error code browser 
     * @param codeId - the id of the error code
     **/
    
    public static void readMore(String codeId)
    {
        SystemUtils.openLink("https://www.google.ca/#q="+codeId);
    }
}
