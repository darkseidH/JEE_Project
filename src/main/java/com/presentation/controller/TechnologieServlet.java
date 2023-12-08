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

@WebServlet(name = "TechnologieServlet", value = "/technologieServlet")
public class TechnologieServlet extends HttpServlet {
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

       if(req.getParameter("submitFAddTechnologie")!= null){
           String[] selectedTechnologies = req.getParameterValues("selectedTechnologies[]");
           if (selectedTechnologies != null) {
               for (String Nomtechnologie : selectedTechnologies) {
                   Technologie technologie = new Technologie();
                   technologie.setNom(Nomtechnologie);
                   technologie.setProjetId(projet_id);
                   gestion_technologie.addTechnologie(technologie);
               }
           }
       }

       if(req.getParameter("submitFAddDevloperTProjet")!= null) {
           String[] selectedDevelopers = req.getParameterValues("selectedDevloppeurs[]");
           if (selectedDevelopers != null) {
               for (String selecltedDeveloper : selectedDevelopers) {
                   String[] split = selecltedDeveloper.split("-");
                     Long developer_id = Long.parseLong(split[1]);
                     Long technologie_id = Long.parseLong(split[0]);
                     gestion_technologie.addDeveloperTechnologieProjet(technologie_id,developer_id);
               }
           }
       }

       if(req.getParameter("submitAddMethodologie")!= null) {
           String methodologie = req.getParameter("methodologie");
           gestionProjets.addMethodologie(methodologie, projet_id);
       }

       if(req.getParameter("submitAddDateReunion")!= null) {
           String dateReunion = req.getParameter("dateRuenion");
           gestionProjets.addDateReunion(dateReunion, projet_id);
       }


        Projet projet = gestionProjets.getProjetById(projet_id);
        req.setAttribute("projet", projet);
            try {
                Map<Technologie, List<User>> technologieProjetDevelopers = gestion_technologie.getTechnologieAndDevelopersByProjectId(projet_id);
                List<Technologie> technologieList = gestion_technologie.getTechnologiesNProjet(projet_id);
                Map<Technologie, List<User>> DevelopersNProjetTechnologie = gestion_technologie.getTechnologieAndDevelopersNByProjectId(projet_id);
                String afficheInputDateRuenion = gestion_technologie.afficheInputDateRuenion(projet_id);
                List<User> DevelopersProjet = gestionProjets.getDevlopersProjet(projet_id);
                HashMap<Service, List<Tache>> serviceListHashMap = gestionService.getAllServiceTache(projet_id);
                req.setAttribute("afficheInputDateRuenion", afficheInputDateRuenion);
                req.setAttribute("technologies", technologieList);
                req.setAttribute("technologieProjetDevelopers", technologieProjetDevelopers);
                req.setAttribute("DevelopersNProjetTechnologie", DevelopersNProjetTechnologie);
                req.setAttribute("DevelopersProjet",DevelopersProjet);
                req.setAttribute("ServicesTaches",serviceListHashMap);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/WEB-INF/jsp/detailProjectChefMethodologie.jsp").forward(req, resp);
    }

}
