/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Comment;
import dao.entity.Message;
import dao.entity.Notification;
import dao.entity.PrivateMessage;
import dao.entity.Profile;
import dao.entity.User;
import dao.impl.NotificationFacadeLocal;
import dao.impl.PictureFacadeLocal;
import dao.impl.PrivateMessageFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import websocket.SocketMediator;

/**
 *
 * @author Denis
 */
@Stateless
public class PrivateMessageService implements PrivateMessageServiceLocal {

    @EJB
    UserFacadeLocal userFacade;
    @EJB
    PictureFacadeLocal pictureFacade;
    @EJB
    NotificationFacadeLocal notificationFacade;
    @EJB
    PrivateMessageFacadeLocal privateMessageFacade;
    
    @Override
    public Integer publishPrivateMessage(String content, Integer authorId, Integer userId){
        PrivateMessage pm = privateMessageFacade.existConversation(authorId, userId);
        if(pm != null){
            addPrivateMessage(pm, content, authorId, userId);
        }else{
            pm = new PrivateMessage();
            pm.setAuthor(userFacade.find(authorId));
            pm.setDestinataire(userFacade.find(userId));
            pm.setContent(content);
            pm.setDate(new Date());
            privateMessageFacade.create(pm);
            sendNotification(authorId, userId, content, pm.getId());
        }
        return pm.getId();
    }
    
    @Override
    public void addPrivateMessage(PrivateMessage pm, String content, Integer authorId, Integer userId){
        Comment comment = new Comment();
        Message mComment = new Message();
        comment.setSource(pm);
        comment.setComment(mComment);
        mComment.setContent(content);
        mComment.setAuthor(userFacade.find(authorId));
        mComment.setDate(new Date());
        pm.comments.add(comment);
        privateMessageFacade.edit(pm);
        sendNotification(authorId, userId, content, pm.getId());
    }
    
    @Override
    public List<PrivateMessage> findConversation(Integer userId){
        return privateMessageFacade.findConversation(userId);
    }
    
    @Override
    public PrivateMessage getConversation(Integer msgId, Integer userId){
        return privateMessageFacade.getConversationById(msgId, userId);
    }
    
    private void sendNotification(Integer authorId, Integer userId, String content, Integer msgId){
        User user = userFacade.find(authorId);
        Notification not= new Notification();
        Profile p = user.getProfile();
        not.setContent(p.getFirstname()+" "+ p.getLastname() +" sent a private message : "+content);
        not.setDate(new Date());
        not.setLink("http://localhost:8080/SocialNetwork-war/faces/privateMessage.xhtml?conversationId="+msgId);
        not.setUser(userFacade.find(userId));
        notificationFacade.create(not);
        SocketMediator.sendNotification(not);
    }
}
