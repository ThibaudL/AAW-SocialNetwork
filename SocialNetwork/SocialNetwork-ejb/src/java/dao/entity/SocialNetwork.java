package dao.entity;
import java.util.ArrayList;

public class SocialNetwork {
	public ArrayList<User> m_users = new ArrayList<User>();

	public int hashCode() {
		int lHashCode = 0;
		if ( this.m_users != null ) {
			lHashCode += this.m_users.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof SocialNetwork) {
			SocialNetwork lSocialNetworkObject = (SocialNetwork) object;
			boolean lEquals = true;
			lEquals &= ((this.m_users == lSocialNetworkObject.m_users)
				|| (this.m_users != null && this.m_users.equals(lSocialNetworkObject.m_users)));
			return lEquals;
		}
		return false;
	}
}