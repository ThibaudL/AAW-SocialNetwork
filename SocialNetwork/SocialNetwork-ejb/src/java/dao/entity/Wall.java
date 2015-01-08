package dao.entity;
import java.util.ArrayList;

public class Wall {
	private int m_id;
	public ArrayList<Message> m_messages = new ArrayList<Message>();

	public int getId() {
		return this.m_id;
	}

	public void setId(int id) {
		this.m_id = id;
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this.m_messages != null ) {
			lHashCode += this.m_messages.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object instanceof Wall) {
			Wall lWallObject = (Wall) object;
			boolean lEquals = true;
			lEquals &= this.m_id == lWallObject.m_id;
			lEquals &= ((this.m_messages == lWallObject.m_messages)
				|| (this.m_messages != null && this.m_messages.equals(lWallObject.m_messages)));
			return lEquals;
		}
		return false;
	}
}