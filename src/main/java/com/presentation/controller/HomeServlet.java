package com.presentation.controller;

import com.buisness.GestionProjets;
import com.buisness.GestionUser;
import com.presentation.model.Projet;
import com.presentation.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


@WebServlet(name = "homeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();
    GestionProjets gestionProjet = new GestionProjets();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<Projet, User> projets = gestionProjet.mapProjectsToChef();
        List<User> users;
        try {
            users = gestionUser.findUsersWithRole("chef");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("projets", projets);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    }
}
