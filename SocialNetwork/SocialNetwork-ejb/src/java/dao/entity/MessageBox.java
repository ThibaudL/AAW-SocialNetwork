package dao.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class MessageBox implements Serializable {
    
    @Id
    private int m_id;
    
    @OneToMany(mappedBy="messagebox")
    public List<PrivateMessage> m_messages ;
    
    @OneToOne
    @JoinColumn(name="user_fk")     
    private User m_user;

    public MessageBox() {
        m_messages = new ArrayList<>();
    }

        
    public int getId() {
            return this.m_id;
    }

    public void setId(int id) {
            this.m_id = id;
    }

    public User getUser() {
        return m_user;
    }

    public void setUser(User user) {
        this.m_user = user;
    }
    
    

    @Override
    public int hashCode() {
            int lHashCode = 0;
            if ( this.m_messages != null ) {
                    lHashCode += this.m_messages.hashCode();
            }
            if ( lHashCode == 0 ) {
                    lHashCode = super.hashCode();
            }
            return lHashCode;
    }

    @Override
    public boolean equals(Object object) {
            if (this == object) {
                    return true;
            } else if (object instanceof MessageBox) {
                    MessageBox lMessageBoxObject = (MessageBox) object;
                    boolean lEquals = true;
                    lEquals &= this.m_id == lMessageBoxObject.m_id;
                    lEquals &= ((this.m_messages == lMessageBoxObject.m_messages)
                            || (this.m_messages != null && this.m_messages.equals(lMessageBoxObject.m_messages)));
                    return lEquals;
            }
            return false;
    }
}