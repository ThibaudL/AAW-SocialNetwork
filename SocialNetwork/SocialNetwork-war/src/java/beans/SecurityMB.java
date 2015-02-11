/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import utils.SessionUtils;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "SecurityMB")
@RequestScoped
public class SecurityMB {

    /**
     * Creates a new instance of Security
     */
    public SecurityMB() {
    }
    
    public boolean getIsConnected(){
        if(SessionUtils.getItem(SessionUtils.ID_KEY) == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            try {
                context.redirect(context.getRequestContextPath() + "/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(UserMB.class.getName()).log(Level.SEVERE, null, ex);
            }

            return false;
        }else{
            return true;
        }
    }
    
}
