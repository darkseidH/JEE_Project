package com.buisness;

import com.data.GestionUserData;
import com.presentation.model.User;

public class GestionUser implements I_Gestion_User {

    GestionUserData gestionUserData = new GestionUserData();
    @Override
    public User addUSer(User user) {
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
    public User findUser(User user) {
        return gestionUserData.findUserData(user);
    }

    @Override
    public User getUser(User user) {
        return null;
    }
}
