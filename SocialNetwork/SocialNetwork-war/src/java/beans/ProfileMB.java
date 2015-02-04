/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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

    private String firstname;
    private String lastname;
    private String information;
    private String profilePicture;
    private Long birthdayTimestamp;
    private String birthdayString;
    private Part profilePictureFile;

    
    @EJB
    ProfileServiceLocal profileService;
    
    /**
     * Creates a new instance of ProfileBean
     */ 
    public ProfileMB() {
        
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Long getBirthdayTimestamp() {
        return birthdayTimestamp;
    }

    public void setBirthdayTimestamp(Long birthdayTimestamp) {
        this.birthdayTimestamp = birthdayTimestamp;
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
            this.birthdayTimestamp = (DateFormat.getDateInstance().parse(birthdayString)).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String createProfile(){
        Integer userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        upload();
        profileService.createProfile(firstname, lastname,information,profilePicture,birthdayTimestamp,userId);
        return "home.xhtml";
    }
    
    private void upload(){
        try {
            if(profilePictureFile != null)
                profilePicture = new Scanner(profilePictureFile.getInputStream())
                    .useDelimiter("\\A").next();
        } catch (IOException ex) {
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
}
