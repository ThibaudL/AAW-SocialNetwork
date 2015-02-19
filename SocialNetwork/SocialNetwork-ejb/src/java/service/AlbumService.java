/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Album;
import dao.impl.AlbumFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Denis
 */
@Stateless
public class AlbumService implements AlbumServiceLocal {

    @EJB
    AlbumFacadeLocal albumFacade;
    @EJB
    UserFacadeLocal userFacade;
    
    @Override
    public void createAlbum(String name, Integer userId) {
        Album album = new Album();
        album.setTitle(name);
        album.setAuthor(userFacade.find(userId));
        albumFacade.create(album);
    }

    @Override
    public Album viewAlbum(Integer id, Integer userId) {
        return albumFacade.findAlbumByUser(id, userId);
    }

    @Override
    public List<Album> getAlbums(Integer userId) {
        return albumFacade.findByUserId(userId);
    }

    @Override
    public void removeAlbum(Integer albumId, Integer userId) {
        albumFacade.removeAlbum(albumId, userId);
    }
    
    

}
