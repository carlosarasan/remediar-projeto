package com.example.demo2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo2.dao.PacienteDAO;
import com.example.demo2.model.Paciente;

public class PacienteController {

    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField nascimentoField;
    @FXML private TextField enderecoField;
    @FXML private TextField telefoneField;
    @FXML private TextArea historicoField;

    public void salvarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNome(nomeField.getText());
        paciente.setCpf(cpfField.getText());
        paciente.setDataNascimento(nascimentoField.getText());
        paciente.setEndereco(enderecoField.getText());
        paciente.setTelefone(telefoneField.getText());
        paciente.setHistoricoClinico(historicoField.getText());

        boolean sucesso = PacienteDAO.salvar(paciente);
        if (sucesso) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Paciente salvo com sucesso!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar paciente.");
            alert.show();
        }
    }
}
