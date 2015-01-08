/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Thibaud
 */
@Entity
@DiscriminatorValue("PrivateMessage")
public class PrivateMessage extends Message {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="messageBox_fk")
    private MessageBox m_messageBox;

    public MessageBox getMessageBox() {
        return m_messageBox;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.m_messageBox = messageBox;
    }
    
    @Override
    public String toString() {
        return "dao.entity.PrivateMessage[ id=" + this.getId() + " ]";
    }
    
}
