package dao.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
@DiscriminatorValue("PublicMessage")
public class PublicMessage extends Message {
    
    @Override
    public int hashCode() {
        int lHashCode = 0;
        
        return lHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } 
        return false;
    }
    
    
}