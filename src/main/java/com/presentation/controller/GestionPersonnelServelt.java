package com.presentation.controller;

import com.buisness.GestionUser;
import com.presentation.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GestionPersonnel", value = "/gestion_Personnel")
public class GestionPersonnelServelt extends HttpServlet {
    GestionUser gestionUser = new GestionUser();


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetPage;
        List<User> users;
        try {
            users = gestionUser.findAllUsersExceptDirector();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        targetPage = "/WEB-INF/jsp/gestion_Personnel.jsp";
        request.setAttribute("users", users);
        request.getRequestDispatcher(targetPage).forward(request, response);
    }
}
