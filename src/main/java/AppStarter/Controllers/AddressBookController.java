package AppStarter.Controllers;

import AppStarter.JpaMemoryDatabase.AddressBook;
import AppStarter.JpaMemoryDatabase.AddressBookRepository;
import AppStarter.JpaMemoryDatabase.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AddressBookController {

    @Autowired
    private final AddressBookRepository addressBookRepo;

    AddressBookController(AddressBookRepository addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping(path = "/addressBooks")
    public ModelAndView getAddressBookList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addressBooks");
        List<AddressBook> bookList =  (List<AddressBook>) addressBookRepo.findAll();
        modelAndView.addObject("bookList", bookList);
        return modelAndView;
    }

    @GetMapping(path = "/createAddressBook")
    public ModelAndView addressBookForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addressBook");
        modelAndView.addObject("addressBook", new AddressBook());
        return modelAndView;
    }

    @PostMapping(path = "/createAddressBook")
    public String makeAddressBook(@ModelAttribute AddressBook book, Model model) {
        model.addAttribute("addressBook", book);
        addressBookRepo.save(book);
        return "addressBookResult";
    }

    @PostMapping(path = "addBuddyToAddressBook", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public void addBuddyToAddressBook(@RequestBody BuddyInfo buddy, @RequestParam(name="id") Long id) {

        for (AddressBook book : addressBookRepo.findAll()) {
            System.out.println("Address Book ID is: " + book.getId());
            System.out.println("Buddylist: " + book.getBuddyList().toString());
        }

        Optional<AddressBook> possibleBook = addressBookRepo.findById(id);

        possibleBook.ifPresent(book -> {
            buddy.setAddressBook(book);
            book.addBuddy(buddy);
            System.out.println(book.getBuddyList().toString());
            AddressBook bookReturn = addressBookRepo.save(book);
            System.out.println(bookReturn.getBuddyList().toString());
        });
    }
}
