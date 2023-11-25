package com.data;

import com.presentation.model.User;
import jakarta.persistence.*;

public class GestionUserData {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public User findUserData(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";

            TypedQuery<User> userCheck = entityManager.createQuery(query, User.class);
            userCheck.setParameter("email", user.getEmail());
            userCheck.setParameter("password", user.getPassword());

            User foundUser = null;

            if (!userCheck.getResultList().isEmpty()) {
                foundUser = userCheck.getSingleResult();
            }

            return foundUser;
        } finally {
            entityManager.close();
        }
    }

}

