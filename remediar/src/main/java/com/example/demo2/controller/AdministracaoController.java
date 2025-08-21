package com.example.demo2.controller;

import com.example.demo2.dao.AdministracaoDAO;
import com.example.demo2.model.Administracao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministracaoController {

    @FXML private TextField idPrescricaoField;
    @FXML private TextField dataHoraField;
    @FXML private TextField responsavelField;
    @FXML private TextField observacaoField;

    @FXML private TableView<Administracao> tabelaAdministracoes;
    @FXML private TableColumn<Administracao, Integer> colId;
    @FXML private TableColumn<Administracao, Integer> colPrescricaoId;
    @FXML private TableColumn<Administracao, String> colDataHora;
    @FXML private TableColumn<Administracao, String> colResponsavel;
    @FXML private TableColumn<Administracao, String> colObservacoes;

    private ObservableList<Administracao> listaAdministracoes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrescricaoId.setCellValueFactory(new PropertyValueFactory<>("prescricaoId"));
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));
        colResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        colObservacoes.setCellValueFactory(new PropertyValueFactory<>("observacoes"));

        tabelaAdministracoes.setItems(listaAdministracoes);
        carregarAdministracoes();

        tabelaAdministracoes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                preencherCampos(newSel);
            }
        });
    }

    public void registrarAdministracao() {
        try {
            int prescricaoId = Integer.parseInt(idPrescricaoField.getText());

            Administracao adm = new Administracao();
            adm.setPrescricaoId(prescricaoId);
            adm.setDataHora(dataHoraField.getText());
            adm.setResponsavel(responsavelField.getText());
            adm.setObservacoes(observacaoField.getText());

            boolean sucesso = AdministracaoDAO.registrar(adm);
            if (sucesso) {
                showAlert(Alert.AlertType.INFORMATION, "Administração registrada com sucesso!");
                carregarAdministracoes();
                limparCampos();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro ao registrar administração.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "ID da prescrição inválido.");
        }
    }

    @FXML
    public void atualizarAdministracao() {
        Administracao adm = tabelaAdministracoes.getSelectionModel().getSelectedItem();
        if (adm != null) {
            try {
                adm.setPrescricaoId(Integer.parseInt(idPrescricaoField.getText()));
                adm.setDataHora(dataHoraField.getText());
                adm.setResponsavel(responsavelField.getText());
                adm.setObservacoes(observacaoField.getText());

                boolean sucesso = AdministracaoDAO.atualizar(adm);
                if (sucesso) {
                    showAlert(Alert.AlertType.INFORMATION, "Administração atualizada com sucesso!");
                    carregarAdministracoes();
                    limparCampos();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro ao atualizar administração.");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "ID da prescrição inválido.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Selecione uma administração para atualizar.");
        }
    }

    @FXML
    public void excluirAdministracao() {
        Administracao adm = tabelaAdministracoes.getSelectionModel().getSelectedItem();
        if (adm != null) {
            boolean sucesso = AdministracaoDAO.excluir(adm.getId());
            if (sucesso) {
                showAlert(Alert.AlertType.INFORMATION, "Administração excluída com sucesso!");
                carregarAdministracoes();
                limparCampos();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro ao excluir administração.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Selecione uma administração para excluir.");
        }
    }

    @FXML
    public void voltarTelaInicial() {
        try {
            Stage stage = (Stage) idPrescricaoField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml"));
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao voltar à tela inicial.");
        }
    }

    private void carregarAdministracoes() {
        listaAdministracoes.setAll(AdministracaoDAO.listarTodas());

    }

    private void preencherCampos(Administracao adm) {
        idPrescricaoField.setText(String.valueOf(adm.getPrescricaoId()));
        dataHoraField.setText(adm.getDataHora());
        responsavelField.setText(adm.getResponsavel());
        observacaoField.setText(adm.getObservacoes());
    }

    private void limparCampos() {
        idPrescricaoField.clear();
        dataHoraField.clear();
        responsavelField.clear();
        observacaoField.clear();
        tabelaAdministracoes.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType tipo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
