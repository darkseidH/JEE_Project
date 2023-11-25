package com.data;

import com.presentation.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class GestionUserData {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public boolean checkUser(String email, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            String query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";

            try {
                TypedQuery<User> user_check = entityManager.createQuery(query, User.class);
                user_check.setParameter("email", email);
                user_check.setParameter("password", password);

                return user_check.getResultList().size() == 1;
            } finally {
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return false; // You might want to handle the case when an exception occurs.
    }
}
