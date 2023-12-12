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

@WebServlet(name = "DatailProjectChef", value = "/DetailProjectChef")
public class DetailProjectChefServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();
    I_Gestion_technologie gestionTechnologie = new GestionTechnologie();
    I_GestionService gestionService = new GestionService();
    I_Gestion_Notifications gestionNotifications = new GestionNotifications();
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long projectId = Long.parseLong(req.getParameter("projectId"));
        Projet projet = gestionProjets.getProjetById(projectId);
        Long notificationId = Long.parseLong(req.getParameter("notificationId"));
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("id");
        if(notificationId != 0)
        gestionNotifications.deleteNotification(notificationId);
        req.setAttribute("projet", projet);
            try {
                Map<Technologie, List<User>> technologieProjetDevelopers = gestionTechnologie.getTechnologieAndDevelopersByProjectId(projectId);
                Map<Technologie, List<User>> DevelopersNProjetTechnologie = gestionTechnologie.getTechnologieAndDevelopersNByProjectId(projectId);
                List<Technologie> technologieList = gestionTechnologie.getTechnologiesNProjet(projectId);
                String afficheInputDateRuenion = gestionTechnologie.afficheInputDateRuenion(projectId);
                List<User> DevelopersProjet = gestionProjets.getDevlopersProjet(projectId);
                HashMap<Service, List<Tache>> serviceListHashMap = gestionService.getAllServiceTache(projectId);
                req.setAttribute("afficheInputDateRuenion", afficheInputDateRuenion);
                req.setAttribute("technologies", technologieList);
                req.setAttribute("technologieProjetDevelopers", technologieProjetDevelopers);
                req.setAttribute("DevelopersNProjetTechnologie", DevelopersNProjetTechnologie);
                req.setAttribute("DevelopersProjet",DevelopersProjet);
                req.setAttribute("ServicesTaches",serviceListHashMap);
                List<Notification> notifications = gestionNotifications.getAllNotificationsUser(id);
                req.setAttribute("notifications",notifications);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefMethodologie.jsp").forward(req, resp);
    }
}
