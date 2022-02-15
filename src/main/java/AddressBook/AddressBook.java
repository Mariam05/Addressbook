package AddressBook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity=BuddyInfo.class)
    private List<BuddyInfo> buddyInfoList;

    private String name;

    protected AddressBook(){
        this("AddressBook");
    }

    public AddressBook(String name){
        this.name = name;
        buddyInfoList = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public void addBuddy(BuddyInfo buddyInfo) {
        buddyInfoList.add(buddyInfo);
    }

    public List<BuddyInfo> getBuddyInfoList(){
        return this.buddyInfoList;
    }

    public void removeBuddy(Long buddyID) {
        buddyInfoList.removeIf(buddyInfo -> buddyInfo.getId() == buddyID);
    }

    @Override
    public String toString(){
        String str = "AddressBook " + this.name + "\n";
//        for (BuddyInfo bud : buddyInfoList){
//            str += bud.toString();
//        }
        return str;
    }

}
