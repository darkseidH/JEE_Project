package com.data;

import java.sql.ResultSet;

public interface I_GestionNotificationsData {
    ResultSet getAllNotificationsUser(Long id);
    void deleteNotification(Long id);
}
