package com.example.demo2.dao;



import com.example.demo2.database.Database;

import java.sql.*;

public class RelatorioDAO {

    public static String getMedicamentosPorPaciente(int pacienteId) {
        StringBuilder sb = new StringBuilder();
        String sql = """
            SELECT m.nome, m.dosagem
            FROM prescricao p
            JOIN medicamento m ON p.medicamento_id = m.id
            WHERE p.paciente_id = ?
            """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append("Medicamento: ").append(rs.getString("nome"))
                        .append(", Dosagem: ").append(rs.getString("dosagem"))
                        .append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao consultar medicamentos.";
        }
        return sb.toString();
    }

    public static String getAdministracoesPorPaciente(int pacienteId) {
        StringBuilder sb = new StringBuilder();
        String sql = """
            SELECT a.data_hora, a.responsavel, a.observacoes
            FROM administracao a
            JOIN prescricao p ON a.prescricao_id = p.id
            WHERE p.paciente_id = ?
            """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append("Data/Hora: ").append(rs.getString("data_hora"))
                        .append(", Responsável: ").append(rs.getString("responsavel"))
                        .append(", Observações: ").append(rs.getString("observacoes"))
                        .append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao consultar administrações.";
        }
        return sb.toString();
    }

    public static String getAdministracoesPorDia() {
        StringBuilder sb = new StringBuilder();
        String sql = """
            SELECT DATE(data_hora) as dia, COUNT(*) as total
            FROM administracao
            GROUP BY dia
            ORDER BY dia DESC
            """;

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                sb.append("Dia: ").append(rs.getString("dia"))
                        .append(" - Total de Administrações: ").append(rs.getInt("total"))
                        .append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao consultar administrações por dia.";
        }
        return sb.toString();
    }
}
