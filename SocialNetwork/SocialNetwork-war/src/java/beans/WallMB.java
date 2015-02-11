/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Picture;
import dao.entity.Profile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.FriendServiceLocal;
import service.PictureServiceLocal;
import service.ProfileServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name ="WallMB")
@SessionScoped
public class WallMB implements Serializable{

    private Integer userId;
    private StreamedContent readableProfilePicture; 
    private Profile profile;
    @EJB
    ProfileServiceLocal profileService;
    
    @EJB
    FriendServiceLocal friendService;
    
    @EJB
    PictureServiceLocal pictureService;
    
    
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
    
    public void addFriend(){
        try {
            friendService.addFriend((Integer)SessionUtils.getItem(SessionUtils.ID_KEY), userId);
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/wall.xhtml?wallId="+getUserId());
            
            //return "wall.xhtml?wallId="+getUserId();
        } catch (IOException ex) {
            Logger.getLogger(WallMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean areFriends(){
        Integer uID;
        Integer fID;
        String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
        uID = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        if(idString == null)
            return false; 
        
        
        try{
            fID = Integer.parseInt(idString);
        }catch(NumberFormatException nfe){
            return false;
        }

        return friendService.areFriends(uID, fID);
    }
    
    public void removeFriendship(){
        try {
            Integer uID;
            Integer fID;
            String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
            uID = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
            if(idString != null){
                fID = Integer.parseInt(idString);
                friendService.removeFriendship(uID, fID);

                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect(context.getRequestContextPath() + "/faces/wall.xhtml?wallId="+getUserId());
            }
        } catch (IOException ex) {
            Logger.getLogger(WallMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public StreamedContent getReadableMessagePicture(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }else{

            Map<String, String> map =context.getExternalContext().getRequestParameterMap();
            String pictureId = map.get("pictureId");

            if(pictureId != null){

                Integer pId = Integer.parseInt(pictureId);
                if(pId != null){
                    Picture p = pictureService.findPicture(pId);

                    return new DefaultStreamedContent(new ByteArrayInputStream(p.getContent()));
                }
            }

            
            return new DefaultStreamedContent();
        }
    }
    
    public StreamedContent getReadableFriendPicture(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }else{

            Map<String, String> map =context.getExternalContext().getRequestParameterMap();
            String friendPictureId = map.get("friendPictureId");

            if(friendPictureId != null){

                Integer pId = Integer.parseInt(friendPictureId);
                if(pId != null){
                    Profile p = profileService.getProfile(pId);

                    return new DefaultStreamedContent(new ByteArrayInputStream(p.getPicture()));
                }
            }

            
            return new DefaultStreamedContent();
        }
    }
    
    
}
