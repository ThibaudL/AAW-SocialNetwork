/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Notification;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface NotificationFacadeLocal {

    void create(Notification notification);

    void edit(Notification notification);

    void remove(Notification notification);

    Notification find(Object id);

    List<Notification> findByUserId(Integer userId);
    
    List<Notification> findAll();

    List<Notification> findRange(int[] range);

    int count();
    
}
