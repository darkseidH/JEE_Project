package com.data;

import com.presentation.model.Technologie;

import java.sql.ResultSet;

public interface I_Gestion_TechnologieData {
    void addTechnologie(Technologie technologie);

    ResultSet getTechnologiesProjet(long l);

    ResultSet getDevloperTechnologieProjet(Long id);

    ResultSet getTechnologiesNProjet(Long projectId);

    ResultSet getDevloperTechnologieNProjet(Long id);

    void addDeveloperTechnologieProjet(Long technologieId, Long developerId);

    ResultSet afficheInputDateRuenion(Long projectId);
}
