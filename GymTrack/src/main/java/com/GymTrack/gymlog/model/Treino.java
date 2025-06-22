package br.com.gymtrack.gymlog.model;

import java.time.LocalDate;

public class Treino {
    private int id;
    private String nome;
    private int series;     // CAMPO ATUALIZADO
    private int repeticoes; // NOVO CAMPO
    private LocalDate data;


    public Treino(int id, String nome, int series, int repeticoes, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
        this.data = data;
    }


    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getSeries() { return series; }
    public int getRepeticoes() { return repeticoes; }
    public LocalDate getData() { return data; }
}