/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.PrivateMessage;
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
public class PrivateMessageFacade extends AbstractFacade<PrivateMessage> implements PrivateMessageFacadeLocal {
    @PersistenceContext(unitName = "SocialNetwork-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivateMessageFacade() {
        super(PrivateMessage.class);
    }
    
    @Override
    public List<PrivateMessage> findByAuthorId(Integer id) {
        Query q = em.createQuery("SELECT pm FROM PrivateMessage pm WHERE pm.author.id="+id);
        return q.getResultList();
    }
    
    @Override
    public PrivateMessage existConversation(Integer userId, Integer userId2) {
        Query q = em.createQuery("SELECT pm FROM PrivateMessage pm WHERE (pm.author.id="+userId + " AND pm.destinataire.id=" +userId2+") OR (pm.author.id="+userId2 + " AND pm.destinataire.id=" +userId+")" );
        List<PrivateMessage> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }
    
    @Override
    public List<PrivateMessage> findConversation(Integer userId) {
        Query q = em.createQuery("SELECT pm FROM PrivateMessage pm WHERE (pm.author.id="+userId + ") OR (pm.destinataire.id=" +userId+")" );
        return q.getResultList();
    }
    
    public PrivateMessage getConversationById(Integer msgId, Integer userId){
        Query q = em.createQuery("SELECT pm FROM PrivateMessage pm WHERE ((pm.author.id="+userId + ") OR (pm.destinataire.id=" +userId+")) AND pm.id="+msgId );
        List<PrivateMessage> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }
}
