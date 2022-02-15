package AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BuddyInfoController {

    @Autowired
    BuddyInfoRepository repository;

    @GetMapping("/buddy")
    public String createBuddy(@RequestParam(value = "name", defaultValue = "Jane") String name,
                              @RequestParam(value = "phone", defaultValue = "123") String phonenum,
                              Model model) {

        BuddyInfo buddyInfo = new BuddyInfo(name, phonenum);
        repository.save(buddyInfo);
        model.addAttribute("buddy", buddyInfo);
        return "buddy";
    }


    @DeleteMapping("/buddy/delete")
    public String deleteBuddy(@RequestParam(value="id") String id, Model model){
        repository.deleteById(Long.parseLong(id));
        model.addAttribute("buddies", repository.findAll());
        return "addressbook";
    }

}
