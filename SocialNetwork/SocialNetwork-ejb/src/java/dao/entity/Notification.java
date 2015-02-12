package dao.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "Notifications")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column
    private String content;
    
    @Column
    private boolean readed;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNotif;
    
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
        return this.readed;
    }

    public void setRead(boolean read) {
        this.readed = read;
    }

    public Date getDate() {
        return this.dateNotif;
    }

    public void setDate(Date date) {
        this.dateNotif = date;
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
        if ( this.dateNotif != null ) {
            lHashCode += this.dateNotif.hashCode();
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
            lEquals &= Objects.equals(this.id, lNotificationObject.id);
            lEquals &= ((this.content == null ? lNotificationObject.content == null : this.content.equals(lNotificationObject.content))
                || (this.content != null && this.content.equals(lNotificationObject.content)));
            lEquals &= Objects.equals(this.readed, lNotificationObject.readed);
            lEquals &= ((this.dateNotif == lNotificationObject.dateNotif)
                || (this.dateNotif != null && this.dateNotif.equals(lNotificationObject.dateNotif)));
            return lEquals;
        }
        return false;
    }
}