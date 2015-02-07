/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import service.ProfileService;
import service.UserService;
import websocket.SocketMediator;

/**
 *
 * @author Thibaud
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(UserService.class);
        set.add(ProfileService.class);
        set.add(SocketMediator.class);
        return set; //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
