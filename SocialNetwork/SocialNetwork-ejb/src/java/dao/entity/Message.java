package dao.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int m_id;
    
    @Column
    private User m_author;
    private String m_content;
    private User m_destinataire;
    private Date m_sendDate;
    private List<Message> m_comments;

    public Message() {
        m_comments = new ArrayList<>();
    }
    
    public Date getSendDate() {
        return m_sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.m_sendDate = sendDate;
    }
    

    public int getId() {
            return this.m_id;
    }

    public void setId(int id) {
            this.m_id = id;
    }

    public User getAuthor() {
            return this.m_author;
    }

    public void setAuthor(User author) {
            this.m_author = author;
    }

    public String getContent() {
            return this.m_content;
    }

    public void setContent(String content) {
            this.m_content = content;
    }

    public User getDestinataire() {
            return this.m_destinataire;
    }

    public void setDestinataire(User destinataire) {
            this.m_destinataire = destinataire;
    }

    public int hashCode() {
            int lHashCode = 0;
            if ( this.m_author != null ) {
                    lHashCode += this.m_author.hashCode();
            }
            if ( this.m_content != null ) {
                    lHashCode += this.m_content.hashCode();
            }
            if ( this.m_destinataire != null ) {
                    lHashCode += this.m_destinataire.hashCode();
            }
            if ( this.m_comments != null ) {
                    lHashCode += this.m_comments.hashCode();
            }
            if ( lHashCode == 0 ) {
                    lHashCode = super.hashCode();
            }
            return lHashCode;
    }

    public boolean equals(Object object) {
            if (this == object) {
                    return true;
            } else if (object instanceof Message) {
                    Message lMessageObject = (Message) object;
                    boolean lEquals = true;
                    lEquals &= this.m_id == lMessageObject.m_id;
                    lEquals &= ((this.m_author == lMessageObject.m_author)
                            || (this.m_author != null && this.m_author.equals(lMessageObject.m_author)));
                    lEquals &= ((this.m_content == lMessageObject.m_content)
                            || (this.m_content != null && this.m_content.equals(lMessageObject.m_content)));
                    lEquals &= ((this.m_destinataire == lMessageObject.m_destinataire)
                            || (this.m_destinataire != null && this.m_destinataire.equals(lMessageObject.m_destinataire)));
                    lEquals &= ((this.m_comments == lMessageObject.m_comments)
                            || (this.m_comments != null && this.m_comments.equals(lMessageObject.m_comments)));
                    return lEquals;
            }
            return false;
    }
}