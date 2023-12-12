package com.buisness;

import com.presentation.model.Notification;

import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_Notifications {
    List<Notification> getAllNotificationsUser(Long id) throws SQLException;
    void deleteNotification(Long id);
}
