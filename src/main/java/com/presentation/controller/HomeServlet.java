package com.presentation.controller;

import com.buisness.GestionNotifications;
import com.buisness.GestionProjets;
import com.buisness.GestionUser;
import com.buisness.I_Gestion_Notifications;
import com.presentation.model.Notification;
import com.presentation.model.Projet;
import com.presentation.model.User;
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


@WebServlet(name = "homeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    
    GestionUser gestionUser = new GestionUser();
    GestionProjets gestionProjet = new GestionProjets();
    I_Gestion_Notifications gestionNotifications = new GestionNotifications();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String targetPage;
        String email = session.getAttribute("email").toString();
        Long id = (Long) session.getAttribute("id");
        switch (session.getAttribute("role").toString()) {
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
                try {
                    List<Notification> notifications = gestionNotifications.getAllNotificationsUser(id);
                    request.setAttribute("notifications",notifications);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                HashMap<Projet, User> projets1 = gestionProjet.mapChefProjets(email);
                List<User> users1;
                try {
                    users1 = gestionUser.findUsersWithRole("chef");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("projets", projets1);
                request.setAttribute("users", users1);
                targetPage = "/WEB-INF/jsp/home_chef.jsp";
                break;
            case "developer":
                try {
                    List<Notification> notifications = gestionNotifications.getAllNotificationsUser(id);
                    request.setAttribute("notifications",notifications);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
}
