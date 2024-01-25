package engine;

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
    private String standingDown = "Down2.png";
    private String standingUp = "Up2.png";
    private String standingLeft = "Left2.png";
    private String standingRight = "Right2.png";
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
        this.testImageList.add(new Image("Up2.png"));
        this.testImageList.add(new Image("Down2.png"));
        this.testImageList.add(new Image("Right2.png"));
        this.testImageList.add(new Image("Left2.png"));
        this.barrier = new LinkedList<>();

        this.x = x;
        this.y = y;
        this.id = ID;

        NPC.this.NPCReactions();

        createObstacleTile(48, 48, this.x, this.y);

    }

    private void NPCReactions() {

        System.out.println("NPC CREADO con id: " + id);
        System.out.println("La coordenada de x en el NPC es: " + x);
        System.out.println("La coordenada de x en el NPC es: " + y);

        }

    public void createObstacleTile(double w, double h, double x, double y) {
        ObstacleTile tile = new ObstacleTile(w, h, x, y);
        this.root.getChildren().add(tile);
        this.barrier.add(tile);

    }

    public void npcInteraction(String quote, double x, double y) {
        System.out.println("x: " + x + " npcX: " + this.x + " y: " + y + " npcY: " + this.y);
        if ((x >= this.x - 40 && x <= this.x + 80) && (y >= this.y - 25 && y <= this.y + 55)) {

            System.out.println(this.id + " Se interactua " + quote);
            for (Dialog dialog : getDialogs()){
                dialog.invokeDialog(frase);
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
            System.out.println(this.id + " No se interactua " + quote);
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




