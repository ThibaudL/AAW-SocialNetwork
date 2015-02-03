/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Local;

/**
 *
 * @author Thibaud
 */
@Local
public interface UserServiceLocal {
    
    public boolean connectUser(String login, String password);
    public String connectedUserToString();
    public boolean registrationUser(String login, String password);
    public boolean searchByEmail(String email);
    public Integer getUserId();
    public Integer getProfileId();
}
