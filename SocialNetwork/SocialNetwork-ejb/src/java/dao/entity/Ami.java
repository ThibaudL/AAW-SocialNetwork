@Entity
public class Ami {
    public User user;
    public User friend;

    public int hashCode() {
        int lHashCode = 0;
        if ( this.user != null ) {
            lHashCode += this.user.hashCode();
        }
        if ( this.friend != null ) {
            lHashCode += this.friend.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Ami) {
            Ami lAmiObject = (Ami) object;
            boolean lEquals = true;
            lEquals &= ((this.user == lAmiObject.user)
                || (this.user != null && this.user.equals(lAmiObject.user)));
            lEquals &= ((this.friend == lAmiObject.friend)
                || (this.friend != null && this.friend.equals(lAmiObject.friend)));
            return lEquals;
        }
        return false;
    }
}