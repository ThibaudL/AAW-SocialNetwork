/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.UserServiceLocal;

/**
 *
 * @author Denis
 */
@ManagedBean(name = "UserMB")
@SessionScoped
public class UserMB implements Serializable{
    private static final long serialVersionUID = 490994887388213027L;
    private static final String CONNECTION_ERROR = "Connection error";
    private static final String REGISTRATION_ERROR = "Registration error";

    @EJB
    UserServiceLocal userService;
    
    private String login;
    private String password;
    
    private boolean error;
    private String errorMsg;
    
    /**
     * Creates a new instance of UserMB
     */
    public UserMB() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public String checkConnection(){
        if(userService.connectUser(login, password)){
            this.error = false;
            return "manage.xhtml";
        }else{
            this.error = true;
            this.errorMsg = CONNECTION_ERROR;
            return "index.xhtml";
        }
    }
    
    
    public String displayProfile(){
        return userService.connectedUserToString();
    }
    
    public String checkRegistration(){
        if(userService.registrationUser(login, password)){
            this.error = false;
            this.errorMsg = "";
            return "manage.xhtml";
        }else{
            this.error = true;
            this.errorMsg = REGISTRATION_ERROR;
            return "index.xhtml";
        }
    }
    
    
}
