package com.presentation.controller;

import com.buisness.*;
import com.presentation.model.*;
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
import java.util.Map;

@WebServlet(name = "DatailProjectDev", value = "/DetailProjectDev")
public class DetailProjectDevServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();
    I_GestionService gestionService = new GestionService();
    I_Gestion_Notifications gestionNotifications = new GestionNotifications();
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long projectId = Long.parseLong(req.getParameter("projectId"));
        Projet projet = gestionProjets.getProjetById(projectId);
        String email = (String) req.getSession().getAttribute("email");
        Long notificationId = Long.parseLong(req.getParameter("notificationId"));
        if(notificationId != 0)
            gestionNotifications.deleteNotification(notificationId);
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("id");
        req.setAttribute("projet", projet);
        try {
            HashMap<Service, List<Tache>> serviceListHashMap = gestionService.getAllServiceTacheDeveloperProject(projectId,email);
            req.setAttribute("ServicesTaches",serviceListHashMap);
            List<Notification> notifications = gestionNotifications.getAllNotificationsUser(id);
            req.setAttribute("notifications",notifications);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/detailProjectDev.jsp").forward(req, resp);
    }
}
