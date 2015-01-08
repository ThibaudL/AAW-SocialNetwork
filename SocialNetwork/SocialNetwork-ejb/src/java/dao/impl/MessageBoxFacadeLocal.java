/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.entity.MessageBox;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface MessageBoxFacadeLocal {

    void create(MessageBox messageBox);

    void edit(MessageBox messageBox);

    void remove(MessageBox messageBox);

    MessageBox find(Object id);

    List<MessageBox> findAll();

    List<MessageBox> findRange(int[] range);

    int count();
    
}
