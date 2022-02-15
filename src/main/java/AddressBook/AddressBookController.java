package AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/addressbook/new")
    public String createAddressBook(@RequestParam(value = "name", defaultValue = "My Address Book") String name, Model model){
        AddressBook addressBook = new AddressBook(name);
        addressBookRepository.save(addressBook);
        model.addAttribute("addressbookname", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddyInfoList());
        return "addressbook";
    }
//
    @RequestMapping("/addressbook/get")
    public String getAddressBook(@RequestParam(value = "id", defaultValue = "1") String id,
                                 Model model){
        AddressBook ab = addressBookRepository.findById(Long.parseLong(id)).orElse(null);

        log.info("getting bud");
        if (ab != null){
            model.addAttribute("buddies", ab.getBuddyInfoList());
            for (BuddyInfo bud : ab.getBuddyInfoList()){
                log.info("bud: " + bud.toString());
            }
            model.addAttribute("addressbookname", ab.getName());
        } else {
            model.addAttribute("buddies", ab.getBuddyInfoList());
            model.addAttribute("addressbookname", "not found");
            log.warn("Couldn't find addressbook");
        }

        return "addressbook";
    }
//
    @GetMapping("/addressbook/addressbooks")
    public String getAllAddressBooks(Model model){
        List<AddressBook> addressBooks = new ArrayList<>();
       addressBookRepository.findAll().forEach(addressBooks::add);
       model.addAttribute("addressbooks", addressBooks);
       return "addressbooklist";

    }


    @RequestMapping("/addressbook/addbuddy")
    public String addBuddy(@RequestParam(value = "name", defaultValue = "Jane") String name,
                           @RequestParam(value = "phone", defaultValue = "123") String phonenum,
                           @RequestParam(value = "id", defaultValue = "1") String id,
                           Model model) {
        BuddyInfo buddyInfo = new BuddyInfo(name, phonenum);
        buddyInfoRepository.save(buddyInfo);

        AddressBook ab = addressBookRepository.findById(Long.parseLong(id)).orElse(null);

        if (ab != null){
            ab.addBuddy(buddyInfo);
            addressBookRepository.save(ab);
            model.addAttribute("buddies", ab.getBuddyInfoList());
            model.addAttribute("addressbookname", ab.getName());
        } else {
            model.addAttribute("buddies", ab.getBuddyInfoList());
            model.addAttribute("addressbookname", "not found");
        }


        return "addressbook";
    }
}
