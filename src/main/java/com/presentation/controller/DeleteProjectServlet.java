package com.presentation.controller;

import com.buisness.GestionProjets;
import com.presentation.model.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "DeleteProjectServlet", value = "/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();


    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        Projet projet = new Projet();
        projet.setId(projectId);
        if (gestionProjets.deleteProjet(projet)) {
            request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
        }
    }
}
