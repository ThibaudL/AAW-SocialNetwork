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
@Table(name = "Comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "message_fk")
    public Message comment;
    
    @OneToOne
    @JoinColumn(name = "source_fk")
    public Message source;

    @Override
    public int hashCode() {
        int lHashCode = 0;
        if ( this.comment != null ) {
            lHashCode += this.comment.hashCode();
        }
        if ( this.source != null ) {
            lHashCode += this.source.hashCode();
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
        } else if (object instanceof Comment) {
            Comment lCommentsObject = (Comment) object;
            boolean lEquals = true;
            lEquals &= ((this.comment == lCommentsObject.comment)
                || (this.comment != null && this.comment.equals(lCommentsObject.comment)));
            lEquals &= ((this.source == lCommentsObject.source)
                || (this.source != null && this.source.equals(lCommentsObject.source)));
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
    
      public Message getComment() {
        return comment;
    }

    public void setComment(Message comment) {
        this.comment = comment;
    }

    public Message getSource() {
        return source;
    }

    public void setSource(Message source) {
        this.source = source;
    }
}