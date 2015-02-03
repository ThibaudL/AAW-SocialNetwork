package dao.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
@DiscriminatorValue("PublicMessage")
public class PublicMessage extends Message {
    
    @OneToOne
    @JoinColumn(name = "user_fk")
    public User user;

    @Override
    public int hashCode() {
        int lHashCode = 0;
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof PublicMessage) {
            PublicMessage lPublicMessageObject = (PublicMessage) object;
            boolean lEquals = true;
            lEquals &= ((this.user == lPublicMessageObject.user)
                || (this.user != null && this.user.equals(lPublicMessageObject.user)));
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