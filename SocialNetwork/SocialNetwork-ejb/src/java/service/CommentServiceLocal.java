/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Message;
import javax.ejb.Local;

/**
 *
 * @author Thibaud
 */
@Local
public interface CommentServiceLocal {
    public void getComment(Integer messageId);
    public void sendComment(Message msg, String commentContent, Integer userId);
}
