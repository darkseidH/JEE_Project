package com.data;

import com.presentation.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionServiceData implements I_GestionServiceData{
    Connection conn = MySqlConnection.openConnection();
    @Override
    public void addService(Service service) {
        String req = "insert into Service (description,duree,developer_id,projet_id) values (?,?,?,?);";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, service.getDescription());
            st.setLong(2, service.getDuree());
            st.setLong(3,service.getDeveloperId());
            st.setLong(4,service.getProjetId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet getAllServiceByIdProject(Long ProjectId) {
        ResultSet res = null;
        String req = "select * from service where  projet_id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, ProjectId);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}