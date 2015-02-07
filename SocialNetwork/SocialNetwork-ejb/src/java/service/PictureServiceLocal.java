/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Picture;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface PictureServiceLocal {
    void createPicture(Integer albumId, byte[] content);
    Picture findPicture(Integer id);
}
