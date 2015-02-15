/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Message;
import dao.entity.Comment;
import dao.entity.Picture;
import dao.entity.Profile;
import dao.entity.PublicMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import service.CommentServiceLocal;
import service.FriendServiceLocal;
import service.MessageServiceLocal;
import service.PictureServiceLocal;
import service.ProfileServiceLocal;
import service.UserServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "MessageMB")
@SessionScoped
public class MessageMB implements Serializable{
    private static final String VIDEO = "#VIDEO#";
    private static final String LINK = "#LINK#";
    private final static long IMAGE_SIZE = 2000000;
    
   
    private UploadedFile pictureFile;
    
    
    @EJB
    MessageServiceLocal messageService; 
    @EJB
    ProfileServiceLocal profileService;
    @EJB
    UserServiceLocal userService;
    @EJB 
    FriendServiceLocal friendService;
    @EJB
    PictureServiceLocal pictureService;
    @EJB
    CommentServiceLocal commentService;

    private String messageText;
    private String messageTextPicture;
    private Part publishPicture;
    private String commentToSend;
    private String videoUrl;
    private String linkUrl;
    
    
    
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

    public String getCommentToSend() {
        return commentToSend;
    }

    public void setCommentToSend(String commentToSend) {
        this.commentToSend = commentToSend;
    }
    
    
    
    public void publishMessage(){
        Integer userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        if(userId != null){
            messageService.publishPublicMessage(messageText, userId);
            removeContent();
        }
    } 
    
    public void publishVideo(){
        Integer userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        if(userId != null){
            Logger.getLogger(MessageMB.class.getName()).log(Level.SEVERE, "VIDEO : "+this.videoUrl);
            
            messageService.publishPublicMessage(VIDEO + this.videoUrl + VIDEO, userId);
            removeContent();
        }
    } 
 
    
    public void publishLink(){
        Integer userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        if(userId != null){
            messageService.publishPublicMessage(LINK + linkUrl + LINK, userId);
            removeContent();
        }
    } 
    
    public void publishMessagePicture(){
        Integer userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        if(pictureFile.getSize() < IMAGE_SIZE && pictureFile.getSize() > 0){
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = pictureFile.getInputstream();
                byte[] buffer = new byte[1024];
                while (true) {
                    int r = in.read(buffer);
                    if (r == -1) break;
                    out.write(buffer, 0, r);
                }
                messageService.publishPublicMessagePicture(messageTextPicture, userId, out.toByteArray());
                

            } catch (IOException ex) {
                Logger.getLogger(AlbumMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        removeContent();
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
    

    public List<PublicMessage> getMyMessages() {
        Integer userId;
        String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
        try{
            userId = Integer.parseInt(idString);
        }catch(NumberFormatException nfe){
            userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        }
        
        if(!Objects.equals(userId, (Integer)SessionUtils.getItem(SessionUtils.ID_KEY))){
            if(!friendService.areFriends(userId,(Integer)SessionUtils.getItem(SessionUtils.ID_KEY))){
                return new ArrayList<PublicMessage>();
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;

    }

    public UploadedFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(UploadedFile pictureFile) {
        this.pictureFile = pictureFile;
    }
    
    private void removeContent(){
        videoUrl = "";
        messageText = "";
        linkUrl = "";
        messageTextPicture = "";
        pictureFile = null;
    }
    
    public void sendComment(Message msg){
        Logger.getLogger(MessageMB.class.getName()).log(Level.SEVERE,FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());

        String content = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("inputId"+msg.getId());
        commentService.sendComment(msg, content, (Integer)SessionUtils.getItem(SessionUtils.ID_KEY));
    }
     
    public List<Comment> getCommentsByMessage(Message msg){
        return msg.getComments();
    }
    

    
    

}
