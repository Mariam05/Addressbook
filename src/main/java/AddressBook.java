import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<BuddyInfo> buddyInfoList;

    @Transient
    private List<AddressBookListener> listeners;

    public AddressBook(){
        buddyInfoList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public Long getId(){
        return id;
    }

    public void addBuddy(BuddyInfo buddyInfo) {
        buddyInfoList.add(buddyInfo);
        notifyListeners(new AddressBookEvent(this));
    }

    public ArrayList<BuddyInfo> getBuddyInfoList(){
        return buddyInfoList;
    }

    public void removeBuddy(int buddyIndex) {
        buddyInfoList.remove(buddyIndex);
        notifyListeners(new AddressBookEvent(this));
    }

    public void clear(){
        buddyInfoList = new ArrayList<>();
        notifyListeners(new AddressBookEvent(this));
    }

    public void addListener(AddressBookListener listener){
        listeners.add(listener);
    }

    private void notifyListeners(AddressBookEvent event){
        for(AddressBookListener listener: listeners){
            listener.handleAddressBookEvent(event);
        }

    }

}
