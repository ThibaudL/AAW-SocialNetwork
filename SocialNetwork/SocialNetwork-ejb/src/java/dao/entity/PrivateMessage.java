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
    public User destinataire;

    @Override
    public int hashCode() {
        int lHashCode = 0;
        return lHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof PrivateMessage) {
            PrivateMessage lPrivateMessageObject = (PrivateMessage) object;
            boolean lEquals = true;
            lEquals &= ((this.destinataire == lPrivateMessageObject.destinataire)
                || (this.destinataire != null && this.destinataire.equals(lPrivateMessageObject.destinataire)));
            return lEquals;
        }
        return false;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }
    
    
}