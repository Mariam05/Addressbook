package AddressBook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNumber;

    public BuddyInfo(){
        this.name = "John Doe";
//        this.phoneNumber = ""
    }

    public BuddyInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return this.name + " " + this.getPhoneNumber();
    }

}
