/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Profile;
import dao.entity.PublicMessage;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.FriendServiceLocal;
import service.MessageServiceLocal;
import service.ProfileServiceLocal;
import service.UserService;
import service.UserServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "MessageMB")
@SessionScoped
public class MessageMB implements Serializable{
    
    @EJB
    MessageServiceLocal messageService; 
    @EJB
    ProfileServiceLocal profileService;
    @EJB
    UserServiceLocal userService;
    @EJB 
    FriendServiceLocal friendService;

    private String messageText;
    private String messageTextPicture;
    private Part publishPicture;
    /**
     * Creates a new instance of MessageBean
     */
    public MessageMB() {
        messageTextPicture ="";
    }
    
    public boolean getIsMessageTextPictureSet(){
        if(messageTextPicture == null || messageTextPicture == "")
            return false;
        return true;
    }
    
     public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
    public void publishMessage(){
        Integer userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        if(userId != null){
            messageService.publishPublicMessage(messageText, userId);
            messageText = "";
        }
    } 
    
 
    
    public List<PublicMessage> getMessages(){
        Integer userId;
        String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
        if(idString == null){
            userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        }else{
            userId = Integer.parseInt(idString);
        }
        return messageService.getMyNews(userId);
    }
    

 
    public Part getPublishPicture() {
        return publishPicture;
    }

    public void setPublishPicture(Part publishPicture) {
        this.publishPicture = publishPicture;
    }
    
    public StreamedContent getReadableProfilePicture() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }else{

            Map<String, String> map =context.getExternalContext().getRequestParameterMap();
            String userId = map.get("commentUserId");

            if(userId != null){

                Integer pId = Integer.parseInt(userId);
                if(pId != null){
                    Profile profile = profileService.getProfile(pId);

                    return new DefaultStreamedContent(new ByteArrayInputStream(profile.getPicture()));
                }
            }

            
            return new DefaultStreamedContent();
        }
    }

    public List<PublicMessage> getMyMessages() {
        Integer userId;
        String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
        if(idString == null){
            userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        }else{
            userId = Integer.parseInt(idString);
            if(userId != (Integer)SessionUtils.getItem(SessionUtils.ID_KEY)){
                if(!friendService.areFriends(userId,(Integer)SessionUtils.getItem(SessionUtils.ID_KEY))){
                    return new ArrayList<PublicMessage>();
                }
            }
        } 
        return messageService.getMyMessages(userId);
    }

    public String getMessageTextPicture() {
        return messageTextPicture;
    }

    public void setMessageTextPicture(String messageTextPicture) {
        this.messageTextPicture = messageTextPicture;
    }

}
