package com.example.demo2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo2.dao.AdministracaoDAO;
import com.example.demo2.model.Administracao;

public class AdministracaoController {

    @FXML private TextField idPrescricaoField;
    @FXML private TextField dataHoraField;
    @FXML private TextField responsavelField;
    @FXML private TextField observacaoField;

    public void registrarAdministracao() {
        Administracao adm = new Administracao();
        adm.setPrescricaoId(Integer.parseInt(idPrescricaoField.getText()));
        adm.setDataHora(dataHoraField.getText());
        adm.setResponsavel(responsavelField.getText());
        adm.setObservacoes(observacaoField.getText());

        boolean sucesso = AdministracaoDAO.registrar(adm);
        if (sucesso) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Administração registrada com sucesso!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao registrar administração.");
            alert.show();
        }
    }
}
