<%@ page import="com.presentation.model.User" %>
<%@ page import="com.buisness.GestionUser" %>
<%@ page import="java.util.List" %>
<%@ page import="com.buisness.GestionProjets" %>
<%@ page import="com.presentation.model.Projet" %>
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

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
-->
<!-- beautify ignore:start -->
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

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="resources/assets/js/config.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">

    <!-- Include SweetAlert JS -->
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
                    <a href="index.html" class="app-brand-link">
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
                    <li class="menu-item active">
                        <a href="index.html" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-home-circle"></i>
                            <div data-i18n="Analytics">Dashboard</div>
                        </a>
                    </li>

                    <li class="menu-item">
                        <a href="javascript:void(0);" class="menu-link menu-toggle">
                            <i class="menu-icon tf-icons bx bx-dock-top"></i>
                            <div data-i18n="Account Settings">Account Settings</div>
                        </a>
                        <ul class="menu-sub">
                            <li class="menu-item">
                                <a href="pages-account-settings-account.html" class="menu-link">
                                    <div data-i18n="Account">Account</div>
                                </a>
                            </li>
                        </ul>
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
                            <!-- Place this tag where you want the button to render. -->


                            <!-- User -->
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
                                                    <span class="fw-semibold d-block">John Doe</span>
                                                    <small class="text-muted">Admin</small>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div class="dropdown-divider"></div>
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="#">
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

                <!-- / Navbar -->

                <!-- Content wrapper -->
                <div class="content-wrapper">
                    <!-- Content -->

                    <div class="container-xxl flex-grow-1 container-p-y">
                        <div class="card">
                            <h5 class="card-header">Les Projets</h5>
                            <div class="table-responsive text-nowrap">
                                <table class="table">
                                    <thead class="table-dark">
                                    <tr>
                                        <th>Projet</th>
                                        <th>Client</th>
                                        <th>Chef Projet</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-border-bottom-0">
                                    <%
                                        GestionProjets gestionProjets = new GestionProjets();
                                        GestionUser gestionUser = new GestionUser();
                                        List<Projet> projets = gestionProjets.findAllProjets();
                                        for (Projet projet : projets) {
                                            User user = new User();
                                            user.setId(projet.getChefProjet_id());
                                            user = gestionUser.findUserWithId(user);

                                    %>
                                    <tr  style="margin-bottom: 150px">
                                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i>
                                            <strong><%= projet.getNom() %>
                                            </strong></td>
                                        <td><%= projet.getNomClient() %>
                                        </td>
                                        <td><%= user.getFirst_name() + " " + user.getLast_name() %>
                                        </td>
                                        <td><span class="badge bg-label-primary me-1">Active</span></td>
                                        <td>
                                            <div class="dropdown">
                                                <button type="button" class="btn p-0 dropdown-toggle hide-arrow"
                                                        data-bs-toggle="dropdown">
                                                    <i class="bx bx-dots-vertical-rounded"></i>
                                                </button>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="javascript:void(0);"
                                                    ><i class="bx bx-edit-alt me-1"></i> Edit</a
                                                    >
                                                    <a class="dropdown-item" href="javascript:void(0);" onclick="confirmDelete('<%= projet.getId() %>')"
                                                    ><i class="bx bx-trash me-1"></i> Delete</a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- </div>
        <div class="row"> -->

                    </div>
                    <!-- / Content -->


                    <div class="content-backdrop fade"></div>
                </div>
                <!-- Content wrapper -->
            </div>
            <!-- / Layout page -->
        </div>

        <!-- Overlay -->
        <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    <div class="buy-now">
        <a

                class="btn btn-danger btn-buy-now"
                onclick="toggle()"
        ><img src="resources/assets/img/favicon/icons8-add-80.png" width="30px" height="30px" alt=""></a
        >
    </div>
</div>

<div id="popup" class="container">
    <form class="project-form" action="add_project" method="post">
        <div id="div_Form">
            <div>
                <label for="nom_projet">Nom du Projet :</label>
                <input type="text" id="nom_projet" name="nom_projet" class="form-control" required>

                <label for="description_projet">Description du Projet :</label>
                <textarea id="description_projet" name="description_projet" class="form-control" required></textarea>

                <label for="client_projet">Client du Projet :</label>
                <input type="text" id="client_projet" name="client_projet" class="form-control" required>

                <label for="date_demarrage">Date de Démarrage :</label>
                <input type="date" id="date_demarrage" name="date_demarrage" class="form-control" required>
            </div>
            <div>
                <label for="date_livraison">Date de Livraison :</label>
                <input type="date" id="date_livraison" name="date_livraison" class="form-control" required>

                <%--                <label for="jours_developpement">Nombre de Jours de Développement :</label>--%>
                <%--                <input type="number" id="jours_developpement" name="jours_developpement" class="form-control" required>--%>

                <label for="chef_projet">Chef de Projet :</label>
                <select id="chef_projet" name="chef_projet" class="form-control" required>
                    <%
                        User user = new User();
                        user.setRole("chef");

                        List<User> chefs = new GestionUser().findUsersWithRole(user);
                        for (User chef : chefs) {
                    %>
                    <option value="<%= chef.getId() %>"><%= chef.getFirst_name() + " " + chef.getLast_name() %>
                    </option>
                    <%
                        }
                    %>
                </select>

            </div>
        </div>
        <div style="display: flex; justify-content: space-between; margin-top: 23px;">
            <button type="text" class="btn-fermer" onclick="closePopup()">fermer</button>
            <button type="submit" class="btn-submit">Créer Projet</button>
        </div>

    </form>
</div>

<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="resources/assets/vendor/libs/jquery/jquery.js"></script>
<script src="resources/assets/vendor/libs/popper/popper.js"></script>
<script src="resources/assets/vendor/js/bootstrap.js"></script>
<script src="resources/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

<script src="resources/assets/vendor/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->
<script src="resources/assets/vendor/libs/apex-charts/apexcharts.js"></script>

<!-- Main JS -->
<script src="resources/assets/js/main.js"></script>

<!-- Page JS -->
<script src="assets/js/dashboards-analytics.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>

<script type="text/javascript">
    function toggle(){
        var blur = document.getElementById("bleur");
        blur.classList.toggle("active1");
        var popup = document.getElementById("popup");
        popup.style.visibility = "visible";
        popup.style.opacity = 1;
    }
    function closePopup(){
        var blur = document.getElementById("bleur");
        blur.classList.remove("active1");
        var popup = document.getElementById("popup");
        popup.style.visibility = "hidden";
        popup.style.opacity = 0;
    }
    function confirmDelete(projectId) {
        // Use SweetAlert for the confirmation dialog
        Swal.fire({
            title: 'Are you sure?',
            text: 'You won\'t be able to revert this!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                // If the user clicks "Yes," redirect to the delete servlet with the project ID
                window.location.href = 'deleteProject?projectId=' + projectId;
            }
        });
    }
</script>
</body>
</html>