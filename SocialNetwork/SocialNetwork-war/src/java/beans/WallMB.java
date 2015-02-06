/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Profile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.ProfileServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name ="WallMB")
@SessionScoped
public class WallMB {

    private Integer userId;
    private StreamedContent readableProfilePicture; 
    private Profile profile;
    @EJB
    ProfileServiceLocal profileService;
    
    
    public void init() {
        String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
        if(idString == null){
            userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        }else{
            userId = Integer.parseInt(idString);
        }
        profile = profileService.getProfile(userId);
        
        Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, "DEBUG ::::::::::::::::  " + userId + "     " + profile);

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
    
    private StreamedContent initImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            return new DefaultStreamedContent(new ByteArrayInputStream(profile.getPicture()));
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    
}
