/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.PublicMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
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
import service.MessageServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "MessageMB")
@SessionScoped
public class MessageMB {
    
    @EJB
    MessageServiceLocal messageService; 

    private String messageText;
    private List<PublicMessage>  messages; 
    private List<PublicMessage>  myMessages; 
    private Part publishPicture;
    private StreamedContent readableProfilePicture;
    /**
     * Creates a new instance of MessageBean
     */
    public MessageMB() {
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
    
    private void loadMessages(){
        Integer userId;
        String idString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId");
        if(idString == null){
            userId = (Integer)SessionUtils.getItem(SessionUtils.ID_KEY);
        }else{
            userId = Integer.parseInt(idString);
        }
        messageService.loadPublicMessages(userId);
    }
    
    public List<PublicMessage> getMessages(){
        loadMessages();
        messages = messageService.getMyNews();
        return messages;
    }
    
    public void setMessages(List<PublicMessage>  m){  
        messages = m;
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

                String messageId = context.getExternalContext().getRequestParameterMap().get("messageId");
                Integer id = Integer.parseInt(messageId);
                for (PublicMessage message : messages) { 
                    if(Objects.equals(message.getId(), id)){
                        return new DefaultStreamedContent(new ByteArrayInputStream(message.getAuthor().getProfile().getPicture()));
                    }
                } 
                   
            return new DefaultStreamedContent();
        }
    }

    public void setReadableProfitePicture(StreamedContent readableProfilePicture) {
        this.readableProfilePicture = readableProfilePicture;
    }

    public List<PublicMessage> getMyMessages() {
        loadMessages();
        myMessages = messageService.getMyMessages();
        return myMessages;
    }

    public void setMyMessages(List<PublicMessage> myMessages) {
        this.myMessages = myMessages;
    }
    
    
    
    
}
