package com.example.demo2.model;

public class Medicamento {

    private int id;
    private String nome;
    private String dosagem;
    private String finalidade;
    private String fabricante;
    private int quantidadeEstoque;
    private String formaAdministracao; // Novo campo

    public Medicamento() {
    }

    public Medicamento(int id, String nome, String dosagem, String finalidade, String fabricante, int quantidadeEstoque, String formaAdministracao) {
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
        this.finalidade = finalidade;
        this.fabricante = fabricante;
        this.quantidadeEstoque = quantidadeEstoque;
        this.formaAdministracao = formaAdministracao;
    }

    // Getters e Setters
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

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getFormaAdministracao() {
        return formaAdministracao;
    }

    public void setFormaAdministracao(String formaAdministracao) {
        this.formaAdministracao = formaAdministracao;
    }
}
