@Entity
public class Notification {
    private int id;
    private String content;
    private boolean read;
    private Date date;
    public User notifications;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int hashCode() {
        int lHashCode = 0;
        if ( this.content != null ) {
            lHashCode += this.content.hashCode();
        }
        if ( this.date != null ) {
            lHashCode += this.date.hashCode();
        }
        if ( this.notifications != null ) {
            lHashCode += this.notifications.hashCode();
        }
        if ( lHashCode == 0 ) {
            lHashCode = super.hashCode();
        }
        return lHashCode;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Notification) {
            Notification lNotificationObject = (Notification) object;
            boolean lEquals = true;
            lEquals &= this.id == lNotificationObject.id;
            lEquals &= ((this.content == lNotificationObject.content)
                || (this.content != null && this.content.equals(lNotificationObject.content)));
            lEquals &= this.read == lNotificationObject.read;
            lEquals &= ((this.date == lNotificationObject.date)
                || (this.date != null && this.date.equals(lNotificationObject.date)));
            lEquals &= ((this.notifications == lNotificationObject.notifications)
                || (this.notifications != null && this.notifications.equals(lNotificationObject.notifications)));
            return lEquals;
        }
        return false;
    }
}