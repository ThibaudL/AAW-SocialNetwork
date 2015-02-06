/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Friend;
import dao.entity.Profile;
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
public class FriendFacade extends AbstractFacade<Friend> implements FriendFacadeLocal {
    @PersistenceContext(unitName = "SocialNetwork-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FriendFacade() {
        super(Friend.class);
    }
    
    @Override
    public List<Friend> findByUserId(Integer id) {
        Query q = em.createQuery("SELECT f FROM Friend f WHERE f.user.id="+id);
        return q.getResultList();
    }

    @Override
    public void create(Friend entity) {
        super.create(entity); 
    }

    @Override
    public void addValidFriend(Integer userId, Integer friendId) {
        Query q = em.createQuery("UPDATE Friend f SET f.valid = 0 WHERE f.user.id="+friendId+ " AND f.friend.id="+userId);  
    }
    
    
    

 
    
    
    
}
