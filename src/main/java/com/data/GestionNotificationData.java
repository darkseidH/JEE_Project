package com.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionNotificationData implements I_GestionNotificationsData{
    Connection conn = MySqlConnection.openConnection();
    @Override
    public ResultSet getAllNotificationsUser(Long id) {
        ResultSet res = null;
        String req = "select * from Notification where userId = ?;";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setLong(1, id);
            res = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    @Override
    public void deleteNotification(Long id) {
        String sql = "DELETE FROM Notification WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les erreurs de manière appropriée dans votre application.
        }
    }
}
