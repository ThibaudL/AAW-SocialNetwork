/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.PublicMessage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface PublicMessageFacadeLocal {

    void create(PublicMessage publicMessage);

    void edit(PublicMessage publicMessage);

    void remove(PublicMessage publicMessage);

    PublicMessage find(Object id);
    
    List<PublicMessage> findByAuthorId(Object id);

    List<PublicMessage> findAll();

    List<PublicMessage> findRange(int[] range);

    int count();
    
}
