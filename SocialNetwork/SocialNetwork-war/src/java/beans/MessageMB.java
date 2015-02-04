/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.PublicMessage;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
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
    private Part publishPicture;
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
        messageService.publishPublicMessage(messageText, userId);
        messageText = "";
    } 
    
    private void loadMessages(){
        Integer userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        messageService.loadPublicMessages(userId);
    }
    
    public List<PublicMessage> getMessages(){
        loadMessages();
        messages = messageService.getMessagesContents();
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
    
    
    
}
