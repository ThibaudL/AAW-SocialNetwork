/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Comment;
import dao.entity.Message;
import dao.impl.CommentFacadeLocal;
import dao.impl.MessageFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Thibaud
 */
@Stateless
public class CommentService implements CommentServiceLocal {
    
    @EJB
    CommentFacadeLocal commentFacade;
    @EJB
    UserFacadeLocal userFacade;
    @EJB
    MessageFacadeLocal messageFacade;

    @Override
    public void getComment(Integer messageId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendComment(Message msg, String commentContent, Integer userId) {
        Comment comment = new Comment();
        Message mComment = new Message();
        comment.setSource(msg);
        comment.setComment(mComment);
        mComment.setContent(commentContent);
        mComment.setAuthor(userFacade.find(userId));
        mComment.setDate(new Date());
        //messageFacade.create(mComment);
        //commentFacade.create(comment);
        msg.comments.add(comment);
        messageFacade.edit(msg);
    }
    
}
