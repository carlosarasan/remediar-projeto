package com.example.demo2.controller;

import com.example.demo2.dao.MedicamentoDAO;
import com.example.demo2.dao.PacienteDAO;
import com.example.demo2.model.Medicamento;
import com.example.demo2.model.Paciente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BuscaController {

    @FXML private TextField nomePacienteField;
    @FXML private TextField cpfField;
    @FXML private TextField nomeMedicamentoField;
    @FXML private TextField finalidadeField;
    @FXML private TextArea resultadoArea;

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    @FXML
    public void buscarPacientePorNome() {
        String nome = nomePacienteField.getText();
        List<Paciente> pacientes = pacienteDAO.buscarPorNome(nome);
        if (pacientes.isEmpty()) {
            resultadoArea.setText("Nenhum paciente encontrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Paciente p : pacientes) {
                sb.append(p.toString()).append("\n----------------------\n");
            }
            resultadoArea.setText(sb.toString());
        }
    }

    @FXML
    public void buscarPacientePorCPF() {
        String cpf = cpfField.getText();
        Paciente p = pacienteDAO.buscarPorCPF(cpf);
        if (p == null) {
            resultadoArea.setText("Paciente não encontrado.");
        } else {
            resultadoArea.setText(p.toString());
        }
    }

    @FXML
    public void buscarMedicamentoPorNome() {
        String nome = nomeMedicamentoField.getText();
        List<Medicamento> medicamentos = medicamentoDAO.buscarPorNome(nome);
        if (medicamentos.isEmpty()) {
            resultadoArea.setText("Nenhum medicamento encontrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Medicamento m : medicamentos) {
                sb.append(m.toString()).append("\n----------------------\n");
            }
            resultadoArea.setText(sb.toString());
        }
    }

    @FXML
    public void buscarMedicamentoPorFinalidade() {
        String finalidade = finalidadeField.getText();
        List<Medicamento> medicamentos = medicamentoDAO.buscarPorFinalidade(finalidade);
        if (medicamentos.isEmpty()) {
            resultadoArea.setText("Nenhum medicamento encontrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Medicamento m : medicamentos) {
                sb.append(m.toString()).append("\n----------------------\n");
            }
            resultadoArea.setText(sb.toString());
        }
    }

    // ✅ Novo método para voltar à tela inicial
    @FXML
    public void voltarInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) nomePacienteField.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao voltar para a tela inicial.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
