package com.data;

import com.presentation.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionUserData {
    Connection conn = MySqlConnection.openConnection();

    public ResultSet findUserWithEmailData(User user) throws SQLException {
        ResultSet res = null;
        String req = "SELECT * FROM User WHERE email = ? and is_active = 1;";
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
        String query = "INSERT INTO User (email, first_name, last_name, password, role, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, user.getFirst_name());
            st.setString(3, user.getLast_name());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole());
            st.setBoolean(6, user.getIs_active());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User updateUserData(User user) {
        String query = "UPDATE User SET email = ?, first_name = ?, last_name = ?, password = ?, role = ? , is_active = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, user.getFirst_name());
            st.setString(3, user.getLast_name());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole());
            st.setBoolean(6, user.getIs_active());
            st.setLong(7, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean deleteUserData(Long id) {
        String query = "UPDATE User SET is_active = 0 WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ResultSet findUsersWithRoleData(String role) {
        ResultSet res = null;
        String req = "SELECT * FROM User WHERE role = ? and is_active = 1;";
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

    public ResultSet findAllUsersExceptDirectorData() {
        ResultSet res = null;
        String req = "SELECT * FROM User WHERE role != ?";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, "director");
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}

