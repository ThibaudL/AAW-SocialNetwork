/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Profile;
import dao.entity.User;
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
public class ProfileFacade extends AbstractFacade<Profile> implements ProfileFacadeLocal {
    @PersistenceContext(unitName = "SocialNetwork-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfileFacade() {
        super(Profile.class);
    }

    @Override
    public Profile findByUserId(Integer id) {
        Query q = em.createQuery("SELECT p FROM Profile p WHERE p.user.id="+id);
        List<Profile> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }
    
    
    @Override
    public List<Profile> searchProfile(String nameStartWith) {
         Query q = em.createQuery("SELECT p FROM Profile p WHERE LOWER(p.firstname) LIKE LOWER('"+ nameStartWith+"%') OR LOWER(p.lastname) LIKE LOWER('"+ nameStartWith+"%')");
         return q.getResultList();
    }
}
