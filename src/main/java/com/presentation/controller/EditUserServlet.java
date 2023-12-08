package com.presentation.controller;

import com.buisness.GestionUser;
import com.presentation.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditUser", value = "/edit_user")
public class EditUserServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetPage;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String userId = request.getParameter("idUser");

        if (firstName == null || lastName == null || email == null || role == null || status == null || userId == null) {
            String message = "error=Erreur lors de la modification de l'utilisateur";
            response.sendRedirect(request.getContextPath() + "/gestion_Personnel?" + message);
        } else {
            User user = new User();
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setEmail(email);
            user.setRole(role);
            boolean isActive = Integer.parseInt(status) == 1;
            user.setIs_active(isActive);
            user.setId(Long.parseLong(userId));
            if (gestionUser.updateUser(user) != null) {
                String message = "success=Utilisateur modifié avec succès";
                response.sendRedirect(request.getContextPath() + "/gestion_Personnel?" + message);
            } else {
                String message = "error=Erreur lors de la modification de l'utilisateur";
                response.sendRedirect(request.getContextPath() + "/gestion_Personnel?" + message);
            }
        }
    }
}
