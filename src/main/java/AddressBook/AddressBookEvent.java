package AddressBook;

import java.util.ArrayList;

public class AddressBookEvent {
    private final AddressBook book;

    public AddressBookEvent(AddressBook book){
        this.book = book;
    }

    public ArrayList<BuddyInfo> getUpdatedList(){
        return book.getBuddyInfoList();
    }

}


