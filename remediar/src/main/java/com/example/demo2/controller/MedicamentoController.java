package com.example.demo2.controller;

import com.example.demo2.dao.MedicamentoDAO;
import com.example.demo2.model.Medicamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicamentoController {

    @FXML private TextField nomeField;
    @FXML private TextField dosagemField;
    @FXML private TextField finalidadeField;
    @FXML private TextField fabricanteField;
    @FXML private TextField formaAdministracaoField;
    @FXML private TextField estoqueField;

    @FXML private TableView<Medicamento> tabelaMedicamentos;
    @FXML private TableColumn<Medicamento, Integer> colId;
    @FXML private TableColumn<Medicamento, String> colNome;
    @FXML private TableColumn<Medicamento, String> colDosagem;
    @FXML private TableColumn<Medicamento, String> colFinalidade;
    @FXML private TableColumn<Medicamento, String> colFabricante;
    @FXML private TableColumn<Medicamento, String> colForma;
    @FXML private TableColumn<Medicamento, Integer> colEstoque;

    private final MedicamentoDAO dao = new MedicamentoDAO();
    private Medicamento selecionado = null;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDosagem.setCellValueFactory(new PropertyValueFactory<>("dosagem"));
        colFinalidade.setCellValueFactory(new PropertyValueFactory<>("finalidade"));
        colFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        colForma.setCellValueFactory(new PropertyValueFactory<>("formaAdministracao"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));

        carregarTabela();

        tabelaMedicamentos.setOnMouseClicked(event -> {
            selecionado = tabelaMedicamentos.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                nomeField.setText(selecionado.getNome());
                dosagemField.setText(selecionado.getDosagem());
                finalidadeField.setText(selecionado.getFinalidade());
                fabricanteField.setText(selecionado.getFabricante());
                formaAdministracaoField.setText(selecionado.getFormaAdministracao());
                estoqueField.setText(String.valueOf(selecionado.getQuantidadeEstoque()));
            }
        });
    }

    private void carregarTabela() {
        ObservableList<Medicamento> dados = FXCollections.observableArrayList(dao.buscarTodos());
        tabelaMedicamentos.setItems(dados);
    }

    @FXML
    public void salvarMedicamento() {
        try {
            Medicamento medicamento = new Medicamento();
            medicamento.setNome(nomeField.getText());
            medicamento.setDosagem(dosagemField.getText());
            medicamento.setFinalidade(finalidadeField.getText());
            medicamento.setFabricante(fabricanteField.getText());
            medicamento.setFormaAdministracao(formaAdministracaoField.getText());
            medicamento.setQuantidadeEstoque(Integer.parseInt(estoqueField.getText()));

            if (dao.salvar(medicamento)) {
                showAlert(Alert.AlertType.INFORMATION, "Medicamento salvo com sucesso!");
                limparCampos();
                carregarTabela();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro ao salvar medicamento.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Estoque deve ser um número.");
        }
    }

    @FXML
    public void atualizarMedicamento() {
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selecione um medicamento para atualizar.");
            return;
        }

        try {
            selecionado.setNome(nomeField.getText());
            selecionado.setDosagem(dosagemField.getText());
            selecionado.setFinalidade(finalidadeField.getText());
            selecionado.setFabricante(fabricanteField.getText());
            selecionado.setFormaAdministracao(formaAdministracaoField.getText());
            selecionado.setQuantidadeEstoque(Integer.parseInt(estoqueField.getText()));

            if (dao.update(selecionado)) {
                showAlert(Alert.AlertType.INFORMATION, "Atualizado com sucesso.");
                limparCampos();
                carregarTabela();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro ao atualizar medicamento.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Estoque deve ser um número.");
        }
    }

    @FXML
    public void excluirMedicamento() {
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selecione um medicamento para excluir.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Confirmar exclusão?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            if (dao.delete(selecionado.getId())) {
                showAlert(Alert.AlertType.INFORMATION, "Excluído com sucesso.");
                limparCampos();
                carregarTabela();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro ao excluir.");
            }
        }
    }

    @FXML
    public void voltarInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Tela Inicial");
            stage.show();
            ((Stage) nomeField.getScene().getWindow()).close();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao voltar para a tela inicial.");
        }
    }

    private void limparCampos() {
        nomeField.clear();
        dosagemField.clear();
        finalidadeField.clear();
        fabricanteField.clear();
        formaAdministracaoField.clear();
        estoqueField.clear();
        selecionado = null;
    }

    private void showAlert(Alert.AlertType tipo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
