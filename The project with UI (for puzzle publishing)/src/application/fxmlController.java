package application;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSplitPaneUI;

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
import javafx.stage.StageStyle;

import com.jfoenix.controls.*;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class fxmlController implements Initializable, ChangeListener {
	Timeline timeline = new Timeline();

	MyNode[][] matrix;
	boolean changeColor = true;
	LinkedList<String> wantedList = new LinkedList<String>();
	char[][] table = { { 'a', '*', ' ', 'o', 'k' }, { '*', 'l', ' ', ' ', '*' }, { ' ', ' ', ' ', ' ', 'm' },
			{ ' ', ' ', 'p', '*', ' ' }, { 'c', '*', ' ', ' ', 'k' }, };
	int n, m;

	@FXML
	private JFXButton add;

	@FXML
	private JFXTextField txt1;

	@FXML
	private JFXListView<myLabel> list;

	@FXML
	private Button black;

	@FXML
	private Button back;

	@FXML
	private Button hints;

	@FXML
	private Button button;

	@FXML
	private Button summary;

	@FXML
	private Pane pane;

	@FXML
	private JFXButton browse;

	@FXML
	private JFXTextField fileName;

	@FXML
	private JFXCheckBox box1;

	@FXML
	private JFXCheckBox box2;

	@FXML
	private JFXCheckBox box3;

	@FXML
	private JFXCheckBox box4;

	@FXML
	private JFXListView<myLabel> across1;

	@FXML
	private JFXListView<myLabel> down1;

	private boolean change = false;

	LinkedList<String> listOfWords = new LinkedList<String>();
	LinkedList<String> technology = new LinkedList<String>();
	LinkedList<String> arts = new LinkedList<String>();
	LinkedList<String> health = new LinkedList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (WelcomeController.whatChoosen == 1)

		{
			table = new char[][] { { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
					{ '*', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*' },
					{ ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
					{ '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', '*' },
					{ ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },

			};
			// WelcomeController.whatChoosen = 0;
		} else if (WelcomeController.whatChoosen == 2) {
			table = new char[][] { { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
					{ '*', '*', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', '*', '*', '*', '*' },
					{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
					{ '*', '*', '*', '*', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', '*', '*' },
					{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' },
					{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' }

			};

			// WelcomeController.whatChoosen = 0;
		}

		speedBar.valueChangingProperty().addListener(this);
		speedBar.setDisable(true);
		speedBar.setValue(5);
		hints.setDisable(true);
		summary.setDisable(true);

		box4.setSelected(true);
		box1.setSelected(true);
		box2.setSelected(true);
		box3.setSelected(true);

		fileName.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("fileName changed from " + oldValue + " to " + newValue);
			myFunc();

		});

		box1.selectedProperty().addListener((observable, o, n) -> {

			if (box1.isSelected() && box2.isSelected() && box3.isSelected()) {
				box4.setSelected(true);
			} else {
				// box4.setSelected(false);
			}
			checkBoxes();
		});
		box2.selectedProperty().addListener((observable, o, n) -> {

			if (box1.isSelected() && box2.isSelected() && box3.isSelected()) {
				box4.setSelected(true);
			} else {
				// box4.setSelected(false);
			}
			checkBoxes();
		});
		box3.selectedProperty().addListener((observable, o, n) -> {
			if (box1.isSelected() && box2.isSelected() && box3.isSelected()) {
				box4.setSelected(true);
			} else {
				// box4.setSelected(false);
			}

			checkBoxes();
		});

		box4.selectedProperty().addListener((observable, o, n) -> {
			change = false;
			if (box4.isSelected()) {
				box1.setSelected(true);
				box2.setSelected(true);
				box3.setSelected(true);
			} else if (box1.isSelected() && box2.isSelected() && box3.isSelected()) {
				box1.setSelected(false);
				box2.setSelected(false);
				box3.setSelected(false);
			}
			checkBoxes();
			change = true;
		});

		box4.setSelected(false);
		box4.setSelected(true);
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
			}
		});
		new Thread(sleeper).start();

	}

	public void checkBoxes() {

		wantedList.clear();
		if (box1.isSelected()) {
			wantedList.addAll(health);
		}
		if (box2.isSelected()) {
			wantedList.addAll(technology);
		}
		if (box3.isSelected()) {
			wantedList.addAll(arts);
			System.out.println("selected");
		}
		if (box4.isSelected()) {
			wantedList.clear();
			wantedList.addAll(health);
			wantedList.addAll(technology);
			wantedList.addAll(arts);
		}
		changeList(wantedList);

		if (change) {
			if (!(box1.isSelected() && box2.isSelected() && box3.isSelected())) {
				box4.setSelected(false);
			}
		}

	}

	@FXML
	public void makeBlack() {
		changeColor = !changeColor;
	}

	boolean isDone = false;
	boolean isGenerated = false;

	@FXML
	public boolean next() throws CloneNotSupportedException, IOException {
		System.out.println(isDone);
		if (!isGenerated) {
			status.setText("");
			isDone = !generate();
			if (isDone) {
				timeline.stop();
			}
		}

		if (!isDone) {
			System.out.println("-->" + currentGrid);
			currentGrid2 = new char[currentGrid.length][currentGrid[0].length];
			for (int i = 0; i < currentGrid.length; i++) {

				for (int j = 0; j < currentGrid[0].length; j++) {
					currentGrid2[i][j] = currentGrid[i][j];
				}
			}
			currentGrid = iterate(currentGrid);
			if (currentGrid == null) {
				if (states.isEmpty()) {
					System.out.println("no solution found");
					ErrorController.msg = "Sorry, no solution found!";
					showError();
				} else {
					currentGrid = printBasedOnState(states.peek(), currentGrid2);
					table = currentGrid;
					changeGrid();

					status.setText(str2);

					System.out.println("Optimal solution found");
				}
				isDone = true;
			}
			return false;

		} else {
			return true;
		}

	}

	@FXML
	private JFXSlider speedBar;

	@FXML
	private void animation(ActionEvent ev) throws CloneNotSupportedException, IOException {

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		System.out.println(speedBar.getValue());
		timeline.stop();
		System.out.println("accessed!2");

		boolean f = false;
		System.out.println(f);
		if (!isGenerated)
			f = next();
		System.out.println(f);
		if (f || (isGenerated && !isDone)) {
			speedBar.setDisable(false);
			ani(speedBar.getValue());
		}
	}

	@FXML
	private Label status;

	String str2 = "";

	public char[][] iterate(char[][] grid) throws CloneNotSupportedException, IOException {

		String str = "Iteration#" + iterationNumber + " |";

		iterationNumber++;

		System.out.println("This is iteration #" + iterationNumber);

		// printing the stack
		printStack(states);
		if (states.empty()) {
			return null;
		}

		// creating an array list of variables
		ArrayList<Variable> temp = new ArrayList<Variable>();

		// printing the current grid
		currentGrid = printBasedOnState(states.peek(), grid);

		LinkedList<String> hors = new LinkedList<String>();
		LinkedList<String> vers = new LinkedList<String>();

		List<var> Hvars = new LinkedList<var>();
		List<var> Vvars = new LinkedList<var>();

		// checking if the current grid is solved
		if (isSolved(currentGrid)) {
			str += "The Puzzle is solved!";
			generate.setDisable(true);
			animation.setDisable(true);
			nextBtn.setDisable(true);

			hints.setDisable(false);
			summary.setDisable(false);

			for (int i = 0; i < states.peek().variables.size(); i++) {
				if (states.peek().variables.get(i).startX == states.peek().variables.get(i).endX) {
					hors.add(states.peek().variables.get(i).currentValue);
					Hvars.add(new var(states.peek().variables.get(i).currentValue,
							states.peek().variables.get(i).startX, states.peek().variables.get(i).startY));
				} else {
					vers.add(states.peek().variables.get(i).currentValue);
					Vvars.add(new var(states.peek().variables.get(i).currentValue,
							states.peek().variables.get(i).startX, states.peek().variables.get(i).startY));
				}

			}

			Collections.sort(Hvars);
			Collections.sort(Vvars);

			for (int i = 0; i < Hvars.size(); i++) {
				System.out.println(Hvars.get(i).val + " +++++++ " + Hvars.get(i).x + "," + Hvars.get(i).y);
				Hvars.get(i).id = i + 1;
			}
			for (int i = 0; i < Vvars.size(); i++) {
				System.out.println(Vvars.get(i).val + " +++++++ " + Vvars.get(i).x + "," + Vvars.get(i).y);
				Vvars.get(i).id = i + 1; // keep
			}
			HintsController.Hvars = new ArrayList<var>(Hvars);
			HintsController.Vvars = new ArrayList<var>(Vvars);

			System.out.println("hauu");

			changeColor = false;

			// momkn
			table = currentGrid;

			changeGrid();

			//printList(hors);
			HintsController.hors.clear();
			for (int i = 0; i < hors.size(); i++) {
				HintsController.hors.add(hors.get(i));
			}
			// printList(vers);
			HintsController.vers.clear();
			;
			for (int i = 0; i < vers.size(); i++) {
				HintsController.vers.add(vers.get(i));
			}

			addToAcrossAndDown(hors, vers);

			return null;

		}

		// cloning the peek to the temp
		for (Variable v : states.peek().variables) {
			temp.add((Variable) v.clone());
		}

		// creating a new temp that holds the original temp values sorted by ID
		ArrayList<Variable> temp2 = new ArrayList<Variable>(temp);

		// sorting the new temp
		Collections.sort(temp2, sortByID);

		// getting the variable with the highest heuristic
		Variable choosenVariable = temp.get(0);

		int cc = choosenVariable.mostConstrainingVariableHeuristic;

		int minIndex = 0;

		int minValue = choosenVariable.mostConstrainedVariableHeuristic;

		for (int k = 0; k < temp.size(); k++) {
			if (temp.get(k).mostConstrainingVariableHeuristic == cc && temp.get(k).val != null) {
				if (temp.get(k).mostConstrainedVariableHeuristic <= minValue) {
					minIndex = k;
					minValue = temp.get(k).mostConstrainedVariableHeuristic;
				}
			} else
				break;
		}

		// now min index represent the variable with both most constraining variable and
		// minimum remaining value
		choosenVariable = temp.get(minIndex);

		// if the variable is already filled
		if (choosenVariable.mostConstrainedVariableHeuristic <= 0) {
			states.clear();
			return null;
		}

		// this list will have all the used words
		ArrayList<String> newList = new ArrayList<String>();

		// adding the used words in the grid
		newList.addAll(states.peek().wordsUsed);

		// adding the used words in that variable ( useful in backtracking )
		newList.addAll(triedShit.get(choosenVariable.uniqueID).words);

		// getting a list of all the values that can fit initially (with the current
		// constraints) and also sorted by their min conflict
		ArrayList<String> values = choosenVariable.assignAValue(newList, temp2);

		// if there's no values, then backtrack
		if (values == null) {
			str += " No values for the choosen variable | Backtracking";
			backtracksNumber++;
			triedShit.get(choosenVariable.uniqueID).words.clear();
			states.pop();
			printStack(states);
			return currentGrid;
		}

		boolean ff = false;

		// otherwise we will traverse the values to see who satisfy both the forward
		// checking and the arc consistency
		for (int i = 0; i < values.size(); i++) {

			ArrayList<Variable> t1 = new ArrayList<Variable>();

			for (Variable v : states.peek().variables) {
				t1.add((Variable) v.clone());
			}
			Variable choosen = t1.get(minIndex);

			ArrayList<Variable> t2 = new ArrayList<Variable>(t1);

			Collections.sort(t2, sortByID);

			boolean ffff = choosen.addConstraintsToNeighbors(values.get(i), t2, newList);

			// if the ith value satisfies both the arc consistency and the forward checking
			// then we will assign it safely
			if (ffff) {
				str += "The value: " + values.get(i) + " was choosen";

				choosen.currentValue = values.get(i);
				triedShit.get(choosen.uniqueID).words.add(values.get(i));
				Collections.sort(t1, heuristic1);
				State nextState = new State(t1);
				nextState.selectedWord = choosen.currentValue;
				states.push(nextState);
				ff = true;
				break;

			}

		}

		// if the loop is done and there's no value that satisfy the forward checking
		// and the arc consistency, then we will backtrack
		if (!ff) {
			str += "No values that satisfys the forward checking and arc consisetncy | Backtracking";

			backtracksNumber++;
			triedShit.get(choosenVariable.uniqueID).words.clear();
			states.pop();
			printStack(states);
		}

		status.setText(str);

		str2 = str;

		if (!states.isEmpty()) {
			char[][] newGrid = printBasedOnState(states.peek(), grid);
			table = newGrid;
		}
		else {
			table = currentGrid;
		}

		changeGrid();
		//System.out.println("loop ended thx");

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;

		Runtime runtime = Runtime.getRuntime(); // Get the Java runtime
		long memory = runtime.totalMemory() - runtime.freeMemory(); // Calculate the used memory

		SummaryController.timeCount = totalTime;
		SummaryController.stepsCount = iterationNumber;
		SummaryController.backCount = backtracksNumber;
		SummaryController.memCount = memory;

		return currentGrid;
	}

	@FXML
	public void getFile() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		fileName.setText(file.getPath());
	}

	public void myFunc() {
		String name = fileName.getText();
		int len = name.length();
		String sub = "";
		if (len > 3) {
			sub = name.substring(len - 4, len);
		}
		//System.out.println(sub);

		if (len >= 4 && sub.equals(".txt")) {
			fileName.setStyle("-fx-background-color: transparent;");
			fileName.setStyle("-fx-text-fill: green;");

			// listOfWords.clear();

			try {
				if (box1.isSelected() || box2.isSelected() || box3.isSelected() || box4.isSelected()) {
					makeList();
					listOfWords = wantedList;
				} else {
					ErrorController.msg = "Please choose a topic to filter the file!";
					fileName.setText("");
					showError();
				}
				// now you know
			} catch (Exception e) {
				// fileName.setStyle("-fx-background-color: #FFCCCC;");
				fileName.setStyle("-fx-text-fill: red;");
				try {
					ErrorController.msg = "File not found, or wrong file syntax!";
					showError();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		} else {
			// fileName.setStyle("-fx-background-color: #FFCCCC;");
			fileName.setStyle("-fx-text-fill: red;");

		}
	}

	LinkedList<String> others = new LinkedList<String>();

	public void makeList() throws Exception {
		// File file=new
		// File("C:\\Users\\Dell\\eclipse-workspace\\three\\src\\application\\file.txt");
		File file = new File(fileName.getText());
		Scanner br = new Scanner(new FileReader(file));
		int fileSize = 0;
		LinkedList<String[]> list2 = new LinkedList<String[]>();

		try {
			String line = br.nextLine().toLowerCase();

			while (br.hasNextLine()) {
				String arr[] = line.split(",");
				String arr2[]=new String[2];
				if(arr.length==1) {
					arr2[0]=arr[0];
					arr2[1]="arts";
					list2.add(arr2);
				}else {
				list2.add(arr);
				}
				//System.out.println(line);
				line = br.nextLine().toLowerCase();
				fileSize++;

			}
			String arr[] = line.split(",");
			String arr2[]=new String[2];
			if(arr.length==1) {
				arr2[0]=arr[0];
				arr2[1]="arts";
				list2.add(arr2);
			}else {
			list2.add(arr);
			}
			fileSize++;
			//System.out.println(line);
			//System.out.println(fileSize);

			for (int i = 0; i < list2.size(); i++) {
				for (int j = 0; j < (list2.get(i)).length; j++) {
					//System.out.print("    " + (list2.get(i))[j]);
					// if ((list.get(i)).length==1) {
					// others.add((list.get(i))[0]);
					// }
					if (true) {
						others.add((list2.get(i))[j]);
					}

					if ((list2.get(i)).length == 1) {

					} else if (j == (list2.get(i)).length - 1) {
						if (((list2.get(i))[j]).compareToIgnoreCase("health") == 0) {
							health.add((list2.get(i))[0]);
						} else if (((list2.get(i))[j]).compareToIgnoreCase("arts") == 0) {
							arts.add((list2.get(i))[0]);
						} else if (((list2.get(i))[j]).compareToIgnoreCase("technology") == 0) {
							technology.add((list2.get(i))[0]);
						}
					}
				}
				//System.out.println();
			}
			HashMap<String, String> hints = new HashMap<String, String>();
			for (int i = 0; i < list2.size(); i++) {
				if ((list2.get(i)).length == 1 || (list2.get(i)).length == 2) {
					hints.put(((list2.get(i))[0]), ((list2.get(i))[0]));
				} else {
					String hint = "";
					for (int j = 1; j < (list2.get(i)).length - 1; j++) {
						if (j == (list2.get(i)).length - 2) {
							hint += (list2.get(i))[j];

						} else {
							hint += (list2.get(i))[j] + ", ";
						}
					}
					hints.put(((list2.get(i))[0]), hint);
				}
			}
			// System.out.println(hints);

			wantedList.clear();
			if (box1.isSelected()) {
				wantedList.addAll(health);
			}
			if (box2.isSelected()) {
				wantedList.addAll(technology);
			}
			if (box3.isSelected()) {
				wantedList.addAll(arts);
				//System.out.println("selected");
			}
			if (box4.isSelected()) {
				wantedList.clear();
				wantedList.addAll(health);
				wantedList.addAll(technology);
				wantedList.addAll(arts);
			}
			//System.out.println("befoooooooore real");
			// wantedList.addAll(arts);
			//System.out.println(box3.isSelected());
			//printList(wantedList);
			// printList(arts);
			//System.out.println("afteeeer reaaaaaaaaaal");

			/**********/
			changeList(wantedList);

			HintsController.map = new HashMap<String, String>(hints);

			/************/
			// if none?
			// choose file!
		} finally {
			br.close();
		}
	}

	public void changeList(LinkedList<String> wantedList) {
		list.getItems().clear();
		for (int i = 0; i < wantedList.size(); i++) {
			myLabel lbl = new myLabel(wantedList.get(i), 0);

			// System.out.println("w="+(lbl.lbl).getWidth());

			list.getItems().add(lbl);
			(lbl.x).setOnMouseClicked((event) -> {
				int j = 0;
				for (j = 0; j < list.getItems().size(); j++) { // size
					// System.out.println(list.getHeight());
					if (((list.getItems().get(j)).txt).equals(lbl.txt)) {
						break;
					}

				}
				list.getItems().remove(j);
			});

		}
	}

	public void getListFromGUI() {

		LinkedList<String> newList = new LinkedList<String>();
		System.out.println("here?");

		for (int i = 0; i < list.getItems().size(); i++) { // size
			// System.out.println(list.getHeight());
			try {
				newList.add((list.getItems().get(i).txt));
				System.out.println(list.getItems().get(i).txt);
			} catch (Exception e) {
				break;
			}
		}
		System.out.println("out?");
		listOfWords.clear();
		listOfWords.addAll(newList);

	}

	public void addToAcrossAndDown(LinkedList<String> across, LinkedList<String> down) {

		across1.getItems().clear();
		for (int i = 0; i < across.size(); i++) {
			myLabel lbl = new myLabel(across.get(i), 0);

			// System.out.println("w="+(lbl.lbl).getWidth());
			lbl.x.setText("-");
			across1.getItems().add(lbl);

		}

		down1.getItems().clear();
		for (int i = 0; i < down.size(); i++) {
			myLabel lbl = new myLabel(down.get(i), 0);

			// System.out.println("w="+(lbl.lbl).getWidth());
			lbl.x.setText("-");
			down1.getItems().add(lbl);

		}

	}

	public static void printList(LinkedList<String> list) {

		for (int i = 0; i < list.size(); i++) {
			System.out.print("   " + list.get(i));
		}
		System.out.println();
	}

	@FXML
	public void back() throws IOException {

		Stage stage = (Stage) back.getScene().getWindow();
		stage.close();

		Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add("application/application.css");
		stage.setTitle("Welcome");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

	}

	public void showSummary() throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("Summary.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add("application/application.css");
		Stage stage = new Stage();
		stage.setTitle("Summary");
		root.requestFocus();
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	public void goo() {

		changeColor = true;

		n = Main.n;
		m = Main.m;
		System.out.println("n " + n);
		System.out.println("m " + m);

		double sceneWidth = pane.getWidth();
		double sceneHeight = pane.getHeight();

		double gridWidth = (sceneWidth / n);
		double gridHeight = (sceneHeight / m);

		matrix = new MyNode[n][m];

		Group root = new Group();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				int color = 0; // white
				String name = "";

				MyNode node = new MyNode(name, j, i, i * gridWidth, j * gridHeight, gridWidth, gridHeight, color);

				/*********/
				(node.rectangle).setOnMouseClicked((event) -> {
					// System.out.println(node.i +" "+node.j);
					if (changeColor) {
						node.revColor();
					}
				});
				(node.label).setOnMouseClicked(e -> {
					// System.out.println(node.i +" "+node.j);
					if (changeColor) {
						node.revColor();
					}
				});
				/***********/

				root.getChildren().add(node);

				matrix[i][j] = node; // remember

			}
		}

		// heeeeere
		pane.getChildren().add(root);
		if (WelcomeController.whatChoosen != 0) {
			changeGrid();
		}
	}

	public void openHints() throws IOException {

		HintsController.arr = table;
		HintsController.n = n;
		HintsController.m = m;

		Parent root = FXMLLoader.load(getClass().getResource("Hints.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("application/application.css");
		Stage stage = new Stage();
		stage.setTitle("Results");
		stage.setResizable(false);
		stage.setScene(scene);

		stage.show();

		changeGrid();

		/*************************/
		// FXMLLoader loader = new FXMLLoader(
		// getClass().getResource(
		// "Hints.fxml"
		// )
		// );
		//
		// Stage stage = new Stage(StageStyle.DECORATED);
		// stage.setScene(
		// new Scene(
		// (Pane) loader.load()
		// )
		// );
		//
		// HintsController controller =
		// loader.<HintsController>getController();

		/*****************************/
	}

	@FXML
	private JFXButton generate;

	@FXML
	private JFXButton animation;
	@FXML
	private JFXButton nextBtn;

	@FXML
	public void clr() {
		timeline.stop();
		WelcomeController.whatChoosen = 0;
		status.setText("");
		speedBar.setDisable(true);
		speedBar.setValue(5);
		changeColor = true;
		hints.setDisable(true);
		summary.setDisable(true);
		generate.setDisable(false);
		animation.setDisable(false);
		nextBtn.setDisable(false);
		currentGrid = null;
		isDone = false;
		isGenerated = false;

		for (int i = 0; i < Main.n; i++) {
			for (int j = 0; j < Main.m; j++) {
				matrix[i][j].color = 0;
				(matrix[i][j]).rectangle.setFill(Color.WHITE);
				(matrix[i][j]).label.setText("");

			}
		}

	}

	public void changeGrid() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j].color = table[j][i] == '*' ? 1 : 0;
				matrix[i][j].rectangle.setFill(Color.WHITE);
				matrix[i][j].label.setText("" + table[j][i]);
				if (table[j][i] == '*') {
					matrix[i][j].rectangle.setFill(Color.BLACK);
					matrix[i][j].label.setText("");
				}
			}
		}

	}

	private static final Random random = new Random();

	private static int[] setToArray(Set<Integer> aSet) {
		int[] result = new int[aSet.size()];
		int index = 0;
		for (int number : aSet) {
			result[index] = number;
			index++;
		}
		return result;
	}

	public static int[] getDistinctRandomNumbers(int count, int maxValue) {
		Set<Integer> was = new HashSet<>();
		for (int i = Math.max(0, maxValue - count); i < maxValue; i++) {
			int curr = i == 0 ? 0 : random.nextInt(i);
			if (was.contains(curr))
				curr = i;
			was.add(curr);
		}
		return setToArray(was);
	}

	public void g70() {
		clr();
		generatePercent(0.7);
	}

	public void g50() {
		clr();
		generatePercent(0.5);
	}

	public void g30() {
		clr();
		generatePercent(0.3);
	}

	public void g20() {
		clr();
		generatePercent(0.2);
	}

	public void g10() {
		clr();
		generatePercent(0.1);
	}

	public void generatePercent(double percentage) {

		int max = Main.n * Main.m;
		int count = (int) (percentage * max);
		int[] arr = getDistinctRandomNumbers(count, max);
		System.out.println(max + " we " + count);
		for (int i = 0; i < Main.n; i++) {
			for (int j = 0; j < Main.m; j++) {
				matrix[i][j].color = 0;
				(matrix[i][j]).rectangle.setFill(Color.WHITE);
			}
		}
		for (int i = 0; i < arr.length; i++) {
			int x = (arr[i] / Main.n);
			int y = (arr[i] % (Main.n));
			matrix[x][y].color = 1;
			(matrix[x][y]).rectangle.setFill(Color.BLACK);
		}

	}

	public void british() {
		clr();
		char[][] arr = new char[Main.n][Main.m];
		generateBritishStyle(arr);
		for (int i = 0; i < Main.n; i++) {
			for (int j = 0; j < Main.m; j++) {
				if (arr[i][j] != '*') {
					matrix[i][j].color = 0;
					(matrix[i][j]).rectangle.setFill(Color.WHITE);
				} else {
					matrix[i][j].color = 1;
					(matrix[i][j]).rectangle.setFill(Color.BLACK);
				}
			}
		}
	}

	public void american() {
		clr();
		char[][] arr = new char[Main.n][Main.m];
		generateAmericanStyle(arr);
		System.out.println("here");
		for (int i = 0; i < Main.n; i++) {
			for (int j = 0; j < Main.m; j++) {
				if (arr[i][j] != '*') {
					matrix[i][j].color = 0;
					(matrix[i][j]).rectangle.setFill(Color.WHITE);
				} else {
					matrix[i][j].color = 1;
					(matrix[i][j]).rectangle.setFill(Color.BLACK);
				}
			}
		}
	}

	@FXML
	public void addList() {
		String txt = txt1.getText();

		/// check if valid
		boolean flag = true;
		if (txt.length() <= 2) {
			flag = false;
			System.out.println("len");
		}
		if (txt.matches(".*\\d+.*")) {
			flag = false;
			System.out.println("num");
		}
		if (txt.contains(" ")) {
			flag = false;
			System.out.println("space");
		}
		txt1.setText("");

		if (flag) {
			txt = txt.toLowerCase();
			myLabel lbl = new myLabel(txt, 0);

			// System.out.println("w="+(lbl.lbl).getWidth());

			list.getItems().add(lbl);
			(lbl.x).setOnMouseClicked((event) -> {
				int j = 0;
				for (j = 0; j < list.getItems().size(); j++) { // size
					// System.out.println(list.getHeight());
					if (((list.getItems().get(j)).txt).equals(lbl.txt)) {
						break;
					}

				}
				list.getItems().remove(j);
			});

		} else {
			try {
				ErrorController.msg = "Invalid added word!";
				showError();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void showError() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Error.fxml"));
		Parent root = (Parent) loader.load();
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Error");
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	public static class myLabel extends HBox {

		Label lbl;
		int index;
		Label x;
		String txt;

		public myLabel(String txt, int index) {
			this.txt = txt;
			lbl = new Label(txt);
			this.index = index;
			x = new Label("x");
			Label emp = new Label("   ");
			getChildren().addAll(x, emp, lbl);
		}

	}

	public static class MyNode extends StackPane {
		Rectangle rectangle;
		int color;
		String name;
		Label label;
		int i;
		int j;

		public MyNode(String name, int i, int j, double x, double y, double width, double height, int color) {

			// rectangle
			this.i = i;
			this.j = j;
			rectangle = new Rectangle(width, height);
			rectangle.setStroke(Color.GRAY);
			this.color = color;
			if (color == 1) {
				rectangle.setFill(Color.BLACK);
			} else {
				// B2DBBF
				// rectangle.setFill(Color.web("#B2DBBF"));
				rectangle.setFill(Color.WHITE);
			}

			// label
			this.name = name;
			label = new Label(this.name);
			label.setFont(new Font("andalus", 22));

			// position
			setTranslateX(x);
			setTranslateY(y);

			getChildren().addAll(rectangle, label);

		}

		public void setColor(int color) {
			if (color == 1) {
				this.rectangle.setFill(Color.BLACK);
			} else {
				this.rectangle.setFill(Color.WHITE);
			}
		}

		public void revColor() {
			if (color == 1) {
				color = 0;
				this.rectangle.setFill(Color.WHITE);
			} else {
				color = 1;
				this.rectangle.setFill(Color.BLACK);
			}
		}

	}

	/**
	 * @throws CloneNotSupportedException
	 * @throws IOException
	 *             ******
	 * 
	 * 
	 */

	@FXML
	public void generateToFinal() throws CloneNotSupportedException, IOException {
		while (!next())
			;
	}

	public boolean generate() throws CloneNotSupportedException, IOException {
		System.out.println(
				"_____________________________________________________________________________________________");

		for (int i = 0; i < Main.n; i++) {
			for (int j = 0; j < Main.m; j++) {
				System.out.println(">>" + matrix[i][j].color);
			}
		}
		// main
		// --------------
		 try {

		hints.setDisable(true);
		summary.setDisable(true);

		if ((fileName.getText()).compareTo("") == 0 && list.getItems().size()==0) {
			ErrorController.msg = "Please choose a file!";
			showError();
			return false;
		}
		if (!box1.isSelected() && !box2.isSelected() && !box3.isSelected() && !box4.isSelected()) {
			ErrorController.msg = "Please choose a topic!";
			showError();
			return false;
		}

		Variable.counter = 0;

		// clear grid

		// listOfWords.clear();
		// wantedList.clear();
		//
		//
		// makeList();
		//
		//
		// listOfWords=wantedList; //its already done :P

		getListFromGUI();

		// --------------

		// hashmap from size to value object

		// --------------------------
		HashMap<Integer, Value> newMap = new HashMap<Integer, Value>();
		System.out.println("u dont say " + listOfWords.size());
		for (int i = 0; i < listOfWords.size(); i++) {

			String str = listOfWords.get(i);
			System.out.println(str + "wth");
			int size = str.length();

			if (newMap.containsKey(size)) {
				Value val = newMap.get(size);
				val.add(str);
			} else {
				Value val = new Value(size);
				val.add(str);
				newMap.put(size, val);
			}

		}
		for (Integer size : newMap.keySet()) {
			newMap.get(size).setCombinations();
		}
		// ----------------------------
		char[][] grid = null;
		if (WelcomeController.whatChoosen != 0) {
			grid = new char[Main.n][Main.m];
			for (int i = 0; i < Main.n; i++) {
				for (int j = 0; j < Main.m; j++) {
					if (matrix[j][i].color == 1) {
						grid[i][j] = '*';
					} else {
						grid[i][j] = ' ';
					}
				}
			}
			// clr();
		} else {
			// clr();
			grid = new char[Main.n][Main.m];
			for (int i = 0; i < Main.n; i++) {
				for (int j = 0; j < Main.m; j++) {
					if (matrix[j][i].color == 1) {
						grid[i][j] = '*';
					} else {
						grid[i][j] = ' ';
					}
				}
			}
		}

		// char[][] grid = { { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', '
		// ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
		// { '*', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*' },
		// { ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
		// { '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', '*' },
		// { ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
		//
		// };

		// char[][] grid = { { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', '
		// ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' '
		// },
		// { '*', '*', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', '*', '*', '*', '*'
		// },
		// { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' '
		// },
		// { '*', '*', '*', '*', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', '*', '*'
		// },
		// { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' '
		// },
		// { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' }
		//
		// };

		// char[][] grid =
		// { { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ' ,' ','
		// '},
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ' ,' ',' '},
		// { '*', '*', '*', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*' ,'*','*'},
		// { ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', '*', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ' ,' ',' '},
		// { '*', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', '*' ,'*','*'},
		// { ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ' ,' ',' '},
		// { ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ' ,' ',' '}
		//
		// };
		// defi il predefined, w ino iza knt 7atet klmat mn 3ndi ma ytlob file

		// b a s i l
		// a p p * o
		// d * a w o
		// a b c d o
		// f u k * l
		// -----------------

		// new Scanner(System.in).nextLine();
		ArrayList<Variable> horVariables = new ArrayList<Variable>();
		ArrayList<Variable> verVariables = new ArrayList<Variable>();
		findHorSlots(horVariables, grid, newMap);
		findVerSlots(verVariables, grid, newMap);
		print(grid);

		// connecting the neighbors
		// ------------------
		for (int i = 0; i < horVariables.size(); i++) {
			for (int j = 0; j < verVariables.size(); j++) {
				int[] info = horVariables.get(i).intersects(verVariables.get(j));
				if (info != null) {
					horVariables.get(i)
							.addAnotherN(new IntersectionInfo(verVariables.get(j).uniqueID, info[0], info[1]));
				}
			}
			horVariables.get(i).setMostConstrainingVariableHeuristic2();
		}
		// ------------------

		// connecting the neighbors
		// -------------------
		for (int i = 0; i < verVariables.size(); i++) {
			for (int j = 0; j < horVariables.size(); j++) {
				int[] info = verVariables.get(i).intersects(horVariables.get(j));

				if (info != null) {
					verVariables.get(i)
							.addAnotherN(new IntersectionInfo(horVariables.get(j).uniqueID, info[0], info[1]));

				}
			}
			verVariables.get(i).setMostConstrainingVariableHeuristic2();

		}
		// -------------------

		// defining heuristic1
		// -----------
		heuristic1 = new MostConstrainingVariablePriority();
		// -----------

		// defining id based sort
		// ------------
		sortByID = new UniqueIdPriority();
		// ------------

		// defining the initial state
		// ------------
		ArrayList<Variable> initialState = new ArrayList<Variable>();
		// ------------

		// adding the variables
		// ------------
		initialState.addAll(horVariables);
		initialState.addAll(verVariables);
		// ------------

		// sorting the initial state
		// --------------------
		Collections.sort(initialState, heuristic1);
		// --------------------

		shit = new ArrayList<Variable>();
		for (Variable v : initialState) {
			shit.add((Variable) v.clone());
		}

		triedShit = new ArrayList<Variable>(shit);

		Collections.sort(triedShit, sortByID);

		// starting the DFS
		// -----------------------

		// defining the initial state
		initialS = new State(initialState);

		// defining the stack
		states = new Stack<State>();

		// pushing the initial state to the stack
		states.push(initialS);

		iterationNumber = 0;

		currentGrid = null;

		backtracksNumber = 0;

		startTime = System.nanoTime();

		System.out.println("bef : " + currentGrid);
		currentGrid = new char[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.println(grid[i][j]);
				currentGrid[i][j] = grid[i][j];
			}
		}
		System.out.println("aft : " + currentGrid);
		isGenerated = true;
		return true;
		 }
		catch(Exception e) {
			ErrorController.msg="Sorry, no solution found!";
			showError();
			return false;
		}
	}

	MostConstrainingVariablePriority heuristic1;
	UniqueIdPriority sortByID;
	ArrayList<Variable> shit;
	ArrayList<Variable> triedShit;
	State initialS;
	Stack<State> states;
	int iterationNumber;
	char[][] currentGrid;
	char[][] currentGrid2;
	int backtracksNumber;
	long startTime;

	/**********************
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * coooooode
	 * 
	 * 
	 * 
	 * 
	 * heeeeeere
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public static void generateAmericanStyle(char[][] arr) {

		if (arr[0].length <= 2)
			return;

		int start = arr[0].length / 2 - 1;
		int end = arr[0].length / 2 + 1;

		int base = (int) (Math.random() * (end - start + 1)) + start;

		boolean flag = false;

		for (int i = 0; i < arr.length; i++) {

			if (i <= arr.length * 0.2 || i >= arr.length * 0.8) {
				int[] array = new int[arr[0].length - Math.min(i, arr[0].length)];
				for (int k = 0; k < array.length; k++) {
					array[k] = k;
				}

				for (int k = 0; k < 0.2 * arr[0].length - i; k++) {
					int rand = (int) (Math.random() * (arr[0].length - i - k));
					array[rand] = array[rand] + array[array.length - 1 - i];
					array[array.length - 1] = array[rand] - array[array.length - 1 - i];
					array[rand] = array[rand] - array[array.length - 1 - i];
					arr[i][array[array.length - 1 - i]] = '*';
				}
			} else {

				if (!flag) {
					arr[i][base] = '*';
					flag = true;
				} else {
					int rand = (int) (Math.random() * 2) + 1;
					base = base - rand;
					if (base >= 0) {
						arr[i][base] = '*';
					}
				}

			}

		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = arr[0].length - Math.min(i, arr[0].length); j < arr[0].length; j++) {
				System.out.print("-");
				arr[i][j] = arr[arr.length - 1 - i][arr[0].length - 1 - j];
			}
			System.out.println();
		}

	}

	public static void generateBritishStyle(char[][] arr) {

		int[] random = new int[arr[0].length / 2];

		for (int i = 0; i < random.length; i++) {
			random[i] = i * 2;
		}

		Integer[] selected = new Integer[random.length / 4];

		int tracker = random.length - 1;

		for (int i = 0; i < selected.length; i++) {
			int s = (int) (Math.random() * (tracker + 1));
			selected[i] = random[s];

			random[tracker] = random[tracker] ^ random[s];
			random[s] = random[tracker] ^ random[s];
			random[tracker] = random[tracker] ^ random[s];

			tracker--;
		}

		Integer[] selected2 = new Integer[selected.length];
		for (int i = 0; i < selected.length; i++) {
			selected2[i] = selected[i] + 1;
		}

		Collections.shuffle(Arrays.asList(selected2));

		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				if (!inIt(i, selected)) {
					arr[i][(int) ((Math.random()) * arr[i].length)] = '*';
				}
			} else {
				for (int j = 0; j < arr[i].length; j++) {
					if (j % 2 == 1) {
						arr[i][j] = '*';
					}
				}
				if (inIt(i, selected2)) {
					int s = (int) ((Math.random()) * arr[i].length);
					if (s % 2 == 1) {
						s--;
					}
					arr[i][s] = '*';
				}

			}
		}

	}

	public static boolean inIt(int aim, Integer[] selected2) {
		for (int i = 0; i < selected2.length; i++) {
			if (selected2[i] == aim)
				return true;
		}
		return false;
	}

	public static void printStack(Stack<State> states) {

		System.out.println("-*-*-*-*-*-");
		for (int i = 0; i < states.size(); i++) {
			System.out.println(states.get(i).wordsUsed);
			System.out.println();
		}
		System.out.println("-*-*-*-*-*-");

	}

	public static char[][] printBasedOnState(State s, char[][] grid) {

		char[][] newGrid = new char[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}

		ArrayList<Variable> vars = s.variables;

		for (int i = 0; i < vars.size(); i++) {
			Variable var = vars.get(i);
			assign(newGrid, var);
		}
		print(newGrid);
		System.out.println();
		System.out.println("______");
		System.out.println();
		return newGrid;
	}

	public static boolean isSolved(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == ' ')
					return false;
			}
		}
		return true;
	}

	public static void assign(char[][] arr, Variable var) {
		if (var.startX == var.endX) {
			for (int i = 0; i < var.currentValue.length(); i++) {
				arr[var.startX][var.startY + i] = var.currentValue.charAt(i);
			}
		} else {
			for (int i = 0; i < var.currentValue.length(); i++) {
				arr[var.startX + i][var.startY] = var.currentValue.charAt(i);
			}
		}
	}

	public static Variable getByID(ArrayList<Variable> state, int id) {
		for (int i = 0; i < state.size(); i++) {
			if (state.get(i).uniqueID == id)
				return state.get(i);
		}
		return null;
	}

	public static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
	}

	public static void findHorSlots(ArrayList<Variable> horVariables, char[][] grid, HashMap<Integer, Value> newMap) {

		for (int i = 0; i < grid.length; i++) {
			int tracker = 0;
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '*') {
					if (tracker < j) {
						int size = j - tracker;
						if (size != 1) {
							Value val = newMap.get(size);
							Variable var = new Variable(i, tracker, i, j - 1, val);
							System.out.println(var);
							horVariables.add(var);
						}
					}
					tracker = j + 1;
				}
			}
			if (tracker != grid[i].length) {

				int size = grid[i].length - tracker;
				if (size != 1) {
					Value val = newMap.get(size);
					Variable var = new Variable(i, tracker, i, grid[i].length - 1, val);
					System.out.println(var);

					horVariables.add(var);
				}

			}
		}

	}

	public static void findVerSlots(ArrayList<Variable> verVariables, char[][] grid, HashMap<Integer, Value> newMap) {

		for (int i = 0; i < grid[0].length; i++) {
			int tracker = 0;
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i] == '*') {
					if (tracker < j) {

						int size = j - tracker;
						if (size != 1) {
							Value val = newMap.get(size);
							Variable var = new Variable(tracker, i, j - 1, i, val);
							System.out.println(var);

							verVariables.add(var);
						}
					}
					tracker = j + 1;
				}
			}
			if (tracker != grid.length) {
				int size = grid.length - tracker;
				if (size != 1) {
					Value val = newMap.get(size);
					Variable var = new Variable(tracker, i, grid.length - 1, i, val);
					verVariables.add(var);
				}
			}
		}
	}

	public void ani(double speed) {
		timeline = new Timeline();
		// System.out.println(speed);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(speed != 0 ? 1 / speed : Double.MAX_VALUE), e -> {

			try {

				boolean temp = next();
				System.out.println(temp);
				if (temp) {
					timeline.stop();
				}
			} catch (CloneNotSupportedException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		timeline.getKeyFrames().add(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		timeline.stop();
		System.out.println("accessed!1");
		ani(speedBar.getValue());
	}
}
