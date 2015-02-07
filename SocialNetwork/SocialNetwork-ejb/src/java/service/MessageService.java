/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Friend;
import dao.entity.PublicMessage;
import dao.entity.User;
import dao.impl.PublicMessageFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import websocket.SocketMediator;

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
    
    List<PublicMessage> myNewsMessages;
    List<PublicMessage> myMessages;
    
    @Override
    public void publishPublicMessage(String content, Integer userId) {
        PublicMessage publicMessage = new PublicMessage();
        publicMessage.setAuthor(userFacade.find(userId));
        publicMessage.setContent(content);
        publicMessage.setDate(new Date());
        publicMessageFacade.create(publicMessage);
        SocketMediator.send("Message published by " + publicMessage.getAuthor().getProfile().getFirstname() + " : " + content , userId);
    }

    @Override
    public void loadPublicMessages(Integer authorId) {
        myMessages = publicMessageFacade.findByAuthorId(authorId);
        myNewsMessages = myMessages;
        User currentUser = userFacade.find(authorId);
        Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE,  currentUser.toString());
        
        for (Friend f : currentUser.getFriends()) {
             
            myNewsMessages.addAll(publicMessageFacade.findByAuthorId(f.friend.getId()));
        }
        Collections.sort(myNewsMessages, new Comparator<PublicMessage>() {
            @Override
            public int compare(PublicMessage o1, PublicMessage o2) {
                return -(o1.getDate().compareTo(o2.getDate()));                  
            }
        });
    }

    @Override
    public List<PublicMessage> getMyNews() {
        return myNewsMessages;
    }

    @Override
    public List<PublicMessage> getMyMessages() {
        return myMessages;
    }
    
    
    
    
}
