package dao.entity;


import java.io.Serializable;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="messagetype",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Messages") 
public class Message implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "author_fk")
    private User author;
    
    @Column
    private String content;
    
    @OneToOne
    @JoinColumn(name = "destinataire_fk")
    private User destinataire;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToMany(mappedBy = "source")
    public List<Comment> comments = new ArrayList<>();

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getDestinataire() {
        return this.destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    

    @Override
    public int hashCode() {
        int lHashCode = 0;
        if ( this.author != null ) {
            lHashCode += this.author.hashCode();
        }
        if ( this.content != null ) {
            lHashCode += this.content.hashCode();
        }
        if ( this.destinataire != null ) {
            lHashCode += this.destinataire.hashCode();
        }
        if ( this.date != null ) {
            lHashCode += this.date.hashCode();
        }
        if ( this.comments != null ) {
            lHashCode += this.comments.hashCode();
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
        } else if (object instanceof Message) {
            Message lMessageObject = (Message) object;
            boolean lEquals = true;
            lEquals &= this.id == lMessageObject.id;
            lEquals &= ((this.author == lMessageObject.author)
                || (this.author != null && this.author.equals(lMessageObject.author)));
            lEquals &= ((null == this.content ? lMessageObject.content == null : this.content.equals(lMessageObject.content))
                || (this.content != null && this.content.equals(lMessageObject.content)));
            lEquals &= ((this.destinataire == lMessageObject.destinataire)
                || (this.destinataire != null && this.destinataire.equals(lMessageObject.destinataire)));
            lEquals &= ((this.date == lMessageObject.date)
                || (this.date != null && this.date.equals(lMessageObject.date)));
            lEquals &= ((this.comments == lMessageObject.comments)
                || (this.comments != null && this.comments.equals(lMessageObject.comments)));
            return lEquals;
        }
        return false;
    }
}