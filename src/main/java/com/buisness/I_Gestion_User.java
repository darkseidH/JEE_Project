package com.buisness;

import com.presentation.model.User;

import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_User {
    User addUser(User user);

    boolean deleteUser(Long id);

    User updateUser(User user);

    User findUserWithEmail(User user) throws SQLException;

    List<User> findUsersWithRole(String role) throws SQLException;

    User findUserWithId(Long id) throws SQLException;

    List<User> findAllUsersExceptDirector() throws SQLException;
}

