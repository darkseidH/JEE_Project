package com.buisness;

import com.presentation.model.User;

import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_User {
    public User addUSer(User user);

    public boolean deleteUser(User user);

    public User updateUser(User user);

    public User findUser(User user) throws SQLException;

    //    public User getUser(User user);
    public List<User> getAllChefs();
}

