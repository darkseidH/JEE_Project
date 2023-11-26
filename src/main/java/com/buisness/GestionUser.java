package com.buisness;

import com.data.GestionUserData;
import com.presentation.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionUser implements I_Gestion_User{

    GestionUserData gestionUserData = new GestionUserData();
    @Override
    public User findUserWithEmailandPassword(User user) throws SQLException {
        try {
            ResultSet rs = gestionUserData.findUserWithEmailandPasswordData(user);
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
    public boolean deleteUser(User user) {
        return gestionUserData.deleteUserData(user);
    }
    @Override
    public List<User> getUsersWithRole(User user) throws SQLException {
        ResultSet rs = gestionUserData.getUsersWithRoleData(user);
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            users.add(user);
        }
        return users;
    }

}
