package org.CalculadoraIMC.controller;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.CalculadoraIMC.model.entity.Pessoa;
import org.CalculadoraIMC.utils.IDGenerator;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    private List<Pessoa> listPessoas;
    private Pessoa pessoa;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtAltura;
    @FXML
    private Label lbIMC;
    @FXML
    private TextField txtPeso;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnCalcularIMC;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCarregar;
    @FXML
    private Label labelTipoDeImc;
    @FXML
    private TableView<Pessoa> tbPessoa;
    @FXML
    private TableColumn<Pessoa, Long> idField;
    @FXML
    private TableColumn<Pessoa, String> nomeField;
    @FXML
    private TableColumn<Pessoa, Float> alturaField;
    @FXML
    private TableColumn<Pessoa, Float> pesoField;
    @FXML
    private TableColumn<Pessoa, Float> imcField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listPessoas = new ArrayList<>();
        this.pessoa = new Pessoa();
        this.btnCalcularIMC.setDisable(true);
        this.btnCarregar.setDisable(true);
        this.btnSalvar.setDisable(true);

        idField.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        nomeField.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        alturaField.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getAltura()).asObject());
        pesoField.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPeso()).asObject());
        imcField.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getImc()).asObject());
    }

    @FXML
    public void onBtnNovo() {
        this.pessoa = new Pessoa();
        limparCampos();
        this.pessoa.setId(IDGenerator.generateID());
        this.btnSalvar.setDisable(false);
        this.btnCarregar.setDisable(false);
        this.btnCalcularIMC.setDisable(false);
    }

    @FXML
    public void onBtnCalcularImc() {
        lerFormulario();
        this.pessoa.setImc(this.pessoa.getPeso() / (this.pessoa.getAltura() * this.pessoa.getAltura()));
        this.lbIMC.setText(String.format("%.2f", this.pessoa.getImc()));
        this.pessoa.setClassificacao(classificarIMC(this.pessoa.getImc()));
        this.labelTipoDeImc.setText(classificarIMC(this.pessoa.getImc()));
    }

    @FXML
    public void onBtnSalvar() {
        if (this.pessoa != null) {
            lerFormulario();
            this.listPessoas.add(this.pessoa);
            gravarEmArquivo(this.pessoa);
            updateTableView();

        }
    }


    @FXML
    public void onBtnCarregarDados() {
        lerDadosDoArquivo();
        updateTableView();
    }

    public Pessoa lerFormulario() {
        this.pessoa.setNome(this.txtNome.getText());
        this.pessoa.setAltura(Float.parseFloat(this.txtAltura.getText()));
        this.pessoa.setPeso(Float.parseFloat(this.txtPeso.getText()));
        return this.pessoa;
    }

    public void limparCampos() {
        this.txtNome.setText("");
        this.txtAltura.setText("");
        this.txtPeso.setText("");
    }

    public void lerDadosDoArquivo() {
        File file = new File("pessoas.txt");

        if (!file.exists() || file.length() == 0) {
            System.out.println("O arquivo 'pessoas.txt' não existe ou está vazio.");
            return;
        }
        try {
            List<Pessoa> pessoas = Files.lines(Paths.get("pessoas.txt"))
                    .map(linha -> linha.split(","))
                    .filter(dados -> dados.length == 5)
                    .map(dados -> {
                        try {
                            long id = Long.parseLong(dados[0]);
                            String nome = dados[1];
                            float peso = Float.parseFloat(dados[2]);
                            float altura = Float.parseFloat(dados[3]);
                            float imc = Float.parseFloat(dados[4]);


                            Pessoa pessoa = new Pessoa((int) id, nome, peso, altura);
                            pessoa.setId(id);
                            pessoa.setNome(nome);
                            pessoa.setAltura(altura);
                            pessoa.setPeso(peso);
                            pessoa.setImc(imc);
                            return pessoa;
                        } catch (NumberFormatException e) {
                            System.err.println("Erro ao converter dados da linha: " + String.join(",", dados));
                            return null;
                        }
                    })
                    .filter(pessoa -> pessoa != null)
                    .collect(Collectors.toList());

            listPessoas.addAll(pessoas);
            pessoas.forEach(pessoa ->
                    System.out.printf("ID: %d, Nome: %s, Peso: %.2f, Altura: %.2f, IMC: %.2f%n",
                            pessoa.getId(), pessoa.getNome(), pessoa.getPeso(), pessoa.getAltura(), pessoa.getImc())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gravarEmArquivo(Pessoa pessoa) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pessoas.txt", true))) {
            writer.write(pessoa.getId() + "," + pessoa.getNome() + "," + pessoa.getPeso() + "," + pessoa.getAltura() + "," + pessoa.getImc());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String classificarIMC(float imc) {
        if (imc < 18.5) {
            return "Abaixo do Peso";
        } else if (imc < 24.9) {
            return "Peso Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade Grau 1";
        } else if (imc < 39.9) {
            return "Obesidade Grau 2";
        } else {
            return "Obesidade Grau 3";
        }
    }

    public void updateTableView() {
        ObservableList<Pessoa> observableList = FXCollections.observableArrayList(listPessoas);
        tbPessoa.setItems(observableList);
    }
}