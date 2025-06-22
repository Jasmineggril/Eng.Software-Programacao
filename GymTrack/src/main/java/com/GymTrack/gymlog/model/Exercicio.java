package br.com.GymTrack.gymlog.model;

public class Exercicio {
    private int id;
    private String nome;
    private int series;
    private String repeticoes;

    public Exercicio(String nome, int series, String repeticoes) {
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
    }


}