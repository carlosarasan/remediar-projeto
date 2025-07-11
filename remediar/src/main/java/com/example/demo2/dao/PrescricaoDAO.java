package com.example.demo2.dao;

import com.example.demo2.database.Database;
import com.example.demo2.model.Prescricao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PrescricaoDAO {

    public static boolean salvar(Prescricao p) {
        String sql = "INSERT INTO prescricao (paciente_id, medicamento_id, dosagem, frequencia, horario, medico_responsavel) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getPacienteId());
            stmt.setInt(2, p.getMedicamentoId());
            stmt.setString(3, p.getDosagem());
            stmt.setString(4, p.getFrequencia());
            stmt.setString(5, p.getHorario());
            stmt.setString(6, p.getMedicoResponsavel());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
