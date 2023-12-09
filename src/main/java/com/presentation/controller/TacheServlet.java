package com.presentation.controller;

import com.buisness.*;
import com.presentation.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TacheServlet", value = "/TacheServlet")
public class TacheServlet extends HttpServlet {
    I_GestionTache gestionTache = new GetionTache();
    I_Gestion_Projet gestionProjets = new GestionProjets();
    I_GestionService gestionService = new GestionService();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long projet_id = Long.parseLong(req.getParameter("projet_id"));

        if(req.getParameter("submitAddTache")!= null) {
            Tache tache = new Tache();
            System.out.println("ezfzefze");
            Long id = Long.parseLong(req.getParameter("selectedService"));
            tache.setService_id(id);
            tache.setAvancement(Integer.parseInt(req.getParameter("avancement")));
            tache.setDescription(req.getParameter("description"));
            gestionTache.addTache(tache);
        }


        Projet projet = gestionProjets.getProjetById(projet_id);
        req.setAttribute("projet", projet);
        String email = (String) req.getSession().getAttribute("email");
        req.setAttribute("projet", projet);
        try {
            HashMap<Service, List<Tache>> serviceListHashMap = gestionService.getAllServiceTacheDeveloperProject(projet_id,email);
            req.setAttribute("ServicesTaches",serviceListHashMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/detailProjectDev.jsp").forward(req, resp);
    }
}
