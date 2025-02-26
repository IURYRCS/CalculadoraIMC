package org.CalculadoraIMC;

import org.CalculadoraIMC.utils.PathFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;

public class MainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(new FileInputStream(PathFXML.pathBase() + "\\MainView.fxml"));
        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Calculo IMC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();
    }
}