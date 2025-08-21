package com.example.demo2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import com.example.demo2.dao.RelatorioDAO;

import java.time.LocalDate;

public class RelatorioController {

    @FXML private TextField pacienteField;
    @FXML private TextField medicamentoField;
    @FXML private TextField profissionalField;
    @FXML private DatePicker dataInicialPicker;
    @FXML private DatePicker dataFinalPicker;
    @FXML private TextArea resultadoArea;
    @FXML private Label mensagemLabel;


    @FXML
    public void listarPrescricoesPorFiltros() {
        String paciente = pacienteField.getText();
        String medicamento = medicamentoField.getText();
        LocalDate dataInicio = dataInicialPicker.getValue();
        LocalDate dataFim = dataFinalPicker.getValue();

        // Caso usuário informe intervalo de datas, validamos
        if (dataInicio != null && dataFim != null) {
            if (dataInicio.isAfter(dataFim)) {
                mensagemLabel.setText("Período inválido. A data inicial não pode ser maior que a final.");
                resultadoArea.clear();
                return;
            }
        }

        String resultado = RelatorioDAO.getPrescricoesFiltradas(paciente, medicamento);
        resultadoArea.setText(resultado);

        if (resultado.startsWith("Nenhum") || resultado.startsWith("Erro")) {
            mensagemLabel.setText(resultado);
        } else {
            mensagemLabel.setText("Relatório gerado com sucesso.");
        }
    }


    @FXML
    public void listarAdministracoesPorFiltros() {
        String paciente = pacienteField.getText();
        String profissional = profissionalField.getText();
        LocalDate dataInicio = dataInicialPicker.getValue();
        LocalDate dataFim = dataFinalPicker.getValue();

        if (dataInicio == null || dataFim == null) {
            mensagemLabel.setText("Por favor, selecione as datas inicial e final.");
            return;
        }

        if (dataInicio.isAfter(dataFim)) {
            mensagemLabel.setText("Período inválido. A data inicial não pode ser maior que a final.");
            resultadoArea.clear();
            return;
        }

        String resultado = RelatorioDAO.getAdministracoesFiltradas(paciente, profissional, dataInicio, dataFim);
        resultadoArea.setText(resultado);

        if (resultado.startsWith("Nenhum") || resultado.startsWith("Erro")) {
            mensagemLabel.setText(resultado);
        } else {
            mensagemLabel.setText("Relatório gerado com sucesso.");
        }
    }


    @FXML
    public void listarAdministracoesPorDia() {
        LocalDate dataInicio = dataInicialPicker.getValue();
        LocalDate dataFim = dataFinalPicker.getValue();

        // Caso usuário informe intervalo de datas, validamos
        if (dataInicio != null && dataFim != null) {
            if (dataInicio.isAfter(dataFim)) {
                mensagemLabel.setText("Período inválido. A data inicial não pode ser maior que a final.");
                resultadoArea.clear();
                return;
            }
        }

        String resultado = RelatorioDAO.getAdministracoesPorDia();
        resultadoArea.setText(resultado);
        mensagemLabel.setText("Relatório gerado com sucesso.");
    }


    @FXML
    public void voltarTelaInicial(ActionEvent event) {
        try {

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml"));
            Stage novaStage = new Stage();
            novaStage.setScene(new Scene(loader.load()));
            novaStage.setTitle("Tela Inicial");
            novaStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
