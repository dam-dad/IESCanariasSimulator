package engine.minijuego;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MinijuegoController implements Initializable {

    @FXML
    private GridPane maingrid;

    private Puntuacion puntuacion;
    private Diana dianaActual;

    private int dianasAcertadas;

    @FXML
    private Label tiempoLabel;

    @FXML
    public void actualizarTiempo() {
        tiempoLabel.setText("Tiempo: " + tiempoRestante + " s");
    }
    @FXML
    private Label puntosLabel;


    private int tiempoRestante = 60; // 60 segundos

    public MinijuegoController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntuacion = new Puntuacion();
        puntosLabel.setText("Puntos: " + puntuacion.getPuntos());
        tiempoLabel.setText("Tiempo: " + tiempoRestante + " s"); // Mostrar tiempo inicial
        iniciarMinijuego();
    }


    private void iniciarMinijuego() {
        // Resto del código

        dianaActual = generarDiana();
        mostrarDiana();

        // Actualizar el temporizador en la interfaz gráfica
        Timeline temporizadorTimeline = new Timeline(new KeyFrame(Duration.seconds(1.25), event -> {
            tiempoRestante--;
            tiempoLabel.setText("Tiempo: " + tiempoRestante + " s");
            if (tiempoRestante <= 0) {
                finDelJuego();
            }
        }));
        temporizadorTimeline.setCycleCount(tiempoRestante); // Establecer la duración del temporizador
        temporizadorTimeline.play();
    }


    private void finDelJuego() {
    }


    private void mostrarDiana() {
        generarDiana();
    }


    private void clicEnDiana() {
        puntuacion.sumarPuntos(50);
        puntosLabel.setText("Puntos: " + puntuacion.getPuntos());

        dianasAcertadas++;

        eliminarDiana();

        // Verificar si el jugador acertó dos dianas
        if (dianasAcertadas % 10 == 0) {
            // Esperar 2 segundos antes de generar una nueva diana
            Timeline esperaTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                Platform.runLater(() -> generarNuevaDiana());
            }));
            esperaTimeline.play();
        }
        // Puedes ajustar el tiempo de espera según tus necesidades
    }

    private void eliminarDiana() {
        StackPane stackPane = obtenerStackPaneDianaActual();

        // Verificar si la diana existe en el GridPane
        if (stackPane != null) {
            // Obtener la ImageView dentro del StackPane
            ImageView imageView = (ImageView) stackPane.getChildren().get(0);

            // Eliminar la ImageView y su contenedor asociado con Platform.runLater
            Platform.runLater(() -> {
                stackPane.getChildren().remove(imageView);
                maingrid.getChildren().remove(stackPane);
            });
        }
    }



    private StackPane obtenerStackPaneDianaActual() {
        // Obtener el StackPane asociado a la diana actual desde el GridPane
        for (Node node : maingrid.getChildren()) {
            if (node instanceof StackPane) {
                return (StackPane) node;
            }
        }
        return null;
    }


    private Diana generarDiana() {
        dianaActual = new Diana();

        Image imagenDiana = new Image("/diana.png");
        ImageView imageView = new ImageView(imagenDiana);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Agregar evento de clic a la diana
        imageView.setOnMouseClicked(event -> clicEnDiana());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);

        // Agregar la diana al GridPane
        maingrid.add(stackPane, dianaActual.getColumna(), dianaActual.getFila());

        // Centrar la imagen en la posición del GridPane
        GridPane.setHalignment(stackPane, HPos.CENTER);
        GridPane.setValignment(stackPane, VPos.CENTER);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            // Eliminar la ImageView y su contenedor asociado
            stackPane.getChildren().remove(imageView);
            maingrid.getChildren().remove(stackPane);

            // Generar la siguiente diana después de que la actual desaparezca
            generarNuevaDiana();
        }));
        timeline.play();

        return dianaActual;
    }


    private void generarNuevaDiana() {
        // Llamar a la función generarDiana para crear una nueva diana
        mostrarDiana();
    }
}