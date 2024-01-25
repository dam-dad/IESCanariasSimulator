package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import engine.Maps;

public class MainMenuController {

    private Stage stage;
    private Maps maps = new Maps();

    @FXML
    private Button jugarButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void jugarAction() {
        // Cuando se presiona el botón, se invocará este método
        maps.calleInstituto(stage);
    }

    public void setWorld(Maps maps) {
        this.maps = maps;
    }
}