/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Friend;
import dao.entity.Notification;
import dao.impl.FriendFacadeLocal;
import dao.impl.NotificationFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import websocket.SocketMediator;

/**
 *
 * @author Denis
 */
@Stateless
public class FriendService implements FriendServiceLocal {

    @EJB
    FriendFacadeLocal friendFacade;
    @EJB
    UserFacadeLocal userFacade;
    @EJB
    NotificationFacadeLocal notificationFacade;
    
    @Override
    public List<Friend> getFriends(Integer userId) {
        return friendFacade.findByUserId(userId);
    } 

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        Friend f = new Friend();
        f.setValid(false);
        f.setUser(userFacade.find(userId));
        f.setFriend(userFacade.find(friendId));
        friendFacade.create(f);
        Notification not = new Notification();
        not.setUser(f.getFriend());
        not.setDate(new Date());
        not.setContent(f.user.getProfile().getFirstname()+" "+ f.user.getProfile().getLastname() + " wants to be your friend.");
        not.setLink("http://localhost:8080/SocialNetwork-war/faces/friend.xhtml");
        notificationFacade.create(not);
        SocketMediator.sendNotification(not);

    }

    @Override
    public void validFriendShip(Integer userId, Integer friendId) {
        Friend f = new Friend();
        f.setValid(true);
        f.setUser(userFacade.find(friendId));
        f.setFriend(userFacade.find(userId));
        friendFacade.create(f);
        friendFacade.validFriendship(userId, friendId);
        Notification not = new Notification();
        not.setUser(f.getFriend());
        not.setDate(new Date());
        not.setContent(f.user.getProfile().getFirstname()+" "+ f.user.getProfile().getLastname() + " accepted your friend invitation.");
        not.setLink("http://localhost:8080/SocialNetwork-war/faces/wall.xhtml?wallId="+f.user.getId());
        notificationFacade.create(not);
        SocketMediator.sendNotification(not);

    }
    
    @Override
    public void sendFriendInvit(Integer userId, Integer friendId){
        Friend f = new Friend();
        f.setValid(true);
        f.setUser(userFacade.find(userId));
        f.setFriend(userFacade.find(friendId));
        friendFacade.create(f);
        Notification not = new Notification();
        not.setUser(f.getFriend());
        not.setDate(new Date());
        not.setContent(f.user.getProfile().getFirstname()+" "+ f.user.getProfile().getLastname() + " wants to be your friend.");
        not.setLink("http://localhost:8080/SocialNetwork-war/faces/friend.xhtml");
        notificationFacade.create(not);
        SocketMediator.sendNotification(not);
    }

    @Override
    public List<Friend> getWaitingInvit(Integer userId) {
        return friendFacade.findWaitingInvit(userId);
    }

    @Override
    public void removeFriendship(Integer userId, Integer friendId) {
        friendFacade.removeFriendship(userId,friendId);
    }

    @Override
    public boolean areFriends(Integer userID, Integer friendId) {
        return friendFacade.areFriends(userID, friendId);
    }
    
     
    
    
}
