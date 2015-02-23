/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.PrivateMessage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import service.PrivateMessageServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Denis 
 */
@ManagedBean(name = "PrivateMessageMB")
@SessionScoped
public class PrivateMessageMB {
    @EJB
    PrivateMessageServiceLocal privateMessageService;
    
    private String destinataire;
    private String contentMsg;
    private Integer conversationId;

    /**
     * Creates a new instance of PrivateMessageMB
     */
    public PrivateMessageMB() {
        
    }
    
    public void init(){
        try{
            destinataire ="";
            contentMsg = "";
            this.conversationId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("conversationId"));
            this.getConversation();
        }catch(NumberFormatException nfe){
        }
    }
    
    public PrivateMessage getConversation(){
        if(this.conversationId != null){
            PrivateMessage pm = privateMessageService.getConversation(this.conversationId, (Integer) SessionUtils.getItem(SessionUtils.ID_KEY));
            Integer authorId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
            if(!Objects.equals(authorId, pm.getDestinataire().getId()))
                this.destinataire = pm.getDestinataire().getProfile().getFirstname() + " " + pm.getDestinataire().getProfile().getLastname();
            else
                this.destinataire = pm.getAuthor().getProfile().getFirstname() + " " + pm.getAuthor().getProfile().getLastname();
            return pm;
        }else
            return null;
    }
    
    public void addMessageForConversation(){
        if(this.conversationId != null){
            PrivateMessage pm = this.getConversation();
            Integer authorId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
            Integer destinataireId = pm.getAuthor().getId();
            if(!Objects.equals(authorId, pm.getDestinataire().getId())){
                destinataireId = pm.getDestinataire().getId();
            }
            this.privateMessageService.addPrivateMessage(pm, contentMsg, authorId, destinataireId);
        }
        destinataire ="";
        contentMsg = "";
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/privateMessage.xhtml?conversationId="+this.conversationId);
        } catch (IOException ex) {
            Logger.getLogger(AlbumMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String publishPrivateMessage(){
        String tab[] = this.destinataire.split("@");
        if(tab.length > 1){
            String number = tab[1].replace("[", "").replace("]", "");
            try{
                Integer destinataireId = Integer.parseInt(number);
                privateMessageService.publishPrivateMessage(contentMsg, (Integer) SessionUtils.getItem(SessionUtils.ID_KEY), destinataireId);
                
            }catch(NumberFormatException nfe){
            }
        }
        contentMsg = "";
        destinataire = "";
        return "listPrivateMessage.xhtml";
    }
    
    public List<PrivateMessage> getPrivateMsg(){
        return privateMessageService.findConversation((Integer) SessionUtils.getItem(SessionUtils.ID_KEY));
    }
  
    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getContentMsg() {
        return contentMsg;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }
    
    
    
    
}
