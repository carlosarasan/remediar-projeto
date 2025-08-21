package com.example.demo2.dao;

import com.example.demo2.database.Database;

import java.sql.*;
import java.time.LocalDate;

public class RelatorioDAO {

    public static String getPrescricoesFiltradas(String paciente, String medicamento) {
        StringBuilder sb = new StringBuilder();
        String sql = """
            SELECT p.id, pa.nome AS paciente, m.nome AS medicamento, p.observacoes
            FROM prescricao p
            JOIN paciente pa ON p.paciente_id = pa.id
            JOIN medicamento m ON p.medicamento_id = m.id
            WHERE (pa.nome LIKE ? OR pa.cpf LIKE ?) AND m.nome LIKE ?
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + paciente + "%");
            stmt.setString(2, "%" + paciente + "%");
            stmt.setString(3, "%" + medicamento + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Paciente: ").append(rs.getString("paciente"))
                        .append(", Medicamento: ").append(rs.getString("medicamento"))
                        .append(", Obs: ").append(rs.getString("observacoes")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao gerar relatório de prescrições.";
        }

        return sb.length() == 0 ? "Nenhum dado encontrado para os filtros selecionados." : sb.toString();
    }

    public static String getAdministracoesFiltradas(String paciente, String profissional, LocalDate dataInicial, LocalDate dataFinal) {
        StringBuilder sb = new StringBuilder();
        String sql = """
            SELECT a.data_hora, a.responsavel, pa.nome AS paciente, a.observacoes
            FROM administracao a
            JOIN prescricao p ON a.prescricao_id = p.id
            JOIN paciente pa ON p.paciente_id = pa.id
            WHERE (pa.nome LIKE ? OR pa.cpf LIKE ?) AND a.responsavel LIKE ?
            AND DATE(a.data_hora) BETWEEN ? AND ?
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + paciente + "%");
            stmt.setString(2, "%" + paciente + "%");
            stmt.setString(3, "%" + profissional + "%");
            stmt.setDate(4, Date.valueOf(dataInicial));
            stmt.setDate(5, Date.valueOf(dataFinal));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append("Paciente: ").append(rs.getString("paciente"))
                        .append(", Responsável: ").append(rs.getString("responsavel"))
                        .append(", Data/Hora: ").append(rs.getString("data_hora"))
                        .append(", Obs: ").append(rs.getString("observacoes")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao gerar relatório de administrações.";
        }

        return sb.length() == 0 ? "Nenhum dado encontrado para os filtros selecionados." : sb.toString();
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
                        .append(" - Total: ").append(rs.getInt("total")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao consultar administrações por dia.";
        }

        return sb.length() == 0 ? "Nenhum dado encontrado." : sb.toString();
    }
}
