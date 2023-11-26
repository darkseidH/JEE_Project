package com.data;

import com.presentation.model.User;

import java.sql.*;

public class GestionUserData {
    Connection conn = MySqlConnection.openConnection();

    public ResultSet findUserData(User user) {
        ResultSet res=null;
        String req = "SELECT * FROM User WHERE email = " + user.getEmail() + " and password = " + user.getPassword();
        try {
            Statement st = conn.createStatement();
            res=st.executeQuery(req);
        } catch (SQLException e) {

        }
        return res;
    }

//    public List<User> getallChefs() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            String query = "SELECT * FROM User u WHERE u.role = :role";
//
//            TypedQuery<User> chefs = entityManager.createQuery(query, User.class);
//            chefs.setParameter("role", "chef");
//
//
//            if (!chefs.getResultList().isEmpty()) {
//                return chefs.getResultList();
//            }
//            return null;
//        } finally {
//            entityManager.close();
//        }
//    }
}

