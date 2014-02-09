/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.scenes;


import com.queeq.obdq.internationalization.LN;
import com.queeq.obdq.scenes.controller.ModalWindowController;
import com.queeq.obdq.scenes.impementation.support.PageFooter;
import com.queeq.obdq.scenes.impementation.support.PageHeader;
import com.queeq.obdq.scenes.view.templates.LandingPageTemplate;
import com.queeq.obdq.settings.Globals;
import com.queeq.obdq.settings.SettingsUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author admin
 */
public class LandingPage extends LandingPageTemplate 
{
    public LandingPage()
    {
    }

    @Override
    public void getPageContent(StackPane root,BorderPane pane) 
    {
        root.getChildren().clear();
        /**Page layout below**/
    
        // define background image
            ImageView background = new ImageView();
            background.setId("backgroundImageLandingPage");
        root.getChildren().add(background);
        

            //set header       
            pane.setTop(PageHeader.getPageHeader(pane));

            //define body
                TilePane iconsPane = new TilePane();
                iconsPane.setPrefColumns(4);
                iconsPane.setHgap(5);
                iconsPane.setVgap(5);
                iconsPane.setPadding(new Insets(10,10,10,10));
                int EntryPageListSize=Globals.EntryPageListContent.size();
                for(int i=0;i<EntryPageListSize;i++)
                {
                    try
                    {
                        iconsPane.getChildren().add(getEntryIconButton((Class)Globals.EntryPageListContent.get(i)));
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
               }

            pane.setCenter(iconsPane);
            //set footer content
            pane.setBottom(PageFooter.getPageFooter());
        root.getChildren().add(pane);
        if(!SettingsUtils.isLicenseAccepted())
        {
           ModalWindowController.openModalWindow(LN.getString("landingPage.License.title"),LN.getString("landingPage.License.message"),getConnectionNotAvailableModalWindowButton(),Globals.root,false);                           
        }

    }
     private static ImageView getEntryIconButton(Class tmpCls) throws Exception
    {
       // Class tmpCls=(Class)toSort.get(i);
        Object tmpObj = tmpCls.newInstance();
        Method method = tmpCls.getMethod("getEntryIcon", null);
        return (ImageView)method.invoke(tmpObj, null);
    }
     /**
     * Generates the connection not available modal window buttons 
     ***/
    private ArrayList<Button> getConnectionNotAvailableModalWindowButton()
    {
        ArrayList<Button> buttons = new ArrayList(); 
            Button buttonAcceptLicense = new Button(LN.getString("landingPage.buttonAcceptLicense"));
            buttonAcceptLicense.setId("buttonAcceptLicense");
            buttonAcceptLicense.setOnAction(new EventHandler<ActionEvent>() 
            {
                public void handle(ActionEvent e) 
                {
                    SettingsUtils.acceptLicese();
                    ModalWindowController.closeModalWindow(Globals.root, Globals.modalWindow);
                }
            });
            Button buttonRejectLicense = new Button(LN.getString("landingPage.buttonRejectLicense"));
            buttonRejectLicense.setId("buttonRejectLicense");
            buttonRejectLicense.setOnAction(new EventHandler<ActionEvent>() 
            {
                public void handle(ActionEvent e) 
                {
                    System.exit(1);
                }
            });
        buttons.add(buttonAcceptLicense);
        buttons.add(buttonRejectLicense);
        return buttons;
    }
}
