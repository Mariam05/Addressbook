package AddressBook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationLauncher {
    private static final Logger log = LoggerFactory.getLogger(ApplicationLauncher.class);
    public static void main(String[] args){
        SpringApplication.run(ApplicationLauncher.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(AddressBookRepository repository, BuddyInfoRepository buddyInfoRepository) {
//        return (args) -> {
//
//            AddressBook ab = new AddressBook("My Address Book");
//
//            BuddyInfo defaultBud = new BuddyInfo();
//            BuddyInfo arthur = new BuddyInfo("Arthur", "123");
//
//
//            buddyInfoRepository.save(defaultBud);
//            buddyInfoRepository.save(arthur);
//            // fetch all customers
//            log.info("Buds found with findAll():");
//            log.info("-------------------------------");
//            for (BuddyInfo bud : buddyInfoRepository.findAll()) {
//                log.info(bud.toString());
//            }
//
//            // save a few customers
//            log.info("Repos?");
//            repository.save(ab);
//
//            ab.addBuddy(defaultBud);
//            ab.addBuddy(arthur);
//
//            // fetch all customers
//            log.info("Repos found with findAll():");
//            log.info("-------------------------------");
//            for (AddressBook customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//
//        };
//    }
}
