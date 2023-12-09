package com.data;

import com.presentation.model.Service;

import java.sql.ResultSet;

public interface I_GestionServiceData {
   public void  addService(Service service);
   public ResultSet getAllServiceByIdProject(Long ProjectId);

   ResultSet getAllServiceByIdProjectDeveloper(Long projetId, String email);
}
