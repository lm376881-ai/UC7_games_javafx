package br.senac.sp.gamesfx.model;

import java.time.LocalDate;

public class Jogo {

    //Propriedades de uma Classe
    private int id;
    private String titulo;
    private String plataforma;
    private String categoria;
    private String estudio;
    private double preco;
    private LocalDate dataLancamento;
    private boolean finalizado;

    public  Jogo(int id, String titulo, String plataforma){
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
    }

    //Construtor Default/Padrão
<<<<<<< HEAD
    public Jogo(){}


=======
    public Jogo(){

    }
>>>>>>> a62e25edc39768ec657550d5496abcf39667a9f3

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {

        this.preco = preco;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }


}