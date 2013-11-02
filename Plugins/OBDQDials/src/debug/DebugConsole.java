/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import com.queeq.obdq.dials.scenes.view.DialsPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class DebugConsole extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        StackPane root = new StackPane();
        BorderPane pane =new BorderPane();
        root.getChildren().add(pane);
        //root.getChildren().add(btn);
        new DialsPage().getPageContent(root,pane);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:///"+System.getProperty("user.dir")+"/themes/orangeq/css/stylesheet.css");
        primaryStage.setTitle("Debug view");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
