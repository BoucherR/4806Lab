package AppStarter.JpaMemoryDatabase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class representing an address book, comprised of models.AppStarter.JpaMemoryDatabase.BuddyInfo objects
 */
@Entity
public class AddressBook {

    @Id @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private Collection<BuddyInfo> buddyList;

    public AddressBook() {
        buddyList = new ArrayList<BuddyInfo>();
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Collection<BuddyInfo> getBuddyList() {
        return buddyList;
    }
    public void setBuddyList(Collection<BuddyInfo> buddyList) {
        this.buddyList = buddyList;
    }
}
