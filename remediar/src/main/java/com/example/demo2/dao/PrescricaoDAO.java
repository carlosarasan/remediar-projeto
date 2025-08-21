package com.example.demo2.dao;

import com.example.demo2.database.Database;
import com.example.demo2.model.Prescricao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescricaoDAO {

    public static boolean salvar(Prescricao p) {
        String sql = "INSERT INTO prescricao (paciente_id, medicamento_id, dosagem, frequencia, horario, duracao, medico_responsavel) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getPacienteId());
            stmt.setInt(2, p.getMedicamentoId());
            stmt.setString(3, p.getDosagem());
            stmt.setString(4, p.getFrequencia());
            stmt.setString(5, p.getHorario());
            stmt.setString(6, p.getDuracao());
            stmt.setString(7, p.getMedicoResponsavel());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizar(Prescricao p) {
        String sql = "UPDATE prescricao SET paciente_id=?, medicamento_id=?, dosagem=?, frequencia=?, horario=?, duracao=?, medico_responsavel=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getPacienteId());
            stmt.setInt(2, p.getMedicamentoId());
            stmt.setString(3, p.getDosagem());
            stmt.setString(4, p.getFrequencia());
            stmt.setString(5, p.getHorario());
            stmt.setString(6, p.getDuracao());
            stmt.setString(7, p.getMedicoResponsavel());
            stmt.setInt(8, p.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean excluir(int id) {
        String sql = "DELETE FROM prescricao WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Prescricao> buscarTodas() {
        List<Prescricao> lista = new ArrayList<>();
        String sql = "SELECT * FROM prescricao";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prescricao p = new Prescricao();
                p.setId(rs.getInt("id"));
                p.setPacienteId(rs.getInt("paciente_id"));
                p.setMedicamentoId(rs.getInt("medicamento_id"));
                p.setDosagem(rs.getString("dosagem"));
                p.setFrequencia(rs.getString("frequencia"));
                p.setHorario(rs.getString("horario"));
                p.setDuracao(rs.getString("duracao"));
                p.setMedicoResponsavel(rs.getString("medico_responsavel"));
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
