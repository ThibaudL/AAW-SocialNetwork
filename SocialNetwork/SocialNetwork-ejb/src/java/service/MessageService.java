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
import java.util.Comparator;
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
        User currentUser = userFacade.find(authorId);
        for (Friend f : currentUser.getFriends()) {
            messages.addAll(publicMessageFacade.findByAuthorId(f.friend.getId()));
        }
        messages.sort(new Comparator<PublicMessage>() {
            @Override
            public int compare(PublicMessage o1, PublicMessage o2) {
                return o1.getDate().compareTo(o2.getDate());                  
            }
        });
    }

    @Override
    public List<Object[]> getMessagesContents() {
        List<Object[]> contents = new ArrayList<>();
        if(messages != null && messages.size()>0){
            for (PublicMessage msg : messages) {
                Object[] content = new Object[3];
                Object[] author = new Object[3];

                author[0] = msg.getAuthor().getProfile().getFirstname();
                author[1] = msg.getAuthor().getProfile().getLastname();
                author[2] = msg.getAuthor().getProfile().getPicture();
                
                content[0] = author;
                content[1] = msg.getContent();
                content[2] = msg.getDate();
                
                contents.add(content);
            }
        }
        return contents;
    }
    
    
    
    
}
