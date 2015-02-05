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
    
    
}