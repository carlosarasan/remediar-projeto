package com.example.demo2.model;



public class Administracao {
    private int id;
    private int prescricaoId;
    private String dataHora;
    private String responsavel;
    private String observacoes;

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPrescricaoId() { return prescricaoId; }
    public void setPrescricaoId(int prescricaoId) { this.prescricaoId = prescricaoId; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}

