package AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BuddyInfoController {

    @Autowired
    BuddyInfoRepository repository;

    @Autowired
    AddressBookRepository book_repository;


    @GetMapping("/bud/new/{book_id}")
    public String newBudForm(@PathVariable String book_id, Model model) {
        BuddyInfo bud = new BuddyInfo();
        model.addAttribute("buddyInfo", new BuddyInfo());
        model.addAttribute("addressbookid", book_id);
        return "buddyform";
    }


    @DeleteMapping("/buddy/delete")
    public String deleteBuddy(@RequestParam(value="id") String id, Model model){
        repository.deleteById(Long.parseLong(id));
        model.addAttribute("buddies", repository.findAll());
        return "addressbook";
    }

//    @GetMapping("/buddy/new")
//    public @ResponseBody BuddyInfo getBuddy(@PathVariable(value = "id") String id, Model model){
//
//    }

}
