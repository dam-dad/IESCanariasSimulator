package engine;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Maps {
    private Pane root = new Pane();
    private LinkedList<ObstacleTile> barrier;
    private Character character;

    private NPC npc;

    private Scene scene;

    private double x = 300;

    private double y = 300;

    private Image BackgroundImage;
    private BackgroundSize size = new BackgroundSize(-1.0, -1.0, false, false, true, false);

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private ImageView character_image = new ImageView(new Image("Down2.png"));

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private Dialog dialog;


    //Constructor

    public Maps() {

        this.barrier = new LinkedList<>();
    }

    public void worldBasics(){
        character_image.setLayoutX(x);
        character_image.setLayoutY(y);
        this.root.getChildren().add(character_image);
        ImageView inventory = new ImageView(new Image("inventory.jpg"));
        inventory.setLayoutX(5.0);
        inventory.setLayoutY(5.0);
        this.root.getChildren().add(inventory);
        ImageView key = new ImageView(new Image("key.png"));
        key.setLayoutX(10.0);
        key.setLayoutY(10.0);
        key.setOpacity(0.2);
        this.root.getChildren().add(key);

        dialog.dialog.setLayoutX(17);
        dialog.dialog.setLayoutY(600);
        root.getChildren().add(dialog.dialog);
        root.getChildren().add(dialog.dialogText);
    }

    public void calleInstituto(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Calle del instituto.");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("Down2.png")); //Aquí realmente solo marcamos la posición inicial que va a tener el npc en el escenario
        ImageView npc_image2 = new ImageView(new Image("Left2.png"));
        ImageView npc_image3 = new ImageView(new Image("Right2.png"));

        ImageView dialogImage = new ImageView(new Image("dialog_box2.png"));

        BackgroundImage = new Image("calleInstituto.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList<ObstacleTile>();

            //Muro izquierda
        this.createObstacleTile(19.0, 788.0, 268.0, 28.0); //(Width, Height, x, y)
            //Valla
        this.createObstacleTile(11.0, 671.0, 620.0, 0.0);
        this.createObstacleTile(11.0, 68.0, 620.0, 732.0);
            //Flores
        this.createObstacleTile(97.0, 39.0, 633.0, 741.0);
        this.createObstacleTile(97.0, 39.0, 633.0, 584.0);
            //Escaleras
        this.createObstacleTile(68.0, 16.0, 732.0, 624.0);
        this.createObstacleTile(68.0, 16.0, 732.0, 716.0);


        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 1, 370, 350);

        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        NPC npc2 = new NPC(this.root, stage, scene, npc_image2, 2, 550, 150);

        this.npc = npc2;
        npc2.NPCBasics(npc_image2, npc2.getX(), npc2.getY(), barrier);

        NPC npc3 = new NPC(this.root, stage, scene, npc_image3, 3, 300, 550);

        this.npc = npc3;
        npc3.NPCBasics(npc_image3, npc3.getX(), npc3.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(1);
        character.addNPC(npc);
        character.addNPC(npc2);
        character.addNPC(npc3);

        npc.addDialogs(dialog, "Tengo sueño");
        npc2.addDialogs(dialog, "Hola que tal");
        npc3.addDialogs(dialog, "Me cago en Java");


        System.out.println(character.getNPCs()); //DEBUG


    }

    //Instituto-plaza
    public void institutoPlaza(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Calle del instituto.");
        stage.setScene(scene);
        stage.show();

        ImageView npc_image = new ImageView(new Image("Down2.png"));
        ImageView npc_image2 = new ImageView(new Image("Down2.png"));
        ImageView npc_image3 = new ImageView(new Image("Down2.png"));


        BackgroundImage = new Image("instituto-plaza.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

            //Muro Derecha
        this.createObstacleTile(92.0, 600, 708.0, 200.0); //(Width, Height, x, y)
            //Edificio Medio
        this.createObstacleTile(223.0, 112.0, 280.0, 688.0);
            //Vallas
        this.createObstacleTile(7.0, 403.0, 496.0, 216.0);
        this.createObstacleTile(207.0, 31.0, 280.0, 204.0);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 1, 100, 250);

        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        NPC npc2 = new NPC(this.root, stage, scene, npc_image2, 1, 400, 650);

        this.npc = npc2;
        npc2.NPCBasics(npc_image2, npc2.getX(), npc2.getY(), barrier);

        NPC npc3 = new NPC(this.root, stage, scene, npc_image3, 1, 50, 150);

        this.npc = npc3;
        npc3.NPCBasics(npc_image3, npc3.getX(), npc3.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(2);
        character.addNPC(npc);
        character.addNPC(npc2);
        character.addNPC(npc3);

        npc.addDialogs(dialog, "¿Cuál es la forma orientada a objetos para volverse rico? \nHerencia");
        npc2.addDialogs(dialog, "Fran, ten piedad con la recu porfa :(");
        npc3.addDialogs(dialog, "4 días para conseguir que los diálogos funcionen :')");

        System.out.println(character.getNPCs());


    }

    public void plaza(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Plaza del Alteza.");
        stage.setScene(scene);
        stage.show();

        BackgroundImage = new Image("plaza.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();
            //Muro Derecha
        this.createObstacleTile(31.0, 343.0, 480.0, 0.0); //(Width, Height, x, y)
            //Muro Derecha 2
        this.createObstacleTile(31.0, 175.0, 480.0, 416.0);
            //Papelera
        this.createObstacleTile(27.0, 46.0, 452.0, 280.0);
            //Pared
        this.createObstacleTile(479.0, 67.0, 0.0, 0.0);
            //Caja
        this.createObstacleTile(67.0, 67.0, 408.0, 20.0);
            //Muro Abajo
        this.createObstacleTile(511.0, 59.0, 0.0, 592.0);
            //Balancin
        this.createObstacleTile(19.0, 32.0, 248.0, 192.0);
            //Tobogan
        this.createObstacleTile(75.0, 123.0, 48.0, 116.0);
            //Banco
        this.createObstacleTile(103.0, 43.0, 188.0, 548.0);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(3);


    }
    public void plaza2(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Plaza del Alteza.");
        stage.setScene(scene);
        stage.show();

        BackgroundImage = new Image("plaza2.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();
        //Muro Izquierda
        this.createObstacleTile(31.0, 147.0, 0.0, 0.0); //(Width, Height, x, y)
        //Muro Izquierda 2
        this.createObstacleTile(31.0, 395.0, 0.0, 196.0);
        //Papelera
        this.createObstacleTile(27.0, 46.0, 32.0, 388.0);
        //Pared
        this.createObstacleTile(800.0, 67.0, 0.0, 0.0);
        //Muro Abajo
        this.createObstacleTile(583.0, 59.0, 0.0, 592.0);
        //Muro Abajo 2
        this.createObstacleTile(48.0, 59.0, 752.0, 592.0);
        //PaloMuro
        this.createObstacleTile(31.0, 45.0, 552.0, 556.0);
        //PaloMuro 2
        this.createObstacleTile(31.0, 45.0, 752.0, 556.0);
        //Banco arriba
        this.createObstacleTile(39.0, 103.0, 32.0, 276.0);
        //Banco mitad
        this.createObstacleTile(39.0, 103.0, 32.0, 440.0);
        //Banco abajo
        this.createObstacleTile(103.0, 43.0, 244.0, 548.0);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(4);

    }

    public void arcade(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Salón Arcade.");
        stage.setScene(scene);
        stage.show();

        BackgroundImage = new Image("arcade.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();
        //Muro Izquierda
        this.createObstacleTile(159.0, 800.0, 0.0, 0.0); //(Width, Height, x, y)
        //Muro Derecha
        this.createObstacleTile(159.0, 800.0, 644.0, 0.0);
        //Pared fondo
        this.createObstacleTile(800.0, 395.0, 0.0, 0.0);
        //Pared 1
        this.createObstacleTile(355.0, 36.0, 0.0, 764.0);
        //Pared 2
        this.createObstacleTile(352.0, 36.0, 448.0, 764.0);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(5);

    }

    // Método para obtener la referencia a la Scene


    public void createObstacleTile(double w, double h, double x, double y) {
        ObstacleTile tile = new ObstacleTile(w, h, x, y);
        this.root.getChildren().add(tile);
        this.barrier.add(tile);

    }


}
