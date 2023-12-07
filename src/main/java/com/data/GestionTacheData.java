package com.data;

import com.presentation.model.Tache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionTacheData implements I_GestionTacheData{
    Connection conn = MySqlConnection.openConnection();
    @Override
    public void addTache(Tache tache) {
        String req = "insert into tache (avancement,discription,service_id) values (?,?,?);";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, tache.getAvancement());
            st.setString(2, tache.getDescription());
            st.setLong(3,tache.getService_id());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet getAllTacheService(Long service_id) {
        ResultSet res = null;
        String req = "select * from tache where  service_id = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, service_id);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
