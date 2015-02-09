/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Friend;
import dao.impl.FriendFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Denis
 */
@Stateful
public class FriendService implements FriendServiceLocal {

    @EJB
    FriendFacadeLocal friendFacade;
    @EJB
    UserFacadeLocal userFacade;
    
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
    }

    @Override
    public void validFriendShip(Integer userId, Integer friendId) {
        Friend f = new Friend();
        f.setValid(true);
        f.setUser(userFacade.find(friendId));
        f.setFriend(userFacade.find(userId));
        friendFacade.create(f);
        friendFacade.validFriendship(userId, friendId);
    }
    
    @Override
    public void sendFriendInvit(Integer userId, Integer friendId){
        Friend f = new Friend();
        f.setValid(true);
        f.setUser(userFacade.find(userId));
        f.setFriend(userFacade.find(friendId));
        friendFacade.create(f);
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
