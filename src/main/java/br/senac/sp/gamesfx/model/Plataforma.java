package br.senac.sp.gamesfx.model;

import java.time.LocalDate;

public class Plataforma {

    private int id;
    private String nome;
    private String fabricante;
    private LocalDate dataLancamento;
    private double valor;

    public Plataforma (int id, String nome, String fabricante, LocalDate dataLancamento, double valor) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
    }

    public Plataforma (){

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

}

