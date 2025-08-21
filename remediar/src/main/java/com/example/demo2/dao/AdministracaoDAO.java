package com.example.demo2.dao;

import com.example.demo2.database.Database;
import com.example.demo2.model.Administracao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean atualizar(Administracao adm) {
        String sql = "UPDATE administracao SET prescricao_id=?, data_hora=?, responsavel=?, observacoes=? WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adm.getPrescricaoId());
            stmt.setString(2, adm.getDataHora());
            stmt.setString(3, adm.getResponsavel());
            stmt.setString(4, adm.getObservacoes());
            stmt.setInt(5, adm.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean excluir(int id) {
        String sql = "DELETE FROM administracao WHERE id=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Administracao> listarTodas() {
        List<Administracao> lista = new ArrayList<>();
        String sql = "SELECT * FROM administracao ORDER BY id DESC";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Administracao adm = new Administracao();
                adm.setId(rs.getInt("id"));
                adm.setPrescricaoId(rs.getInt("prescricao_id"));
                adm.setDataHora(rs.getString("data_hora"));
                adm.setResponsavel(rs.getString("responsavel"));
                adm.setObservacoes(rs.getString("observacoes"));
                lista.add(adm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


}
