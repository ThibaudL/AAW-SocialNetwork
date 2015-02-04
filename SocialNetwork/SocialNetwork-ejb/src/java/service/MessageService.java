/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.PublicMessage;
import dao.impl.PublicMessageFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Thibaud
 */
@Stateless
public class MessageService implements MessageServiceLocal {
    
    @EJB
    PublicMessageFacadeLocal publicMessageFacade;
    @EJB
    UserFacadeLocal userFacade;
    
    List<PublicMessage> messages;
    
    @Override
    public void publishPublicMessage(String content, Integer userId) {
        PublicMessage publicMessage = new PublicMessage();
        publicMessage.setAuthor(userFacade.find(userId));
        publicMessage.setContent(content);
        publicMessage.setDate(new Date());
        publicMessageFacade.create(publicMessage);
    }

    @Override
    public void loadPublicMessages(Integer authorId) {
        messages = publicMessageFacade.findByAuthorId(authorId);
    }

    @Override
    public List<Object[]> getMessagesContents() {
        List<Object[]> contents = new ArrayList<>();
        if(messages != null){
            for (PublicMessage msg : messages) {
                Object[] content = new Object[3];
                
                content[0] = msg.getAuthor().getId();
                content[1] = msg.getContent();
                content[2] = msg.getDate();
                
                contents.add(content);
            }
        }
        return contents;
    }
    
    
    
    
}
