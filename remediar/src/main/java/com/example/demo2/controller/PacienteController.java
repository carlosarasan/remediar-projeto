package com.example.demo2.controller;

import com.example.demo2.dao.PacienteDAO;
import com.example.demo2.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PacienteController {

    @FXML private TextField nomeField;
    @FXML private TextField cpfField;
    @FXML private TextField nascimentoField;
    @FXML private TextField enderecoField;
    @FXML private TextField telefoneField;
    @FXML private TextArea historicoField;

    @FXML private TableView<Paciente> tabelaPacientes;
    @FXML private TableColumn<Paciente, String> colNome;
    @FXML private TableColumn<Paciente, String> colCpf;
    @FXML private TableColumn<Paciente, String> colNascimento;
    @FXML private TableColumn<Paciente, String> colTelefone;

    private final PacienteDAO dao = new PacienteDAO();
    private Paciente selecionado = null;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        carregarTabela();

        tabelaPacientes.setOnMouseClicked(event -> {
            selecionado = tabelaPacientes.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                nomeField.setText(selecionado.getNome());
                cpfField.setText(selecionado.getCpf());
                nascimentoField.setText(selecionado.getDataNascimento());
                enderecoField.setText(selecionado.getEndereco());
                telefoneField.setText(selecionado.getTelefone());
                historicoField.setText(selecionado.getHistoricoClinico());
            }
        });
    }

    private void carregarTabela() {
        List<Paciente> pacientes = dao.buscarTodos();
        tabelaPacientes.setItems(FXCollections.observableArrayList(pacientes));
    }

    public void salvarPaciente() {
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim().replaceAll("[^\\d]", "");
        String nascimento = nascimentoField.getText().trim();
        String endereco = enderecoField.getText().trim();
        String telefone = telefoneField.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || nascimento.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios.");
            return;
        }

        if (!isCPFValido(cpf)) {
            showAlert(Alert.AlertType.ERROR, "CPF inválido.");
            return;
        }

        if (dao.buscarPorCPF(cpf) != null) {
            showAlert(Alert.AlertType.ERROR, "CPF já cadastrado.");
            return;
        }

        Paciente paciente = new Paciente(0, nome, cpf, nascimento, endereco, telefone, historicoField.getText());
        boolean sucesso = PacienteDAO.salvar(paciente);

        if (sucesso) {
            showAlert(Alert.AlertType.INFORMATION, "Paciente salvo com sucesso!");
            limparCampos();
            carregarTabela();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro ao salvar paciente.");
        }
    }

    public void atualizarPaciente() {
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selecione um paciente na tabela.");
            return;
        }

        selecionado.setNome(nomeField.getText());
        selecionado.setDataNascimento(nascimentoField.getText());
        selecionado.setEndereco(enderecoField.getText());
        selecionado.setTelefone(telefoneField.getText());
        selecionado.setHistoricoClinico(historicoField.getText());

        if (dao.update(selecionado)) {
            showAlert(Alert.AlertType.INFORMATION, "Paciente atualizado com sucesso!");
            limparCampos();
            carregarTabela();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro ao atualizar paciente.");
        }
    }

    public void excluirPaciente() {
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Selecione um paciente na tabela.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir este paciente?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            if (dao.delete(selecionado.getCpf())) {
                showAlert(Alert.AlertType.INFORMATION, "Paciente excluído com sucesso.");
                limparCampos();
                carregarTabela();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro ao excluir paciente.");
            }
        }
    }

    public void voltarTelaInicial() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) nomeField.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Não foi possível carregar a tela inicial.");
        }
    }

    private void limparCampos() {
        nomeField.clear();
        cpfField.clear();
        nascimentoField.clear();
        enderecoField.clear();
        telefoneField.clear();
        historicoField.clear();
        selecionado = null;
    }

    private void showAlert(Alert.AlertType tipo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private boolean isCPFValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            int dig1 = 11 - (soma % 11); if (dig1 >= 10) dig1 = 0;
            soma = 0;
            for (int i = 0; i < 10; i++) soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            int dig2 = 11 - (soma % 11); if (dig2 >= 10) dig2 = 0;
            return dig1 == Character.getNumericValue(cpf.charAt(9)) && dig2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}
