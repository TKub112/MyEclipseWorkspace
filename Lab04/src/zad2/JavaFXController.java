package zad2;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @FXML
    public Label scoreLabel;
    @FXML
    public Label timeLabel;
    // Reference to the main application.
    private JavaFXStarter mainApp;
    
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
    
    protected void setQuestions(String[] names)
    {
    	
    	question1.setText(names[0]);
    	question2.setText(names[1]);
    	question3.setText(names[2]);
    	question4.setText(names[3]);
    	question5.setText(names[4]);
    	start=System.nanoTime();
    	
        
    }
    protected Map<String,String> getAnswers()
    {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(question1.getText(), answer1.getText());
    	map.put(question2.getText(), answer2.getText());
    	map.put(question3.getText(), answer3.getText());
    	map.put(question4.getText(), answer4.getText());
    	map.put(question5.getText(), answer5.getText());
    	return map;
    }
    
    @FXML
    protected void buttonEventHandler(ActionEvent event) throws IOException{
    	stop = System.nanoTime();
    	sendAnswer.setDisable(true);
    	testTime = stop - start;
    	double testTime1=(double)testTime/1000000000;
    	
    	
    	zad2.JsonFile answer = zad2.createJsonFile(getAnswers());
    	
    	
    	score = zad2.getScore(answer);
    	scoreLabel.setText("Score:"+Double.toString(score));
    	
    	timeLabel.setText("Time" + Double.toString(round(testTime1,2))+"s");
    }
    
    public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
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
