package com.example.demo2.dao;

import com.example.demo2.database.Database;
import com.example.demo2.model.Medicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    public boolean salvar(Medicamento medicamento) {
        String sql = "INSERT INTO medicamento (nome, dosagem, finalidade, fabricante, quantidade_estoque, forma_administracao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getDosagem());
            stmt.setString(3, medicamento.getFinalidade());
            stmt.setString(4, medicamento.getFabricante());
            stmt.setInt(5, medicamento.getQuantidadeEstoque());
            stmt.setString(6, medicamento.getFormaAdministracao()); // novo campo

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Medicamento medicamento) {
        String sql = "UPDATE medicamento SET nome = ?, dosagem = ?, finalidade = ?, fabricante = ?, quantidade_estoque = ?, forma_administracao = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getDosagem());
            stmt.setString(3, medicamento.getFinalidade());
            stmt.setString(4, medicamento.getFabricante());
            stmt.setInt(5, medicamento.getQuantidadeEstoque());
            stmt.setString(6, medicamento.getFormaAdministracao()); // novo campo
            stmt.setInt(7, medicamento.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM medicamento WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
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
                Medicamento m = montarMedicamento(rs);
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
                Medicamento m = montarMedicamento(rs);
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Medicamento> buscarTodos() {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamento";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medicamento m = montarMedicamento(rs);
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método auxiliar para evitar repetição
    private Medicamento montarMedicamento(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setDosagem(rs.getString("dosagem"));
        m.setFinalidade(rs.getString("finalidade"));
        m.setFabricante(rs.getString("fabricante"));
        m.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
        m.setFormaAdministracao(rs.getString("forma_administracao")); // novo campo
        return m;
    }
}
