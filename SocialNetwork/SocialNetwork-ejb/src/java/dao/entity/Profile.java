package dao.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column
    private String picture;
    @Column
    private String information;
    
    @OneToOne
    @JoinColumn(name="user_fk")
    public User user;

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

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    @Override
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
        if ( this.user != null ) {
            lHashCode += this.user.hashCode();
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
        } else if (object instanceof Profile) {
            Profile lProfileObject = (Profile) object;
            boolean lEquals = true;
            lEquals &= this.id == lProfileObject.id;
            lEquals &= ((this.firstname == null ? lProfileObject.firstname == null : this.firstname.equals(lProfileObject.firstname))
                || (this.firstname != null && this.firstname.equals(lProfileObject.firstname)));
            lEquals &= ((this.lastname == null ? lProfileObject.lastname == null : this.lastname.equals(lProfileObject.lastname))
                || (this.lastname != null && this.lastname.equals(lProfileObject.lastname)));
            lEquals &= ((this.birthdate == lProfileObject.birthdate)
                || (this.birthdate != null && this.birthdate.equals(lProfileObject.birthdate)));
            lEquals &= ((this.picture == null ? lProfileObject.picture == null : this.picture.equals(lProfileObject.picture))
                || (this.picture != null && this.picture.equals(lProfileObject.picture)));
            lEquals &= ((this.information == null ? lProfileObject.information == null : this.information.equals(lProfileObject.information))
                || (this.information != null && this.information.equals(lProfileObject.information)));
            lEquals &= ((this.user == lProfileObject.user)
                || (this.user != null && this.user.equals(lProfileObject.user)));
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
}