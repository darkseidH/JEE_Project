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
    public List<Projet> findAllProjetsByEmailDev(String email) {
        ResultSet res = gestionProjetsData.findAllProjetsByEmailDevData(email);
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

    @Override
    public Projet getProjetById(long l) {
        return gestionProjetsData.getProjetByIdData(l);
    }

    @Override
    public void addMethodologie(String methodologie, Long projetId) {
        gestionProjetsData.addMethodologieData(methodologie, projetId);
    }

    @Override
    public void addDateReunion(String dateReunion, Long projetId) {
        gestionProjetsData.addDateReunionData(dateReunion, projetId);
    }

    @Override
    public List<User> getDevlopersProjet(Long projetId) throws SQLException {
        List<User> devlopersProjet = new ArrayList<>();
        ResultSet resultSet = gestionProjetsData.getDevlopersProjet(projetId);
        if (resultSet == null) return null;
        while(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirst_name(resultSet.getString("first_name"));
            user.setLast_name(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            devlopersProjet.add(user);
        }
        return devlopersProjet;
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
        return projetsChef;
    }

    public HashMap<Projet, User> mapProjectsNameToDirector(String name) {
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

        return projetsChef;
    }

    public HashMap<Projet, User> mapProjectsNameToChef(String name,String email) {
        GestionProjets gestionProjets = new GestionProjets();
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        projets = gestionProjets.findAllProjetsByStartNameTochef(name,email);
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
        return projetsChef;
    }

    public HashMap<Projet, User> mapProjectsNameToDev(String name,String email) {
        GestionProjets gestionProjets = new GestionProjets();
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        projets = gestionProjets.findAllProjetsByStartNameToDev(name,email);
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
        return projetsChef;
    }

    private List<Projet> findAllProjetsByStartNameTochef(String name, String email) {
        ResultSet res = gestionProjetsData.findAllProjetsByStartNameTochefData(name,email);
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

    private List<Projet> findAllProjetsByStartNameToDev(String name, String email) {
        ResultSet res = gestionProjetsData.findAllProjetsByStartNameToDevloperData(name,email);
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

    public HashMap<Projet, User> mapProjectsNameToDevloper(String name, String email) {
        GestionProjets gestionProjets = new GestionProjets();
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        projets = gestionProjets.findAllProjetsByStartNameToDevloper(name,email);
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
        return projetsChef;
    }

    private List<Projet> findAllProjetsByStartNameToDevloper(String name, String email) {
        ResultSet res = gestionProjetsData.findAllProjetsByStartNameToDevloperData(name,email);
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
        return projetsChef;
    }
    public HashMap<Projet, User> mapDevProjets(String email) {
        GestionProjets gestionProjets = new GestionProjets();
        GestionUser gestionUser = new GestionUser();
        List<Projet> projets;
        HashMap<Projet, User> projetsChef;
        projets = gestionProjets.findAllProjetsByEmailDev(email);
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
        return projetsChef;
    }

    public Projet findProjetByName(String name) throws SQLException {
        Projet projet = new Projet();
        ResultSet res = gestionProjetsData.findProjetByNameData(name);
        while (res.next()){
            projet.setId(res.getLong("id"));
            projet.setNom(res.getString("nom"));
            projet.setNomClient(res.getString("nomClient"));
            projet.setChefProjet_id(res.getLong("chefProjet_id"));
            projet.setNombreJourDeveloppement(res.getInt("nombreJourDeveloppement"));
            projet.setDateDemarrage(res.getDate("dateDemarrage"));
            projet.setDateLiverison(res.getDate("dateLiverison"));
            projet.setDescription(res.getString("description"));
        }
        if (projet.getId() == 0)
            return null;
        return projet;
    }
}
