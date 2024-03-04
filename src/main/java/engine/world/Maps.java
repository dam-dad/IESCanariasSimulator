package engine.world;


import engine.combate.peleitas.FightController;
import engine.minijuego.MinijuegoController;
import engine.objects.Character;
import engine.objects.Elements;
import engine.objects.NPC;
import engine.ui.Dialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private Elements elements;

    private Scene scene;

    private double x = 290;

    private double y = 400;

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

    private int sumadorCombate = 0;


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

        dialog.getDialog().setLayoutX(17);
        dialog.getDialog().setLayoutY(600);
        root.getChildren().add(dialog.getDialog());
        root.getChildren().add(dialog.getDialogText());
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
        ImageView npc_image3 = new ImageView(new Image("NPCs/random4_Right.png"));

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
    public void calleInstituto2(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Calle del instituto.");
        stage.setScene(scene);
        stage.show();

        ImageView npc_image = new ImageView(new Image("NPCs/edu_Down.png"));
        ImageView npc_image2 = new ImageView(new Image("Down2.png"));
        ImageView npc_image3 = new ImageView(new Image("Down2.png"));


        BackgroundImage = new Image("calleinstituto2.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

            //Muro Izquierda
        this.createObstacleTile(16.0, 403, 0, 217.0); //(Width, Height, x, y)
        this.createObstacleTile(16.0, 112, 0, 688.0);
            //Muro derecha
        this.createObstacleTile(12.0, 544, 620, 256.0);
        this.createObstacleTile(168.0, 12, 632, 256.0);
            //Bancos y vallas
        this.createObstacleTile(28.0, 104.0, 244.0, 416.0);
        this.createObstacleTile(28.0, 104.0, 380.0, 416.0);

        this.createObstacleTile(12.0, 32.0, 236.0, 244.0);
        this.createObstacleTile(12.0, 32.0, 236.0, 364.0);
        this.createObstacleTile(12.0, 32.0, 236.0, 544.0);
        this.createObstacleTile(12.0, 32.0, 236.0, 664.0);
        this.createObstacleTile(12.0, 16.0, 236.0, 784.0);

        this.createObstacleTile(12.0, 32.0, 404.0, 244.0);
        this.createObstacleTile(12.0, 32.0, 404.0, 364.0);
        this.createObstacleTile(12.0, 32.0, 404.0, 544.0);
        this.createObstacleTile(12.0, 32.0, 404.0, 664.0);
        this.createObstacleTile(12.0, 16.0, 404.0, 784.0);


        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 6, 100, 250);

        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        NPC npc2 = new NPC(this.root, stage, scene, npc_image2, 2, 400, 650);

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
        npc2.addDialogs(dialog, "Tuviste piedad, gracias :D");
        npc3.addDialogs(dialog, "4 días para conseguir que los diálogos funcionen :')");

        System.out.println(character.getNPCs());


    }

    //Plaza Alteza
    public void plaza(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Plaza del Alteza.");
        stage.setScene(scene);
        stage.show();

        ImageView npc_image = new ImageView(new Image("NPCs/flavia_Up.png"));
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

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 7, 330, 540);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(3);

        character.addNPC(npc);
        npc.addDialogs(dialog, "...");

    }
    public void plaza2(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Plaza del Alteza.");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("NPCs/SolajeroRight.png"));
        ImageView npc_image2 = new ImageView(new Image("NPCs/BrianUp.png"));

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

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 8, 40, 310);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        NPC npc2 = new NPC(this.root, stage, scene, npc_image2, 5, 370, 530);
        this.npc = npc2;
        npc2.NPCBasics(npc_image2, npc2.getX(), npc2.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(4);
        character.addNPC(npc);
        character.addNPC(npc2);

        npc.addDialogs(dialog, "...");
        npc2.addDialogs(dialog, "...");
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

        ImageView arcadeImage = new ImageView("arcadeMachine.png");
        Elements element = new Elements(this.root, stage, scene, 1, 250, 380);

        this.elements = element;
        element.elementsBasics(arcadeImage, element.getX(), element.getY(), 60, 87, barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(5);
        character.addElements(element);

    }

    //Parada guagua
    public void paradaGuagua(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Parada de la 026.");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("NPCs/random3_Left.png"));

        BackgroundImage = new Image("paradaGuagua.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Abajo
        this.createObstacleTile(800.0, 60, 0, 740.0); //(Width, Height, x, y)
        //Edificio
        this.createObstacleTile(468.0, 512, 0, 0.0);
        //Muro Derecha
        this.createObstacleTile(60.0, 800, 740, 0.0);

        ImageView guaguaImage = new ImageView("parada026.png");
        Elements element = new Elements(this.root, stage, scene, 2, 675, 30);

        this.elements = element;
        element.elementsBasics(guaguaImage, element.getX(), element.getY(), 50, 180, barrier);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 2, 630, 150);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(6);
        character.addElements(element);
        element.addDialogs(dialog, "Parada de la 026");
        character.addNPC(npc);
        npc.addDialogs(dialog, "...");
    }

    //instituto-plaza
    public void institutoPlaza(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Aparcamientos");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("NPCs/random_Right.png"));

        BackgroundImage = new Image("instituto-plaza.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Valla izquierda
        this.createObstacleTile(236.0, 32, 0, 204.0); //(Width, Height, x, y)
        //Valla Izquierda 2
        this.createObstacleTile(8.0, 100, 240, 216.0);
        this.createObstacleTile(8.0, 248, 240, 420.0);
        //Casa Izquierda
        this.createObstacleTile(248.0, 132, 0, 668.0);
        //Valla Derecha
        this.createObstacleTile(208.0, 32, 560, 204.0);
        this.createObstacleTile(8.0, 404, 776, 216.0);
        //Casa Derecha
        this.createObstacleTile(240, 112, 560, 688.0);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 1, 290, 265);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(7);
        character.addNPC(npc);
        npc.addDialogs(dialog, "...");
    }

    //placita
    public void placita(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Placita");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("NPCs/monchi_Down.png"));

        BackgroundImage = new Image("placita.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Casa
        this.createObstacleTile(528.0, 432, 272, 0.0); //(Width, Height, x, y)
        //Muro y banco
        this.createObstacleTile(32.0, 176, 272, 432.0);
        this.createObstacleTile(104, 44, 392, 584.0);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 4, 425, 640);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(8);
        character.addNPC(npc);
        npc.addDialogs(dialog, "...");

    }

    //Lobby
    public void lobbyInstituto(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Lobby del IES Canarias");
        stage.setScene(scene);
        stage.show();

        ImageView npc_image = new ImageView(new Image("NPCs/secretaria_Left.png"));

        BackgroundImage = new Image("lobby_instituto.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Secretaría
        this.createObstacleTile(104.0, 292, 0, 140.0); //(Width, Height, x, y)
        this.createObstacleTile(188.0, 152, 104, 140.0);
        this.createObstacleTile(324, 108, 292, 140.0);
        this.createObstacleTile(184, 152, 616, 140.0);
        //Portería
        this.createObstacleTile(61, 300, 739, 372.0);
        //Barra de abajo
        this.createObstacleTile(800, 140, 0, 660.0);

        //Elements

        ImageView cartelImage = new ImageView("puerta.png");
        Elements element = new Elements(this.root, stage, scene, 4, 204, 145);

        this.elements = element;
        element.elementsBasics(cartelImage, element.getX(), element.getY(), 60, 148, barrier);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 12, 370, 350);

        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(9);
        character.addNPC(npc);
        npc.addDialogs(dialog, "Tengo sueño");
        character.addElements(element);
        element.addDialogs(dialog, "La secretaría está cerrada.\nVuelva en otro momento a ver si tiene más suerte. \nCampeón.");

    }

    //Lobby 2
    public void lobbyInstituto2(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Lobby del IES Canarias");
        stage.setScene(scene);
        stage.show();

        BackgroundImage = new Image("lobby2_instituto.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Izquierda
        this.createObstacleTile(48.0, 712, 0, 0.0); //(Width, Height, x, y)
        //Muro Derecha
        this.createObstacleTile(260.0, 712, 540, 0.0);
        //Pared del fondo
        this.createObstacleTile(192, 108, 48, 0);
        this.createObstacleTile(192, 108, 348, 0);

        ImageView cartelImage = new ImageView("cartelmovil.png");
        Elements element = new Elements(this.root, stage, scene, 3, 425, 30);

        ImageView cartelImage2 = new ImageView("cartelmovil.png");
        Elements element2 = new Elements(this.root, stage, scene, 3, 650, 630);

        ImageView cartelImage3 = new ImageView("cartelmovil.png");
        Elements element3 = new Elements(this.root, stage, scene, 3, 110, 30);

        this.elements = element;
        element.elementsBasics(cartelImage, element.getX(), element.getY(), 50, 70, barrier);

        this.elements = element2;
        element2.elementsBasics(cartelImage2, element2.getX(), element2.getY(), 50, 70, barrier);

        this.elements = element3;
        element3.elementsBasics(cartelImage3, element3.getX(), element3.getY(), 50, 70, barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(10);
        character.addElements(element);
        character.addElements(element2);
        character.addElements(element3);
        element.addDialogs(dialog, "QUEDA PROHIBIDO EL USO DE MÓVILES");
        element2.addDialogs(dialog, "QUEDA PROHIBIDO EL USO DE MÓVILES");
        element3.addDialogs(dialog, "QUEDA PROHIBIDO EL USO DE MÓVILES");
    }

    //subidaInstituto
    public void subidaInstituto(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Lobby del IES Canarias");
        stage.setScene(scene);
        stage.show();

        BackgroundImage = new Image("subida_instituto.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Izquierda
        this.createObstacleTile(48.0, 568, 0, 0.0); //(Width, Height, x, y)
        this.createObstacleTile(48.0, 80, 0, 720.0);
        //Muro Derecha
        this.createObstacleTile(460.0, 568, 340, 0.0);
        //Pared Abajo
        this.createObstacleTile(460, 80, 340, 720);
        //Bancos
        this.createObstacleTile(28, 104, 56, 4);
        this.createObstacleTile(28, 104, 56, 296);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(11);

    }

    //subidaInstituto2
    public void subidaInstituto2(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Lobby del IES Canarias");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("NPCs/YormanRight.png"));

        BackgroundImage = new Image("subida_instituto2.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Izquierda
        this.createObstacleTile(48.0, 800, 0, 0.0); //(Width, Height, x, y)
        //Muro Derecha
        this.createObstacleTile(460.0, 652, 340, 148);
        //Pared Arriba
        this.createObstacleTile(156, 108, 48, 0);
        this.createObstacleTile(688, 108, 212, 0);
        //Bancos
        this.createObstacleTile(28, 104, 56, 316);
        this.createObstacleTile(28, 104, 56, 672);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 9, 80, 235);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);


        //Elements

        ImageView cartelImage = new ImageView("cartelmovil.png");
        Elements element = new Elements(this.root, stage, scene, 3, 450, 30);

        this.elements = element;
        element.elementsBasics(cartelImage, element.getX(), element.getY(), 50, 70, barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(12);
        character.addNPC(npc);
        npc.addDialogs(dialog, "Tengo sueño");
        character.addElements(element);
        element.addDialogs(dialog, "QUEDA PROHIBIDO EL USO DE MÓVILES");
    }

    //lobbyAulas
    public void lobbyAulas(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Aulas");
        stage.setScene(scene);
        stage.show();

        BackgroundImage = new Image("lobby_aulas.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Izquierda
        this.createObstacleTile(240, 180, 0, 0.0); //(Width, Height, x, y)
        this.createObstacleTile(240, 524, 0, 276);
        //Muro Derecha
        this.createObstacleTile(240, 180, 560, 0.0); //(Width, Height, x, y)
        this.createObstacleTile(240, 524, 560, 276);
        //Barandilla
        this.createObstacleTile(8, 116, 440, 0);
        //Bancos
        this.createObstacleTile(28, 104, 244, 48);
        this.createObstacleTile(28, 104, 528, 476);
        this.createObstacleTile(28, 104, 244, 476);

        //Elements

        ImageView cartelImage = new ImageView("puerta.png");
        Elements element = new Elements(this.root, stage, scene, 4, 628, 32);

        this.elements = element;
        element.elementsBasics(cartelImage, element.getX(), element.getY(), 60, 148, barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(13);
        character.addElements(element);
        element.addDialogs(dialog, "CERRADO. \nPara ir al baño, vete a tu casa o yo que sé XD");
    }

    //lobbyAulas2
    public void lobbyAulas2(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Aulas");
        stage.setScene(scene);
        stage.show();
        ImageView npc_image = new ImageView(new Image("NPCs/BettyUp.png"));

        BackgroundImage = new Image("lobby_aulas2.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Izquierda
        this.createObstacleTile(240, 344, 0, 0.0); //(Width, Height, x, y)
        this.createObstacleTile(240, 200, 0, 440);
        //Muro Derecha
        this.createObstacleTile(240, 344, 560, 0.0); //(Width, Height, x, y)
        this.createObstacleTile(240, 200, 560, 440);
        //Muro Fondo
        this.createObstacleTile(48, 56, 240, 164);
        //Barandilla
        this.createObstacleTile(164, 24, 396, 212);
        //Barra de abajo
        this.createObstacleTile(800, 164, 0, 636);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 10, 375, 370);
        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(14);
        character.addNPC(npc);
        npc.addDialogs(dialog, "Tengo sueño");
    }

    //Aula
    public void aula(Stage stage) {
        this.root = new Pane();
        this.scene = new Scene(this.root, 800.0, 800.0);
        setScene(scene);
        stage.setTitle("Aulas");
        stage.setScene(scene);
        stage.show();

        ImageView npc_image = new ImageView(new Image("NPCs/FranDown.png"));

        BackgroundImage = new Image("aula.png");


        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        //Colisiones
        this.barrier = new LinkedList();

        //Muro Izquierda
        this.createObstacleTile(128, 84, 12, 248.0); //(Width, Height, x, y)
        this.createObstacleTile(260, 400, 12, 400);
        //Muro Derecha
        this.createObstacleTile(44, 488, 756, 312); //(Width, Height, x, y)
        this.createObstacleTile(260, 388, 484, 512);
        //Muro Fondo
        this.createObstacleTile(800, 220, 0, 0);

        //NPCs

        NPC npc = new NPC(this.root, stage, scene, npc_image, 11, 185, 235);

        this.npc = npc;
        npc.NPCBasics(npc_image, npc.getX(), npc.getY(), barrier);

        //Dialog

        dialog = new Dialog(root, stage, scene);

        worldBasics();

        character = new Character(this.root, stage, scene, this.barrier, character_image);

        character.setI(15);
        character.addNPC(npc);
        npc.addDialogs(dialog, "Tengo sueño");
    }

    public void minijuego(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tablero.fxml"));
        MinijuegoController minijuego = new MinijuegoController();
        minijuego.setStage(stage);
        loader.setController(minijuego);
        Parent root = loader.load();
        stage.setTitle("Minijuego de Dianas");
        stage.setScene(new Scene(root, 800, 800));

        BackgroundImage = new Image("doomFondo.png");

        Background background = new Background(new BackgroundImage[]{new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)});
        this.root.setBackground(background);

        stage.show();

        MinijuegoController controlador = loader.getController();
        controlador.actualizarTiempo();

    }

    public void combate(Stage stage, int i, int sumador) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fight.fxml"));
        FightController fight = new FightController();
        fight.setI(i);
        fight.setStage(stage);
        loader.setController(fight);
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("Fight");
        stage.setScene(scene);
        stage.show();

        character = new Character(this.root, stage, scene, this.barrier, character_image);
    }

    // Método para obtener la referencia a la Scene


    public void createObstacleTile(double w, double h, double x, double y) {
        ObstacleTile tile = new ObstacleTile(w, h, x, y);
        this.root.getChildren().add(tile);
        this.barrier.add(tile);

    }

    public int getSumadorCombate() {
        return sumadorCombate;
    }

    public void setSumadorCombate(int sumadorCombate) {
        this.sumadorCombate = sumadorCombate;
    }
}