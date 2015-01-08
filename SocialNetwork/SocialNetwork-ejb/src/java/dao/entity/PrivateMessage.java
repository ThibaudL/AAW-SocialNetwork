package dao.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
@DiscriminatorValue("PrivateMessage")
public class PrivateMessage extends Message {
    
    @OneToOne
    @JoinColumn(name = "user_fk")
    public User user;

    @Override
    public int hashCode() {
        int lHashCode = 0;
        if ( this.user != null ) {
            lHashCode += this.user.hashCode();
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
        } else if (object instanceof PrivateMessage) {
            PrivateMessage lPrivateMessageObject = (PrivateMessage) object;
            boolean lEquals = true;
            lEquals &= ((this.user == lPrivateMessageObject.user)
                || (this.user != null && this.user.equals(lPrivateMessageObject.user)));
            return lEquals;
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}