package dao.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int m_id;
    @Column
    private String m_content;
    @Column
    private boolean m_read;
    @Temporal(TemporalType.TIMESTAMP)
    private Date m_dateNotiFication;
    
    @ManyToOne
    @JoinColumn(name="user_fk")
    private User m_user;
    
    public int getId() {
            return this.m_id;
    }

    public void setId(int id) {
            this.m_id = id;
    }

    public String getContent() {
            return this.m_content;
    }

    public void setContent(String content) {
            this.m_content = content;
    }

    public boolean getRead() {
            return this.m_read;
    }

    public void setRead(boolean read) {
            this.m_read = read;
    }
    
    public Date getDateNotiFication() {
        return this.m_dateNotiFication;
    }

    public void setDateNotiFication(Date dateNotiFication) {
        this.m_dateNotiFication = dateNotiFication;
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
            if ( this.m_content != null ) {
                    lHashCode += this.m_content.hashCode();
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
            } else if (object instanceof Notification) {
                    Notification lNotificationObject = (Notification) object;
                    boolean lEquals = true;
                    lEquals &= this.m_id == lNotificationObject.m_id;
                    lEquals &= ((this.m_content == null ? lNotificationObject.m_content == null : this.m_content.equals(lNotificationObject.m_content))
                            || (this.m_content != null && this.m_content.equals(lNotificationObject.m_content)));
                    lEquals &= this.m_read == lNotificationObject.m_read;
                    return lEquals;
            }
            return false;
    }
    
    
}