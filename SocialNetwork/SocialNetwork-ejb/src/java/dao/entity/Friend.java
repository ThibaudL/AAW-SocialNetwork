package dao.entity;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Friends")
public class Friend implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "user_fk")
    public User user;
    
    @OneToOne
    @JoinColumn(name = "friend_fk")
    public User friend;

    @Override
    public int hashCode() {
        int lHashCode = 0;
      /*  if ( this.user != null ) {
            lHashCode += this.user.hashCode();
        }
        if ( this.friend != null ) {
            lHashCode += this.friend.hashCode();
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
        } else if (object instanceof Friend) {
            Friend lAmiObject = (Friend) object;
            boolean lEquals = true;
            lEquals &= ((this.user == lAmiObject.user)
                || (this.user != null && this.user.equals(lAmiObject.user)));
            lEquals &= ((this.friend == lAmiObject.friend)
                || (this.friend != null && this.friend.equals(lAmiObject.friend)));
            return lEquals;
        }
        return false;
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

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}