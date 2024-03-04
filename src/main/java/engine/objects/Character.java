package engine.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import engine.combate.peleitas.FightController;
import engine.combate.peleitas.SumadorCombate;
import engine.world.Maps;
import engine.world.ObstacleTile;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Character {

    private Maps mapsInstance = new Maps();
    private Pane root;
    private Stage stage;
    private AnimationTimer timer;
    private String standingDown = "Down2.png";
    private String standingUp = "Up2.png";
    private String standingLeft = "Left2.png";
    private String standingRight = "Right2.png";
    private ImageView character_image;
    private LinkedList<ObstacleTile> barrier;
    private boolean moveUp;
    private boolean moveRight;
    private boolean moveDown;
    private boolean moveLeft;
    private ArrayList<Image> walkingUpImageList;
    private ArrayList<Image> walkingDownImageList;
    private ArrayList<Image> walkingRightImageList;
    private ArrayList<Image> walkingLeftImageList;
    private int switchWhenZero = 0;
    private int upCount;
    private int downCount;
    private int rightCount;
    private int leftCount;
    private double x;
    private double y;
    private int i = 0;
    private int mx = 0;
    private int my = 0;

    private List<NPC> npcs = new ArrayList<>();
    public void addNPC(NPC npc) {
        npcs.add(npc);
    }
    public List<NPC> getNPCs() {
        return npcs;
    }

    private List<Elements> ob = new ArrayList<>();
    public void addElements(Elements obs) {
        ob.add(obs);
    }
    public List<Elements> getElements() {
        return ob;
    }
    private Random random = new Random();

    private int sumador;

    public Character(Pane root, Stage stage, Scene scene, LinkedList<ObstacleTile> barrier, final ImageView character_image)
    {
        this.barrier = barrier;
        this.character_image = character_image;
        this.root = root;
        this.stage = stage;

        spritesStarter();
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        scene.setOnKeyReleased(e -> handleKeyRelease(e.getCode()));

        timerStart();

    }

    private void timerStart(){
        timer = new AnimationTimer() {
            public void handle(long now) {
                int dx = 0;
                int dy = 0;


                if (Character.this.moveUp) {
                    dy -= 2;
                    my = -2;
                    mx = 0;
                    if (Character.this.switchWhenZero == 0) {
                        character_image.setImage(Character.this.walkingUpImageList.get(Character.this.upCount % 3));
                        ++Character.this.upCount;
                        Character.this.switchWhenZero = 4;
                    } else {
                        --Character.this.switchWhenZero;
                    }
                } else if (Character.this.moveDown) {
                    dy += 2;
                    my = 2;
                    mx = 0;
                    if (Character.this.switchWhenZero == 0) {
                        character_image.setImage(Character.this.walkingDownImageList.get(Character.this.downCount % 3));
                        ++Character.this.downCount;
                        Character.this.switchWhenZero = 4;
                    } else {
                        --Character.this.switchWhenZero;
                    }
                } else if (Character.this.moveRight) {
                    dx += 2;
                    mx = 2;
                    my = 0;
                    if (Character.this.switchWhenZero == 0) {
                        character_image.setImage(Character.this.walkingRightImageList.get(Character.this.rightCount % 3));
                        ++Character.this.rightCount;
                        Character.this.switchWhenZero = 4;
                    } else {
                        --Character.this.switchWhenZero;
                    }
                } else if (Character.this.moveLeft) {
                    dx -= 2;
                    mx = -2;
                    my = 0;
                    if (Character.this.switchWhenZero == 0) {
                        character_image.setImage(Character.this.walkingLeftImageList.get(Character.this.leftCount % 3));
                        ++Character.this.leftCount;
                        Character.this.switchWhenZero = 4;
                    } else {
                        --Character.this.switchWhenZero;
                    }
                }

                Character.this.moveCharacter(dx, dy);
            }
        };
        this.timer.start();
    }
    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case UP, W -> moveUp = true;
            case DOWN, S -> moveDown = true;
            case RIGHT, D -> moveRight = true;
            case LEFT, A -> moveLeft = true;
            case SPACE -> handleSpaceKeyPress();
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case UP, W -> moveUp = false;
            case DOWN, S -> moveDown = false;
            case RIGHT, D -> moveRight = false;
            case LEFT, A -> moveLeft = false;
            case SPACE -> System.out.println("No interacción");
        }
    }

    private void handleSpaceKeyPress() {
        for (NPC npc : getNPCs()) {
            npc.npcInteraction(x, y);
        }
        for (Elements ob : getElements()) {
            ob.elementInteraction(x, y);
        }
    }

    private void moveCharacter(int dx, int dy) {

        if (shouldStartRandomCombat()) {
            startRandomCombat();
        }

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

        else {
            double cx = this.character_image.getBoundsInLocal().getWidth() / 2.0;
            double cy = this.character_image.getBoundsInLocal().getHeight() / 2.0;
            x = cx + this.character_image.getLayoutX() + (double)dx;
            y = cy + this.character_image.getLayoutY() + (double)dy;

            mapsInstance.setX(x);
            mapsInstance.setY(y);
            //System.out.println(mapsInstance.getX() + " " + mapsInstance.getY());



            if (x - cx >= 0.0 && x + cx <= 800.0 && y - cy >= 0.0 && y + cy <= 800.0 && !this.checkCollision(x - cx, y - cy)) {
                this.character_image.relocate(x - cx, y - cy);
            }

            mapsChanger();

        }

    }


    private boolean shouldStartRandomCombat() {
        // Verificar si el personaje se está moviendo
        if (!isMoving()) {
            return false;
        }
        double probability = 0.001;

        // Generar un número aleatorio entre 0 y 1
        double randomValue = random.nextDouble();

        // Verificar si el número aleatorio es menor que la probabilidad
        return randomValue < probability;
    }

    private void startRandomCombat() {
        System.out.println("¡Combate aleatorio!");
        System.out.println(x);

        this.timer.stop();
        this.root = new Pane();

        mapsInstance.setX(290);
        mapsInstance.setY(102);
        try {
            sumador += 1;
            mapsInstance.combate(stage, i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mapsChanger(){
        //CalleInstituto
        if (x >= 137.0 && x <= 257.0 && y >= 757.0 && y <= 778.0 &&
                i == 0) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();
            mapsInstance.setX(300);
            mapsInstance.setY(300);

            mapsInstance.calleInstituto(stage);

        }

        else if (x >= 0.0 && x <= 800.0 && y >= 23.0 && y <= 43 &&
                i == 1) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(470);
            mapsInstance.setY(719);

            mapsInstance.calleInstituto2(stage);

        }

        else if (x >= 0.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                i == 1) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(75);
            mapsInstance.setY(60);
            mapsInstance.placita(stage);

        }

        else if (x >= 757.0 && x <= 778.0 && y >= 0.0 && y <= 800.0 &&
                i == 1) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(25);
            mapsInstance.setY(454);
            mapsInstance.lobbyInstituto(stage);

        }

        //calleInstituto2
        else if (x >= 0.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                i == 2) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(500);
            mapsInstance.setY(73);
            mapsInstance.calleInstituto(stage);

        }

        else if (x >= 0.0 && x <= 30.0 && y >= 0.0 && y <= 800.0 &&
                i == 2) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(x + 640);
            mapsInstance.setY(y - 24);
            mapsInstance.institutoPlaza(stage);

        }

        else if (x >= 757.0 && x <= 778.0 && y >= 0.0 && y <= 800.0 &&
                i == 2) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(25);
            mapsInstance.setY(583);
            mapsInstance.paradaGuagua(stage);

        }

        //institutoPlaza
        else if (x >= 770.0 && x <= 800.0 && y >= 0.0 && y <= 800.0 &&
                i == 7) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(x - 680);
            mapsInstance.setY(y - 24);
            mapsInstance.calleInstituto2(stage);

        }

        else if (x >= 0.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                i == 7) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(620);
            mapsInstance.setY(45);
            mapsInstance.plaza(stage);

        }

        //plaza1
        else if (x >= 23.0 && x <= 47.0 && y >= 0.0 && y <= 800.0 &&
                i == 3) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(x + 640);
            mapsInstance.setY(y - 24);
            mapsInstance.plaza2(stage);

        }

        else if (x >= 460.0 && x <= 800.0 && y >= 23.0 && y <= 47.0 &&
                i == 3) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(370);
            mapsInstance.setY(719);
            mapsInstance.institutoPlaza(stage);

        }

        //plaza2
        else if (x >= 757.0 && x <= 778.0 && y >= 0.0 && y <= 800.0 &&
                i == 4) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(x - 680);
            mapsInstance.setY(y - 24);
            mapsInstance.plaza(stage);

        }

        else if (x >= 472.0 && x <= 535.0 && y >= 0.0 && y <= 62.0 &&
                i == 4) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(370);
            mapsInstance.setY(725);
            mapsInstance.arcade(stage);

        }

        //arcade
        else if (x >= 0.0 && x <= 800.0 && y >= 750.0 && y <= 800.0 &&
                i == 5) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(472);
            mapsInstance.setY(70);
            mapsInstance.plaza2(stage);

        }

        //Parada Guagua
        else if (x >= 0.0 && x <= 30.0 && y >= 0.0 && y <= 800.0 &&
                i == 6) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(730);
            mapsInstance.setY(100);
            mapsInstance.calleInstituto2(stage);

        }

        //Placita
        else if (x >= 0.0 && x <= 800.0 && y >= 23.0 && y <= 43 &&
                i == 8) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(422);
            mapsInstance.setY(707);

            mapsInstance.calleInstituto(stage);

        }

        //Lobby
        else if (x >= 0.0 && x <= 30.0 && y >= 0.0 && y <= 800.0 &&
                i == 9) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(694);
            mapsInstance.setY(652);
            mapsInstance.calleInstituto(stage);

        }

        else if (x >= 757.0 && x <= 778.0 && y >= 0.0 && y <= 800.0 &&
                i == 9) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(25);
            mapsInstance.setY(690);
            mapsInstance.lobbyInstituto2(stage);

        }

        //lobbyInstituto2
        else if (x >= 0.0 && x <= 30.0 && y >= 0.0 && y <= 800.0 &&
                i == 10) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(730);
            mapsInstance.setY(272);
            mapsInstance.lobbyInstituto(stage);

        }

        else if (x >= 240.0 && x <= 348.0 && y >= 20.0 && y <= 108.0 &&
                i == 10) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(180);
            mapsInstance.setY(720);
            mapsInstance.subidaInstituto(stage);

        }

        //subidaInstituto
        else if (x >= 0.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                i == 11) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(290);
            mapsInstance.setY(102);
            mapsInstance.lobbyInstituto2(stage);

        }

        else if (x >= 0.0 && x <= 800.0 && y >= 0.0 && y <= 50.0 &&
                i == 11) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(180);
            mapsInstance.setY(720);
            mapsInstance.subidaInstituto2(stage);

        }

        //subidaInstituto2
        else if (x >= 0.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                i == 12) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(290);
            mapsInstance.setY(102);
            mapsInstance.subidaInstituto(stage);

        }

        else if (x >= 156.0 && x <= 212.0 && y >= 0.0 && y <= 120.0 &&
                i == 12) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(350);
            mapsInstance.setY(720);
            mapsInstance.lobbyAulas(stage);

        }

        //lobbyAulas
        else if (x >= 0.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                i == 13) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(290);
            mapsInstance.setY(102);
            mapsInstance.subidaInstituto2(stage);

        }

        else if (x >= 450 && x <= 700.0 && y >= 0.0 && y <= 116.0 &&
                i == 13) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(300);
            mapsInstance.setY(230);
            mapsInstance.lobbyAulas2(stage);

        }

        //lobbyAulas2
        else if (x >= 284.0 && x <= 404.0 && y >= 178.0 && y <= 220.0 &&
                i == 14) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(487);
            mapsInstance.setY(121);
            mapsInstance.lobbyAulas(stage);

        }

        else if (x >= 770.0 && x <= 800.0 && y >= 0.0 && y <= 800.0 &&
                i == 14) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(725);
            mapsInstance.setY(230);
            mapsInstance.aula(stage);

        }

        //Aula
        else if (x >= 770.0 && x <= 800.0 && y >= 0.0 && y <= 800.0 &&
                i == 15) {
            // Hacer algo si las escenas son iguales
            this.timer.stop();
            this.root = new Pane();

            mapsInstance.setX(710);
            mapsInstance.setY(380);
            mapsInstance.lobbyAulas2(stage);

        }
        else {
            //System.out.println("x: " + x + ", y: " + y + ", i: " + this.i);
        }
    }

    private boolean checkCollision(double wantsToGoToThisX, double wantsToGoToThisY) {
        Iterator<ObstacleTile> it = this.barrier.iterator();

        boolean inside;
        do {
            if (!it.hasNext()) {
                return false;
            }

            ObstacleTile t = (ObstacleTile)it.next();
            double spriteMinX = wantsToGoToThisX + 5.0;
            double spriteMinY = (wantsToGoToThisY + 5.0) + 24.0;
            double spriteMaxX = wantsToGoToThisX + this.character_image.getBoundsInLocal().getWidth() - 5.0;
            double spriteMaxY = wantsToGoToThisY + this.character_image.getBoundsInLocal().getHeight() - 1.0;
            double tMinX = t.getX();
            double tMinY = t.getY();
            double tMaxX = t.getX() + t.getWidth();
            double tMaxY = t.getY() + t.getHeight();
            inside = spriteMaxX > tMinX && spriteMinX < tMaxX && spriteMaxY > tMinY && spriteMinY < tMaxY;

        } while(!inside);

        return true;
    }

    private void spritesStarter(){
        walkingUpImageList = new ArrayList();
        walkingUpImageList.add(new Image("Up1.png"));
        walkingUpImageList.add(new Image("Up2.png"));
        walkingUpImageList.add(new Image("Up3.png"));
        walkingDownImageList = new ArrayList();
        walkingDownImageList.add(new Image("Down1.png"));
        walkingDownImageList.add(new Image("Down2.png"));
        walkingDownImageList.add(new Image("Down3.png"));
        walkingRightImageList = new ArrayList();
        walkingRightImageList.add(new Image("Right1.png"));
        walkingRightImageList.add(new Image("Right2.png"));
        walkingRightImageList.add(new Image("Right3.png"));
        walkingLeftImageList = new ArrayList();
        walkingLeftImageList.add(new Image("Left1.png"));
        walkingLeftImageList.add(new Image("Left2.png"));
        walkingLeftImageList.add(new Image("Left3.png"));
    }

    private boolean isMoving() {
        return moveUp || moveDown || moveRight || moveLeft;
    }

    //Getters y Setters


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
