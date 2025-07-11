package com.example.demo2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicioController {

    private void abrirTela(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(fxml.replace(".fxml", ""));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirCadastroPaciente(ActionEvent event) {
        abrirTela("CadastroPaciente.fxml");
    }

    public void abrirCadastroMedicamento(ActionEvent event) {
        abrirTela("CadastroMedicamento.fxml");
    }

    public void abrirPrescricao(ActionEvent event) {
        abrirTela("PrescricaoMedica.fxml");
    }

    public void abrirAdministracao(ActionEvent event) {
        abrirTela("AdministracaoMedicamento.fxml");
    }

    public void abrirRelatorios(ActionEvent event) {
        abrirTela("Relatorios.fxml");
    }


    public void abrirBusca(ActionEvent event) {
        abrirTela("Busca.fxml");
    }
}
