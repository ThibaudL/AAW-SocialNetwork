/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Friend;
import dao.entity.Picture;
import dao.entity.PublicMessage;
import dao.entity.User;
import dao.impl.PictureFacade;
import dao.impl.PictureFacadeLocal;
import dao.impl.PublicMessageFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
    @EJB
    PictureFacadeLocal pictureFacade;
    
    
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
    public void publishPublicMessagePicture(String content, Integer userId, byte[] picture) {
        Picture p = new Picture();
        p.setContent(picture);
        pictureFacade.create(p);
        
        PublicMessage publicMessage = new PublicMessage();
        publicMessage.setAuthor(userFacade.find(userId));
        publicMessage.setContent(content);
        publicMessage.setDate(new Date());
        publicMessage.setPicture(p);
        publicMessageFacade.create(publicMessage);
        SocketMediator.send("Message published by " + publicMessage.getAuthor().getProfile().getFirstname() + " : " + content , userId);
    }


    @Override
    public List<PublicMessage> getMyNews(Integer authorId) {
        List<PublicMessage> myMessages = publicMessageFacade.findByAuthorId(authorId);
        User currentUser = userFacade.find(authorId);        
        for (Friend f : currentUser.getFriends()) {
            if(f.isValid())
                myMessages.addAll(publicMessageFacade.findByAuthorId(f.friend.getId()));
        }
        Collections.sort(myMessages, new Comparator<PublicMessage>() {
            @Override
            public int compare(PublicMessage o1, PublicMessage o2) {
                return -(o1.getDate().compareTo(o2.getDate()));                  
            }
        });    
        return myMessages;
    }

    @Override
    public List<PublicMessage> getMyMessages(Integer authorId) {
        User currentUser = userFacade.find(authorId);
        List<PublicMessage> messages = publicMessageFacade.findByAuthorId(authorId);
        for (PublicMessage message : messages) {
            message.setAuthor(currentUser);
        }
        return messages;
    }
    
    
    
    
}
