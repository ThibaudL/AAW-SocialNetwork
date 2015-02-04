/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Profile;
import javax.ejb.Local;

/**
 *
 * @author Thibaud
 */
@Local
public interface ProfileServiceLocal {
    
    public String getInformation();
    public void setInformation(String information);

    public void editProfile(Profile profile);
    public Profile getProfile(Integer userId);
}
