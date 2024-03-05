package controllers;

import engine.world.Maps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FinalController {
    private Stage stage;
    private Maps maps = new Maps();

    private MainMenuController menu = new MainMenuController();

    public void setStage(Stage stage) {
        this.stage = stage;

    }
    @FXML
    void jugarAction(ActionEvent event) {
        try {
            postFinalPantalla(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setWorld(Maps maps) {
        this.maps = maps;
    }

    public void postFinalPantalla(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/PostFinal.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        PostFinalController controller = loader.getController();
        controller.setStage(stage);
        controller.setWorld(new Maps());
    }

    public void MainMenu(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/MainMenu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        MainMenuController controller = loader.getController();
        controller.setStage(stage);
        controller.setWorld(new Maps());
    }
}
