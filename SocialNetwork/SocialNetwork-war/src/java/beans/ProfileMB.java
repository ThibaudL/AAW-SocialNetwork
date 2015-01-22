/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import service.ProfileServiceLocal;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "ProfileMB")
@RequestScoped
public class ProfileMB implements Serializable{

    private String firstname;
    private String lastname;
    private String information;
    private String profilePicture;
    private Long birthdayTimestamp;
    
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
        return profileService.getInformation();
    }

    public void setInformation(String information) {
        this.information = information;
        profileService.setInformation(information);
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
    
    
    
}
