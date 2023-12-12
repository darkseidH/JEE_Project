package com.buisness;

import com.presentation.model.Competence;

import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_Competence {
    List<Competence> getAllCompetenceDeveloper(Long id) throws SQLException;
    void addCompetenceDeveloper(Competence competence);
}
