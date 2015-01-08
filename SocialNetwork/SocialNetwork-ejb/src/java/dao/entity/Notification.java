package dao.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Notification implements Serializable {
    @Id
    private Integer id;
    
    @Column
    private String content;
    
    @Column
    private boolean read;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToOne
    @JoinColumn(name = "user_fk")
    public User user;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    @Override
    public int hashCode() {
        int lHashCode = 0;
        if ( this.content != null ) {
            lHashCode += this.content.hashCode();
        }
        if ( this.date != null ) {
            lHashCode += this.date.hashCode();
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
            lEquals &= this.id == lNotificationObject.id;
            lEquals &= ((this.content == lNotificationObject.content)
                || (this.content != null && this.content.equals(lNotificationObject.content)));
            lEquals &= this.read == lNotificationObject.read;
            lEquals &= ((this.date == lNotificationObject.date)
                || (this.date != null && this.date.equals(lNotificationObject.date)));
            return lEquals;
        }
        return false;
    }
}