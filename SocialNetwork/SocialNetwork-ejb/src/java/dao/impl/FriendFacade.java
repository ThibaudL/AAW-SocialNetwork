/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Friend;
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
        Query q = em.createQuery("SELECT f FROM Friend f WHERE f.user.id="+id + " AND f.valid=1");
        return q.getResultList();
    }

    @Override
    public void create(Friend entity) {
        super.create(entity); 
    }

    @Override
    public void validFriendship(Integer userId, Integer friendId) {
        Query q = em.createQuery("UPDATE Friend f SET f.valid = 1 WHERE (f.user.id="+userId+ " AND f.friend.id="+friendId+")"
                                                                        + " OR "
                                                                        + "(f.friend.id="+userId+ " AND f.user.id="+friendId+")"
        );  
        q.executeUpdate();
    }
    
    @Override
    public void removeFriendship(Integer userId, Integer friendId){
        Query q = em.createQuery("DELETE FROM Friend f WHERE (f.user.id="+userId+" AND f.friend.id="+friendId+")"
                                                        + " OR "
                                                        + "(f.friend.id="+userId+ " AND f.user.id="+friendId+")"
        
        );
        q.executeUpdate();
    }

    @Override
    public List<Friend> findWaitingInvit(Integer userId) {
        Query q = em.createQuery("SELECT f FROM Friend f WHERE (f.user.id="+userId
                                                                + " OR "
                                                                + "f.friend.id="+userId+")"
                                                                 +" AND f.valid = 0");
        return q.getResultList();
    }

    @Override
    public boolean areFriends(Integer userId, Integer friendId) {
        Query q = em.createQuery("SELECT f FROM Friend f WHERE (f.user.id="+userId+" AND f.friend.id="+friendId+") AND f.valid = 1");  
        return (q.getResultList().size()>0);
    }
    

    @Override
    public Friend find(Object id) {
        Query q = em.createQuery("SELECT f FROM Friend f WHERE f.friend.id="+id + " AND f.valid=1");
        List<Friend> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
        
    }
    
    
    

 
    
    
    
}
