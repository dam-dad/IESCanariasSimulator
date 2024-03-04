package engine.objects;

import engine.ui.Dialog;
import engine.world.ObstacleTile;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NPC {
    private double x = 0;
    private double y = 0;
    private Pane root = new Pane();
    private Stage stage;
    private String standingDown;
    private String standingUp;
    private String standingLeft;
    private String standingRight;
    private ImageView character_image;
    private LinkedList<ObstacleTile> barrier;
    private ArrayList<Image> testImageList;
    private int id;
    private String frase = "Hola";


    private List<Dialog> dialogs = new ArrayList<>();
    public void addDialogs(Dialog dialog, String frase) {
        this.frase = frase; dialogs.add(dialog);
    }
    public List<Dialog> getDialogs() {
        return dialogs;
    }


    public NPC(Pane root, Stage stage, Scene scene, final ImageView character_image, int ID, double x, double y)

    {

        this.character_image = character_image;
        this.root = root;
        this.stage = stage;
        this.testImageList = new ArrayList();
        this.barrier = new LinkedList<>();
        this.x = x;
        this.y = y;
        this.id = ID;

        if (ID == 1) {
            standingDown = "NPCs/random_Down.png";
            standingUp = "NPCs/random_Up.png";
            standingLeft = "NPCs/random_Left.png";
            standingRight = "NPCs/random_Right.png";

        } else if (ID == 2) {
            standingDown = "NPCs/random3_Down.png";
            standingUp = "NPCs/random3_Up.png";
            standingLeft = "NPCs/random3_Left.png";
            standingRight = "NPCs/random3_Right.png";
        } else if (ID == 3) {
            standingDown = "NPCs/random4_Down.png";
            standingUp = "NPCs/random4_Up.png";
            standingLeft = "NPCs/random4_Left.png";
            standingRight = "NPCs/random4_Right.png";
        } else if (ID == 4) {
            standingDown = "NPCs/monchi_Down.png";
            standingUp = "NPCs/monchi_Up.png";
            standingLeft = "NPCs/monchi_Left.png";
            standingRight = "NPCs/monchi_Right.png";
        } else if (ID == 5) {
            standingDown = "NPCs/BrianDown.png";
            standingUp = "NPCs/BrianUp.png";
            standingLeft = "NPCs/BrianLeft.png";
            standingRight = "NPCs/BrianRight.png";
        } else if (ID == 6) {
            standingDown = "NPCs/edu_Down.png";
            standingUp = "NPCs/edu_Up.png";
            standingLeft = "NPCs/edu_Left.png";
            standingRight = "NPCs/edu_Right.png";
        } else if (ID == 7) {
            standingDown = "NPCs/flavia_Down.png";
            standingUp = "NPCs/flavia_Up.png";
            standingLeft = "NPCs/flavia_Left.png";
            standingRight = "NPCs/flavia_Right.png";
        } else if (ID == 8) {
            standingDown = "NPCs/SolajeroDown.png";
            standingUp = "NPCs/SolajeroUp.png";
            standingLeft = "NPCs/SolajeroLeft.png";
            standingRight = "NPCs/SolajeroRight.png";
        } else if (ID == 9) {
            standingDown = "NPCs/YormanDown.png";
            standingUp = "NPCs/YormanUp.png";
            standingLeft = "NPCs/YormanLeft.png";
            standingRight = "NPCs/YormanRight.png";
        } else if (ID == 10) {
            standingDown = "NPCs/BettyDown.png";
            standingUp = "NPCs/BettyUp.png";
            standingLeft = "NPCs/BettyLeft.png";
            standingRight = "NPCs/BettyRight.png";
        } else if (ID == 11) {
            standingDown = "NPCs/FranDown.png";
            standingUp = "NPCs/FranUp.png";
            standingLeft = "NPCs/FranLeft.png";
            standingRight = "NPCs/FranRight.png";
        } else if (ID == 12) {
            standingDown = "NPCs/secretaria_Down.png";
            standingUp = "NPCs/secretaria_Up.png";
            standingLeft = "NPCs/secretaria_Left.png";
            standingRight = "NPCs/secretaria_Right.png";
        }

        NPC.this.NPCReactions();

        createObstacleTile(48, 48, this.x, this.y);

    }

    private void NPCReactions() {

        System.out.println("NPC CREADO con id: " + id);
        System.out.println("La coordenada de x en el NPC es: " + x);
        System.out.println("La coordenada de x en el NPC es: " + y);

    }

    private void createObstacleTile(double w, double h, double x, double y) {
        ObstacleTile tile = new ObstacleTile(w, h, x, y);
        this.root.getChildren().add(tile);
        this.barrier.add(tile);

    }

    public void npcInteraction(double x, double y) {
        System.out.println("x: " + x + " npcX: " + this.x + " y: " + y + " npcY: " + this.y);
        if ((x >= this.x - 40 && x <= this.x + 80) && (y >= this.y - 25 && y <= this.y + 55)) {

            System.out.println(this.id + " Se interactua ");
            for (Dialog dialog : getDialogs()){
                if (dialog.getDialog().getOpacity() == 0) {
                    dialog.invokeDialog(frase);
                    //if (id = )

                } else {
                    dialog.getDialog().setOpacity(0);
                    dialog.getDialogText().setOpacity(0);
                }

            }

            if (y < this.y || x < this.x) {

                if ((this.x-x) < (this.y-y)){

                    this.character_image.setImage(new Image(this.standingUp));
                } else if ((this.y-y) < (this.x-x)) {

                    this.character_image.setImage(new Image(this.standingLeft));
                }

            } else if (y > this.y || x > this.x) {

                if ((this.x-x) > (this.y-y)) {

                    this.character_image.setImage(new Image(this.standingDown));
                } else if ((this.y-y) > (this.x-x)) {

                    this.character_image.setImage(new Image(this.standingRight));
                }
            }
        }
        else {
            System.out.println(this.id + " No se interactua ");
        }

    }

    public void NPCBasics(ImageView npc_image, double x, double y, LinkedList<ObstacleTile> barrier){
        npc_image.setLayoutX(x);
        npc_image.setLayoutY(y);
        this.root.getChildren().add(npc_image);
        this.barrier = barrier;
        this.createObstacleTile(40, 40, x + 5, y); //(Width, Height, x, y)
        System.out.println("COLISION CREADA");

    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}


