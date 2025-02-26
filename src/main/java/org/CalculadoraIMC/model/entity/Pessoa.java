package org.CalculadoraIMC.model.entity;

public class Pessoa {
    private String nome;
    private double Altura;
    private double Peso;

    public Pessoa(String nome, double altura, double peso) {
        this.nome = nome;
        Altura = altura;
        Peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double altura) {
        Altura = altura;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }
}
