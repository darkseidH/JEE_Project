package com.buisness;

import com.data.GestionTacheData;
import com.data.I_GestionTacheData;
import com.presentation.model.Tache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetionTache implements I_GestionTache{
    I_GestionTacheData gestionTacheData = new GestionTacheData();
    @Override
    public void addTache(Tache tache) {
       gestionTacheData.addTache(tache);
    }

    @Override
    public List<Tache> getAllTacheService(Long service_id) throws SQLException {
        List<Tache> taches = new ArrayList<>();
        ResultSet resultSet = gestionTacheData.getAllTacheService(service_id);
        if (resultSet == null) return null;
        while(resultSet.next()){
              Tache tache = new Tache();
              tache.setId(resultSet.getLong("id"));
              tache.setService_id(resultSet.getLong("service_id"));
              tache.setDescription(resultSet.getString("description"));
        }
        return taches;
    }
}
