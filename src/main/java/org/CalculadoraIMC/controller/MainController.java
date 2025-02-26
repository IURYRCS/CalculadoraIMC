package org.CalculadoraIMC.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
     TextField txtNome;
    @FXML
     TextField txtAltura;
    @FXML
     TextField txtPeso;

    @FXML
     Button btnCalcularIMC;

    @FXML
     Button btnSalvar;

    @FXML
     Button btnCarregar;

    @FXML
     Label labelCalculo;

    @FXML
     Label labelTipoDeImc;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }
    @FXML
    public void onBtnCalcularImc(){
    }
    @FXML
    public void onBtnSalvar(){
    }
    @FXML
    public void onBtnCarregarDados(){
    }
}
