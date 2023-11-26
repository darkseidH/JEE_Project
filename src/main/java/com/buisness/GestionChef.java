package com.buisness;

import com.data.GestionUserData;
import com.presentation.model.User;
import java.sql.SQLException;
import java.util.List;

public class GestionChef implements I_Gestion_User {
    GestionUserData gestionUserData = new GestionUserData();


    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User findUserWithEmailandPassword(User user) throws SQLException {
        return null;
    }

    @Override
    public List<User> findUsersWithRole(User user) throws SQLException {
        return null;
    }

    @Override
    public User findUserWithId(User user) throws SQLException {
        return null;
    }
}
