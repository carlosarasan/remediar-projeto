package com.example.demo2.controller;

import com.example.demo2.dao.PrescricaoDAO;
import com.example.demo2.model.Prescricao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class PrescricaoController {

    @FXML private TextField idPacienteField;
    @FXML private TextField idMedicamentoField;
    @FXML private TextField dosagemField;
    @FXML private TextField frequenciaField;
    @FXML private TextField horarioField;
    @FXML private TextField medicoField;
    @FXML private TextField duracaoField;
    @FXML private TableColumn<Prescricao, String> colDuracao;


    @FXML private TableView<Prescricao> tabelaPrescricoes;
    @FXML private TableColumn<Prescricao, Integer> colId;
    @FXML private TableColumn<Prescricao, Integer> colPaciente;
    @FXML private TableColumn<Prescricao, Integer> colMedicamento;
    @FXML private TableColumn<Prescricao, String> colDosagem;
    @FXML private TableColumn<Prescricao, String> colFrequencia;
    @FXML private TableColumn<Prescricao, String> colHorario;
    @FXML private TableColumn<Prescricao, String> colMedico;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPaciente.setCellValueFactory(new PropertyValueFactory<>("pacienteId"));
        colMedicamento.setCellValueFactory(new PropertyValueFactory<>("medicamentoId"));
        colDosagem.setCellValueFactory(new PropertyValueFactory<>("dosagem"));
        colFrequencia.setCellValueFactory(new PropertyValueFactory<>("frequencia"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colMedico.setCellValueFactory(new PropertyValueFactory<>("medicoResponsavel"));
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));


        tabelaPrescricoes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> preencherCampos(newSel));
        carregarPrescricoes();
    }

    @FXML
    private void prescrever(ActionEvent event) {
        salvarPrescricao();
    }

    public void salvarPrescricao() {
        if (camposVazios()) return;

        Prescricao prescricao = new Prescricao();
        prescricao.setPacienteId(Integer.parseInt(idPacienteField.getText()));
        prescricao.setMedicamentoId(Integer.parseInt(idMedicamentoField.getText()));
        prescricao.setDosagem(dosagemField.getText());
        prescricao.setFrequencia(frequenciaField.getText());
        prescricao.setHorario(horarioField.getText());
        prescricao.setMedicoResponsavel(medicoField.getText());
        prescricao.setDuracao(duracaoField.getText());


        if (PrescricaoDAO.salvar(prescricao)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Prescrição registrada com sucesso!");
            limparCampos();
            carregarPrescricoes();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao registrar prescrição.");
        }
    }

    public void atualizarPrescricao() {
        Prescricao selecionada = tabelaPrescricoes.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecione uma prescrição para atualizar.");
            return;
        }

        selecionada.setPacienteId(Integer.parseInt(idPacienteField.getText()));
        selecionada.setMedicamentoId(Integer.parseInt(idMedicamentoField.getText()));
        selecionada.setDosagem(dosagemField.getText());
        selecionada.setFrequencia(frequenciaField.getText());
        selecionada.setHorario(horarioField.getText());
        selecionada.setMedicoResponsavel(medicoField.getText());
        selecionada.setDuracao(duracaoField.getText());


        if (PrescricaoDAO.atualizar(selecionada)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Prescrição atualizada com sucesso!");
            limparCampos();
            carregarPrescricoes();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao atualizar prescrição.");
        }
    }

    public void excluirPrescricao() {
        Prescricao selecionada = tabelaPrescricoes.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selecione uma prescrição para excluir.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir esta prescrição?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES && PrescricaoDAO.excluir(selecionada.getId())) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Prescrição excluída com sucesso!");
            limparCampos();
            carregarPrescricoes();
        }
    }

    public void voltarTelaInicial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) idPacienteField.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao voltar para a tela inicial.");
        }
    }

    private void preencherCampos(Prescricao p) {
        if (p != null) {
            idPacienteField.setText(String.valueOf(p.getPacienteId()));
            idMedicamentoField.setText(String.valueOf(p.getMedicamentoId()));
            dosagemField.setText(p.getDosagem());
            frequenciaField.setText(p.getFrequencia());
            horarioField.setText(p.getHorario());
            medicoField.setText(p.getMedicoResponsavel());
            duracaoField.setText(p.getDuracao());

        }
    }

    private void limparCampos() {
        idPacienteField.clear();
        idMedicamentoField.clear();
        dosagemField.clear();
        frequenciaField.clear();
        horarioField.clear();
        medicoField.clear();
        tabelaPrescricoes.getSelectionModel().clearSelection();
        duracaoField.clear();

    }

    private void mostrarAlerta(Alert.AlertType tipo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private boolean camposVazios() {
        StringBuilder faltando = new StringBuilder("Preencha os seguintes campos:\n");

        boolean vazio = false;

        if (idPacienteField.getText().isEmpty()) {
            faltando.append("- ID do Paciente\n");
            vazio = true;
        }
        if (idMedicamentoField.getText().isEmpty()) {
            faltando.append("- ID do Medicamento\n");
            vazio = true;
        }
        if (dosagemField.getText().isEmpty()) {
            faltando.append("- Dosagem\n");
            vazio = true;
        }
        if (frequenciaField.getText().isEmpty()) {
            faltando.append("- Frequência\n");
            vazio = true;
        }
        if (horarioField.getText().isEmpty()) {
            faltando.append("- Horário\n");
            vazio = true;
        }
        if (duracaoField.getText().isEmpty()) {
            faltando.append("- Duração / Data de Término\n");
            vazio = true;
        }
        if (medicoField.getText().isEmpty()) {
            faltando.append("- Médico Responsável\n");
            vazio = true;
        }

        if (vazio) {
            mostrarAlerta(Alert.AlertType.WARNING, faltando.toString());
        }

        return vazio;
    }


    private void carregarPrescricoes() {
        tabelaPrescricoes.setItems(FXCollections.observableArrayList(PrescricaoDAO.buscarTodas()));
    }
}
