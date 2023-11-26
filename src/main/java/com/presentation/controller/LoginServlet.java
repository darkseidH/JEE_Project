package com.presentation.controller;


import com.buisness.GestionUser;
import com.presentation.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();

    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (request.getParameter("email") != null && request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            try {
                user = gestionUser.findUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (user != null) {
                if (user.getEmail() != null && user.getRole() != null) {
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("role", user.getRole());
                    if (user.getRole().equals("director")) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
                    }
                    if (user.getRole().equals("chefProjet")) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home_chef.jsp");
                    }
                    if (user.getRole().equals("developer")) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home_dev.jsp");
                    }
                } else {
                    request.setAttribute("error", "Invalid email or password");
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                }
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Please enter your email and password");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}
