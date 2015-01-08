@Entity
public class Comments {
    public Message comments;
    public Message comment;
    public Message source;

    public int hashCode() {
        int lHashCode = 0;
        if ( this.comments != null ) {
            lHashCode += this.comments.hashCode();
        }
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

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Comments) {
            Comments lCommentsObject = (Comments) object;
            boolean lEquals = true;
            lEquals &= ((this.comments == lCommentsObject.comments)
                || (this.comments != null && this.comments.equals(lCommentsObject.comments)));
            lEquals &= ((this.comment == lCommentsObject.comment)
                || (this.comment != null && this.comment.equals(lCommentsObject.comment)));
            lEquals &= ((this.source == lCommentsObject.source)
                || (this.source != null && this.source.equals(lCommentsObject.source)));
            return lEquals;
        }
        return false;
    }
}