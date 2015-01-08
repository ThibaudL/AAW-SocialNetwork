package dao.entity;


import javax.persistence.Entity;
@Entity
public class PublicMessage extends Message {
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
        } else if (object instanceof PublicMessage) {
            PublicMessage lPublicMessageObject = (PublicMessage) object;
            boolean lEquals = true;
            lEquals &= ((this.unnamed_User_ == lPublicMessageObject.unnamed_User_)
                || (this.unnamed_User_ != null && this.unnamed_User_.equals(lPublicMessageObject.unnamed_User_)));
            return lEquals;
        }
        return false;
    }
}