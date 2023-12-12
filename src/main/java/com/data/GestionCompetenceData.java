package com.data;

import com.presentation.model.Competence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionCompetenceData implements I_GestionCompetenceData{
    Connection conn = MySqlConnection.openConnection();
    @Override
    public ResultSet getAllCompetenceDeveloper(Long id) {
        ResultSet res = null;
        String req = "select * from competence where developer_id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, id);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    @Override
    public void addCompetence(Competence competence) {
        String query = "insert into Competence (nom,developer_id) values (?,?);";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, competence.getNom());
            st.setLong(2, competence.getDeveloper_id());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
