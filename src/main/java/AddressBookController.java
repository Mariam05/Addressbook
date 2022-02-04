import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookController {

    private AddressBook model;

    public AddressBookController(AddressBook model){
        this.model = model;
    }

    public void subscribeToModel(AddressBookListener listener){
        model.addListener(listener);
    }

    public AddressBook getAddressBook(){
        return model;
    }

    public void addBud(BuddyInfo newBuddy) {
        System.out.println("Adding bud");
        model.addBuddy(newBuddy);
    }

    public void removeBud(int index){
        model.removeBuddy(index);
    }

    public void clearBook() {
        model.clear();
    }
}
