package com.presentation.controller;


import com.buisness.GestionProjets;
import com.buisness.GestionUser;
import com.presentation.model.Projet;
import com.presentation.model.User;
import com.util.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();
    GestionProjets gestionProjet = new GestionProjets();

    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        if (!request.getParameter("email").isEmpty() && !request.getParameter("password").isEmpty()) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = new User();
            user.setEmail(email);

            try {
                user = gestionUser.findUserWithEmail(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (user == null) {
                request.setAttribute("error", "User not found");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }

            boolean isTrue = Password.checkPassword(password, user.getPassword()) && user.getIs_active();

            if (!isTrue) {
                request.setAttribute("error", "Invalid password");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                session.setAttribute("id", user.getId());
                session.setAttribute("lastName", user.getLast_name());
                session.setAttribute("firstName", user.getFirst_name());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", user.getRole());
                String targetPage;
                switch (user.getRole()) {
                    case "director":
                        HashMap<Projet, User> projets = gestionProjet.mapProjectsToChef();
                        List<User> users;
                        try {
                            users = gestionUser.findUsersWithRole("chef");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        request.setAttribute("projets", projets);
                        request.setAttribute("users", users);
                        targetPage = "/WEB-INF/jsp/home.jsp";
                        break;
                    case "chef":
                        HashMap<Projet, User> projets1 = gestionProjet.mapChefProjets(email);
                        request.setAttribute("projets", projets1);
                        targetPage = "/WEB-INF/jsp/home_chef.jsp";
                        break;
                    case "developer":
                        HashMap<Projet, User> projets2 = gestionProjet.mapDevProjets(email);
                        request.setAttribute("projets", projets2);
                        targetPage = "/WEB-INF/jsp/home_dev.jsp";
                        break;
                    default:
                        targetPage = "/WEB-INF/jsp/error.jsp";
                        break;
                }
                request.getRequestDispatcher(targetPage).forward(request, response);
            }
        } else {
            request.setAttribute("error", "Please enter your email and password");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        RequestDispatcher dispatcher = null;
//        dispatcher = request.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(request, response);
//    }
}
