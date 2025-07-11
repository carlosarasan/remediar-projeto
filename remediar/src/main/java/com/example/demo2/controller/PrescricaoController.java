package com.example.demo2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo2.dao.PrescricaoDAO;
import com.example.demo2.model.Prescricao;

public class PrescricaoController {

    @FXML private TextField idPacienteField;
    @FXML private TextField idMedicamentoField;
    @FXML private TextField dosagemField;
    @FXML private TextField frequenciaField;
    @FXML private TextField horarioField;
    @FXML private TextField medicoField;

    public void salvarPrescricao() {
        Prescricao prescricao = new Prescricao();
        prescricao.setPacienteId(Integer.parseInt(idPacienteField.getText()));
        prescricao.setMedicamentoId(Integer.parseInt(idMedicamentoField.getText()));
        prescricao.setDosagem(dosagemField.getText());
        prescricao.setFrequencia(frequenciaField.getText());
        prescricao.setHorario(horarioField.getText());
        prescricao.setMedicoResponsavel(medicoField.getText());

        boolean sucesso = PrescricaoDAO.salvar(prescricao);
        if (sucesso) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Prescrição registrada com sucesso!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao registrar prescrição.");
            alert.show();
        }
    }
}
