package application;

import com.jfoenix.controls.JFXRadioButton;

import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

public class RadioButtonSelectionHandler {

    private boolean selected = false;

    private JFXRadioButton radioButton;

    public RadioButtonSelectionHandler(JFXRadioButton radioButton) {
        this.radioButton = radioButton;
    }

    EventHandler<MouseEvent> mousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(radioButton.isSelected())
                selected = true;
        }
    };

    EventHandler<MouseEvent> mouseReleased = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if(selected)
                radioButton.setSelected(false);
            selected = false;
        }
    };

    public EventHandler<MouseEvent> getMousePressed() {
        return mousePressed;
    }

    public EventHandler<MouseEvent> getMouseReleased() {
        return mouseReleased;
    }
}