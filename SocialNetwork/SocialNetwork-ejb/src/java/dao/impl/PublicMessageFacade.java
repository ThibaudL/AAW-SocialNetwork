/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Profile;
import dao.entity.PublicMessage;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Denis
 */
@Stateless
public class PublicMessageFacade extends AbstractFacade<PublicMessage> implements PublicMessageFacadeLocal {
    @PersistenceContext(unitName = "SocialNetwork-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicMessageFacade() {
        super(PublicMessage.class);
    }

    @Override
    public List<PublicMessage> findByAuthorId(Object id) {
        Query q = em.createQuery("SELECT pm FROM PublicMessage pm WHERE pm.author.id="+id);
        return q.getResultList();
    }
    
}
