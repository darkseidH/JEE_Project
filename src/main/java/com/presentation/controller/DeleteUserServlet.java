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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteUserServlet", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public HttpServletRequest setRequestsAttributes(HttpServletRequest request) {
        List<User> users = new ArrayList<>();
        try {
            users = gestionUser.findAllUsersExceptDirector();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        return request;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long usertId = Long.parseLong(request.getParameter("usertId"));
        if (gestionUser.deleteUser(usertId)) {
            request.setAttribute("error", "User Supprimé avec succès");
            request = setRequestsAttributes(request);
            request.getRequestDispatcher("/WEB-INF/jsp/gestion_Personnel.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Erreur lors de la suppression de l'utilisateur");
            request = setRequestsAttributes(request);
            request.getRequestDispatcher("/WEB-INF/jsp/gestion_Personnel.jsp").forward(request, response);
        }
    }
}
