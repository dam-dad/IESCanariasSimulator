package engine;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Dialog {

    ImageView dialog = new ImageView(new Image("dialog_box.png"));
    Text dialogText = new Text (50, 670, "");
    Pane root;
    Stage stage;

    public Dialog(Pane root, Stage stage, Scene scene){
        dialog.setLayoutX(17);
        dialog.setLayoutY(600);
        dialog.setOpacity(0);

        this.dialog = dialog;

        dialogText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        this.root = root;
        this.stage = stage;

    }

    public void invokeDialog(String dialogLine){
        dialogText.setText(dialogLine);
        dialogText.setOpacity(1);
        dialog.setOpacity(0.7);


        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(ev -> { dialogText.setOpacity(0); dialog.setOpacity(0); });
        pause.play();



        System.out.println("DialogBA " + dialog.getOpacity());
    }
}
