package com.example.demo2.controller;



import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo2.dao.RelatorioDAO;

public class RelatorioController {

    @FXML private TextField pacienteIdField;
    @FXML private TextArea resultadoArea;

    public void listarMedicamentosPaciente() {
        int id = Integer.parseInt(pacienteIdField.getText());
        String resultado = RelatorioDAO.getMedicamentosPorPaciente(id);
        resultadoArea.setText(resultado);
    }

    public void listarAdministracoesPaciente() {
        int id = Integer.parseInt(pacienteIdField.getText());
        String resultado = RelatorioDAO.getAdministracoesPorPaciente(id);
        resultadoArea.setText(resultado);
    }

    public void listarAdministracoesPorDia() {
        String resultado = RelatorioDAO.getAdministracoesPorDia();
        resultadoArea.setText(resultado);
    }
}

