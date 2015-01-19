package dao.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column
    private String email;
    @Column
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date subcriptionDate;
    
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<PublicMessage> publicMessages = new ArrayList<>();
    
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<Notification> notifications = new ArrayList<>();
    
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<PrivateMessage> privateMessages = new ArrayList<>();
    
    @OneToOne(mappedBy="user", fetch = FetchType.LAZY)
    private Profile profile;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSubcriptionDate() {
        return subcriptionDate;
    }

    public void setSubcriptionDate(Date subcriptionDate) {
        this.subcriptionDate = subcriptionDate;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<PublicMessage> getPublicMessages() {
        return publicMessages;
    }

    public void setPublicMessages(List<PublicMessage> publicMessages) {
        this.publicMessages = publicMessages;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<PrivateMessage> getPrivateMessages() {
        return privateMessages;
    }

    public void setPrivateMessages(List<PrivateMessage> privateMessages) {
        this.privateMessages = privateMessages;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean addFriend(Friend f) {
        return friends.add(f);
    }

    public boolean removeFriend(Friend f) {
        return friends.remove(f);
    }

    public boolean addNotification(Notification n) {
        return notifications.add(n);
    }

    public boolean removeNotification(Notification n) {
        return notifications.remove(n);
    }

    public boolean addPrivateMessage(PrivateMessage pm) {
        return privateMessages.add(pm);
    }

    public boolean removePrivateMessage(PrivateMessage pm) {
        return privateMessages.remove(pm);
    }

    public boolean addPublicMessage(PublicMessage pm) {
        return publicMessages.add(pm);
    }

    public boolean removePublicMessage(PublicMessage pm) {
        return publicMessages.remove(pm);
    }
    

    @Override
    public int hashCode() {
        int lHashCode = 0;
      /*  if ( this.email != null ) {
            lHashCode += this.email.hashCode();
        }
        if ( this.password != null ) {
            lHashCode += this.password.hashCode();
        }
        if ( this.friends != null ) {
            lHashCode += this.friends.hashCode();
        }
        if ( this.publicMessages != null ) {
            lHashCode += this.publicMessages.hashCode();
        }
        if ( this.notifications != null ) {
            lHashCode += this.notifications.hashCode();
        }
        if ( this.privateMessages != null ) {
            lHashCode += this.privateMessages.hashCode();
        }
        if ( this.profile != null ) {
            lHashCode += this.profile.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }*/
        return lHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof User) {
            User lUserObject = (User) object;
            boolean lEquals = true;
            lEquals &= Objects.equals(this.id, lUserObject.id);
            lEquals &= ((this.email == null ? lUserObject.email == null : this.email.equals(lUserObject.email))
                || (this.email != null && this.email.equals(lUserObject.email)));
            lEquals &= ((this.password == null ? lUserObject.password == null : this.password.equals(lUserObject.password))
                || (this.password != null && this.password.equals(lUserObject.password)));
            lEquals &= ((this.friends == lUserObject.friends)
                || (this.friends != null && this.friends.equals(lUserObject.friends)));
            lEquals &= ((this.publicMessages == lUserObject.publicMessages)
                || (this.publicMessages != null && this.publicMessages.equals(lUserObject.publicMessages)));
            lEquals &= ((this.notifications == lUserObject.notifications)
                || (this.notifications != null && this.notifications.equals(lUserObject.notifications)));
            lEquals &= ((this.privateMessages == lUserObject.privateMessages)
                || (this.privateMessages != null && this.privateMessages.equals(lUserObject.privateMessages)));
            lEquals &= ((this.profile == lUserObject.profile)
                || (this.profile != null && this.profile.equals(lUserObject.profile)));
            return lEquals;
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "";
        str += id;
        str += " ";
        str += this.email;
        str += " ";
        str += this.password;
        str += " ";
        str += this.friends;
        str += " ";
        str += this.publicMessages;
        str += " ";
        str += this.notifications;
        str += " ";
        str += this.privateMessages;
        str += " ";
        str += this.profile;
        return str;
    }
    
    
}