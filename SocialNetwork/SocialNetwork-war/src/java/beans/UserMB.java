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
    private static final String CONNECTION_ERROR = "Email / Password not found";
    private static final String REGISTRATION_ERROR = "Email adress already used";
    private static final int REGISTER = 2;
    private static final int CONNECT = 1;

    @EJB
    UserServiceLocal userService;
    
    private String login;
    private String password;
    
    private boolean error;
    private String errorMsg;
    
    private Integer isRegistering=1;

    
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
        if(this.isRegistering == CONNECT){
            if(userService.connectUser(login, password)){
                this.error = false;
                return "home.xhtml";
            }else{
                this.error = true;
                this.errorMsg = CONNECTION_ERROR;
            }
        }else if(this.isRegistering == REGISTER){
            return checkRegistration();
        }
        return "index.xhtml";

    }

    public Integer getIsRegistering() {
        return isRegistering;
    }

    public void setIsRegistering(Integer isRegistering) {
        this.isRegistering = isRegistering;
    }
    
    
    
    public String displayProfile(){
        return userService.connectedUserToString();
    }
    
    public String checkRegistration(){
        if(userService.registrationUser(login, password)){
            this.error = false;
            this.errorMsg = "";
            return "profileSetUp.xhtml";
        }else{
            this.error = true;
            this.errorMsg = REGISTRATION_ERROR;
        }
        return "index.xhtml";
    }
    
    
}
