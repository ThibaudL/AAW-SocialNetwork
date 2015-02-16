/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Comment;
import dao.entity.Message;
import dao.entity.PrivateMessage;
import dao.impl.NotificationFacadeLocal;
import dao.impl.PictureFacadeLocal;
import dao.impl.PrivateMessageFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Denis
 */
@Stateful
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
    public void publishPrivateMessage(String content, Integer authorId, Integer userId){
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
        }
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
    }
    
    public List<PrivateMessage> findConversation(Integer userId){
        return privateMessageFacade.findConversation(userId);
    }
}
