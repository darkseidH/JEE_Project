package com.presentation.controller;

import com.buisness.GestionUser;
import com.presentation.model.User;
import com.util.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "accountSetting", value = "/accountSetting")
public class AccountSettingServlet extends HttpServlet {
    GestionUser gestionUser = new GestionUser();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/jsp/account_Setting.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Long idUser = (Long) session.getAttribute("idUser");
        User user;
        try {
            user = gestionUser.findUserWithId(idUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("newPassword");
        String passwordConfirmation = request.getParameter("newPasswordValidation");

        if (password == null || passwordConfirmation == null || oldPassword == null) {
            request.setAttribute("error", "empty");
            request.getRequestDispatcher("/WEB-INF/jsp/account_Setting.jsp").forward(request, response);
        } else {
            if (!Password.checkPassword(oldPassword, user.getPassword())) {
                request.setAttribute("error", "wrongOldPassword");
                request.getRequestDispatcher("/WEB-INF/jsp/account_Setting.jsp").forward(request, response);
            } else {
                if (!password.equals(passwordConfirmation)) {
                    request.setAttribute("error", "PasswordsNotEquivalent");
                    request.getRequestDispatcher("/WEB-INF/jsp/account_Setting.jsp").forward(request, response);
                } else {
                    String hashedNewPassword = Password.hashPassword(password);
                    user.setPassword(hashedNewPassword);
                    gestionUser.updateUser(user);
                    request.setAttribute("error", "success");
                    request.getRequestDispatcher("/WEB-INF/jsp/account_Setting.jsp").forward(request, response);
                }
            }
        }
    }
    // TODO : add a way to change email and name
}
