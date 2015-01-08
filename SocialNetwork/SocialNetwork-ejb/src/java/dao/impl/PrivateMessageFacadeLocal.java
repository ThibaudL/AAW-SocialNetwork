/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.PrivateMessage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface PrivateMessageFacadeLocal {

    void create(PrivateMessage privateMessage);

    void edit(PrivateMessage privateMessage);

    void remove(PrivateMessage privateMessage);

    PrivateMessage find(Object id);

    List<PrivateMessage> findAll();

    List<PrivateMessage> findRange(int[] range);

    int count();
    
}
