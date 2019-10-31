package serverApp;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.prism.paint.Color;

import clientApp.Packet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *Server application is starting java fx panel for serve GUI. Refershing lists of disks on server. 
 *Running threads thats buffering a packet and making a packets.
 * @author Tobiasz Kubiak
 * @version 1.0
 */
public class ServerApp extends Application {
	BlockingQueue<Packet> taskQueue = new LinkedBlockingQueue<Packet>();
    
    Scene serverscene;
    Label stan2 = new Label("Start watku...");
    Label stan1 = new Label("Start watku...");
    
    
    
    
    
    
    
    PacketBuffer getPacket = new PacketBuffer(taskQueue);
    PacketMaker makeRespond = new PacketMaker(taskQueue,stan2);
    PacketMaker makeRespond2 = new PacketMaker(taskQueue,stan1);
    
    
    
   
    ObservableList<String> files1;
    ObservableList<String> files2;
    ObservableList<String> files3;
    ObservableList<String> files4;
    ObservableList<String> files5;
    Timer methodRefresher = new Timer();
    /**
     * Method that end Java FX GUI and stop application
     */
    @Override
    public void stop(){
	    System.out.println("Stage is closing");
	    getPacket.shutdown();
	    makeRespond.exitServer();
	    makeRespond2.exitServer();
	    methodRefresher.cancel();
    }
    
    /**
     * Method that runs Java FX GUI and running threads that getting packets and making packets
     */
    @Override
    public void start(Stage primaryStage)
    {
	
	HBox root = new HBox(5);
	stan1.setMinHeight(40);
	stan1.setMinWidth(300);
	stan1.setFont(new Font("Arial", 14));
	stan2.setMinHeight(40);
	stan2.setMinWidth(300);
	stan2.setFont(new Font("Arial", 14));

	VBox window1 = new VBox(4);
	window1.setPadding(new Insets(5));
	Label disc1Label = new Label("Dysk 1");
	ListView<String> filesList1 = new ListView<>();
	filesList1.setPrefWidth(160);
	filesList1.setPrefHeight(300);
	window1.getChildren().addAll(disc1Label,filesList1);
	
	
	VBox window2 = new VBox(4);
	window2.setPadding(new Insets(5));
	Label disc2Label = new Label("Dysk 2");
	ListView<String> filesList2 = new ListView<>();
	
	filesList2.setPrefWidth(160);
	filesList2.setPrefHeight(300);
	
	window2.getChildren().addAll(disc2Label,filesList2);
	
	
	VBox windows3 = new VBox(4);
	windows3.setPadding(new Insets(5));
	Label disc3Label = new Label("Dysk 3");
	ListView<String> filesList3 = new ListView<>();
	filesList3.setPrefWidth(160);
	filesList3.setPrefHeight(300);
	windows3.getChildren().addAll(disc3Label,filesList3);
	
	
	VBox window4 = new VBox(5);
	window4.setPadding(new Insets(5));
	Label disc4Label = new Label("Dysk 4");
	ListView<String> filesList4 = new ListView<>();
	filesList4.setPrefWidth(160);
	filesList4.setPrefHeight(300);
	
	window4.getChildren().addAll(disc4Label,filesList4);
	VBox window5 = new VBox(5);
	window5.setPadding(new Insets(5));
	Label label5 = new Label("Dysk 5");
	ListView<String> filesList5 = new ListView<>();
	filesList5.setPrefWidth(160);
	filesList5.setPrefHeight(300);
	window5.getChildren().addAll(label5,filesList5);

	VBox window6 = new VBox(5);
	window6.setPadding(new Insets(5));

	Label thread1Info = new Label("Watek 1:");
	Label thread2Info = new Label("Watek 2:");
	thread1Info.setFont(new Font("Cambria", 30));
	thread2Info.setFont(new Font("Cambria", 30));
	stan1.setTextFill(Paint.valueOf("red"));
	stan2.setTextFill(Paint.valueOf("red"));
	Button endButton = new Button("Zakoncz");
	
	
	window6.setPrefWidth(150);
	window6.setPrefHeight(300);
	VBox window0 = new VBox(5);
	window0.setPadding(new Insets(5));
	window0.setPrefWidth(150);
	window0.setPrefHeight(300);
	
	endButton.setOnAction(new EventHandler<ActionEvent>(){
	    @Override
	    public void handle(ActionEvent event) {
		Platform.exit();
	    }
	});

	
	window6.getChildren().addAll(thread1Info, stan2, thread2Info, stan1,endButton);
	
	root.getChildren().addAll(window0,window6,window1,window2,windows3,window4,window5);

	methodRefresher.scheduleAtFixedRate(new TimerTask() {

	    @Override
	    public void run() {
		
		ObservableList<String> updateList1 = FxGetfolder1.readFolder("serverDiscs\\1");
		ObservableList<String> updateList2 = FxGetfolder1.readFolder("serverDiscs\\2");
		ObservableList<String> updateList3 = FxGetfolder1.readFolder("serverDiscs\\3");
		ObservableList<String> updateList4 = FxGetfolder1.readFolder("serverDiscs\\4");
		ObservableList<String> updateList5 = FxGetfolder1.readFolder("serverDiscs\\5");
		
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run()
			    {
				
				files3 = updateList3;
				filesList3.setItems(files3);
				files4 = updateList4;
				filesList4.setItems(files4);
				files5 = updateList5;
				filesList5.setItems(files5);
				files1 = updateList1;
				filesList1.setItems(files1);
				files2 = updateList2;
				filesList2.setItems(files2);
			    }
	        }); 
		
	    }
	    
	},2,2000);
	
	serverscene = new Scene(root);
	primaryStage.setTitle("Serwer");
	primaryStage.setScene(serverscene);
	primaryStage.setOpacity(0.98);
	primaryStage.show();
	
	Thread getter = new Thread(getPacket);
	getter.start();
	
	Thread maker1 = new Thread(makeRespond);
	Thread maker2 = new Thread(makeRespond2);
	maker1.start();
	maker2.start();
	
    }
    
    public static void main(String[] args) {
	launch(args);
    }
    
    
}
