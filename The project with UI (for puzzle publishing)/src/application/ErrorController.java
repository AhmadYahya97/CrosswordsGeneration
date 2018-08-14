package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController implements Initializable{

    @FXML
    private JFXButton ok;
    
    @FXML
    private Label error;
    
    public static String msg;

    @FXML
    public void closeWindow() {
    	Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String sth=msg;
		error.setText(sth);
		
	}

}
