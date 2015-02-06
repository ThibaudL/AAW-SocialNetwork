/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Friend;
import dao.entity.Profile;
import dao.entity.PublicMessage;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.FriendServiceLocal;
import service.ProfileServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Denis
 */
@ManagedBean(name = "FriendMB")
@SessionScoped
public class FriendMB implements Serializable{

    private StreamedContent readableProfilePicture;
    @EJB
    FriendServiceLocal friendService;
    @EJB
    ProfileServiceLocal profileService;

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
    
    public StreamedContent getReadableProfilePicture() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }else{

                String friendId = context.getExternalContext().getRequestParameterMap().get("friendId");
                Integer id = Integer.parseInt(friendId);
                Profile p = profileService.getProfile(id);
                return new DefaultStreamedContent(new ByteArrayInputStream(p.getPicture()));
        } 
    }
}
