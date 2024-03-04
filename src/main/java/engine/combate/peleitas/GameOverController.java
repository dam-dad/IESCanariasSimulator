package engine.combate.peleitas;
import controllers.MainMenuController;
import engine.world.Maps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverController {

    Maps maps = new Maps();
    MainMenuController mainMenu = new MainMenuController();
    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void reintentarAction(ActionEvent event) {
        maps.paradaGuagua(stage);
    }

    @FXML
    void volverAction(ActionEvent event) {
        try {
            mainMenu.mainMenuPantalla(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void gameOverPantalla(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameOver.fxml"));
        GameOverController gameOver = new GameOverController();
        gameOver.setStage(stage);
        loader.setController(gameOver);
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }
}
