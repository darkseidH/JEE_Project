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
@WebServlet(name = "searchProjectDircteurServlet", value = "/search_project_dircteur")
public class SearchProjectDircteurServlet extends HttpServlet {
    GestionProjets gestionProjets = new GestionProjets();

    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String valueSearch = request.getParameter("valueSearch");
        HashMap<Projet, User> matchingProjects = gestionProjets.mapProjectsNameToChef(valueSearch);
        StringBuilder htmlResponse = new StringBuilder();

        if (matchingProjects != null) {
            for (Map.Entry<Projet, User> entry : matchingProjects.entrySet()) {
                Projet currentProjet = entry.getKey();
                User currentUser = entry.getValue();
                htmlResponse.append("<tr style=\"margin-bottom: 150px\">");
                htmlResponse.append("<td><i class=\"fab fa-angular fa-lg text-danger me-3\"></i>");
                htmlResponse.append("<strong>").append(currentProjet.getNom()).append("</strong></td>");
                htmlResponse.append("<td>").append(currentProjet.getNomClient()).append("</td>");
                htmlResponse.append("<td>").append(currentUser.getFirst_name()).append(" ").append(currentUser.getLast_name()).append("</td>");
                htmlResponse.append("<td><span class=\"badge bg-label-primary me-1\">Active</span></td>");
                htmlResponse.append("<td>");
                htmlResponse.append("<div class=\"dropdown\">");
                htmlResponse.append("<button type=\"button\" class=\"btn p-0 dropdown-toggle hide-arrow\"");
                htmlResponse.append("data-bs-toggle=\"dropdown\">");
                htmlResponse.append("<i class=\"bx bx-dots-vertical-rounded\"></i>");
                htmlResponse.append("</button>");
                htmlResponse.append("<div class=\"dropdown-menu\">");
                htmlResponse.append("<a class=\"dropdown-item\" href=\"javascript:void(0);\"");
                htmlResponse.append("onclick=\"EditProject('").append(currentProjet.getId()).append("','','")
                        .append(currentProjet.getNom()).append("','").append(currentProjet.getNomClient()).append("','")
                        .append(currentProjet.getDateDemarrage()).append("','").append(currentProjet.getDateLiverison())
                        .append("', '").append(currentProjet.getChefProjet_id()).append("')\"");
                htmlResponse.append("><i class=\"bx bx-edit-alt me-1\"></i> Edit</a>");
                htmlResponse.append("<a class=\"dropdown-item\" href=\"javascript:void(0);\"");
                htmlResponse.append("onclick=\"confirmDelete('").append(currentProjet.getId()).append("')\"");
                htmlResponse.append("><i class=\"bx bx-trash me-1\"></i> Delete</a>");
                htmlResponse.append("</div>");
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
