/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.Wall;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface WallFacadeLocal {

    void create(Wall wall);

    void edit(Wall wall);

    void remove(Wall wall);

    Wall find(Object id);

    List<Wall> findAll();

    List<Wall> findRange(int[] range);

    int count();
    
}
