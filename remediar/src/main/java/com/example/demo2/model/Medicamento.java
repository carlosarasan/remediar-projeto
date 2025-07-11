package com.example.demo2.model;



public class Medicamento {
    private int id;
    private String nome;
    private String dosagem;
    private String finalidade;
    private String fabricante;
    private int quantidadeEstoque;

    @Override
    public String toString() {
        return "Nome: " + nome + "\nDosagem: " + dosagem + "\nFinalidade: " + finalidade +
                "\nFabricante: " + fabricante + "\nEstoque: " + quantidadeEstoque;
    }


    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getFinalidade() { return finalidade; }
    public void setFinalidade(String finalidade) { this.finalidade = finalidade; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}
