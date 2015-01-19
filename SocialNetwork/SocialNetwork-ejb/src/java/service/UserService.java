/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.User;
import dao.impl.UserFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Thibaud
 */
@Stateful
public class UserService implements UserServiceLocal {

    private User connectedUser;
    
    @EJB
    private UserFacadeLocal userFacade;
    
    @Override
    public boolean connectUser(String login, String password) {
       connectedUser = userFacade.find(1);
       if(connectedUser == null)
           return false;
       return true;
    }

    @Override
    public String connectedUserToString() {
        if(connectedUser != null){
            return connectedUser.toString();
        }
        return null;
    }
}
