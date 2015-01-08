/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Denis
 */
@Stateless
public class SocialNetWorkDAO implements SocialNetWorkDAOLocal {

    @PersistenceContext(unitName="SocialNetwork-ejbPU",type=PersistenceContextType.TRANSACTION)
    private EntityManager em; 

}
