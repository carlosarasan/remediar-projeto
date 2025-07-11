package com.example.demo2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo2.dao.MedicamentoDAO;
import com.example.demo2.model.Medicamento;

public class MedicamentoController {

    @FXML private TextField nomeField;
    @FXML private TextField dosagemField;
    @FXML private TextField finalidadeField;
    @FXML private TextField fabricanteField;
    @FXML private TextField estoqueField;

    public void salvarMedicamento() {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(nomeField.getText());
        medicamento.setDosagem(dosagemField.getText());
        medicamento.setFinalidade(finalidadeField.getText());
        medicamento.setFabricante(fabricanteField.getText());
        medicamento.setQuantidadeEstoque(Integer.parseInt(estoqueField.getText()));

        boolean sucesso = MedicamentoDAO.salvar(medicamento);
        if (sucesso) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Medicamento salvo com sucesso!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao salvar medicamento.");
            alert.show();
        }
    }
}