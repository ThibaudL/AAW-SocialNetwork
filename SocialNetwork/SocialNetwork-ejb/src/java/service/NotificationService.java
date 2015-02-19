/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Notification;
import dao.impl.NotificationFacadeLocal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Thibaud
 */
@LocalBean
@Path("notifications")
@Stateless
public class NotificationService implements NotificationServiceLocal{

    @EJB
    NotificationFacadeLocal notificationFacade;
    
    @Override
    public List<Notification> getNotifications(Integer userId) {
        List<Notification> nots = notificationFacade.findUnreadedByUserId(userId);

        Collections.sort(nots, new Comparator<Notification>() {
            @Override
            public int compare(Notification o1, Notification o2) {
                return -(o1.getDate().compareTo(o2.getDate()));                  
            }
        }); 
        return nots;
    }
    
    @GET
    @Path("setToReaded/{notificationId}")
    @Produces("text/plain") 
    public String setNotificationToReaded(@PathParam("notificationId") Integer notificationId){
        Notification not = notificationFacade.find(notificationId);
        not.setRead(true);
        notificationFacade.edit(not);
        return not.getLink();
    }
    
}
