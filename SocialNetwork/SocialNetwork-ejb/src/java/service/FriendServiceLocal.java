/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Friend;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface FriendServiceLocal {

    List<Friend> getFriends(Integer userId);
    
    List<Friend> getWaitingInvit(Integer userId);

    void addFriend(Integer userId, Integer friendId);

    void validFriendShip(Integer userId, Integer friendId);
    
    void removeFriendship(Integer userId, Integer friendId);
    
    void sendFriendInvit(Integer userId, Integer friendId);
}
