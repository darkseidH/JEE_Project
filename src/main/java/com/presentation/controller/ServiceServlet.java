package com.presentation.controller;

import com.buisness.*;
import com.presentation.model.Projet;
import com.presentation.model.Service;
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

@WebServlet(name = "ServiceServlet", value = "/ServiceServlet")
public class ServiceServlet extends HttpServlet {
    I_Gestion_technologie gestion_technologie = new GestionTechnologie();
    I_Gestion_Projet gestionProjets = new GestionProjets();
    I_GestionService gestionService = new GestionService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long projet_id = Long.parseLong(req.getParameter("projet_id"));

        if(req.getParameter("submitAddService")!= null) {
            Service service = new Service();
            service.setDeveloperId(Integer.parseInt(req.getParameter("developer_Id")));
            service.setId(Long.valueOf(req.getParameter("service_id")));
            service.setProjetId(Integer.parseInt(req.getParameter("projet_Id")));
            service.setDuree(Integer.parseInt(req.getParameter("duree")));
            service.setDescription(req.getParameter("description"));
            gestionService.addService(service);
        }


        Projet projet = gestionProjets.getProjetById(projet_id);
        req.setAttribute("projet", projet);
        try {
            Map<Technologie, List<User>> technologieProjetDevelopers = gestion_technologie.getTechnologieAndDevelopersByProjectId(projet_id);
            List<Technologie> technologieList = gestion_technologie.getTechnologiesNProjet(projet_id);
            Map<Technologie, List<User>> DevelopersNProjetTechnologie = gestion_technologie.getTechnologieAndDevelopersNByProjectId(projet_id);
            String afficheInputDateRuenion = gestion_technologie.afficheInputDateRuenion(projet_id);
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
