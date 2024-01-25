module Controller {
    requires javafx.controls;
    requires javafx.fxml;


    opens controllers to javafx.fxml;
    opens engine to javafx.fxml;
    opens engine.minijuego to javafx.fxml;
    exports engine;
    exports engine.minijuego;
    exports controllers;
    exports exec;
    opens exec to javafx.fxml;
}

