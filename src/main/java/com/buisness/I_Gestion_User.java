package com.buisness;

import com.presentation.model.User;

interface Interface_Gestion_User {
    public User addUSer(User user);
    public boolean deleteUser(User user);
    public User updateUser(User user);
    public User checkUser(User user);
    public User getUser(User user);
}
