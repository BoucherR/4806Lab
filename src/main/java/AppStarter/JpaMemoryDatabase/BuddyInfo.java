package AppStarter.JpaMemoryDatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Class representing information of "buddies," corresponding to a name and phone-number for a given buddy
 */
@Entity
public class BuddyInfo {

    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private AddressBook addressBook;
    private String name;
    private Long phoneNumber;

    public BuddyInfo() {
        this.name = null;
        this.phoneNumber = null;
        this.id = null;
    }

    public BuddyInfo(String name, Long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressBook getAddressBook() {
        return this.addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getName() {
        return name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "BuddyInfo{" +
                "id=" + id +
                ", addressBook=" + addressBook +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

