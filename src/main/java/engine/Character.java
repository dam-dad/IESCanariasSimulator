package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    private List<NPC> npcs = new ArrayList<>();
    public void addNPC(NPC npc) {
        npcs.add(npc);
    }
    public List<NPC> getNPCs() {
        return npcs;
    }

    public Character(Pane root, Stage stage, Scene scene, LinkedList<ObstacleTile> barrier, final ImageView character_image)

    {
        this.barrier = barrier;
        this.character_image = character_image;
        this.root = root;
        this.stage = stage;
        this.walkingUpImageList = new ArrayList();
        this.walkingUpImageList.add(new Image("Up1.png"));
        this.walkingUpImageList.add(new Image("Up2.png"));
        this.walkingUpImageList.add(new Image("Up3.png"));
        this.walkingDownImageList = new ArrayList();
        this.walkingDownImageList.add(new Image("Down1.png"));
        this.walkingDownImageList.add(new Image("Down2.png"));
        this.walkingDownImageList.add(new Image("Down3.png"));
        this.walkingRightImageList = new ArrayList();
        this.walkingRightImageList.add(new Image("Right1.png"));
        this.walkingRightImageList.add(new Image("Right2.png"));
        this.walkingRightImageList.add(new Image("Right3.png"));
        this.walkingLeftImageList = new ArrayList();
        this.walkingLeftImageList.add(new Image("Left1.png"));
        this.walkingLeftImageList.add(new Image("Left2.png"));
        this.walkingLeftImageList.add(new Image("Left3.png"));
        this.upCount = 0;
        this.downCount = 0;
        this.rightCount = 0;
        this.leftCount = 0;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent e) {
                KeyCode code = e.getCode();
                if (code == KeyCode.UP || code == KeyCode.W) {
                    Character.this.moveUp = true;
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    Character.this.moveDown = true;
                } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    Character.this.moveRight = true;
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    Character.this.moveLeft = true;
                } else if (code == KeyCode.SPACE) {
                    for (NPC npc : getNPCs()) {
                            npc.npcInteraction("Hola que tal", x, y);
                    }
                }

            }

        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent e) {
                KeyCode code = e.getCode();
                if (code == KeyCode.UP || code == KeyCode.W) {
                    Character.this.moveUp = false;
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    Character.this.moveDown = false;
                } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    Character.this.moveRight = false;
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    Character.this.moveLeft = false;
                } else if (code == KeyCode.SPACE) {
                System.out.println("No interacción");
                }

            }
        });
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
        System.out.println(this.timer);



    }

    private void moveCharacter(int dx, int dy) {

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
            System.out.println(mapsInstance.getX() + " " + mapsInstance.getY());



            if (x - cx >= 0.0 && x + cx <= 800.0 && y - cy >= 0.0 && y + cy <= 800.0 && !this.checkCollision(x - cx, y - cy)) {
                this.character_image.relocate(x - cx, y - cy);
            }

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

                mapsInstance.setX(626);
                mapsInstance.setY(702);

                mapsInstance.institutoPlaza(stage);

            }
            //instituto-plaza

            else if (x >= 0.0 && x <= 400.0 && y >= 757.0 && y <= 778.0 &&
                    i == 2) {
                // Hacer algo si las escenas son iguales
                this.timer.stop();
                this.root = new Pane();

                mapsInstance.setX(632);
                mapsInstance.setY(73);
                mapsInstance.plaza(stage);

            }

            else if (x >= 400.0 && x <= 800.0 && y >= 757.0 && y <= 778.0 &&
                    i == 2) {
                // Hacer algo si las escenas son iguales
                this.timer.stop();
                this.root = new Pane();

                mapsInstance.setX(428);
                mapsInstance.setY(51);
                mapsInstance.calleInstituto(stage);

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

                mapsInstance.setX(116);
                mapsInstance.setY(702);
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


            else {
                // Imprimir valores de x e y cuando la condición no se cumple
                System.out.println("x: " + x + ", y: " + y + ", i: " + this.i);


            }

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

    //Getters y Setters


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
