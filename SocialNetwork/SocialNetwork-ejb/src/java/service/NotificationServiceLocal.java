/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Notification;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author Thibaud
 */
@Local
public interface NotificationServiceLocal {

    public List<Notification> getNotifications(Integer userId);
    public String setNotificationToReaded(Integer notId);
}
