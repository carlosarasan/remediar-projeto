package com.example.demo2.dao;

import com.example.demo2.database.Database;
import com.example.demo2.model.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UsuarioDAO {

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha_hash = ?";
        String senhaHash = hashSenha(senha);

        System.out.println("Login: " + login);
        System.out.println("Senha digitada: " + senha);
        System.out.println("Hash gerado: " + senhaHash);

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senhaHash);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Se achou usuário com login e senha -> true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private String hashSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.trim().getBytes("UTF-8"));  // <== use trim() e charset
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

}
