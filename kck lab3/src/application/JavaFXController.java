package application;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sun.corba.se.pept.transport.EventHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class JavaFXController {
	boolean gotRespond = false ;
	@FXML
    public GridPane grid;
    @FXML
    public Label question1;
    @FXML
    public Label question2;
    @FXML
    public Label question3;
    @FXML
	public Label question4;
    @FXML
    public Label question5;
    @FXML
    public Button sendAnswer;
    @FXML
    public TextField answer1;
    @FXML
    public TextField answer2;
    @FXML
    public TextField answer3;
    @FXML
    public TextField answer4;
    @FXML
    public TextField answer5;
    

    // Reference to the main application.
    
    
    private long start;
    private long stop;
    private long testTime;
    private double score;


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
    
   
    
    @FXML
    protected void buttonEventHandler(ActionEvent event) throws IOException{
    	
    }
    

	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    
    public void setMainApp() {
        
        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }
}
