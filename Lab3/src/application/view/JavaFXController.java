package application.view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import application.*;


public class JavaFXController {
	@FXML
    public GridPane grid;
    @FXML
    public Label time1;
    @FXML
    public Label name1;
    @FXML
    public Label time2;
    @FXML
	public Label name2;
    @FXML
    public Label time3;
    @FXML
    public Label name3;
    @FXML
    public Label time4;
    @FXML
    public Label name4;

    // Reference to the main application.
    private JavaFXStarter mainApp;
    
    public void setLabel1(String s){
    	name1.setText(s); 
    }

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public JavaFXController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
   
    @FXML
    private void initialize() {
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    
    public void setMainApp(JavaFXStarter mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }
}
