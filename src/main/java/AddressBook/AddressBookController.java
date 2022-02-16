package AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AddressBookController {

    private static final Logger log = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    BuddyInfoRepository buddyInfoRepository;

    @GetMapping("/book/new")
    public String newBookForm(AddressBook addressBook){
//        model.addAttribute("addressB/ook", new AddressBook());
        return "newbookform";
    }


    @PostMapping("/book/new")
    public String createAddressBook(AddressBook addressBook, BindingResult result, Model model){
        log.info("in create address book");
        addressBookRepository.save(addressBook);
        model.addAttribute("addressbook", addressBook);
        return "addressbook";
    }
//
    @GetMapping("/book/{id}")
    public String getAddressBook(@PathVariable("id") String id, Model model){
        AddressBook ab = addressBookRepository.findById(Long.parseLong(id)).orElse(null);

        log.info("getting bud");
        if (ab != null){
            model.addAttribute("addressbook", ab);
            for (BuddyInfo bud : ab.getBuddyInfoList()){
                log.info("bud: " + bud.toString());
            }
        } else {
            log.warn("Couldn't find addressbook");
            return "addressbooklist";
        }

        return "addressbook";
    }

    @GetMapping("/books")
    public String getAllAddressBooks(Model model){
        List<AddressBook> addressBooks = new ArrayList<>();
       addressBookRepository.findAll().forEach(addressBooks::add);
       model.addAttribute("addressbooks", addressBooks);
       return "addressbooklist";

    }

    @PostMapping("/bud/new/{book_id}")
    public String addBuddy(@PathVariable String book_id, BuddyInfo buddyInfo, Model model){
        log.info("in addbuddy addressbook");
        AddressBook ab = addressBookRepository.findById(Long.parseLong(book_id)).orElse(null);
        buddyInfo.setAddressBook(ab);
        buddyInfoRepository.save(buddyInfo);
        ab.addBuddy(buddyInfo);
        addressBookRepository.save(ab);
        log.info("buddyinfo saved. buddy:  " + buddyInfo.toString() + " addressbook: " + buddyInfo.getAddressBook().toString());

        model.addAttribute("addressbook", ab);

        return "addressbook";
    }
}
