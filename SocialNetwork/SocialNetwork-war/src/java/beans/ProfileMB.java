/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Profile;
import dao.entity.User;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import service.ProfileServiceLocal;
import service.UserServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "ProfileMB")
@SessionScoped
public class ProfileMB implements Serializable{

    private Profile profile;
    
    private String birthdayString;
    private Part profilePictureFile;
    
    
    @EJB
    ProfileServiceLocal profileService;
    @EJB
    UserServiceLocal userServiceLocal;
    
    /**
     * Creates a new instance of ProfileBean
     */ 
    public ProfileMB() {
        profile = new Profile();
    }

    public String getBirthdayString() {
        return birthdayString;
    }

    public Part getProfilePictureFile() {
        return profilePictureFile;
    }

    public void setProfilePictureFile(Part profilePictureFile) {
        this.profilePictureFile = profilePictureFile;
    }
    
    

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
        try {
            this.profile.setBirthdate(DateFormat.getDateInstance().parse(birthdayString));
        } catch (ParseException ex) {
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String editProfile(){
        Integer userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        User user =userServiceLocal.getUser(userId);
        profile.setId(user.getProfile().getId());
        profile.setUser(user);
        upload();
        profileService.editProfile(profile);
        return "home.xhtml";
    }
    
    private void upload(){
        try {
            if(profilePictureFile != null)
                profile.setPicture(new Scanner(profilePictureFile.getInputStream())
                    .useDelimiter("\\A").next().getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    

    
    
}
