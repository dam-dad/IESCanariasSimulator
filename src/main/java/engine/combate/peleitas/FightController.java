package engine.combate.peleitas;

import engine.world.Maps;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FightController {


    @FXML
    private Button botonAtaque;

    @FXML
    private Button botonEscapar;

    @FXML
    private Button botonFe;

    @FXML
    private Button botonMagia;

    @FXML
    private AnchorPane fightPane;

    @FXML
    private ImageView imagenJugador;

    @FXML
    private ImageView imagenMonstruo;

    @FXML
    private ProgressBar jugadorBarraVida;

    @FXML
    private Text jugadorVida;

    @FXML
    private ProgressBar monstruoBarraVida;

    @FXML
    private Text monstruoVida;

    @FXML
    private Text nombreMonstruo;

    @FXML
    private Text monstruoDamage;

    @FXML
    private Text jugadorDamage;

    private int x;
    private Jugador jugador = new Jugador(x);
    private Monstruo monstruo = new Monstruo();
    Stage stage;
    Maps maps = new Maps();
    private int I;

    private boolean prioridadMonstruo;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void initialize() {
        initializeFight();
    }

    @FXML
    private void initializeFight() {
        System.out.println("Inicializando la pelea...");

        double progresoJugador = jugador.getVida() / jugador.getVida_maxima();
        jugadorBarraVida.setProgress(progresoJugador);
        aplicarColorBarra(jugadorBarraVida, progresoJugador);
        jugadorVida.setText(String.valueOf(jugador.getVida()));

        imagenMonstruo.setImage(monstruo.getEnemy_image());
        System.out.println("Imagen del monstruo configurada: " + monstruo.getEnemy_image());
        nombreMonstruo.setText(monstruo.getName());

        double progresoMonstruo = monstruo.getVida() / monstruo.getVida_maxima();
        monstruoBarraVida.setProgress(progresoMonstruo);
        aplicarColorBarra(monstruoBarraVida, progresoMonstruo);
        monstruoVida.setText(String.valueOf(monstruo.getVida()));

        if (monstruo.getVelocidad() > jugador.getVelocidad()) {
            prioridadMonstruo = true;
            botonAtaque.fire();
        }
    }

    @FXML
    void actionButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(botonAtaque)) {
            accion(jugador.damageFisico(monstruo));
        } else if (event.getSource().equals(botonMagia)) {
            accion(jugador.damageSkill(monstruo, 0));
        } else if (event.getSource().equals(botonFe)) {
            accion(jugador.damageSkill(monstruo, 1));
        } else if (event.getSource().equals(botonEscapar)) {
            devolverAMundo();
            URL url = this.getClass().getClassLoader().getResource("fxml/FinPelea.fxml");
            if (url == null) return;
            AnchorPane pane = FXMLLoader.load(url);
            fightPane.getChildren().setAll(pane);
        }
    }

    private KeyFrame[] accionJugador(int damage) {
        if (jugador.Muerto()) {
            return new KeyFrame[0];
        }
        double progresoJugador = jugadorBarraVida.getProgress();
        double progresoMonstruo = monstruoBarraVida.getProgress();

        KeyValue monstruoVidaBarraK = new KeyValue(monstruoBarraVida.progressProperty(), monstruo.getVida() / monstruo.getVida_maxima());
        KeyValue monstruoVidaK = new KeyValue(monstruoVida.textProperty(), String.valueOf(monstruo.getVida()));
        KeyValue botonAtaqueK = new KeyValue(botonAtaque.disableProperty(), true);
        KeyValue botonMagiaK = new KeyValue(botonMagia.disableProperty(), true);
        KeyValue botonFeK = new KeyValue(botonFe.disableProperty(), true);
        KeyValue botonEscaparK = new KeyValue(botonEscapar.disableProperty(), true);

        KeyFrame frame0s = new KeyFrame(new Duration(0), botonAtaqueK, botonMagiaK, botonFeK, botonEscaparK, monstruoVidaBarraK, monstruoVidaK);

        KeyValue jugadorDamageK = new KeyValue(jugadorDamage.textProperty(), String.valueOf(damage));

        KeyFrame frame15s = new KeyFrame(new Duration(1350), jugadorDamageK);

        KeyFrame frame175s = new KeyFrame(new Duration(1550), monstruoVidaBarraK, jugadorDamageK);

        monstruo.calcularVida(damage);
        monstruoVidaBarraK = new KeyValue(monstruoBarraVida.progressProperty(), monstruo.getVida() / monstruo.getVida_maxima());
        monstruoVidaK = new KeyValue(monstruoVida.textProperty(), String.valueOf(monstruo.getVida()));
        botonAtaqueK = new KeyValue(botonAtaque.disableProperty(), false);
        botonMagiaK = new KeyValue(botonMagia.disableProperty(), false);
        botonFeK = new KeyValue(botonFe.disableProperty(), false);
        botonEscaparK = new KeyValue(botonEscapar.disableProperty(), false);

        KeyFrame frame200s = new KeyFrame(new Duration(1800), botonAtaqueK, botonMagiaK, botonFeK, botonEscaparK, monstruoVidaBarraK, monstruoVidaK);
        aplicarColorBarra(jugadorBarraVida, progresoJugador);
        aplicarColorBarra(monstruoBarraVida, progresoMonstruo);
        return new KeyFrame[]{frame0s, frame15s, frame175s, frame200s};
    }

    private KeyFrame[] accionMonstruo(int time) {
        if (monstruo.Muerto()) return new KeyFrame[0];

        double progresoJugador = jugadorBarraVida.getProgress();
        double progresoMonstruo = monstruoBarraVida.getProgress();

        KeyValue jugadorVidaBarraK = new KeyValue(jugadorBarraVida.progressProperty(), jugador.getVida() / jugador.getVida_maxima());
        KeyValue jugadorVidaK = new KeyValue(jugadorVida.textProperty(), String.valueOf(jugador.getVida()));
        KeyValue botonAtaqueK = new KeyValue(botonAtaque.disableProperty(), true);
        KeyValue botonMagiaK = new KeyValue(botonMagia.disableProperty(), true);
        KeyValue botonFeK = new KeyValue(botonFe.disableProperty(), true);
        KeyValue botonEscaparK = new KeyValue(botonEscapar.disableProperty(), true);

        KeyFrame frame0s = new KeyFrame(new Duration(0), botonAtaqueK, botonMagiaK, botonFeK, botonEscaparK, jugadorVidaBarraK, jugadorVidaK);

        int damage = monstruo.iaAccion(jugador);

        jugador.calcularVida(damage);
        KeyValue monstruoDamageK = new KeyValue(monstruoDamage.textProperty(), String.valueOf(damage));

        KeyFrame frame1350s = new KeyFrame(new Duration(time + 1350), monstruoDamageK);

        KeyFrame frame1550s = new KeyFrame(new Duration(time + 1550), jugadorVidaBarraK, monstruoDamageK);

        jugadorVidaBarraK = new KeyValue(jugadorBarraVida.progressProperty(), jugador.getVida() / jugador.getVida_maxima());
        monstruoDamageK = new KeyValue(monstruoDamage.textProperty(), null);
        botonAtaqueK = new KeyValue(botonAtaque.disableProperty(), false);
        botonMagiaK = new KeyValue(botonMagia.disableProperty(), false);
        botonFeK = new KeyValue(botonFe.disableProperty(), false);
        botonEscaparK = new KeyValue(botonEscapar.disableProperty(), false);
        jugadorVidaK = new KeyValue(jugadorVida.textProperty(), String.valueOf(jugador.getVida()));

        KeyFrame frame1800s = new KeyFrame(new Duration(time + 1800), monstruoDamageK, jugadorVidaBarraK, botonAtaqueK, botonMagiaK, botonFeK, botonEscaparK, jugadorVidaK);
        aplicarColorBarra(jugadorBarraVida, progresoJugador);
        aplicarColorBarra(monstruoBarraVida, progresoMonstruo);
        return new KeyFrame[]{frame0s, frame1350s, frame1550s, frame1800s};
    }

    private void accion(int damage) {
        Timeline timeline = new Timeline();
        List<KeyFrame> keyFrames = new ArrayList<>();

        if (!prioridadMonstruo) {
            Collections.addAll(keyFrames, accionJugador(damage));
        }

        Collections.addAll(keyFrames, accionMonstruo(prioridadMonstruo ? 0 : 1800));

        timeline.getKeyFrames().addAll(keyFrames);

        prioridadMonstruo = false;

        timeline.play();

        timeline.setOnFinished(e -> {
            if (jugador.Muerto() || monstruo.Muerto()) {
                botonEscapar.fire();
            }
        });
    }

    private void aplicarColorBarra(ProgressBar barra, double progreso) {
        if (progreso <= 0.25) {
            barra.setStyle("-fx-accent: red;");
        } else if (progreso <= 0.5) {
            barra.setStyle("-fx-accent: yellow;");
        } else {
            barra.setStyle("-fx-accent: green;");
        }
    }

    private void devolverAMundo(){
        switch (I) {
            case 1:
                maps.setX(50);
                maps.setY(300);
                maps.calleInstituto(stage);
                break;
            case 2:
                maps.calleInstituto2(stage);
                break;
            case 3:
                maps.plaza(stage);
                break;
            case 4:
                maps.plaza2(stage);
                break;
            case 5:
                maps.arcade(stage);
                break;
            case 6:
                maps.setX(50);
                maps.setY(600);
                maps.paradaGuagua(stage);

                break;
            case 7:
                maps.institutoPlaza(stage);
                break;
            case 8:
                maps.placita(stage);
                break;
            case 9:
                maps.lobbyInstituto(stage);
                break;
            case 10:
                maps.lobbyInstituto2(stage);
                break;
            case 11:
                maps.subidaInstituto(stage);
                break;
            default:
                maps.calleInstituto(stage);
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Monstruo getMonstruo() {
        return monstruo;
    }

    public void setI(int i) {
        I = i;
    }
}