package com.buisness;

import com.data.GestionUserData;
import com.presentation.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionUser implements I_Gestion_User {

    GestionUserData gestionUserData = new GestionUserData();

    @Override
    public User findUserWithEmail(User user) throws SQLException {
        User currentUser = new User();
        try {
            ResultSet rs = gestionUserData.findUserWithEmailData(user);
            if (rs.next()) {
                currentUser.setId(rs.getLong("id"));
                currentUser.setFirst_name(rs.getString("first_name"));
                currentUser.setLast_name(rs.getString("last_name"));
                currentUser.setEmail(rs.getString("email"));
                currentUser.setPassword(rs.getString("password"));
                currentUser.setRole(rs.getString("role"));
                currentUser.setIs_active(rs.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (currentUser.getId() == 0) {
            return null;
        }
        return currentUser;
    }

    @Override
    public User addUser(User user) {
        return gestionUserData.addUserData(user);
    }

    @Override
    public User updateUser(User user) {
        return gestionUserData.updateUserData(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return gestionUserData.deleteUserData(id);
    }

    @Override
    public List<User> findUsersWithRole(String role) throws SQLException {
        ResultSet rs = gestionUserData.findUsersWithRoleData(role);
        ArrayList<User> users = new ArrayList<User>();
        while (rs.next()) {
            User currentUser = new User();
            currentUser.setId(rs.getLong("id"));
            currentUser.setFirst_name(rs.getString("first_name"));
            currentUser.setLast_name(rs.getString("last_name"));
            currentUser.setEmail(rs.getString("email"));
            currentUser.setPassword(rs.getString("password"));
            currentUser.setRole(rs.getString("role"));
            currentUser.setIs_active(rs.getBoolean("is_active"));
            users.add(currentUser);
        }
        return users;
    }

    @Override
    public User findUserWithId(Long id) throws SQLException {
        User user = new User();
        try {
            ResultSet rs = gestionUserData.findUserWithIdData(id);
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setIs_active(rs.getBoolean("is_active"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAllUsersExceptDirector() throws SQLException {
        ResultSet rs = gestionUserData.findAllUsersExceptDirectorData();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            User currentUser = new User();
            currentUser.setId(rs.getLong("id"));
            currentUser.setFirst_name(rs.getString("first_name"));
            currentUser.setLast_name(rs.getString("last_name"));
            currentUser.setEmail(rs.getString("email"));
            currentUser.setPassword(rs.getString("password"));
            currentUser.setRole(rs.getString("role"));
            currentUser.setIs_active(rs.getBoolean("is_active"));
            users.add(currentUser);
        }
        return users;

    }
}
