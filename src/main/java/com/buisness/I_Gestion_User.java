package com.buisness;

import com.presentation.model.User;

public interface I_Gestion_User {
    public User addUSer(User user);
    public boolean deleteUser(User user);
    public User updateUser(User user);
    public User findUser(User user);
    public User getUser(User user);
}
