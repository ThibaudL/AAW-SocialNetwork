/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.SocialNetwork;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface SocialNetworkFacadeLocal {

    void create(SocialNetwork socialNetwork);

    void edit(SocialNetwork socialNetwork);

    void remove(SocialNetwork socialNetwork);

    SocialNetwork find(Object id);

    List<SocialNetwork> findAll();

    List<SocialNetwork> findRange(int[] range);

    int count();
    
}
