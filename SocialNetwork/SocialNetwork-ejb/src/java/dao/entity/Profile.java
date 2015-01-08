@Entity
public class Profile {
    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String picture;
    private String information;
    public User unnamed_User_;

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        throw new UnsupportedOperationException();
    }

    public void setSurname(String surname) {
        throw new UnsupportedOperationException();
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPicture() {
        return this.picture;
    }

    @Entity
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int hashCode() {
        int lHashCode = 0;
        if ( this.firstname != null ) {
            lHashCode += this.firstname.hashCode();
        }
        if ( this.lastname != null ) {
            lHashCode += this.lastname.hashCode();
        }
        if ( this.birthdate != null ) {
            lHashCode += this.birthdate.hashCode();
        }
        if ( this.picture != null ) {
            lHashCode += this.picture.hashCode();
        }
        if ( this.information != null ) {
            lHashCode += this.information.hashCode();
        }
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
        } else if (object instanceof Profile) {
            Profile lProfileObject = (Profile) object;
            boolean lEquals = true;
            lEquals &= this.id == lProfileObject.id;
            lEquals &= ((this.firstname == lProfileObject.firstname)
                || (this.firstname != null && this.firstname.equals(lProfileObject.firstname)));
            lEquals &= ((this.lastname == lProfileObject.lastname)
                || (this.lastname != null && this.lastname.equals(lProfileObject.lastname)));
            lEquals &= ((this.birthdate == lProfileObject.birthdate)
                || (this.birthdate != null && this.birthdate.equals(lProfileObject.birthdate)));
            lEquals &= ((this.picture == lProfileObject.picture)
                || (this.picture != null && this.picture.equals(lProfileObject.picture)));
            lEquals &= ((this.information == lProfileObject.information)
                || (this.information != null && this.information.equals(lProfileObject.information)));
            lEquals &= ((this.unnamed_User_ == lProfileObject.unnamed_User_)
                || (this.unnamed_User_ != null && this.unnamed_User_.equals(lProfileObject.unnamed_User_)));
            return lEquals;
        }
        return false;
    }
}