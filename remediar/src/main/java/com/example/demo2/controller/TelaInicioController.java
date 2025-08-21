package com.example.demo2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicioController {

    private void abrirTela(String fxml, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxml));
            Scene novaCena = new Scene(loader.load());

            // Obtém o Stage atual a partir do evento (janela que está aberta)
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.setScene(novaCena);
            stageAtual.setTitle(fxml.replace(".fxml", ""));
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirCadastroPaciente(ActionEvent event) {
        abrirTela("CadastroPaciente.fxml", event);
    }

    public void abrirCadastroMedicamento(ActionEvent event) {
        abrirTela("CadastroMedicamento.fxml", event);
    }

    public void abrirPrescricao(ActionEvent event) {
        abrirTela("PrescricaoMedica.fxml", event);
    }

    public void abrirAdministracao(ActionEvent event) {
        abrirTela("AdministracaoMedicamento.fxml", event);
    }

    public void abrirRelatorios(ActionEvent event) {
        abrirTela("Relatorios.fxml", event);
    }

    public void abrirBusca(ActionEvent event) {
        abrirTela("Busca.fxml", event);
    }
}
