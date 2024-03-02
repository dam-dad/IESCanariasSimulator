package engine.objects;

import engine.ui.Dialog;
import engine.world.Maps;
import engine.world.ObstacleTile;
import engine.world.World;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Elements {
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

    private Maps mapsInstance = new Maps();


    String frase = "Hola";
    private List<Dialog> dialogs = new ArrayList<>();
    public void addDialogs(Dialog dialog, String frase) {
        this.frase = frase; dialogs.add(dialog);
    }
    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public Elements(Pane root, Stage stage, Scene scene, int ID, double x, double y)

    {

        this.character_image = character_image;
        this.root = root;
        this.stage = stage;
        this.testImageList = new ArrayList();
        this.upCount = 0;
        this.downCount = 0;
        this.rightCount = 0;
        this.leftCount = 0;
        this.barrier = new LinkedList<>();

        this.x = x;
        this.y = y;
        this.id = ID;

        if (ID == 0) {

        }

    }

    public void elementsBasics(ImageView npc_image, double x, double y, LinkedList<ObstacleTile> barrier){
        npc_image.setLayoutX(x);
        npc_image.setLayoutY(y);
        this.root.getChildren().add(npc_image);
        this.barrier = barrier;
        this.createObstacleTile(60, 87, x, y); //(Width, Height, x, y)
        System.out.println("COLISION CREADA");

    }

    public void elementInteraction(double x, double y) {
        System.out.println("x: " + x + " npcX: " + this.x + " y: " + y + " npcY: " + this.y);
        if ((x >= this.x - 40 && x <= this.x + 80) && (y >= this.y - 25 && y <= this.y + 55)) {
            try {
                mapsInstance.minijuego(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println(this.id + " No se interactua ");
        }

    }

    public void createObstacleTile(double w, double h, double x, double y) {
        ObstacleTile tile = new ObstacleTile(w, h, x, y);
        this.root.getChildren().add(tile);
        this.barrier.add(tile);

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}




