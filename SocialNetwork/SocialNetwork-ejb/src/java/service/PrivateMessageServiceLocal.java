/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.entity.PrivateMessage;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Denis
 */
@Local
public interface PrivateMessageServiceLocal {
    Integer publishPrivateMessage(String content, Integer authorId, Integer userId);
    void addPrivateMessage(PrivateMessage pm, String content, Integer authorId, Integer userId);
    List<PrivateMessage> findConversation(Integer userId);
    PrivateMessage getConversation(Integer msgId, Integer userId);
}
