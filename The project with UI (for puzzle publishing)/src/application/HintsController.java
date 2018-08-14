package application;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.*;

import application.fxmlController.MyNode;
import application.fxmlController.myLabel;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class HintsController implements Initializable{
	
	static MyNode[][] matrix;
	@FXML
	private Pane pane;
	@FXML
	private JFXListView<myLabel> across1;	
	@FXML
	private JFXListView<myLabel> down1;	
    public static char[][] arr;
    static int n,m;
    static public LinkedList<String> hors =new LinkedList<String>();
	static public LinkedList<String> vers =new LinkedList<String>();
	static public List<var> Hvars = new LinkedList<var>();
	static public List<var> Vvars = new LinkedList<var>();
	static public HashMap<String,String> map=new HashMap<String,String>();
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		
		
		
		System.out.println("hhhhhhhhhhfffffffffffffffffffffffffffffffffffffffff");
		//System.out.println(map.get(Vvars.get(1).val));
		//System.out.println((Vvars.get(1).val));

        //addToAcrossAndDown();
		System.out.println("your heeeeeeeeeeeere");
		printList(hors);
		System.out.println("neeeeext");
		printList(vers);
		
		
		System.out.println("init 3");
		
		Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
        
            public void handle(WorkerStateEvent event) {
                System.out.println("what");
                goo();
                initData();
                addToAcrossAndDown();
                }
        });
        new Thread(sleeper).start();
		
	}
	
public void addToAcrossAndDown() {
		
		for(int i=0;i<Vvars.size();i++) {
			if(map.get(Vvars.get(i).val)==null) {
				map.put(Vvars.get(i).val, Vvars.get(i).val);
			}
		      myLabel lbl=new myLabel((Vvars.get(i).id)+")   "+map.get(Vvars.get(i).val), 0);
		      
				
				//System.out.println("w="+(lbl.lbl).getWidth());
				
		      lbl.x.setText("");
		      across1.getItems().add(lbl);
		      
				
				
				}
		
		for(int i=0;i<Hvars.size();i++) {
			if(map.get(Hvars.get(i).val)==null) {
				map.put(Hvars.get(i).val, Hvars.get(i).val);
			}
			
			myLabel lbl=new myLabel(Hvars.get(i).id+") "+map.get(Hvars.get(i).val), 0);
				
			
				//System.out.println("w="+(lbl.lbl).getWidth());
	          lbl.x.setText("");
		      down1.getItems().add(lbl);
				
				}
		
	}
	
	public void goo() {
		
		int n = Main.n;
		int m = Main.m;
		System.out.println("n "+n);
		System.out.println("m "+m);

		double sceneWidth = pane.getWidth();
		double sceneHeight = pane.getHeight();

		double gridWidth = (sceneWidth / n);
		double gridHeight = (sceneHeight / m);

		matrix = new MyNode[n][m];
	

		Group root = new Group();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				int color=0; //white
				String name="";
				
				
				MyNode node = new MyNode(name,j,i, i * gridWidth, j * gridHeight, gridWidth, gridHeight,color);
				
				
				root.getChildren().add(node);

				matrix[i][j] = node; //remember

			}
		}
		

		pane.getChildren().add(root);
		

	}
	
	public void initData() {
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j].rectangle.setFill(Color.WHITE);
				if(arr[j][i] == '*') {
					matrix[i][j].rectangle.setFill(Color.BLACK);
				}
			}
		}
		int x=0;
		
		for(int i=0;i<Vvars.size();i++) {
//			System.out.println(Vvars.get(i).val + " +++++++ "
//					+ Vvars.get(i).x+","+Vvars.get(i).y+"");
			matrix[Vvars.get(i).y][Vvars.get(i).x].label.setText((i+1)+"");
			x=i;
			Vvars.get(i).id=i+1;

		}
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjj");
		
			for(int i=0;i<Hvars.size();i++) {	
//			System.out.println(Hvars.get(i).val + " +++++++ "
//					+ Hvars.get(i).x+","+Hvars.get(i).y);
			if(matrix[Hvars.get(i).y][Hvars.get(i).x].label.getText().compareTo("")!=0) {
				Hvars.get(i).id=Integer.parseInt(matrix[Hvars.get(i).y][Hvars.get(i).x].label.getText());
			}else {
			matrix[Hvars.get(i).y][Hvars.get(i).x].label.setText(x+1+"");
			Hvars.get(i).id=x+1;
			}
			
			x++;	
		}
		
		
		
	}
	public void initData2() {
		
		//System.out.println("wasssaaaaaaaaaaaaaaaaap");
		
	}
	public static void printList(LinkedList<String> list) {
		  
		  for(int i=0;i<list.size();i++) {
			  System.out.print("   "+list.get(i));
		  }
		  System.out.println();
	  }

}












