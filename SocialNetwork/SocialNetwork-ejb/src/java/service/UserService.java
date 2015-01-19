/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.User;
import dao.impl.UserFacadeLocal;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
       connectedUser = userFacade.findByEmail(login);
       if(connectedUser == null){
           return false;
       }
       return connectedUser.getPassword().equals(password);
    }

    @Override
    public String connectedUserToString() {
        if(connectedUser != null){
            return connectedUser.toString();
        }
        return null;
    }

    @Override
    public boolean registrationUser(String login, String password) {
        if(userFacade.findByEmail(login) == null){
            connectedUser = new User();
            connectedUser.setEmail(login);
            connectedUser.setPassword(password);
            userFacade.create(connectedUser);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public Object getAttribute(String accesMethod) {
        if(connectedUser != null){
            try {
                return connectedUser.getClass().getMethod("accesMethod", (Class<?>) null).invoke(connectedUser);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }
}
