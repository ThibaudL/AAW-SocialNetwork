import java.util.ArrayList;

@Entity
public class Message {
    private int id;
    private User author;
    private String content;
    private User destinataire;
    private Date date;
    public Comments comment;
    public Comments source;
    public ArrayList<Comments> comments = new ArrayList<Comments>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        if ( this.comment != null ) {
            lHashCode += this.comment.hashCode();
        }
        if ( this.source != null ) {
            lHashCode += this.source.hashCode();
        }
        if ( this.comments != null ) {
            lHashCode += this.comments.hashCode();
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
            lEquals &= this.id == lMessageObject.id;
            lEquals &= ((this.author == lMessageObject.author)
                || (this.author != null && this.author.equals(lMessageObject.author)));
            lEquals &= ((this.content == lMessageObject.content)
                || (this.content != null && this.content.equals(lMessageObject.content)));
            lEquals &= ((this.destinataire == lMessageObject.destinataire)
                || (this.destinataire != null && this.destinataire.equals(lMessageObject.destinataire)));
            lEquals &= ((this.date == lMessageObject.date)
                || (this.date != null && this.date.equals(lMessageObject.date)));
            lEquals &= ((this.comment == lMessageObject.comment)
                || (this.comment != null && this.comment.equals(lMessageObject.comment)));
            lEquals &= ((this.source == lMessageObject.source)
                || (this.source != null && this.source.equals(lMessageObject.source)));
            lEquals &= ((this.comments == lMessageObject.comments)
                || (this.comments != null && this.comments.equals(lMessageObject.comments)));
            return lEquals;
        }
        return false;
    }
}