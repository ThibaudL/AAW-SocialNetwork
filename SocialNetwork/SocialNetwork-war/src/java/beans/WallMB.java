/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Thibaud
 */
@ManagedBean
@SessionScoped
public class WallMB {

    private int userId;
    
    /**
     * Creates a new instance of WallMB
     */
    @PostConstruct
    public void init() {
        userId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId"));
    }
    
}
