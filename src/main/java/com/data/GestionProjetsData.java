package com.data;

import com.presentation.model.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionProjetsData {
    Connection conn = MySqlConnection.openConnection();

    public Projet addProjetData(Projet projet) {
        String query = "insert into Projet (dateDemarrage, dateLiverison, description, methodologie, nom, nomClient, nombreJourDeveloppement, chefProjet_id) values (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, projet.getDateDemarrage());
            st.setDate(2, projet.getDateLiverison());
            st.setString(3, projet.getDescription());
            st.setString(4, projet.getMethodologie());
            st.setString(5, projet.getNom());
            st.setString(6, projet.getNomClient());
            st.setInt(7, projet.getNombreJourDeveloppement());
            st.setLong(8, projet.getChefProjet_id());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return projet;
    }

    public Projet updateProjetData(Projet projet) {
        String query = "update Projet set dateDemarrage = ?, dateLiverison = ?, description = ?, methodologie = ?, nom = ?, nomClient = ?, nombreJourDeveloppement = ? where id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, projet.getDateDemarrage());
            st.setDate(2, projet.getDateLiverison());
            st.setString(3, projet.getDescription());
            st.setString(4, projet.getMethodologie());
            st.setString(5, projet.getNom());
            st.setString(6, projet.getNomClient());
            st.setInt(7, projet.getNombreJourDeveloppement());
            st.setLong(8, projet.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return projet;
    }

    public boolean deleteProjetData(Projet projet) {
        String query = "delete from Projet where id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, projet.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public ResultSet findAllProjetsData() {
        ResultSet res = null;
        String req = "select * from Projet;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

}