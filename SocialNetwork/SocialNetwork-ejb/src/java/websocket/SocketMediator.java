/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import dao.entity.Notification;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import service.UserService;

/**
 *
 * @author Thibaud
 */
@Singleton
@ServerEndpoint("/mediatorendpoint/{userId}")
public class SocketMediator {
    
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    public SocketMediator() {
        
    }
    
    @OnMessage
    public String onMessage(String message, Session session){
        for(Session peer : peers){
            if(!peer.equals(session)){
                try{
                    peer.getBasicRemote().sendText(message);
                }catch (IOException ex){
                    Logger.getLogger(SocketMediator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "message received and processed : "+message;
    }
    
    @OnOpen
    public void onOpen(Session peer,@PathParam("userId") String userId) { 
        peer.getUserProperties().put("userId", userId);
        // , @PathParam("userId") String userId 
        // peer.getUserProperties().put("userId", userId);
        peers.add(peer);
    }
    
    @OnClose
    public void onClose(Session peer){
        peers.remove(peer);
    }
    
    public static void sendToAll(String message, int userId){
        for(Session session : peers){
            if(session.isOpen()){
                try {
                    if(Integer.parseInt(session.getUserProperties().get("userId").toString()) == userId){
                        session.getBasicRemote().sendText(message);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SocketMediator.class.getName()).log(Level.SEVERE, "ERREUR IN SEND TO ALL");
                }
            }
        }
    }
    
    public static void sendNotification(Notification not){
        for(Session session : peers){
            if(session.isOpen()){
                try {

                    if(Integer.parseInt(session.getUserProperties().get("userId").toString()) == not.getUser().getId()){
                        String json = "{\"id\":\"" + not.getId()+ "\", \"content\":\"" + not.getContent()+"\", \"link\":\"" + not.getLink()+"\", \"date\":\"" +not.getDate()+"\"}";
 
                        session.getBasicRemote().sendText(json);

                    }
                } catch (IOException|NumberFormatException ex) {
                    Logger.getLogger(SocketMediator.class.getName()).log(Level.SEVERE, "ERREUR IN SEND NOTIFICATION");
                }
            }
        }
    }
    

    
    
}
