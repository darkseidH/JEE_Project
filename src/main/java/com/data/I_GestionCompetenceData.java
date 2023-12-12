package com.data;

import com.presentation.model.Competence;

import java.sql.ResultSet;

public interface I_GestionCompetenceData {
    ResultSet getAllCompetenceDeveloper(Long id);
    void addCompetence(Competence competence);
}
