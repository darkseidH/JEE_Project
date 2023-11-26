package com.buisness;

import com.data.GestionUserData;
import com.presentation.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GestionUser {

    GestionUserData gestionUserData = new GestionUserData();
//    @Override
//    public User addUSer(User user) {
//        return null;
//    }
//
//    @Override
//    public boolean deleteUser(User user) {
//        return false;
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return null;
//    }

    public User findUser(User user) throws SQLException {
        try (ResultSet rs = gestionUserData.findUserData(user)) {
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("firstName"));
                user.setLast_name(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } // ResultSet is automatically closed here

        return user;
    }


//    @Override
//    public List<User> getAllChefs() {
//        return gestionUserData.getallChefs();
//    }


}
