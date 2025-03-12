package org.CalculadoraIMC.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.CalculadoraIMC.model.entity.Pessoa;
import org.CalculadoraIMC.utils.IDGenerator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    List<Pessoa> listPessoas;
    @FXML
     TextField txtNome;
    @FXML
     TextField txtAltura;
    @FXML
    public Label lbIMC;
    @FXML
     TextField txtPeso;

    @FXML
    Button btnNovo;
    @FXML
     Button btnCalcularIMC;

    @FXML
     Button btnSalvar;

    @FXML
     Button btnCarregar;


    @FXML
     Label labelTipoDeImc;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.btnCalcularIMC.setDisable(true);
        this.btnCarregar.setDisable(true);
        this.btnNovo.setDisable(true);
        this.pessoa = new Pessoa();
        this.listPessoas = new ArrayList<Pessoa>();

    }
    //#================================================================
    @FXML
    public void onBtnNovo(){
        this.pessoa = new Pessoa();
        this.pessoa.setId(IDGenerator.generateID());
        limparCampos();
    }
    @FXML
    public void onBtnCalcularImc(){
        lerFormulario();
        this.pessoa.setImc(this.pessoa.getPeso()/(this.pessoa.getAltura()*this.pessoa.getAltura()));
        this.lbIMC.setText(String.format("%.2f",this.pessoa.getImc()));
        this.pessoa.setClassificacao(classificarIMC(this.pessoa.getImc()));
        this.labelTipoDeImc.setText(classificarIMC((this.pessoa.getImc())));
    }

    @FXML
    public void onBtnSalvar(){
        this.btnNovo.setDisable(false);
        this.btnCarregar.setDisable(false);
        this.btnCalcularIMC.setDisable(false);
        this.listPessoas.add(this.pessoa);


    }
    @FXML
    public void onBtnCarregarDados(){
    }
    private Pessoa pessoa;

    //#==========================================
    public Pessoa lerFormulario(){
       this.pessoa.setNome(this.txtNome.getText());
       this.pessoa.setAltura(Float.parseFloat(this.txtAltura.getText()));
       this.pessoa.setPeso(Float.parseFloat(this.txtPeso.getText()));
        System.out.println(this.pessoa.toString());
        return this.pessoa;
    }
   public void limparCampos(){
        this.txtNome.setText("");
        this.txtAltura.setText("");
        this.txtPeso.setText("");
   }
    private String classificarIMC(float imc) {
        if (imc < 18.5) {
            return "Abaixo do Peso";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso Normal";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc < 34.9) {
            return "Obesidade Grau 1";
        } else if (imc >= 35 && imc < 39.9) {
            return "Obesidade Grau 2";
        } else { // imc >= 40
            return "Obesidade Grau 3";
        }
    }
    private void updateTableView(){
        if(pessoa==null){
            throw new IllegalStateException("Service nao instanciado");
        }

    }
   }
