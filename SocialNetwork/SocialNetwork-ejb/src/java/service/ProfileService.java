/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Thibaud
 */
@Stateful
public class ProfileService implements ProfileServiceLocal {

    //@EJB
    //UserService userService;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String getInformation() {
        //return userService.getProfile().getInformation();
        return "";
    }

    public void setInformation(String information) {
        //userService.getProfile().setInformation(information);
    }
}
