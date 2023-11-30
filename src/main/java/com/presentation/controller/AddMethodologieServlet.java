package com.presentation.controller;

import com.buisness.GestionProjets;
import com.data.GestionProjetsData;
import com.presentation.model.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddMethodologieServlet", value = "/add_methodologie")
public class AddMethodologieServlet extends HttpServlet {
    GestionProjets gestionProjet = new GestionProjets();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodologie = req.getParameter("methodologie");
        String projectId = req.getParameter("project_id");
        gestionProjet.addMethodologieData(methodologie, Long.parseLong(projectId));
        Projet projet = gestionProjet.getProjetById(Long.parseLong(projectId));
        req.setAttribute("projet", projet);
        if(projet.getMethodologie() != null && !projet.getMethodologie().trim().isEmpty()){
            req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefMethodologie.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefWithoutMethodologie.jsp").forward(req, resp);
    }
}
