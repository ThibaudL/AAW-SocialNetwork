/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Notification;
import dao.impl.NotificationFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Thibaud
 */
@Stateless
public class NotificationService implements NotificationServiceLocal{

    @EJB
    NotificationFacadeLocal notificationFacade;
    
    @Override
    public List<Notification> getNotifications(Integer userId) {
        return notificationFacade.findByUserId(userId);
    }
    
}
