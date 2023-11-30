package com.buisness;

import com.data.GestionUserData;
import com.presentation.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionChef implements I_Gestion_User {
    GestionUserData gestionUserData = new GestionUserData();


    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User findUserWithEmail(User user) throws SQLException {
        return null;
    }

    @Override
    public List<User> findUsersWithRole(String role) throws SQLException {
        return null;
    }

    @Override
    public User findUserWithId(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAllUsersExceptDirector() throws SQLException {
        ResultSet resultSet = gestionUserData.findAllUsersExceptDirectorData();
        List<User> users = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setFirst_name(resultSet.getString("first_name"));
            user.setLast_name(resultSet.getString("last_name"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            users.add(user);
        }
        return users;
    }
}
