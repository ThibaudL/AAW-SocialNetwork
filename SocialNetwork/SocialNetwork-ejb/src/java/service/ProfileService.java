/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Profile;
import dao.impl.ProfileFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Thibaud
 */
@Stateful
public class ProfileService implements ProfileServiceLocal {

    private Profile currentProfile;
    
    @EJB
    private ProfileFacadeLocal profileFacade;
    @EJB 
    private UserFacadeLocal userFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String getInformation() {
        //return userService.getProfile().getInformation();
        return currentProfile != null ? currentProfile.getInformation() : "";
    }

    public void setInformation(String information) {
        currentProfile.setInformation(information);
        profileFacade.edit(currentProfile);
    }

    @Override
    public void loadProfile(Integer id) {
        if(id != null)
            currentProfile = profileFacade.find(id);
        else
            currentProfile = new Profile();
    }

    @Override
    public void createProfile(String firstname, String lastname, String information, String profilePicture, Long birthdayTimestamp, Integer userId) {
        currentProfile = new Profile();
        currentProfile.setBirthdate(new Date());
        currentProfile.setFirstname(firstname);
        currentProfile.setLastname(lastname);
        currentProfile.setInformation(information);
        currentProfile.setPicture(profilePicture);
        currentProfile.setUser(userFacade.find(userId));
    }
}
