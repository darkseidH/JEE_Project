package com.presentation.controller;

import com.buisness.GestionProjets;
import com.buisness.GestionTechnologie;
import com.buisness.I_Gestion_technologie;
import com.presentation.model.Projet;
import com.presentation.model.Technologie;
import com.presentation.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DatailProjectChef", value = "/DetailProjectChef")
public class DetailProjectChefServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();
    I_Gestion_technologie gestionTechnologie = new GestionTechnologie();
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long projectId = Long.parseLong(req.getParameter("projectId"));
        Projet projet = gestionProjets.getProjetById(projectId);
        req.setAttribute("projet", projet);
            try {
                Map<Technologie, List<User>> technologieProjetDevelopers = gestionTechnologie.getTechnologieAndDevelopersByProjectId(projectId);
                Map<Technologie, List<User>> DevelopersNProjetTechnologie = gestionTechnologie.getTechnologieAndDevelopersNByProjectId(projectId);
                List<Technologie> technologieList = gestionTechnologie.getTechnologiesNProjet(projectId);
                String afficheInputDateRuenion = gestionTechnologie.afficheInputDateRuenion(projectId);
                req.setAttribute("afficheInputDateRuenion", afficheInputDateRuenion);
                req.setAttribute("technologies", technologieList);
                req.setAttribute("technologieProjetDevelopers", technologieProjetDevelopers);
                req.setAttribute("DevelopersNProjetTechnologie", DevelopersNProjetTechnologie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefMethodologie.jsp").forward(req, resp);
    }
}
