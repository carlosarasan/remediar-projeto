package com.example.demo2.dao;



import com.example.demo2.database.Database;
import com.example.demo2.model.Administracao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdministracaoDAO {

    public static boolean registrar(Administracao adm) {
        String sql = "INSERT INTO administracao (prescricao_id, data_hora, responsavel, observacoes) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adm.getPrescricaoId());
            stmt.setString(2, adm.getDataHora());
            stmt.setString(3, adm.getResponsavel());
            stmt.setString(4, adm.getObservacoes());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

