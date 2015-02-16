/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Album;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Thibaud
 */
@Stateless
public class AlbumFacade extends AbstractFacade<Album> implements AlbumFacadeLocal {
    @PersistenceContext(unitName = "SocialNetwork-ejbPU")
    private EntityManager em;

    @Override 
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlbumFacade() {
        super(Album.class);
    }
    
    @Override
    public List<Album> findByUserId(Integer id) {
        Query q = em.createQuery("SELECT a FROM Album a WHERE a.author.id="+id);
        return q.getResultList();
    }
    
    @Override
    public Album findAlbumByUser(Integer id, Integer userId) {
        Query q = em.createQuery("SELECT a FROM Album a WHERE a.id="+id+" AND a.author.id="+userId);
        //q.setHint("javax.persistence.cache.storeMode", "REFRESH");
        List<Album> l = q.getResultList();
        return l.size() > 0 ? l.get(0) : null;
    }
    
    @Override
    public void removeAlbum(Integer id, Integer userId) {
        Query q;
        q = em.createQuery("DELETE FROM Picture p WHERE p.album.id="+id);
        q.executeUpdate();
        
        q = em.createQuery("DELETE FROM Album a WHERE a.id="+id+" AND a.author.id="+userId);
        q.executeUpdate();
    }
    
}
