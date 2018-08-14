package application;
	
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static int n,m;
	public static boolean isSplashLoaded=false; 
	
	@Override 
	public void start(Stage stage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	        Scene scene = new Scene(root);
	        //scene.getStylesheets().add("application/application.css");  
	        stage.setTitle("CrossWords Generator");
	        stage.setResizable(false);
	        stage.setScene(scene);
	        stage.show();
	       
	        
	          
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
	   // n=in.nextInt();
		//m=in.nextInt();
		launch(args);
	}
}
