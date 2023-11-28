package com.data;

import com.presentation.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionUserData {
    Connection conn = MySqlConnection.openConnection();

    public ResultSet findUserWithEmailData(User user) {
        ResultSet res = null;
        String req = "SELECT * FROM User WHERE email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, user.getEmail());
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public User addUserData(User user) {
        String query = "INSERT INTO User (email, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, user.getFirst_name());
            st.setString(3, user.getLast_name());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User updateUserData(User user) {
        String query = "UPDATE User SET email = ?, first_name = ?, last_name = ?, password = ?, role = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, user.getFirst_name());
            st.setString(3, user.getLast_name());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole());
            st.setLong(6, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean deleteUserData(User user) {
        String query = "DELETE FROM User WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ResultSet findUsersWithRoleData(String role) {
        ResultSet res = null;
        String req = "SELECT * FROM User WHERE role = ?";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, role);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public ResultSet findUserWithIdData(Long id) {
        ResultSet res = null;
        String req = "SELECT * FROM User WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, id);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}

