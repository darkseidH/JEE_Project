package com.buisness;

import com.presentation.model.Service;
import com.presentation.model.Tache;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface I_GestionService {
    public void addService(Service service);
    public List<Service> getAllService(Long projet_id) throws SQLException;

    List<Service> getAllServiceDeveloperProject(Long projet_id,String email) throws SQLException;

    public HashMap <Service, List<Tache>> getAllServiceTache(Long projet_id) throws SQLException;
    public HashMap <Service, List<Tache>> getAllServiceTacheDeveloperProject(Long projet_id,String email) throws SQLException;

}
