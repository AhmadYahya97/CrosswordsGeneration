package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.WelcomeController;

public class WelcomeController implements Initializable {

	String[] fileNames = { "SplashFXML.fxml", "SplashFXML2.fxml", "SplashFXML3.fxml" };

	public int i, j;

	@FXML
	private JFXTextField m;

	@FXML
	private JFXTextField n;

	@FXML
	private JFXButton start;

	@FXML
	private AnchorPane root;

	ToggleGroup group = new ToggleGroup();

	static int whatChoosen;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		r1.setToggleGroup(group);
		r2.setToggleGroup(group);
		RadioButtonSelectionHandler b1Selection = new RadioButtonSelectionHandler(r1);
		r1.setOnMousePressed(b1Selection.getMousePressed());
		r1.setOnMouseReleased(b1Selection.getMouseReleased());

		RadioButtonSelectionHandler b2Selection = new RadioButtonSelectionHandler(r2);
		r2.setOnMousePressed(b2Selection.getMousePressed());
		r2.setOnMouseReleased(b2Selection.getMouseReleased());

		// TODO Auto-generated method stub
		if (!Main.isSplashLoaded) {
			loadSplashScreen(0);
		}
	}

	@FXML
	private JFXRadioButton r1;

	@FXML
	private JFXRadioButton r2;

	@FXML
	void letTheGameBegin() throws IOException {

		if (!r1.isSelected() && !r2.isSelected()) {
			whatChoosen = 0;
			try {
				i = Integer.parseInt(n.getText());
				j = Integer.parseInt(m.getText());
			} catch (Exception e) {
				ErrorController.msg = "Invalid dimensions!";
				showError();
				m.setText("");
				n.setText("");
				return;
			}
			Stage stage2 = (Stage) start.getScene().getWindow();
			stage2.close();

			Main.n = i;
			Main.m = j;
		} else {

			if (r1.isSelected()) {
				whatChoosen = 1;
				Stage stage2 = (Stage) start.getScene().getWindow();
				stage2.close();

				Main.n = 13;
				Main.m = 13;
			} else {
				whatChoosen = 2;
				Stage stage2 = (Stage) start.getScene().getWindow();
				stage2.close();

				Main.n = 15;
				Main.m = 15;
			}

		}

		Parent root = FXMLLoader.load(getClass().getResource("fxml.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		scene.getStylesheets().add("application/application.css");
		stage.setTitle("CrossWords Generator");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		root.requestFocus();

		/*
		 * FXMLLoader loader=new FXMLLoader();
		 * loader.setLocation(getClass().getResource("fxml.fxml")); try { loader.load();
		 * } catch(IOException E) {
		 * Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null,
		 * E); } fxmlController control=loader.getController();
		 */
		// control.print(i,j);

	}

	public int getn() {
		return i;
	}

	public int getm() {
		return j;
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

	private void loadSplashScreen(int i) {
		try {
			Main.isSplashLoaded = true;
			StackPane pane = FXMLLoader.load(getClass().getResource((fileNames[i])));
			root.getChildren().setAll(pane);

			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);

			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), pane);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			fadeOut.setOnFinished((e) -> {
				try {
					AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("Welcome.fxml")));

					if (i != 2) {
						loadSplashScreen(i + 1);
					} else
						root.getChildren().setAll(parentContent);

				} catch (IOException ex) {
					Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
				}
			});

		} catch (IOException ex) {
			Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
