/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.PublicMessage;
import dao.impl.PublicMessageFacadeLocal;
import dao.impl.UserFacade;
import dao.impl.UserFacadeLocal;
import java.util.Date;
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
    
    @Override
    public void publishPublicMessage(String content, Integer userId) {
        PublicMessage publicMessage = new PublicMessage();
        publicMessage.setAuthor(userFacade.find(userId));
        publicMessage.setContent(content);
        publicMessage.setDate(new Date());
        publicMessageFacade.create(publicMessage);
    }
    
    
}
