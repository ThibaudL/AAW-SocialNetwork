package dao.entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Wall {
    
    @Id
    private int m_id;

    @OneToMany(mappedBy="Wall")
    public List<PublicMessage> m_messages;

    public Wall() {
        m_messages = new ArrayList<>();
    }
  
    public int getId() {
            return this.m_id;
    }

    public void setId(int id) {
            this.m_id = id;
    }

    @Override
    public int hashCode() {
            int lHashCode = 0;
            if ( this.m_messages != null ) {
                    lHashCode += this.m_messages.hashCode();
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
            } else if (object instanceof Wall) {
                    Wall lWallObject = (Wall) object;
                    boolean lEquals = true;
                    lEquals &= this.m_id == lWallObject.m_id;
                    lEquals &= ((this.m_messages == lWallObject.m_messages)
                            || (this.m_messages != null && this.m_messages.equals(lWallObject.m_messages)));
                    return lEquals;
            }
            return false;
    }
}