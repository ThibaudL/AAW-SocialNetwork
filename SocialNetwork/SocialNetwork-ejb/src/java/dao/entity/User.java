package dao.entity;
import java.sql.Date;
import java.util.ArrayList;

public class User {
	private int m_id;
	private String m_firstname;
	private String m_surname;
	private String m_email;
	private String m_password;
	private Date m_birthdate;
	private String m_picture;
	private String m_information;
	public ArrayList<User> m_friends = new ArrayList<User>();
	public Wall m_wall;
	public ArrayList<Notification> m_notifications = new ArrayList<Notification>();
	public MessageBox m_messageBox;

	public String getInformation() {
		return this.m_information;
	}

	public void setInformation(String information) {
		this.m_information = information;
	}

	public int getId() {
		return this.m_id;
	}

	public void setId(int id) {
		this.m_id = id;
	}

	public String getFirstname() {
		return this.m_firstname;
	}

	public void setFirstname(String firstname) {
		this.m_firstname = firstname;
	}

	public String getSurname() {
		return this.m_surname;
	}

	public void setSurname(String surname) {
		this.m_surname = surname;
	}

	public String getEmail() {
		return this.m_email;
	}

	public void setEmail(String email) {
		this.m_email = email;
	}

	public String getPassword() {
		return this.m_password;
	}

	public void setPassword(String password) {
		this.m_password = password;
	}

	public Date getBirthdate() {
		return this.m_birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.m_birthdate = birthdate;
	}

	public String getPicture() {
		return this.m_picture;
	}

	public void setPicture(String picture) {
		this.m_picture = picture;
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this.m_firstname != null ) {
			lHashCode += this.m_firstname.hashCode();
		}
		if ( this.m_surname != null ) {
			lHashCode += this.m_surname.hashCode();
		}
		if ( this.m_email != null ) {
			lHashCode += this.m_email.hashCode();
		}
		if ( this.m_password != null ) {
			lHashCode += this.m_password.hashCode();
		}
		if ( this.m_birthdate != null ) {
			lHashCode += this.m_birthdate.hashCode();
		}
		if ( this.m_picture != null ) {
			lHashCode += this.m_picture.hashCode();
		}
		if ( this.m_information != null ) {
			lHashCode += this.m_information.hashCode();
		}
		if ( this.m_friends != null ) {
			lHashCode += this.m_friends.hashCode();
		}
		if ( this.m_wall != null ) {
			lHashCode += this.m_wall.hashCode();
		}
		if ( this.m_notifications != null ) {
			lHashCode += this.m_notifications.hashCode();
		}
		if ( this.m_messageBox != null ) {
			lHashCode += this.m_messageBox.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof User) {
			User lUserObject = (User) object;
			boolean lEquals = true;
			lEquals &= this.m_id == lUserObject.m_id;
			lEquals &= ((this.m_firstname == lUserObject.m_firstname)
				|| (this.m_firstname != null && this.m_firstname.equals(lUserObject.m_firstname)));
			lEquals &= ((this.m_surname == lUserObject.m_surname)
				|| (this.m_surname != null && this.m_surname.equals(lUserObject.m_surname)));
			lEquals &= ((this.m_email == lUserObject.m_email)
				|| (this.m_email != null && this.m_email.equals(lUserObject.m_email)));
			lEquals &= ((this.m_password == lUserObject.m_password)
				|| (this.m_password != null && this.m_password.equals(lUserObject.m_password)));
			lEquals &= ((this.m_birthdate == lUserObject.m_birthdate)
				|| (this.m_birthdate != null && this.m_birthdate.equals(lUserObject.m_birthdate)));
			lEquals &= ((this.m_picture == lUserObject.m_picture)
				|| (this.m_picture != null && this.m_picture.equals(lUserObject.m_picture)));
			lEquals &= ((this.m_information == lUserObject.m_information)
				|| (this.m_information != null && this.m_information.equals(lUserObject.m_information)));
			lEquals &= ((this.m_friends == lUserObject.m_friends)
				|| (this.m_friends != null && this.m_friends.equals(lUserObject.m_friends)));
			lEquals &= ((this.m_wall == lUserObject.m_wall)
				|| (this.m_wall != null && this.m_wall.equals(lUserObject.m_wall)));
			lEquals &= ((this.m_notifications == lUserObject.m_notifications)
				|| (this.m_notifications != null && this.m_notifications.equals(lUserObject.m_notifications)));
			lEquals &= ((this.m_messageBox == lUserObject.m_messageBox)
				|| (this.m_messageBox != null && this.m_messageBox.equals(lUserObject.m_messageBox)));
			return lEquals;
		}
		return false;
	}
}