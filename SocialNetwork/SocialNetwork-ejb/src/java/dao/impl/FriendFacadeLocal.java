/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Friend;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface FriendFacadeLocal {

    void create(Friend friend);

    void edit(Friend friend);

    void remove(Friend friend);

    Friend find(Object id);

    List<Friend> findAll();

    List<Friend> findRange(int[] range);

    int count();
    
    List<Friend> findByUserId(Integer id);

    void addValidFriend(Integer userId, Integer friendId);
    
}
