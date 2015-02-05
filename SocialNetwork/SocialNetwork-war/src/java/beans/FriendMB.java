/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Friend;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.FriendServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Denis
 */
@ManagedBean(name = "FriendMB")
@SessionScoped
public class FriendMB implements Serializable{

    @EJB
    FriendServiceLocal friendService;

    public FriendMB() {
        
    }
    
    public List<Friend> getFriends(){
        //addFriend();
        return friendService.getFriends((Integer) SessionUtils.getItem(SessionUtils.ID_KEY));
    }
    
    public String addFriend(){
        friendService.addFriend((Integer) SessionUtils.getItem(SessionUtils.ID_KEY), 3);
        return "";
    }




    

    
    
}
