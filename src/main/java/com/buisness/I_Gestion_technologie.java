package com.buisness;

import com.presentation.model.Technologie;
import com.presentation.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface I_Gestion_technologie {
    void addTechnologie(Technologie technologie);

    Map<Technologie, List<User>> getTechnologieAndDevelopersByProjectId(long l) throws SQLException;
    List<Technologie> getTechnologiesProjet(long l) throws SQLException;
    List<User> getDevelopersByTechnologie(Long id) throws SQLException;

    List<Technologie> getTechnologiesNProjet(Long projectId);

    Map<Technologie, List<User>> getTechnologieAndDevelopersNByProjectId(Long projectId) throws SQLException;
}
