package AddressBook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "")
@Controller
public class AddressBookController {

    private static final Logger log = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    BuddyInfoRepository buddyInfoRepository;

    @GetMapping("/addressbook/new")
    public String newBookForm(AddressBook addressBook){
        return "newbookform";
    }

    @PostMapping("/addressbook/new")
    public String createAddressBook(AddressBook addressBook, BindingResult result, Model model){
        log.info("in create address book");
        addressBookRepository.save(addressBook);
        model.addAttribute("addressbook", addressBook);
        return "addressbook";
    }

    @PostMapping("/bud/new/{book_id}")
    public String addBuddy(@PathVariable String book_id, BuddyInfo buddyInfo, Model model){
        log.info("in addbuddy addressbook");
        AddressBook ab = addressBookRepository.findById(Long.parseLong(book_id)).orElse(null);

        buddyInfoRepository.save(buddyInfo);
        ab.addBuddy(buddyInfo);
        addressBookRepository.save(ab);
        log.info("buddyinfo saved. buddy:  " + buddyInfo.toString());

        model.addAttribute("addressbook", ab);

        return "addressbook";
    }

    @GetMapping("/addressbook/get/{id}")
    public String addressBookPage(@PathVariable("id") String id, Model model){
        log.info("in getaddressbook");
        AddressBook ab;
        ab = addressBookRepository.findById(Long.parseLong(id)).orElse(null);

        log.info("getting bud");
        if (ab != null){
            model.addAttribute("addressbook", ab);
            for (BuddyInfo bud : ab.getBuddyInfoList()){
                log.info("bud: " + bud.toString());
            }
        } else {
            log.warn("Couldn't find addressbook");
            return "addressbook";
        }

        return "addressbook";
    }

    @GetMapping("/addressbooks")
    public String allAddressBooks(Model model){
        List<AddressBook> addressBooks = new ArrayList<>();
        addressBookRepository.findAll().forEach(addressBooks::add);
        model.addAttribute("addressbooks", addressBooks);
        return "addressbooklist";

    }

    @GetMapping("/book/new")
    public String newBook(AddressBook addressBook){
        return "newbookform";
    }

    // SPA methods

    @CrossOrigin
    @PostMapping(value = "/book/new")
    public @ResponseBody AddressBook saveAddressBook(@RequestParam(value = "name", required = false) String name)   {
        log.info("in create address book. param = " + name);
        AddressBook addressBook = new AddressBook(name);
        addressBookRepository.save(addressBook);
        return addressBook;
    }


    @PostMapping(value = "/bud/save")
    public @ResponseBody BuddyInfo saveBud(@RequestParam(value = "name", required = false) String name ,
                                           @RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "book", required = false) String book_id)   {
        log.info("in save bud. param = " + name +  " phone: " + phone + " book: " + book_id );
        BuddyInfo buddyInfo = new BuddyInfo(name, phone);
        AddressBook ab = addressBookRepository.findById(Long.parseLong(book_id)).orElse(new AddressBook());
        if (ab!= null) { log.info("address book name: " + ab.getName()); }

        buddyInfoRepository.save(buddyInfo);
        ab.addBuddy(buddyInfo);
        addressBookRepository.save(ab);

        return buddyInfo;
    }


    @GetMapping("/book/{id}")
    public @ResponseBody AddressBook getAddressBook(@PathVariable("id") String id, Model model){
        log.info("in getaddressbook");
        AddressBook ab;
        ab = addressBookRepository.findById(Long.parseLong(id)).orElse(null);

        log.info("getting bud");
        if (ab != null){
            model.addAttribute("addressbook", ab);
            for (BuddyInfo bud : ab.getBuddyInfoList()){
                log.info("bud: " + bud.toString());
            }
        } else {
            log.warn("Couldn't find addressbook");
            return new AddressBook();
        }

        return ab;
    }

    @CrossOrigin(origins = "")
    @GetMapping("/books")
    public @ResponseBody List<AddressBook> getAllAddressBooks(Model model){
       List<AddressBook> addressBooks = new ArrayList<>();
       addressBookRepository.findAll().forEach(addressBooks::add);
       model.addAttribute("addressbooks", addressBooks);
       return addressBooks;

    }

    @CrossOrigin(origins = "")
    @GetMapping("/index")
    public String index(){
        return "redirect:/index.html";
    }
}
