package com.example.demo2.dao;

import com.example.demo2.database.Database;
import com.example.demo2.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public static boolean salvar(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, data_nascimento, endereco, telefone, historico_clinico) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getDataNascimento());
            stmt.setString(4, paciente.getEndereco());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getHistoricoClinico());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Paciente buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getDate("data_nascimento").toString());
                p.setEndereco(rs.getString("endereco"));
                p.setTelefone(rs.getString("telefone"));
                p.setHistoricoClinico(rs.getString("historico_clinico"));
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Paciente> buscarPorNome(String nome) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente WHERE nome LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toString()); // Corrigido aqui
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setHistoricoClinico(rs.getString("historico_clinico"));
                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }
}
