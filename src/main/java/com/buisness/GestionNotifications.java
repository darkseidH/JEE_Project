package com.buisness;

import com.data.GestionNotificationData;
import com.data.I_GestionNotificationsData;
import com.presentation.model.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionNotifications implements I_Gestion_Notifications{
    I_GestionNotificationsData gestionNotificationsData = new GestionNotificationData();
    @Override
    public List<Notification> getAllNotificationsUser(Long id) throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        ResultSet resultSet = gestionNotificationsData.getAllNotificationsUser(id);
        if (resultSet == null) return null;
        while (resultSet.next()){
            Notification notification = new Notification();
            notification.setId(resultSet.getLong("id"));
            notification.setContenu(resultSet.getString("contenu"));
            notification.setProjetId(resultSet.getLong("projetId"));
            notification.setUserId(resultSet.getLong("userId"));
            notifications.add(notification);
        }
        return notifications;
    }

    @Override
    public void deleteNotification(Long id) {
      gestionNotificationsData.deleteNotification(id);
    }
}
