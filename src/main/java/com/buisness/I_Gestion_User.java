package com.buisness;

import com.presentation.model.User;
import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_User {
    User addUser(User user);

    boolean deleteUser(User user);

    User updateUser(User user);

    User findUserWithEmailandPassword(User user) throws SQLException;
    List<User> findUsersWithRole(User user) throws SQLException;

    User findUserWithId(User user) throws SQLException;
}

