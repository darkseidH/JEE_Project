package com.presentation.controller;

import com.buisness.GestionProjets;
import com.presentation.model.Projet;
import com.presentation.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "searchProjectChefServlet", value = "/search_project_chef")
public class SearchProjectChefServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();

    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String valueSearch = request.getParameter("valueSearch");
        String email = (String) request.getSession().getAttribute("email");
        HashMap<Projet, User> matchingProjects = gestionProjets.mapProjectsNameToChef(valueSearch,email);
        StringBuilder htmlResponse = new StringBuilder();

        if (matchingProjects != null) {
            for (Map.Entry<Projet, User> entry : matchingProjects.entrySet()) {
                Projet currentProjet = entry.getKey();
                User currentUser = entry.getValue();
                htmlResponse.append("<tr style=\"margin-bottom: 150px\">");
                htmlResponse.append("<td><i class=\"fab fa-angular fa-lg text-danger me-3\"></i>");
                htmlResponse.append("<strong>").append(currentProjet.getNom()).append("</strong></td>");
                htmlResponse.append("<td>").append(currentProjet.getNomClient()).append("</td>");
                htmlResponse.append("<td>").append(currentProjet.getDateLiverison()).append("</td>");
                htmlResponse.append("<td><span class=\"badge bg-label-primary me-1\">Active</span></td>");
                htmlResponse.append("<td>");
                htmlResponse.append("<div class=\"dropdown\">");
                htmlResponse.append("<a href=\"DetailProjetcChef?projectId=").append(currentProjet.getId()).append("\" > <img src=\"resources/images/img.png\" alt=\"\" width=\"25px\" height=\"25px\" style=\"margin-left: 20px;\"> </a>");
                htmlResponse.append("</div>");
                htmlResponse.append("</td>");
                htmlResponse.append("</tr>");
            }
        } else {
            htmlResponse.append("<p>No matching projects found.</p>");
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(htmlResponse);
        }
    }
}
