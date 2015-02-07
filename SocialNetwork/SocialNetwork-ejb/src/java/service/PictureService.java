/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Picture;
import dao.impl.AlbumFacadeLocal;
import dao.impl.PictureFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Denis
 */
@Stateful
public class PictureService implements PictureServiceLocal {
    @EJB
    PictureFacadeLocal pictureFacade;
    @EJB
    AlbumFacadeLocal albumFacade;
    
    @Override
    public void createPicture(Integer albumId, byte[] content) {
        Picture p = new Picture();
        p.setAlbum(albumFacade.find(albumId));
        p.setContent(content);
        pictureFacade.create(p);
    }

    @Override
    public Picture findPicture(Integer id) {
        return pictureFacade.find(id);
    }

}
