package dao.entity;


import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class SocialNetwork {
    public ArrayList<User> users = new ArrayList<User>();

    public int hashCode() {
        int lHashCode = 0;
        if ( this.users != null ) {
            lHashCode += this.users.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof SocialNetwork) {
            SocialNetwork lSocialNetworkObject = (SocialNetwork) object;
            boolean lEquals = true;
            lEquals &= ((this.users == lSocialNetworkObject.users)
                || (this.users != null && this.users.equals(lSocialNetworkObject.users)));
            return lEquals;
        }
        return false;
    }
}