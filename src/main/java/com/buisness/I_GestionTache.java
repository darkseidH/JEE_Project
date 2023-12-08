package com.buisness;

import com.presentation.model.Service;
import com.presentation.model.Tache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface I_GestionTache {
    public void addTache(Tache tache);
    public List<Tache> getAllTacheService(Long service_id) throws SQLException;
}
