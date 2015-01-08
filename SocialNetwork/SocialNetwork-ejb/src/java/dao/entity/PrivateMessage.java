@Entity
public class PrivateMessage extends Message {
    public User unnamed_User_;

    public int hashCode() {
        int lHashCode = 0;
        if ( this.unnamed_User_ != null ) {
            lHashCode += this.unnamed_User_.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof PrivateMessage) {
            PrivateMessage lPrivateMessageObject = (PrivateMessage) object;
            boolean lEquals = true;
            lEquals &= ((this.unnamed_User_ == lPrivateMessageObject.unnamed_User_)
                || (this.unnamed_User_ != null && this.unnamed_User_.equals(lPrivateMessageObject.unnamed_User_)));
            return lEquals;
        }
        return false;
    }
}