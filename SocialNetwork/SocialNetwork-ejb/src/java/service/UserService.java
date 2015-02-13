/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Profile;
import dao.entity.User; 
import dao.impl.UserFacadeLocal;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import websocket.SocketMediator;

/**
 *
 * @author Thibaud
 */
@LocalBean
@Path("toto") 
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
        boolean retour = connectedUser.getPassword().equals(hashToSHA1(password));
           if(retour)
               connectedUser = userFacade.findByEmail(connectedUser.getEmail());
        return retour;        

    }

    @Override
    public String connectedUserToString() {
        if(connectedUser != null){
            refresh();
            SocketMediator.sendToAll("MESSAGE TEST", 1);
            return connectedUser.toString();
        }
        return null;
    }

    @Override
    public boolean registrationUser(String login, String password) {
        if(userFacade.findByEmail(login) == null){
            connectedUser = new User();
            connectedUser.setEmail(login);
            connectedUser.setPassword(hashToSHA1(password));
            userFacade.create(connectedUser);
            connectedUser = userFacade.findByEmail(connectedUser.getEmail());
            return true;
        }else{
            return false;
        }
    }
    
    private String hashToSHA1(String str){
        try {
            MessageDigest cript = MessageDigest.getInstance("SHA-1");
            cript.reset();
            cript.update(str.getBytes(Charset.forName("UTF8")));
            Formatter formatter = new Formatter();
            for(byte b : cript.digest()){
                formatter.format("%02x",b);
            }
            String result = formatter.toString();
            formatter.close();
            return result;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean searchByEmail(String email) {
        return userFacade.findByEmail(email) != null;
    }
    
    public Profile getProfile(){
        return connectedUser.getProfile();
    }

    @Override
    public Integer getUserId() {      
        return connectedUser.getId();
    }

    @Override
    public Integer getProfileId() {
        return connectedUser.getProfile().getId();
    }
    
    private void refresh(){
        this.connectedUser = userFacade.find(this.connectedUser.getId());
    }
    
    @GET
    @Path("tutu/{firstName}")
    @Produces("text/html")
    public String getTutu(@PathParam("firstName")String firstName){
        return "Bonjour "+ userFacade.findByEmail(firstName).toString();
    }

    @Override
    public User getUser(Integer userId ) {
        return userFacade.find(userId);
    }
    
    
}
 