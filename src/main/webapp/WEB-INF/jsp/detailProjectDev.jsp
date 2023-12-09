<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="jdk.jshell.execution.Util" %>
<%@ page import="com.presentation.model.*" %>
<%--
  Created by IntelliJ IDEA.
  User: darkseid
  Date: 11/25/23
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if (session.getAttribute("email") == null || session.getAttribute("id") == null) {
        response.sendRedirect("index.jsp");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (session.getAttribute("email") == null || session.getAttribute("id") == null) {
            response.sendRedirect("index.jsp");
        }
    }
%>
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

    <title>DetailProject</title>

    <meta name="description" content=""/>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/css/multi-select-tag.css">


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



    <style>

        .divContentWrapper {
            width: 1272px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Style pour le conteneur interne avec flexbox */
        #divContainer {
            display: flex;
            justify-content: space-evenly;
            flex-wrap: wrap; /* Permet aux éléments internes de passer à la ligne si nécessaire */
        }

        /* Style pour chaque sous-conteneur interne */
        #divContainer > div {
            flex-basis: calc(50% - 10px); /* Chaque sous-conteneur occupe la moitié de la largeur avec un espace entre eux */
            margin-bottom: 10px;
        }

        /* Media query pour la réponse dynamique */
        @media screen and (max-width: 600px) {
            #divContainer > div {
                flex-basis: 100%; /* Chaque sous-conteneur occupe 100% de la largeur lorsque la largeur de l'écran est inférieure à 600px */
            }
        }


        /* Table styles */
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px; /* Add some spacing at the bottom of the table */
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 12px;
        }

        td {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        /* Service row styles */
        .service-row td {
            font-weight: bold;
        }

        /* Task row styles */
        .task-row td {
            background-color: #f9f9f9;
        }

        /* Add more styles as needed */



    </style>






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
                    <li class="menu-item active">
                        <a href="home" class="menu-link">
                            <i class="menu-icon tf-icons bx bx-home-circle"></i>
                            <div data-i18n="Analytics">Dashboard</div>
                        </a>
                    </li>

                    <li class="menu-item">
                        <a href="accountSetting" class="menu-link">
                            <i class="bx bx-cog me-2"></i>
                            <div data-i18n="Analytics">Account</div>
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
                                        id="searchInput"
                                        oninput="searchProjects()"
                                />
                            </div>
                        </div>


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
                                                    <span class="fw-semibold d-block">John Doe</span>
                                                    <small class="text-muted">Directeur</small>
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

                <!-- / Navbar -->

                <!-- Content wrapper -->
                <div class="content-wrapper">
                    <div>
                        <!-- Content -->
                        <% Projet projet = (Projet) request.getAttribute("projet"); %>
                        <div class="divContentWrapper">
                            <h2>Informations sur le Projet</h2>
                            <div id="divContainer">

                                <div>
                                    <p><strong>Nom du Projet:</strong><%=projet.getNom()%></p>
                                    <p><strong>Date de Démarrage:</strong> <%=projet.getDateDemarrage()%></p>
                                </div>

                                <div>
                                    <p><strong>Client du Projet:</strong> <%=projet.getNomClient()%></p>
                                    <p><strong>Date de Livraison:</strong><%=projet.getDateLiverison()%></p>
                                </div>
                            </div>
                            <p  style="margin-left: 8px; word-wrap: break-word;"><strong>Description du Projet:</strong> Description du projet ici </p>
                            <% if(projet.getMethodologie() != null && !projet.getMethodologie().trim().isEmpty()){ %>
                            <p style="margin-left: 8px;"> <strong>Methodologie:</strong> <%=projet.getMethodologie()%> </p>
                            <% } %>
                            <% if(projet.getDateRuenion() != null){ %>
                            <p style="margin-left: 8px;"> <strong>Date Ruenion:</strong> <%=projet.getDateRuenion()%> </p>
                            <% } %>
                        </div>
                        <script src="https://cdn.jsdelivr.net/gh/habibmhamadi/multi-select-tag@2.0.1/dist/js/multi-select-tag.js"></script>
                        <script>
                            //new MultiSelectTag('methodology')  // id
                            new MultiSelectTag('TechnologiesSelect')  // id
                            // new MultiSelectTag('DevelopersSelect')  // id
                        </script>

                        <div class="divContentWrapper">
                            <h2>Les services</h2>
                            <div>
                                <table>
                                    <%HashMap<Service,List<Tache>> serviceListHashMap = (HashMap<Service, List<Tache>>) request.getAttribute("ServicesTaches");
                                        int i = 0;
                                        if(serviceListHashMap != null){for(Map.Entry<Service, List<Tache>> entry : serviceListHashMap.entrySet()) {
                                            Service service = entry.getKey();
                                            List<Tache> taches = entry.getValue();

                                    %>
                                    <tr>
                                        <td style="padding-left : 10px">service  <%=i%> :  </td>
                                        <td style="padding-left : 10px"><%=service.getDescription()%></td>
                                        <td style="padding-left : 10px">duree : <%=service.getDuree()%></td>
                                    </tr>
                                    <% i++; int j =0;%>
                                    <% for(Tache tache : taches){ j++;%>
                                    <tr>
                                        <td style="padding-left : 60px; padding-right: 50px;">tache <%=j%> :  </td>
                                        <td style="padding-left : 60px"><%=tache.getDescription()%></td>
                                        <td style="padding-left : 60px">Avancement : <%=tache.getAvancement()%></td>
                                    </tr>
                                    <%}}}%>
                                </table>
                            </div>
                            <div>
                                <h3>Ajouter Tache</h3>
                                <form style="margin: 10px; display: flex;flex-direction: row;align-items: center; margin: 10px;justify-content: space-between" method="post" action="TacheServlet">
                                    <label>Discription</label>
                                    <input type="text" name="description">
                                    <label>avancement</label>
                                    <input type="number" name="avancement">
                                    <label>service :</label>
                                    <select id="selectedDevloper" name="selectedService">
                                        <% for(Map.Entry<Service, List<Tache>> entry : serviceListHashMap.entrySet()) {
                                            Service service = entry.getKey();
                                        %>
                                        <option value="<%=service.getId()%>"><%=service.getDescription()%></option>
                                        <% } %>
                                    </select>
                                    <input type="hidden" name="projet_id" value="<%=projet.getId()%>">
                                    <button type="submit" style="background-color: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer;" name="submitAddTache">Submit</button>
                                </form>
                            </div>


                        </div>







                    </div>
                    <!-- / Content -->
                </div>
                <!-- Content wrapper -->
            </div>
            <!-- / Layout page -->
        </div>

        <!-- Overlay -->
        <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->


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

    function searchProjects() {
        const searchInput = document.getElementById('searchInput');
        var inputValue = searchInput.value;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "search_project_chef?valueSearch=" + encodeURIComponent(inputValue), true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                updateTableContent(xhr.responseText);
            }
        };
        xhr.send();
    }

    function updateTableContent(responseText) {
        var Ttable = document.getElementById('tbodyTable');
        Ttable.innerHTML = responseText;
    }
</script>
</body>
</html>
