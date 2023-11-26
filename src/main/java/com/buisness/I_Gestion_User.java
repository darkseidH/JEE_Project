package com.buisness;

import com.presentation.model.User;
import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_User {
    public User addUser(User user);

    public boolean deleteUser(User user);

    public User updateUser(User user);

    public User findUserWithEmailandPassword(User user) throws SQLException;
    public List<User> getUsersWithRole(User user) throws SQLException;
}

