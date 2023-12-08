package com.data;

import com.presentation.model.Tache;

import java.sql.ResultSet;

public interface I_GestionTacheData {
    public void addTache(Tache tache);
    public ResultSet getAllTacheService(Long service_id);
}
