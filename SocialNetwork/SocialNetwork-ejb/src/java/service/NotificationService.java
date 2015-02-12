/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Notification;
import dao.entity.PublicMessage;
import dao.impl.NotificationFacadeLocal;
import java.util.Collections;
import java.util.Comparator;
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
        List<Notification> nots = notificationFacade.findByUserId(userId);

        Collections.sort(nots, new Comparator<Notification>() {
            @Override
            public int compare(Notification o1, Notification o2) {
                return -(o1.getDate().compareTo(o2.getDate()));                  
            }
        }); 
        return nots;
    }
    
    public void setNotificationToReaded(Integer notId){
        Notification not = notificationFacade.find(notId);
        not.setRead(true);
        notificationFacade.edit(not);
    }
    
}
