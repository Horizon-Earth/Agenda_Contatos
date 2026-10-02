package com.mycompany.agendacontatos;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage janela) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
            App.class.getResource("primary.fxml"), "primary.fxml não encontrado"));
        Scene cena = new Scene(loader.load(), 900, 600);
        cena.getStylesheets().add(Objects.requireNonNull(
            App.class.getResource("estilo.css")).toExternalForm());
        janela.setTitle("Horizon Earth | Agenda de contatos");
        janela.setScene(cena);
        janela.setMinWidth(720);
        janela.setMinHeight(520);
        janela.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
