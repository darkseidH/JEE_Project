package com.presentation.controller;

import com.buisness.GestionProjets;
import com.buisness.GestionTechnologie;
import com.buisness.I_Gestion_Projet;
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

@WebServlet(name = "TechnologieServlet", value = "/add_technologie")
public class TechnologieServlet extends HttpServlet {
    I_Gestion_technologie gestion_technologie = new GestionTechnologie();
    I_Gestion_Projet gestionProjets = new GestionProjets();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Long projet_id = Long.parseLong(req.getParameter("projet_id"));
        String[] selectedTechnologies = req.getParameterValues("selectedTechnologies[]");
        if (selectedTechnologies != null) {
            for (String Nomtechnologie : selectedTechnologies) {
               Technologie technologie = new Technologie();
               technologie.setNom(Nomtechnologie);
               technologie.setProjetId(projet_id);
               gestion_technologie.addTechnologie(technologie);
            }
        }

        Projet projet = gestionProjets.getProjetById(projet_id);
        req.setAttribute("projet", projet);
        if(projet.getMethodologie() != null && !projet.getMethodologie().trim().isEmpty()){
            try {
                Map<Technologie, List<User>> technologieProjetDevelopers = gestion_technologie.getTechnologieAndDevelopersByProjectId(projet_id);
                List<Technologie> technologieList = gestion_technologie.getTechnologiesNProjet(projet_id);
                req.setAttribute("technologies", technologieList);
                req.setAttribute("technologieProjetDevelopers", technologieProjetDevelopers);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefMethodologie.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefWithoutMethodologie.jsp").forward(req, resp);
    }

}
