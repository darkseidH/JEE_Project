<!DOCTYPE html>
<%--<%--%>
<%--	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");--%>
<%--	if (session.getAttribute("email") == null || session.getAttribute("id") == null) {--%>
<%--		response.sendRedirect("index.jsp");--%>
<%--	}else--%>
<%--		response.sendRedirect("/WEB-INF/jsp/home.jsp");--%>
<%--%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Se Connecter</title>

    <!-- Font Icon -->
    <link rel="stylesheet"
          href="resources/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="main">

    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="resources/images/signin-image.jpg" alt="sing up image">
                    </figure>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Connexion</h2>
                    <form method="post" action="login" class="register-form"
                          id="login-form">
                        <div class="form-group">
                            <label for="email"><i
                                    class="zmdi zmdi-account material-icons-name"></i></label> <input
                                type="text" name="email" id="email"
                                placeholder="Email"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label> <input
                                type="password" name="password" id="password"
                                placeholder="Mots de passe"/>
                        </div>

                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin"
                                   class="form-submit" value="Connexion"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="resources/js/main.js"></script>
</body>
</html>