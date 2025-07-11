package com.example.demo2.model;


public class Prescricao {
    private int id;
    private int pacienteId;
    private int medicamentoId;
    private String dosagem;
    private String frequencia;
    private String horario;
    private String medicoResponsavel;

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public int getMedicamentoId() { return medicamentoId; }
    public void setMedicamentoId(int medicamentoId) { this.medicamentoId = medicamentoId; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getFrequencia() { return frequencia; }
    public void setFrequencia(String frequencia) { this.frequencia = frequencia; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getMedicoResponsavel() { return medicoResponsavel; }
    public void setMedicoResponsavel(String medicoResponsavel) { this.medicoResponsavel = medicoResponsavel; }
}
