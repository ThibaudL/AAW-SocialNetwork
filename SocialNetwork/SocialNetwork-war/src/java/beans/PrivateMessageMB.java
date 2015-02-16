/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.PrivateMessage;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    /**
     * Creates a new instance of PrivateMessageMB
     */
    public PrivateMessageMB() {
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
