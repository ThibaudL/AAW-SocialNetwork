package dao.entity;


import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class User {
    private int id;
    private String email;
    private String password;
    public ArrayList<Ami> friends = new ArrayList<Ami>();
    public SocialNetwork users;
    public PublicMessage unnamed_PublicMessage_;
    public ArrayList<Notification> notifications = new ArrayList<Notification>();
    public PrivateMessage unnamed_PrivateMessage_;
    public Profile unnamed_Profile_;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public int hashCode() {
        int lHashCode = 0;
        if ( this.email != null ) {
            lHashCode += this.email.hashCode();
        }
        if ( this.password != null ) {
            lHashCode += this.password.hashCode();
        }
        if ( this.friends != null ) {
            lHashCode += this.friends.hashCode();
        }
        if ( this.users != null ) {
            lHashCode += this.users.hashCode();
        }
        if ( this.unnamed_PublicMessage_ != null ) {
            lHashCode += this.unnamed_PublicMessage_.hashCode();
        }
        if ( this.notifications != null ) {
            lHashCode += this.notifications.hashCode();
        }
        if ( this.unnamed_PrivateMessage_ != null ) {
            lHashCode += this.unnamed_PrivateMessage_.hashCode();
        }
        if ( this.unnamed_Profile_ != null ) {
            lHashCode += this.unnamed_Profile_.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof User) {
            User lUserObject = (User) object;
            boolean lEquals = true;
            lEquals &= this.id == lUserObject.id;
            lEquals &= ((this.email == lUserObject.email)
                || (this.email != null && this.email.equals(lUserObject.email)));
            lEquals &= ((this.password == lUserObject.password)
                || (this.password != null && this.password.equals(lUserObject.password)));
            lEquals &= ((this.friends == lUserObject.friends)
                || (this.friends != null && this.friends.equals(lUserObject.friends)));
            lEquals &= ((this.users == lUserObject.users)
                || (this.users != null && this.users.equals(lUserObject.users)));
            lEquals &= ((this.unnamed_PublicMessage_ == lUserObject.unnamed_PublicMessage_)
                || (this.unnamed_PublicMessage_ != null && this.unnamed_PublicMessage_.equals(lUserObject.unnamed_PublicMessage_)));
            lEquals &= ((this.notifications == lUserObject.notifications)
                || (this.notifications != null && this.notifications.equals(lUserObject.notifications)));
            lEquals &= ((this.unnamed_PrivateMessage_ == lUserObject.unnamed_PrivateMessage_)
                || (this.unnamed_PrivateMessage_ != null && this.unnamed_PrivateMessage_.equals(lUserObject.unnamed_PrivateMessage_)));
            lEquals &= ((this.unnamed_Profile_ == lUserObject.unnamed_Profile_)
                || (this.unnamed_Profile_ != null && this.unnamed_Profile_.equals(lUserObject.unnamed_Profile_)));
            return lEquals;
        }
        return false;
    }
}