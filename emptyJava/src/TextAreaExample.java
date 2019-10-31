import java.io.FileInputStream;
import java.io.IOException;

import ch.makery.address.MainApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
 
public class TextAreaExample extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) throws IOException 
    {
        // Create the FXMLLoader 
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        
        loader.setLocation(TextAreaExample.class.getResource("button.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
         
        // Create the Pane and all Details
        
         
        // Create the Scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("MainTheme.css").toExternalForm());
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("A SceneBuilder Example");
        // Display the Stage
        stage.show();
    }
}