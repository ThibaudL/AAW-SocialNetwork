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
@DiscriminatorValue("PublicMessage")
public class PublicMessage extends Message {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="wall_fk")
    private Wall m_wall;

    public Wall getWall() {
        return m_wall;
    }

    public void setWall(Wall wall) {
        this.m_wall = wall;
    }

    @Override
    public String toString() {
        return "dao.entity.PublicMessage[ id=" + this.getId() + " ]";
    }
    
}
