package dao.entity;
import java.util.ArrayList;

public class MessageBox {
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
		} else if (object instanceof MessageBox) {
			MessageBox lMessageBoxObject = (MessageBox) object;
			boolean lEquals = true;
			lEquals &= this.m_id == lMessageBoxObject.m_id;
			lEquals &= ((this.m_messages == lMessageBoxObject.m_messages)
				|| (this.m_messages != null && this.m_messages.equals(lMessageBoxObject.m_messages)));
			return lEquals;
		}
		return false;
	}
}