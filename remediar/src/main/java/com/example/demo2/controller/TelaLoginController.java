package com.example.demo2.controller;

import com.example.demo2.dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class TelaLoginController {

    @FXML private TextField loginField;
    @FXML private PasswordField senhaField;
    @FXML private Label erroLabel;

    @FXML
    public void autenticar(ActionEvent event) {
        String login = loginField.getText();
        String senha = senhaField.getText();

        // 🔹 Verificação de campos obrigatórios
        if (login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            erroLabel.setText("Preencha todos os campos obrigatórios.");
            return; // Interrompe aqui, não tenta autenticar
        }

        UsuarioDAO dao = new UsuarioDAO();

        if (dao.autenticar(login, senha)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicio.fxml")); // Ajuste o caminho se necessário
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Tela Inicial");
                stage.show();

                // Fecha a janela de login
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

            } catch (IOException e) {
                erroLabel.setText("Erro ao carregar a próxima tela.");
                e.printStackTrace();
            }
        } else {
            erroLabel.setText("Usuário ou senha inválidos.");
        }
    }

}
