package com.buisness;

import com.data.GestionTechnologieData;
import com.data.I_Gestion_TechnologieData;
import com.presentation.model.Technologie;
import com.presentation.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionTechnologie implements I_Gestion_technologie {
    I_Gestion_TechnologieData gestionTechnologieData = new GestionTechnologieData();
    I_Gestion_User gestionUser = new GestionUser();

    @Override
    public void addTechnologie(Technologie technologie) {
        gestionTechnologieData.addTechnologie(technologie);
    }


    @Override
    public Map<Technologie, List<User>> getTechnologieAndDevelopersByProjectId(long l) throws SQLException {
        Map<Technologie, List<User>> technologieListMap = new HashMap<>();
        List<Technologie> technologieList = getTechnologiesProjet(l);
        if(technologieList != null){
            for (Technologie technologie : technologieList) {
                List<User> userList = getDevelopersByTechnologie(technologie.getId());
                technologieListMap.put(technologie, userList);
            }
        }
        return technologieListMap;
    }
    @Override
    public List<User> getDevelopersByTechnologie(Long id) throws SQLException {
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = gestionTechnologieData.getDevloperTechnologieProjet(id);
        if (resultSet == null) return null;
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLast_name(resultSet.getString("last_name"));
            user.setFirst_name(resultSet.getString("first_name"));
            user.setId(resultSet.getLong("id"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<Technologie> getTechnologiesNProjet(Long projectId) {
        List<Technologie> technologieList = new ArrayList<>();
        ResultSet resultSet = gestionTechnologieData.getTechnologiesNProjet(projectId);
        if (resultSet == null) return null;
        try {
            while (resultSet.next()) {
                Technologie technologie = new Technologie();
                technologie.setNom(resultSet.getString("nom"));
                technologieList.add(technologie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return technologieList;
    }

    @Override
    public Map<Technologie, List<User>> getTechnologieAndDevelopersNByProjectId(Long projectId) throws SQLException {
        Map<Technologie, List<User>> technologieListMap = new HashMap<>();
        List<Technologie> technologieList = getTechnologiesProjet(projectId);
        if(technologieList != null){
            for (Technologie technologie : technologieList) {
                List<User> userList = getDevelopersByTechnologieNProjet(technologie.getId());
                technologieListMap.put(technologie, userList);
            }
        }
        return technologieListMap;
    }

    private List<User> getDevelopersByTechnologieNProjet(Long id) throws SQLException {
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = gestionTechnologieData.getDevloperTechnologieNProjet(id);
        System.out.println("getDevelopersByTechnologieNProjet");
        if (resultSet == null) return null;
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLast_name(resultSet.getString("last_name"));
            user.setFirst_name(resultSet.getString("first_name"));
            user.setId(resultSet.getLong("id"));
            userList.add(user);
            System.out.println(user.getFirst_name()   + "   "+ id);
        }
        return userList;
    }

    @Override
    public List<Technologie> getTechnologiesProjet(long l) throws SQLException {
        List<Technologie> technologieList = new ArrayList<>();
        ResultSet resultSet = gestionTechnologieData.getTechnologiesProjet(l);
        if (resultSet == null) return null;
        while (resultSet.next()) {
            Technologie technologie = new Technologie();
            technologie.setNom(resultSet.getString("nom"));
            technologie.setId(resultSet.getLong("id"));
            technologie.setProjetId(resultSet.getLong("projet_id"));
            technologieList.add(technologie);
        }
        return technologieList;
    }
}
