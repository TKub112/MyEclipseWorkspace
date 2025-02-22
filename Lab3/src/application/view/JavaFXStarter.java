package application.view;
	
import application.view.JavaFXController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class JavaFXStarter extends Application {
	@FXML
	private void initialize() 
	{
		
	}
	 @Override
	    public void start(Stage stage) throws Exception {
		   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));
		   Parent root =(Parent) fxmlLoader.load();
		   
	       JavaFXController fooController = fxmlLoader.getController();

	       fooController.name1.setText("working");
	      
	       Scene scene = new Scene(root, 300, 275);
	        
	        
	        
	       stage.setTitle("Race");
	        
	        
	       stage.setScene(scene);
	
	        
	        stage.show();
	       
	        
	       
	        
	    }
	public static void main(String[] args) {
		launch(args);
	}
}
