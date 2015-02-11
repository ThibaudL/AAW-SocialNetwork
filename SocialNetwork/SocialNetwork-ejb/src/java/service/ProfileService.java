/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.Profile;
import dao.impl.ProfileFacadeLocal;
import dao.impl.UserFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.primefaces.util.Base64;

/**
 *
 * @author Thibaud
 */
@LocalBean
@Path("serviceProfile") 
@Stateful
public class ProfileService implements ProfileServiceLocal {

    private Profile currentProfile;
    
    @EJB
    private ProfileFacadeLocal profileFacade;
    @EJB 
    private UserFacadeLocal userFacade;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String getInformation() {
        //return userService.getProfile().getInformation();
        return currentProfile != null ? currentProfile.getInformation() : "";
    }

    public void setInformation(String information) {
        currentProfile.setInformation(information);
        profileFacade.edit(currentProfile);
    }


    @Override
    public void editProfile(Profile profile) {
        currentProfile = profile;
        profileFacade.edit(currentProfile);
    }

    @Override
    public Profile getProfile(Integer userId) {
        currentProfile = profileFacade.findByUserId(userId);
        return currentProfile;
    }
    
    @GET
    @Path("startWith/{nameStartWith}")
    @Produces("text/plain")
    public String getProfileByName(@PathParam("nameStartWith") String nameStartWith){
       
        List<Profile> profiles = profileFacade.searchProfile(nameStartWith);
        String result = "[";
        
        if(profiles != null && profiles.size() > 0){
            for (Profile profile : profiles) {
                
                result += "{\"id\":\"" + profile.getUser().getId()+ "\", \"firstname\":\"" + profile.getFirstname()+"\", \"lastname\":\"" + profile.getLastname()+"\", \"picture\":\"" +Base64.encodeToString(profile.getPicture(),false)+"\"},";
            }
            result = result.substring(0, result.length()-1);
            result += "]";

            return result;
        }else{
            return "notfound";
        }  
    } 
}
