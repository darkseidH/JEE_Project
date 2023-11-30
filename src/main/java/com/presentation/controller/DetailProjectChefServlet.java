package com.presentation.controller;

import com.buisness.GestionProjets;
import com.presentation.model.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DatailProjectChef", value = "/DetailProjectChef")
public class DetailProjectChefServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");
        Projet projet = gestionProjets.getProjetById(Long.parseLong(projectId));
        req.setAttribute("projet", projet);
        if(projet.getMethodologie() != null && !projet.getMethodologie().trim().isEmpty()){
            req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefMethodologie.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefWithoutMethodologie.jsp").forward(req, resp);
    }
}
