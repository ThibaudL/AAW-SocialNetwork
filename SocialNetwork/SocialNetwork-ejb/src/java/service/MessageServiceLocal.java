/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.PublicMessage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thibaud
 */
@Local
public interface MessageServiceLocal {
    public void publishPublicMessage(String content, Integer userId);
    
    public void loadPublicMessages(Integer authorId);
    
    public List<PublicMessage> getMessagesContents(); 
}
