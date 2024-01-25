package engine;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Objects {
    double x = 0;
    double y = 0;
    Pane root = new Pane();
    Stage stage;
    AnimationTimer timer;
    public String standingDown = "Down2.png";
    public String standingUp = "Up2.png";
    public String standingLeft = "Left2.png";
    public String standingRight = "Right2.png";
    public ImageView character_image;
    LinkedList<ObstacleTile> barrier;
    boolean moveUp;
    boolean moveRight;
    boolean moveDown;
    boolean moveLeft;
    ArrayList<Image> testImageList;
    int switchWhenZero = 0;
    int upCount;
    int downCount;
    int rightCount;
    int leftCount;
    int i = 0;
    int mx = 0;
    int my = 0;
    int dx = 0;
    int dy = 0;
    //Dialog dialog = new Dialog();

    World worldInstance = new World();
    int id;
    int ChaX = 0;
    int ChaY = 0;

    String frase = "Hola";
    private List<Dialog> dialogs = new ArrayList<>();
    public void addDialogs(Dialog dialog, String frase) {
        this.frase = frase; dialogs.add(dialog);
    }
    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public Objects(Pane root, Stage stage, Scene scene, final ImageView character_image, LinkedList<ObstacleTile> barrier, int ID, double x, double y)

    {

        this.character_image = character_image;
        this.root = root;
        this.stage = stage;
        this.testImageList = new ArrayList();
        this.testImageList.add(new Image("arcadeMachine.png"));
        this.upCount = 0;
        this.downCount = 0;
        this.rightCount = 0;
        this.leftCount = 0;
        this.barrier = new LinkedList<>();

        this.x = x;
        this.y = y;
        this.id = ID;

        Objects.this.NPCReactions();

        createObstacleTile(48, 48, this.x, this.y);

        if (ID == 0) {

        }

    }

    private void NPCReactions() {

        System.out.println("NPC CREADO con id: " + id);
        System.out.println("La coordenada de x en el NPC es: " + x);
        System.out.println("La coordenada de x en el NPC es: " + y);

        this.character_image.setImage(new Image(this.standingDown));

        /*
        if (dx == 0 && dy == 0 && my == -2) {
            this.character_image.setImage(new Image(this.standingUp));
        }
        else if (dx == 0 && dy == 0 && my == 2) {
            this.character_image.setImage(new Image(this.standingDown));
        }
        else if (dx == 0 && dy == 0 && mx == -2) {
            this.character_image.setImage(new Image(this.standingLeft));
        }
        else if (dx == 0 && dy == 0 && mx == 2) {
            this.character_image.setImage(new Image(this.standingRight));
        }

        else {*/


        }

    public void NPCCollision(LinkedList<ObstacleTile> barrier){
        this.barrier = barrier;
        this.createObstacleTile(40, 40, x + 5, y); //(Width, Height, x, y)
        System.out.println("COLISION CREADA");
    }

    public void createObstacleTile(double w, double h, double x, double y) {
        ObstacleTile tile = new ObstacleTile(w, h, x, y);
        this.root.getChildren().add(tile);
        this.barrier.add(tile);

    }

    public void elementInteraction(double x, double y) {
        System.out.println("x: " + x + " npcX: " + this.x + " y: " + y + " npcY: " + this.y);
        if ((x >= this.x - 40 && x <= this.x + 80) && (y >= this.y - 25 && y <= this.y + 55)) {

            System.out.println(this.id + " Se interactua");

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

}




