package com.buisness;

import com.data.GestionServiceData;
import com.data.I_GestionServiceData;
import com.presentation.model.Service;
import com.presentation.model.Tache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestionService implements I_GestionService{
    I_GestionServiceData gestionServiceData = new GestionServiceData();
    I_GestionTache gestionTache = new GetionTache();
    @Override
    public void addService(Service service) {
        gestionServiceData.addService(service);
    }

    @Override
    public List<Service> getAllService(Long projet_id) throws SQLException {
        List<Service> services = new ArrayList<>();
        ResultSet resultSet = gestionServiceData.getAllServiceByIdProject(projet_id);
        if (resultSet == null) return null;
        while(resultSet.next()){
            Service service = new Service();
            service.setId(resultSet.getLong("id"));
            service.setDuree(resultSet.getInt("duree"));
            service.setDescription(resultSet.getString("description"));
            service.setProjetId(resultSet.getInt("projet_id"));
            service.setDeveloperId(resultSet.getInt("developer_id"));
            services.add(service);
        }
        return services;
    }

    @Override
    public HashMap<Service, List<Tache>> getAllServiceTache(Long projet_id) throws SQLException {
        HashMap<Service,List<Tache>> serviceTacheHashMap = new HashMap<>();
        List<Service> services = getAllService(projet_id);
        if(services == null){
            return null;
        }else{
            for(Service service : services){
                List<Tache> taches = gestionTache.getAllTacheService(service.getId());
                serviceTacheHashMap.put(service,taches);
            }
            return serviceTacheHashMap;
        }
    }


}
