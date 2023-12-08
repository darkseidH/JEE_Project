package com.presentation.controller;

import com.buisness.GestionUser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "EditUser", value = "/edit_user")
public class EditUserServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();


}
