/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Album;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface AlbumServiceLocal {
    void createAlbum(String name, Integer userId);
    Album viewAlbum(Integer id, Integer userId);
    List<Album> getAlbums(Integer userId);
    
}
