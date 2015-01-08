package dao.entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

public class SocialNetwork {
    @OneToMany(mappedBy = "SocialNetwork")
    private List<User> m_users;

    public SocialNetwork() {
        this.m_users = new ArrayList<User>();
    }

    public List<User> getUsers() {
        return m_users;
    }

    public void setUsers(List<User> users) {
        this.m_users = users;
    }

    public boolean addUser(User e) {
        return m_users.add(e);
    }

    public boolean removeUser(Object o) {
        return m_users.remove(o);
    }

    @Override
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

    @Override
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