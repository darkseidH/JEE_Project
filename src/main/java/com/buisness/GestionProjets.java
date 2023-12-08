package com.buisness;

import com.data.GestionProjetsData;
import com.presentation.model.Projet;
import com.presentation.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionProjets implements I_Gestion_Projet {
    GestionProjetsData gestionProjetsData = new GestionProjetsData();
    GestionUser gestionUser = new GestionUser();

    @Override
    public Projet addProjet(Projet projet) {
        return gestionProjetsData.addProjetData(projet);
    }

    @Override
    public Projet updateProjet(Projet projet) {
        return gestionProjetsData.updateProjetData(projet);
    }

    @Override
    public boolean deleteProjet(Projet projet) {
        return gestionProjetsData.deleteProjetData(projet);
    }

    @Override
    public List<Projet> findAllProjets() throws SQLException {
        ResultSet res = gestionProjetsData.findAllProjetsData();
        ArrayList<Projet> projets = new ArrayList<>();
        while (res.next()) {
            Projet projet = new Projet();
            projet.setId(res.getLong("id"));
            projet.setNom(res.getString("nom"));
            projet.setNomClient(res.getString("nomClient"));
            projet.setChefProjet_id(res.getLong("chefProjet_id"));
            projet.setNombreJourDeveloppement(res.getInt("nombreJourDeveloppement"));
            projet.setDateDemarrage(res.getDate("dateDemarrage"));
            projet.setDateLiverison(res.getDate("dateLiverison"));
            projet.setDescription(res.getString("description"));
            projets.add(projet);
        }
        return projets;

    }


    public List<Projet> findAllProjetsByEmailChef(String email) {
        ResultSet res = gestionProjetsData.findAllProjetsByEmailChefData(email);
        ArrayList<Projet> projets = new ArrayList<>();
        try {
            while (res.next()) {
                Projet projet = new Projet();
                projet.setId(res.getLong("id"));
                projet.setNom(res.getString("nom"));
                projet.setNomClient(res.getString("nomClient"));
                projet.setChefProjet_id(res.getLong("chefProjet_id"));
                projet.setNombreJourDeveloppement(res.getInt("nombreJourDeveloppement"));
                projet.setDateDemarrage(res.getDate("dateDemarrage"));
                projet.setDateLiverison(res.getDate("dateLiverison"));
                projet.setDescription(res.getString("description"));
                projets.add(projet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projets;
    }


    public List<Projet> findAllProjetsByStartName(String valueSearch) {
        ResultSet res = gestionProjetsData.findAllProjetsByStartNameData(valueSearch);
        ArrayList<Projet> projets = new ArrayList<>();
        try {
            while (res.next()) {
                Projet projet = new Projet();
                projet.setId(res.getLong("id"));
                projet.setNom(res.getString("nom"));
                projet.setNomClient(res.getString("nomClient"));
                projet.setChefProjet_id(res.getLong("chefProjet_id"));
                projet.setNombreJourDeveloppement(res.getInt("nombreJourDeveloppement"));
                projet.setDateDemarrage(res.getDate("dateDemarrage"));
                projet.setDateLiverison(res.getDate("dateLiverison"));
                projet.setDescription(res.getString("description"));
                projets.add(projet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projets;
    }

    public HashMap<Projet, User> mapProjectsToChef() {
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        try {
            projets = findAllProjets();
            projetsChef = projets.stream().collect(
                    HashMap::new,
                    (map, projet) -> {
                        try {
                            User user;
                            user = gestionUser.findUserWithId(projet.getChefProjet_id());
                            map.put(projet, user);
                        } catch (SQLException e) {
                            throw new RuntimeException("Error while processing project: " + projet.getId(), e);
                        }
                    },
                    HashMap::putAll
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        projetsChef.forEach((projet, user) -> System.out.println(projet + " " + user));
        return projetsChef;
    }

    public Projet findProjetByName(String name) {
        ResultSet res = gestionProjetsData.findProjetByNameData(name);
        Projet projet = new Projet();
        try {
            while (res.next()) {
                projet.setId(res.getLong("id"));
                projet.setNom(res.getString("nom"));
                projet.setNomClient(res.getString("nomClient"));
                projet.setChefProjet_id(res.getLong("chefProjet_id"));
                projet.setNombreJourDeveloppement(res.getInt("nombreJourDeveloppement"));
                projet.setDateDemarrage(res.getDate("dateDemarrage"));
                projet.setDateLiverison(res.getDate("dateLiverison"));
                projet.setDescription(res.getString("description"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projet;
    }


    public HashMap<Projet, User> mapProjectsNameToChef(String name) {
        GestionProjets gestionProjets = new GestionProjets();
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        projets = gestionProjets.findAllProjetsByStartName(name);
        projetsChef = projets.stream().collect(
                HashMap::new,
                (map, projet) -> {
                    try {
                        User user;
                        user = gestionUser.findUserWithId(projet.getChefProjet_id());
                        map.put(projet, user);
                    } catch (SQLException e) {
                        throw new RuntimeException("Error while processing project: " + projet.getId(), e);
                    }
                },
                HashMap::putAll
        );
        projetsChef.forEach((projet, user) -> System.out.println(projet + " " + user));
        return projetsChef;
    }


    public HashMap<Projet, User> mapChefProjets(String email) {
        GestionProjets gestionProjets = new GestionProjets();
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        projets = gestionProjets.findAllProjetsByEmailChef(email);
        projetsChef = projets.stream().collect(
                HashMap::new,
                (map, projet) -> {
                    try {
                        User user;
                        user = gestionUser.findUserWithId(projet.getChefProjet_id());
                        map.put(projet, user);
                    } catch (SQLException e) {
                        throw new RuntimeException("Error while processing project: " + projet.getId(), e);
                    }
                },
                HashMap::putAll
        );
        projetsChef.forEach((projet, user) -> System.out.println(projet + " " + user));
        return projetsChef;
    }
}
