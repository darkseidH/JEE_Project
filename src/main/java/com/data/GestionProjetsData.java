package com.data;

import com.presentation.model.Projet;
import com.presentation.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public ResultSet findAllProjetsByStartNameData(String valueSearch) {
        ResultSet res = null;
        String req = "select * from Projet where nom like '" + valueSearch + "%';";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public ResultSet findAllProjetsByEmailChefData(String email) {
        ResultSet res = null;
        String req = "select * from Projet where chefProjet_id = (select id from User where email = ?);";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public ResultSet findAllProjetsByStartNameTochefData(String name, String email) {
        ResultSet res = null;
        String req = "select * from Projet where nom like '" + name + "%' and chefProjet_id = (select id from User where email = ?);";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, email);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public ResultSet findAllProjetsByStartNameToDevloperData(String name, String email) {
        ResultSet res = null;

        return res;

    }

    public Projet getProjetByIdData(long l) {
        ResultSet res = null;
        Projet projet = new Projet();
        String req = "select * from Projet where id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, l);
            res = st.executeQuery();
            while (res.next()) {
                projet.setId(res.getLong("id"));
                projet.setDateDemarrage(res.getDate("dateDemarrage"));
                projet.setDateLiverison(res.getDate("dateLiverison"));
                projet.setDateRuenion(res.getDate("dateRuenion"));
                projet.setDescription(res.getString("description"));
                projet.setMethodologie(res.getString("methodologie"));
                projet.setNom(res.getString("nom"));
                projet.setNomClient(res.getString("nomClient"));
                projet.setNombreJourDeveloppement(res.getInt("nombreJourDeveloppement"));
                projet.setChefProjet_id(res.getLong("chefProjet_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return projet;
    }

    public Projet addMethodologieData(String methodologie, long l) {
        String query = "update Projet set methodologie = ? where id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, methodologie);
            st.setLong(2, l);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addDateReunionData(String dateReunion, Long projetId) {
        String query = "update Projet set dateRuenion = ? where id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dateReunion);
            st.setLong(2, projetId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getDevlopersProjet(Long projetId) {
        ResultSet res = null;
        String req = "select distinct u.* from user u, technologie t, devtechnologie d where u.id = d.developer_id and d.technologie_id = t.id  and t.projet_id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, projetId);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
}