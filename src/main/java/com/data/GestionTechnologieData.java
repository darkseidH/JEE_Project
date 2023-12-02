package com.data;

import com.presentation.model.Technologie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionTechnologieData implements I_Gestion_TechnologieData {
    Connection conn = MySqlConnection.openConnection();

    @Override
    public void addTechnologie(Technologie technologie) {
        String req = "insert into Technologie (nom,projet_id) values (?,?);";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, technologie.getNom());
            st.setLong(2, technologie.getProjetId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public ResultSet getTechnologiesProjet(long l) {
        ResultSet res = null;
        String req = "select * from Technologie where  projet_id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, l);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    @Override
    public ResultSet getDevloperTechnologieProjet(Long id) {
        ResultSet res = null;
        String req = "select * from User where id in (select developer_id from devtechnologie where technologie_id = ?);";
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
    public ResultSet getTechnologiesNProjet(Long projectId) {
        ResultSet res = null;
        String req = "select distinct nom from Competence  where  nom not in (select nom from Technologie where projet_id = ?);";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, projectId);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    @Override
    public ResultSet getDevloperTechnologieNProjet(Long id) {
        ResultSet res = null;
        String req = "SELECT DISTINCT u.* FROM User u, Competence c, Technologie T, devtechnologie d " +
                "WHERE u.id = c.developer_id " +
                "AND c.developer_id NOT IN (SELECT developer_id FROM devtechnologie WHERE technologie_id = ?) " +
                "AND T.id = ?;";

        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, id);
            st.setLong(2, id);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

//    public ResultSet getDevloperTechnologieNProjet(Long id) {
//        ResultSet res = null;
//        String req = "SELECT u.* FROM User u, Competence c, Technologie T, devtechnologie d " +
//                "WHERE u.id = c.developer_id " +
//                "AND c.nom = T.nom " +
//                "AND d.technologie_id = T.id " +
//                "AND c.developer_id NOT IN (SELECT developer_id FROM devtechnologie WHERE technologie_id = ?) " +
//                "AND T.id = ?;";
//        try {
//            PreparedStatement st = conn.prepareStatement(req);
//            st.setLong(1, id);
//            st.setLong(2, id);
//            res = st.executeQuery();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return res;
//    }

}
