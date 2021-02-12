package AppStarter.JpaMemoryDatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(BuddyRepository buddyRepo, AddressBookRepository bookRepo) {
        return (args) -> {
            AddressBook book = new AddressBook();
            bookRepo.save(book);
            // save a few Buddies
            BuddyInfo buddy1 = new BuddyInfo("Jack", 123L);
            BuddyInfo buddy2 = new BuddyInfo("Chloe", 456L);
            BuddyInfo buddy3 = new BuddyInfo("Kim", 789L);
            BuddyInfo buddy4 = new BuddyInfo("David", 147L);

            buddy1.setAddressBook(book);
            buddy2.setAddressBook(book);
            buddy3.setAddressBook(book);
            buddy4.setAddressBook(book);

            List<BuddyInfo> buddyList = new ArrayList<>();
            buddyList.add(buddy1);
            buddyList.add(buddy2);
            buddyList.add(buddy3);
            buddyList.add(buddy4);

            book.setBuddyList(buddyList);

            buddyRepo.save(buddy1);
            buddyRepo.save(buddy2);
            buddyRepo.save(buddy3);
            buddyRepo.save(buddy4);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo buddy : buddyRepo.findAll()) {
                log.info(buddy.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            BuddyInfo buddy = buddyRepo.findById(2L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(buddy.toString());
            log.info("");

            // fetch buddies by name
            log.info("Buddy found with findByName('Bauer'):");
            log.info("--------------------------------------------");
            buddyRepo.findByName("Bauer").forEach(foundBuddy -> {
                log.info(foundBuddy.toString());
            });
            log.info("");

            AddressBook foundBook = bookRepo.findById(1L);
            log.info("Addressbook found with findById(1L):");
            log.info("--------------------------------");
            log.info(foundBook.toString());
            log.info("");
        };
    }

}