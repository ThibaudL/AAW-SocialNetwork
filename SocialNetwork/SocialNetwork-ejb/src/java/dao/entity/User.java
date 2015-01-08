package dao.entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int m_id;

    @Column
    private String m_firstname;
    @Column
    private String m_surname;
    @Column
    private String m_email;
    @Column
    private String m_password;
    @Temporal(TemporalType.DATE)
    private Date m_birthdate;
    @Column
    private String m_picture;
    @Column
    private String m_information;

    @OneToMany(mappedBy="User")
    public List<User> m_friends;
    
    @ManyToOne
    @JoinColumn(name="user_fk")
    private User m_user;

    @OneToOne(mappedBy = "User")
    public Wall m_wall;

    @OneToMany(mappedBy="User")
    public List<Notification> m_notifications;
    
    @OneToOne(mappedBy = "User")
    public MessageBox m_messageBox;

    public User() {
        this.m_friends = new ArrayList<>();
        this.m_notifications = new ArrayList<>();
    }



    public String getInformation() {
            return this.m_information;
    }

    public void setInformation(String information) {
            this.m_information = information;
    }

    public int getId() {
            return this.m_id;
    }

    public void setId(int id) {
            this.m_id = id;
    }

    public String getFirstname() {
            return this.m_firstname;
    }

    public void setFirstname(String firstname) {
            this.m_firstname = firstname;
    }

    public String getSurname() {
            return this.m_surname;
    }

    public void setSurname(String surname) {
            this.m_surname = surname;
    }

    public String getEmail() {
            return this.m_email;
    }

    public void setEmail(String email) {
            this.m_email = email;
    }

    public String getPassword() {
            return this.m_password;
    }

    public void setPassword(String password) {
            this.m_password = password;
    }

    public Date getBirthdate() {
            return this.m_birthdate;
    }

    public void setBirthdate(Date birthdate) {
            this.m_birthdate = birthdate;
    }

    public String getPicture() {
            return this.m_picture;
    }

    public void setPicture(String picture) {
            this.m_picture = picture;
    }

    public User getUser() {
        return m_user;
    }

    public void setUser(User m_user) {
        this.m_user = m_user;
    }

    public Wall getWall() {
        return m_wall;
    }

    public void setWall(Wall m_wall) {
        this.m_wall = m_wall;
    }

    public MessageBox getMessageBox() {
        return m_messageBox;
    }

    public void setMessageBox(MessageBox m_messageBox) {
        this.m_messageBox = m_messageBox;
    }

    public List<User> getFriends() {
        return m_friends;
    }

    public void setFriends(List<User> m_friends) {
        this.m_friends = m_friends;
    }

    public List<Notification> getNotifications() {
        return m_notifications;
    }

    public void setNotifications(List<Notification> m_notifications) {
        this.m_notifications = m_notifications;
    }

    public boolean addFriend(User friend) {
        return m_friends.add(friend);
    }

    public boolean removeFriend(User friend) {
        return m_friends.remove(friend);
    }

    public boolean addNotification(Notification n) {
        return m_notifications.add(n);
    }

    public boolean removeNotification(Notification n) {
        return m_notifications.remove(n);
    }
    
    
    
    
    

    @Override
    public int hashCode() {
            int lHashCode = 0;
            if ( this.m_firstname != null ) {
                    lHashCode += this.m_firstname.hashCode();
            }
            if ( this.m_surname != null ) {
                    lHashCode += this.m_surname.hashCode();
            }
            if ( this.m_email != null ) {
                    lHashCode += this.m_email.hashCode();
            }
            if ( this.m_password != null ) {
                    lHashCode += this.m_password.hashCode();
            }
            if ( this.m_birthdate != null ) {
                    lHashCode += this.m_birthdate.hashCode();
            }
            if ( this.m_picture != null ) {
                    lHashCode += this.m_picture.hashCode();
            }
            if ( this.m_information != null ) {
                    lHashCode += this.m_information.hashCode();
            }
            if ( this.m_friends != null ) {
                    lHashCode += this.m_friends.hashCode();
            }
            if ( this.m_wall != null ) {
                    lHashCode += this.m_wall.hashCode();
            }
            if ( this.m_notifications != null ) {
                    lHashCode += this.m_notifications.hashCode();
            }
            if ( this.m_messageBox != null ) {
                    lHashCode += this.m_messageBox.hashCode();
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
            } else if (object instanceof User) {
                    User lUserObject = (User) object;
                    boolean lEquals = true;
                    lEquals &= this.m_id == lUserObject.m_id;
                    lEquals &= ((this.m_firstname == null ? lUserObject.m_firstname == null : this.m_firstname.equals(lUserObject.m_firstname))
                            || (this.m_firstname != null && this.m_firstname.equals(lUserObject.m_firstname)));
                    lEquals &= ((this.m_surname == null ? lUserObject.m_surname == null : this.m_surname.equals(lUserObject.m_surname))
                            || (this.m_surname != null && this.m_surname.equals(lUserObject.m_surname)));
                    lEquals &= ((this.m_email == null ? lUserObject.m_email == null : this.m_email.equals(lUserObject.m_email))
                            || (this.m_email != null && this.m_email.equals(lUserObject.m_email)));
                    lEquals &= ((this.m_password == null ? lUserObject.m_password == null : this.m_password.equals(lUserObject.m_password))
                            || (this.m_password != null && this.m_password.equals(lUserObject.m_password)));
                    lEquals &= ((this.m_birthdate == lUserObject.m_birthdate)
                            || (this.m_birthdate != null && this.m_birthdate.equals(lUserObject.m_birthdate)));
                    lEquals &= ((this.m_picture == null ? lUserObject.m_picture == null : this.m_picture.equals(lUserObject.m_picture))
                            || (this.m_picture != null && this.m_picture.equals(lUserObject.m_picture)));
                    lEquals &= ((this.m_information == null ? lUserObject.m_information == null : this.m_information.equals(lUserObject.m_information))
                            || (this.m_information != null && this.m_information.equals(lUserObject.m_information)));
                    lEquals &= ((this.m_friends == lUserObject.m_friends)
                            || (this.m_friends != null && this.m_friends.equals(lUserObject.m_friends)));
                    lEquals &= ((this.m_wall == lUserObject.m_wall)
                            || (this.m_wall != null && this.m_wall.equals(lUserObject.m_wall)));
                    lEquals &= ((this.m_notifications == lUserObject.m_notifications)
                            || (this.m_notifications != null && this.m_notifications.equals(lUserObject.m_notifications)));
                    lEquals &= ((this.m_messageBox == lUserObject.m_messageBox)
                            || (this.m_messageBox != null && this.m_messageBox.equals(lUserObject.m_messageBox)));
                    return lEquals;
            }
            return false;
    }
}