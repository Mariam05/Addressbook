package AddressBook;

import javax.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNumber;


    public BuddyInfo(){
        this("Jane Doe", "0123456789");
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
        return this.id + " " +  this.name + " " + this.getPhoneNumber();
    }

}
