/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Profile;
import dao.entity.User;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
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
    private UploadedFile profilePictureFile;
    private StreamedContent readableProfilePicture;
   
    
    @EJB
    ProfileServiceLocal profileService;
    @EJB
    UserServiceLocal userServiceLocal;
    
    /**
     * Creates a new instance of ProfileBean
     */ 
    @PostConstruct
    public void init(){
        Integer userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        if(userId != null){
            User user =userServiceLocal.getUser(userId);
            profile = user != null ? user.getProfile() : new Profile();
            profile.setUser(user);
        }
    }
    
    public ProfileMB() {
        profile = new Profile();
    }

    public String getBirthdayString() {
        return birthdayString;
    }

    public UploadedFile getProfilePictureFile() {
        return profilePictureFile;
    }

    public void setProfilePictureFile(UploadedFile profilePictureFile) {
        this.profilePictureFile = profilePictureFile;
    }
    
    private StreamedContent initImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            return new DefaultStreamedContent(new ByteArrayInputStream(profile.getPicture()));
        }
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
        upload();
        profileService.editProfile(profile);
        return "home.xhtml";
    }
    
    public void upload(){
        try {
            /*
            if(profilePictureFile != null)
            profile.setPicture(profilePictureFile.getContents());
            */
            FacesMessage msg = new FacesMessage("Success! ", profilePictureFile.getFileName() + " is uploaded.");
            
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, "DEBUG DEBUG DEBUG : "+profilePictureFile.getFileName() + " - "+profilePictureFile.getInputstream() + " - "+profilePictureFile.getSize());
           
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = profilePictureFile.getInputstream();
            byte[] buffer = new byte[1024];
            while (true) {
                int r = in.read(buffer);
                if (r == -1) break;
                out.write(buffer, 0, r);
            }

            profile.setPicture(out.toByteArray());
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

    public StreamedContent getReadableProfilePicture() {
        try {
            readableProfilePicture = initImage();
        } catch (IOException ex) {
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readableProfilePicture;
    }

    public void setReadableProfilePicture(StreamedContent readableProfilePicture) {
        this.readableProfilePicture = readableProfilePicture;
    }
     
    
    
    
    

    
    
}
