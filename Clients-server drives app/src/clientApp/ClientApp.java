package clientApp;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 * A client application runned with 2 arguments
 * @author Tobiasz Kubiak
 * @version 1.0
 */
public class ClientApp extends Application {
    	
    
    
    ObservableList<String> filesOnClient = FxGetFolder.getListFilesFolder(pathOfUser);
    ObservableList<String> filesOnServer;
    ObservableList<String> userlist;
    Timer timeResponder = new Timer();
    Scene mainscene;
    static String pathOfUser = "";
    static String nameOfUser = "random";
    Label stan  = new Label("Gotowosc do pracy");
    
    
    /**
     * This method is the entry point for JavaFX applications.
     * It is responsible for the graphical interface, application behavior after pressing specific buttons
     * and cyclic refresh of the file list in the folder.
     */
    @Override
    public void start(Stage primaryStage) {
	try {
	    //state
		
		
		
		stan.setFont(new Font("Arial", 14));
		stan.setPrefHeight(50);
		stan.setTextFill(Paint.valueOf("red"));
		
		HBox root = new HBox(5);

		//window
		VBox window1 = new VBox(5);
		window1.setPadding(new Insets(5));
		Label menuLabel = new Label("Menu");
		Label addLabel = new Label("Dodaj plik");
		Button refreshButton = new Button("Odswiez");
		refreshButton.setMinWidth(120);
		Button shareButton = new Button("Udostepnij");
		shareButton.setMinWidth(120);
		Button endButton = new Button("Zakoncz");
		endButton.setMinWidth(120);
		Button deleteButton = new Button("Usun");		
		deleteButton.setMinWidth(120);
		Button resetButton = new Button("Serwer reset");	
		resetButton.setMinWidth(120);
		Button compressButton = new Button("Kompresuj plik");
		compressButton.setMinWidth(120);
		Button choosefileButton = new Button("Wybierz...");
		choosefileButton.setMinWidth(120);
		FileChooser fileChooser = new FileChooser();
		
		
		
        
        choosefileButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            Thread t = new Thread(new FileCopy(selectedFile,pathOfUser));
			
			t.start();
            
          
        });
		
		window1.getChildren().addAll(addLabel,choosefileButton,menuLabel,deleteButton,refreshButton,shareButton,resetButton,compressButton,endButton);
		VBox wind2 = new VBox(5);
		wind2.setPadding(new Insets(5));
		Label plikiLabel = new Label("Pliki uzytkownika "+nameOfUser);
		ListView<String> filesList = new ListView<>();
		filesList.setPrefWidth(180);
		filesList.setPrefHeight(250);
		
		wind2.getChildren().addAll(plikiLabel,filesList,stan);

		VBox wind3 = new VBox(5);
		wind3.setPadding(new Insets(5));
		Label label3 = new Label("Dostepni uzytkownicy");
		
		ListView<String> usersList = new ListView<>();
			
		usersList.setPrefWidth(180);
		usersList.setPrefHeight(250);
		wind3.getChildren().addAll(label3,usersList);
			
			
		
			
		refreshButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {
			Thread t = new Thread(new UpdateClass(nameOfUser,pathOfUser,filesOnClient,
				usersList,refreshButton,stan,3));
			
			t.start();
		    }
		});
		
		compressButton.setOnAction(new EventHandler<ActionEvent>(){
		    public void handle(ActionEvent event) {
			Thread t = new Thread(new FileCompress(filesList.getSelectionModel().getSelectedItem(),pathOfUser));
			
			t.start();
		    }
		});
		
		resetButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {
			Thread t = new Thread(new UpdateClass(nameOfUser,pathOfUser,filesOnClient,
				usersList,resetButton,stan,4));
			
			t.start();
			
		    }
		});
		
			
		shareButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {
			
			Thread t = new Thread(new UpdateClass(nameOfUser,usersList.getSelectionModel().getSelectedItem(),
				filesList.getSelectionModel().getSelectedItem(), pathOfUser, stan,2));
			
			t.start();
			
			shareButton.setDisable(true);
			
			(new Timer()).schedule(new TimerTask()
				 {
				    @Override
				    public void run() {
					shareButton.setDisable(false);
					
				    }	     
			},2000);
			
		    }
		});
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {
			
		    	
		    	Thread t = new Thread(new UpdateClass(nameOfUser,"blad",
						filesList.getSelectionModel().getSelectedItem(), pathOfUser, stan,1));
	
					
					t.start();
		    }
		});
		
		
		
			
		endButton.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {
			Platform.exit();
		    }
		});
			
		root.getChildren().addAll(window1,wind2,wind3);
			
		
		
		
		
		
		timeResponder.scheduleAtFixedRate( new GetFolderDiffrence(filesList, usersList, filesOnClient, pathOfUser,nameOfUser, refreshButton, stan), 0, 3000);
		
		
		timeResponder.schedule(new TimerTask()
		{
		    
		    @Override
		    public void run() {
			if(filesOnClient != null)
			{
			    Thread t = new Thread(new UpdateClass(nameOfUser,pathOfUser,filesOnClient,
				    usersList,refreshButton,stan,3));
        				
			    t.start();
			}else
			{
			    stan.setText("Niepoprawna sciezka!");
			    refreshButton.setDisable(true);
			    shareButton.setDisable(true);
			}
		    }
		    
		}, 1000);
		
		
		mainscene = new Scene(root,580,350);
		primaryStage.setTitle( "Client "+ nameOfUser);
		primaryStage.setScene(mainscene);
		primaryStage.setOpacity(0.99);
		primaryStage.show();
			
		
	} 
	catch(NullPointerException e) 
	{
	    stan.setText("brak dostepu do folderow!");
	    try 
	    {
		Thread.sleep(5000);
	    } 
	    catch (InterruptedException e1) 
	    {
		e1.printStackTrace();
	    }
	    stop();
	}
    }
	
    public static void main(String[] args) {
	try
	{
	    nameOfUser = args[0];
	    pathOfUser = args[1];
	}
	catch(ArrayIndexOutOfBoundsException e)
	{
	    System.out.println("Brak argumentow wejsciowych");
	}
	
	launch(args);
    }

    @Override
    public void stop(){
	    System.out.println("shutdown application");
	    timeResponder.cancel();
    }
}


