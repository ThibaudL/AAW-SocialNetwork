/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Album;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thibaud
 */
@Local
public interface AlbumFacadeLocal {

    void create(Album album);

    void edit(Album album);

    void remove(Album album);

    Album find(Object id);

    List<Album> findAll();

    List<Album> findRange(int[] range);

    int count();
    
    List<Album> findByUserId(Integer id);
    
    Album findAlbumByUser(Integer id, Integer userId);
    
    void removeAlbum(Integer id, Integer userId);
}
