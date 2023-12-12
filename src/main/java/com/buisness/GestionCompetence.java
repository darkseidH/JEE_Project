package com.buisness;

import com.data.GestionCompetenceData;
import com.data.I_GestionCompetenceData;
import com.presentation.model.Competence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionCompetence implements I_Gestion_Competence{
    I_GestionCompetenceData gestionCompetenceData = new GestionCompetenceData();
    @Override
    public List<Competence> getAllCompetenceDeveloper(Long id) throws SQLException {
        List<Competence> competences = new ArrayList<>();
        ResultSet resultSet = gestionCompetenceData.getAllCompetenceDeveloper(id);
        if(resultSet == null) return  null;
        while (resultSet.next()){
            Competence competence =new Competence();
            competence.setNom(resultSet.getString("nom"));
            competence.setDeveloper_id(resultSet.getLong("developer_id"));
            competence.setId(resultSet.getLong("id"));
            competences.add(competence);
        }
        return competences;
    }

    @Override
    public void addCompetenceDeveloper(Competence competence) {
      gestionCompetenceData.addCompetence(competence);
    }
}
