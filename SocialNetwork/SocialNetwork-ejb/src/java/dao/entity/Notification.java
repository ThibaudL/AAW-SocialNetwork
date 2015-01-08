package dao.entity;
public class Notification {
	private int m_id;
	private String m_content;
	private boolean m_read;

	public int getId() {
		return this.m_id;
	}

	public void setId(int id) {
		this.m_id = id;
	}

	public String getContent() {
		return this.m_content;
	}

	public void setContent(String content) {
		this.m_content = content;
	}

	public boolean getRead() {
		return this.m_read;
	}

	public void setRead(boolean read) {
		this.m_read = read;
	}

	public int hashCode() {
		int lHashCode = 0;
		if ( this.m_content != null ) {
			lHashCode += this.m_content.hashCode();
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
			lEquals &= this.m_id == lNotificationObject.m_id;
			lEquals &= ((this.m_content == lNotificationObject.m_content)
				|| (this.m_content != null && this.m_content.equals(lNotificationObject.m_content)));
			lEquals &= this.m_read == lNotificationObject.m_read;
			return lEquals;
		}
		return false;
	}
}