package engine;

import java.util.LinkedList;

import controllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class World extends Application {
    private LinkedList<ObstacleTile> barrier;
    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    //Constructor

    public World() {

        this.barrier = new LinkedList<>();
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/MainMenu.fxml"));
        Scene scene = new Scene(loader.load());

        MainMenuController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setWorld(new Maps());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
