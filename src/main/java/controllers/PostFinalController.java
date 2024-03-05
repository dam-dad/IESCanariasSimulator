package controllers;

import engine.MusicPlayer;
import engine.world.Maps;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PostFinalController {
    private Stage stage;
    private Maps maps = new Maps();

    private MainMenuController menu = new MainMenuController();

    public void initialize() {
        MusicPlayer efectos;
        efectos = new MusicPlayer("/Effects/screamer.mp3");
        efectos.play();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            efectos.stop();
            Platform.exit();
        }));
        timeline.play();
    }

    public void setStage(Stage stage) {
        this.stage = stage;

    }
    @FXML
    void jugarAction(ActionEvent event) {
        System.out.println("Hola");
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
