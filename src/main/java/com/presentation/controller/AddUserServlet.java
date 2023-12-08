package com.presentation.controller;

import com.buisness.GestionUser;
import com.presentation.model.User;
import com.util.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddUserServlet", value = "/addUser")
public class AddUserServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public HttpServletRequest setRequestsAttributes(HttpServletRequest request) {
        List<User> users = new ArrayList<>();
        try {
            users = gestionUser.findAllUsersExceptDirector();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        return request;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetPage = "/WEB-INF/jsp/gestion_Personnel.jsp";
        request = setRequestsAttributes(request);
        request.getRequestDispatcher(targetPage).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetPage;
        User user = new User();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        Boolean status = Integer.parseInt(request.getParameter("status")) == 1;

        user.setLast_name(lastName);
        user.setFirst_name(firstName);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(Password.hashPassword(password));
        user.setIs_active(status);

        if (firstName == null || lastName == null || email == null || password == null || role == null || status == null) {
            targetPage = "/WEB-INF/jsp/gestion_Personnel.jsp";
            request = setRequestsAttributes(request);
            request.setAttribute("error", "Veuillez remplir tous les champs");
            request.getRequestDispatcher(targetPage).forward(request, response);
            return;
        }

        try {
            User userWithEmail = gestionUser.findUserWithEmail(user);
            if (userWithEmail != null) {
                targetPage = "/WEB-INF/jsp/gestion_Personnel.jsp";
                request = setRequestsAttributes(request);
                request.setAttribute("error", "Cet email est déjà utilisé");
                request.getRequestDispatcher(targetPage).forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        gestionUser.addUser(user);
        targetPage = "/WEB-INF/jsp/gestion_Personnel.jsp";
        request = setRequestsAttributes(request);
        response.sendRedirect(request.getContextPath() + "/gestion_Personnel");
    }
    // TODO: 10/05/2021 add a doGet method to redirect to the same page
    // TODO: verify where insering a user with invalid

}
