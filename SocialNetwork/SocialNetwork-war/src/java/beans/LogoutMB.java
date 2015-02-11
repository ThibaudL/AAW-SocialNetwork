/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thibaud
 */
@ManagedBean(name = "LogoutMB")
@RequestScoped
public class LogoutMB {

    /**
     * Creates a new instance of LogoutMB
     */
    public LogoutMB() {
    }
    
    public String logout(){ 
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
        
        return "index.xhtml";
    }
    
}
