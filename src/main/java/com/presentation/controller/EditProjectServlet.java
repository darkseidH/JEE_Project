package com.presentation.controller;

import com.buisness.GestionProjets;
import com.presentation.model.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "EditProjectServlet", value = "/editProject")
public class EditProjectServlet extends HttpServlet {

    GestionProjets gestionProjets = new GestionProjets();

    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long projectId = Long.parseLong(request.getParameter("idProject"));
        String name = request.getParameter("nom_projet");
        String description = request.getParameter("description_projet");
        LocalDate date_demarrage = Date.valueOf(request.getParameter("date_demarrage")).toLocalDate();
        String client = request.getParameter("client_projet");
        LocalDate date_livraison = Date.valueOf(request.getParameter("date_livraison")).toLocalDate();
        int duree = (int) ChronoUnit.DAYS.between(date_demarrage, date_livraison);
        Long id = Long.parseLong(request.getParameter("chef_projet"));
        Projet projet = new Projet();
        projet.setId(projectId);
        projet.setNom(name);
        projet.setDescription(description);
        projet.setDateDemarrage(Date.valueOf(date_demarrage));
        projet.setNomClient(client);
        projet.setDateLiverison(Date.valueOf(date_livraison));
        projet.setNombreJourDeveloppement(duree);
        projet.setChefProjet_id(id);
        gestionProjets.updateProjet(projet);
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
    }


}
