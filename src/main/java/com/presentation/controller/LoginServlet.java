package com.presentation.controller;

import com.buisness.GestionUser;
import com.data.GestionUserData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    GestionUserData gestionUserData = new GestionUserData();
    public void init() throws ServletException {
        super.init();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (request.getParameter("email") != null && request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean exist = gestionUserData.checkUser(email, password);

            if (exist){
                session.setAttribute("email", email);
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
            }else {
                request.setAttribute("error", "Invalid email or password");
                dispatcher = request.getRequestDispatcher("/index.jsp");
            }
            dispatcher.forward(request, response);
        }
        else {
            request.setAttribute("error", "Please enter your email and password");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
