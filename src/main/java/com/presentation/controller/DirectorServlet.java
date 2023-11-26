//package com.presentation.controller;
//
//import com.buisness.GestionUser;
//import com.presentation.model.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "directorServlet", value = "/getChefs")
//public class DirectorServlet extends HttpServlet {
//    GestionUser gestionUser = new GestionUser();
//    public void init() throws ServletException {
//        super.init();
//    }
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        List<User> chefs = gestionUser.getAllChefs();
//        for (User chef : chefs) {
//            System.out.println(chef);
//        }
//    }
//}
