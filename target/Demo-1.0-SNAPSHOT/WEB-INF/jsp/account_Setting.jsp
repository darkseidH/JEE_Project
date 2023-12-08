<%--
  Created by IntelliJ IDEA.
  User: darkseid
  Date: 11/25/23
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if (session.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
    }
%>>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="resources/assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Dashboard</title>

    <meta name="description" content=""/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="resources/assets/img/favicon/favicon.ico"/>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="resources/assets/vendor/fonts/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="resources/assets/vendor/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="resources/assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="resources/assets/css/demo.css"/>

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="resources/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

    <link rel="stylesheet" href="resources/assets/vendor/libs/apex-charts/apex-charts.css"/>

    <!-- Page CSS -->
    <link rel="stylesheet" href="resources/css/cssDirecteur.css"/>
    <!-- Helpers -->
    <script src="resources/assets/vendor/js/helpers.js"></script>

    <script src="resources/assets/js/config.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>

<body>
<!-- Layout wrapper -->
<div id="bleur">
    <div class="layout-wrapper layout-content-navbar">
        <div class="layout-container">
            <!-- Menu -->

            <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
                <div class="app-brand demo">
                    <a href="home" class="app-brand-link">
                        <img src="resources/assets/img/favicon/favicon.ico" alt="">
                        <span class="app-brand-text demo menu-text fw-bolder ms-2">les Affaires</span>
                    </a>

                    <a href="javascript:void(0);"
                       class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                        <i class="bx bx-chevron-left bx-sm align-middle"></i>
                    </a>
                </div>

                <div class="menu-inner-shadow"></div>

                <ul class="menu-inner py-1">
                    <!-- Dashboard -->
                    <li class="menu-item">
                        <a href="home" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-home-circle"></i>
                            <div data-i18n="Analytics">Dashboard</div>
                        </a>
                    </li>
                    <li class="menu-item active">
                        <a href="accountSetting" class="menu-link">
                            <i class="bx bx-cog me-2"></i>
                            <div data-i18n="Analytics">Account</div>
                        </a>
                    </li>
                    <li class="menu-item">
                        <a href="gestion_Personnel" class="menu-link">
                            <i class="bx bx-cog me-2"></i>
                            <div data-i18n="Analytics">Gestion Personnel</div>
                        </a>
                    </li>
                </ul>
            </aside>

            <!-- Layout container -->
            <div class="layout-page">
                <!-- Navbar -->

                <nav
                        class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                        id="layout-navbar"
                >
                    <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                        <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                            <i class="bx bx-menu bx-sm"></i>
                        </a>
                    </div>

                    <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                        <!-- Search -->
                        <div class="navbar-nav align-items-center">
                            <div class="nav-item d-flex align-items-center">
                                <i class="bx bx-search fs-4 lh-0"></i>
                                <input
                                        type="text"
                                        class="form-control border-0 shadow-none"
                                        placeholder="Searchresources."
                                        aria-label="Searchresources."
                                />
                            </div>
                        </div>
                        <!-- /Search -->

                        <ul class="navbar-nav flex-row align-items-center ms-auto">

                            <li class="nav-item navbar-dropdown dropdown-user dropdown">
                                <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);"
                                   data-bs-toggle="dropdown">
                                    <div class="avatar avatar-online">
                                        <img src="resources/assets/img/avatars/1.png" alt
                                             class="w-px-40 h-auto rounded-circle"/>
                                    </div>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li>
                                        <a class="dropdown-item" href="">
                                            <div class="d-flex">
                                                <div class="flex-shrink-0 me-3">
                                                    <div class="avatar avatar-online">
                                                        <img src="resources/assets/img/avatars/1.png" alt
                                                             class="w-px-40 h-auto rounded-circle"/>
                                                    </div>
                                                </div>
                                                <div class="flex-grow-1">
                                                    <span class="fw-semibold d-block"><%= session.getAttribute("lastName") + " " + session.getAttribute("firstName") %></span>
                                                    <small class="text-muted"><%= session.getAttribute("role")%>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div class="dropdown-divider"></div>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="accountSetting">
                                            <i class="bx bx-cog me-2"></i>
                                            <span class="align-middle">Settings</span>
                                        </a>
                                    </li>
                                    <li>
                                        <div class="dropdown-divider"></div>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="logout">
                                            <i class="bx bx-power-off me-2"></i>
                                            <span class="align-middle">Log Out</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <!--/ User -->
                        </ul>
                    </div>
                </nav>

                <div class="content-wrapper">
                    <%
                        String name = (String) request.getAttribute("error");
                    %>

                    <div class="container-xxl flex-grow-1 container-p-y">
                        <div class="card">
                            <h5 class="card-header">Changer Infos Personnel</h5>
                            <div class="card-body">
                                <input name="error" type="hidden" id="error"
                                       value="<%= request.getAttribute("error") %>">
                                <form class="row g-3 needs-validation" onsubmit="return accountSetting(0)" method="post"
                                      id="formInfoPerso">
                                    <div class="col-md-4">
                                        <label for="firstName" class="form-label">Nom</label>
                                        <input type="text" class="form-control" id="firstName" name="lastName"
                                               value="<%= session.getAttribute("lastName")%>" required>
                                        <div class="valid-feedback">
                                            Looks good!
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="lastName" class="form-label">Prenom</label>
                                        <input type="text" class="form-control" id="lastName" name="firstName"
                                               value="<%= session.getAttribute("firstName")%>" required>
                                        <div class="valid-feedback">
                                            Looks good!
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="text" class="form-control" id="email" name="email"
                                               value="<%= session.getAttribute("email")%>" required>
                                        <div class="valid-feedback">
                                            Looks good!
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex justify-content-end">
                                        <button class="btn btn-primary" type="submit">Changer</button>
                                    </div>
                                </form>

                                <form class="row g-3 needs-validation" onsubmit="return accountSetting(1)" method="post" id="formPassword">
                                    <div class="col-md-4">
                                        <label for="oldPassword" class="form-label">Ancien mot de passe</label>
                                        <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                                               required>
                                        <div class="valid-feedback">
                                            Looks good!
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="newPassword" class="form-label">Nouveau mot de passe</label>
                                        <input type="password" class="form-control" id="newPassword" name="newPassword"
                                               required>
                                        <div class="valid-feedback">
                                            Looks good!
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="newPasswordValidation" class="form-label">Confirmer mot de
                                            passe</label>
                                        <div class="input-group has-validation">
                                            <input type="password" class="form-control" id="newPasswordValidation"
                                                   name="newPasswordValidation" required>
                                            <div class="invalid-feedback">
                                                Please choose a username.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex justify-content-end">
                                        <button class="btn btn-primary" type="submit">Changer</button>
                                    </div>
                                </form>
                            </div>

                        </div>
                        <div class="content-backdrop fade"></div>
                    </div>
                </div>
            </div>

            <div class="layout-overlay layout-menu-toggle"></div>
        </div>

    </div>

<%--    <div id="popup" class="container">--%>
<%--        <form class="project-form" action="add_project" method="post">--%>
<%--            <div id="div_Form">--%>
<%--                <div>--%>
<%--                    <label for="nom_projet">Nom du Projet :</label>--%>
<%--                    <input type="text" id="nom_projet" name="nom_projet" class="form-control" required>--%>

<%--                    <label for="description_projet">Description du Projet :</label>--%>
<%--                    <textarea id="description_projet" name="description_projet" class="form-control"--%>
<%--                              required></textarea>--%>

<%--                    <label for="client_projet">Client du Projet :</label>--%>
<%--                    <input type="text" id="client_projet" name="client_projet" class="form-control" required>--%>

<%--                    <label for="date_demarrage">Date de Démarrage :</label>--%>
<%--                    <input type="date" id="date_demarrage" name="date_demarrage" class="form-control" required>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <label for="date_livraison">Date de Livraison :</label>--%>
<%--                    <input type="date" id="date_livraison" name="date_livraison" class="form-control" required>--%>
<%--                    <label for="chef_projet">Chef de Projet :</label>--%>
<%--                    <select id="chef_projet" name="chef_projet" class="form-control" required>--%>

<%--                    </select>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--            <div style="display: flex; justify-content: space-between; margin-top: 23px;">--%>
<%--                <button type="text" class="btn-fermer" onclick="closePopup()">Fermer</button>--%>
<%--                <button type="submit" class="btn-submit">Créer Projet</button>--%>
<%--            </div>--%>

<%--        </form>--%>
<%--    </div>--%>

    <script src="resources/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="resources/assets/vendor/libs/popper/popper.js"></script>
    <script src="resources/assets/vendor/js/bootstrap.js"></script>
    <script src="resources/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="resources/assets/vendor/js/menu.js"></script>
    <script src="resources/assets/vendor/libs/apex-charts/apexcharts.js"></script>
    <script src="resources/assets/js/main.js"></script>
    <script src="assets/js/dashboards-analytics.js"></script>
    <script async defer src="https://buttons.github.io/buttons.js"></script>
        <%
        System.out.println(request.getAttribute("error"));
        %>

    <script type="text/javascript">
        function showSweetAlert(error) {
            switch (error) {
                case 'empty':
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Please fill in all the fields.',
                    });
                    break;
                case 'wrongOldPassword':
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Incorrect old password.',
                    });
                    break;
                case 'PasswordsNotEquivalent':
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Passwords do not match.',
                    });
                    break;
                case 'Psc':
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: 'Password updated successfully!',
                    });
                    break;
                case 'Yihbu':
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: 'Personal information updated successfully!',
                    });
                    break;
                default:
                    console.error('Unhandled error value:', error);
            }
        }

        console.log('<%= request.getAttribute("error") %>')
        showSweetAlert('<%= request.getAttribute("error") %>');
    </script>

    <script type="text/javascript">
        function accountSetting(param) {
            if (param === 0) {
                var form = document.getElementById("formInfoPerso");
                form.action = "accountSetting?param=0";
            } else {
                var form = document.getElementById("formPassword");
                form.action = "accountSetting?param=1";
            }
        }

    </script>

</body>
</html>