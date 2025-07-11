package com.example.demo2.dao;



import com.example.demo2.database.Database;
import com.example.demo2.model.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.example.demo2.database.Database;
import com.example.demo2.model.Medicamento;

public class MedicamentoDAO {

    public static boolean salvar(Medicamento medicamento) {
        String sql = "INSERT INTO medicamento (nome, dosagem, finalidade, fabricante, quantidade_estoque) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getDosagem());
            stmt.setString(3, medicamento.getFinalidade());
            stmt.setString(4, medicamento.getFabricante());
            stmt.setInt(5, medicamento.getQuantidadeEstoque());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Medicamento> buscarPorNome(String nome) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento WHERE nome LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setDosagem(rs.getString("dosagem"));
                m.setFinalidade(rs.getString("finalidade"));
                m.setFabricante(rs.getString("fabricante"));
                m.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Medicamento> buscarPorFinalidade(String finalidade) {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento WHERE finalidade LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + finalidade + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setDosagem(rs.getString("dosagem"));
                m.setFinalidade(rs.getString("finalidade"));
                m.setFabricante(rs.getString("fabricante"));
                m.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
