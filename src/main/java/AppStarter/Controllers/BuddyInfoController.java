package AppStarter.Controllers;

import AppStarter.JpaMemoryDatabase.AddressBook;
import AppStarter.JpaMemoryDatabase.BuddyInfo;
import AppStarter.JpaMemoryDatabase.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Controller
public class BuddyInfoController {

    @Autowired
    private final BuddyRepository buddyRepo;

    BuddyInfoController(BuddyRepository buddyRepo) {
        this.buddyRepo = buddyRepo;
    }

    @GetMapping(path = "/")
    public String greeting() {
        return "index";
    }

    @GetMapping(path = "/buddies")
    public ModelAndView getBuddies() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buddies");
        List<BuddyInfo> buddyList =  (List<BuddyInfo>) buddyRepo.findAll();
        modelAndView.addObject("buddyList", buddyList);
        return modelAndView;
    }

    @GetMapping("/buddy")
    public ModelAndView buddyForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buddy");
        modelAndView.addObject("buddyInfo", new BuddyInfo());
        return modelAndView;
    }

    @PostMapping(path = "/buddy")
    public String makeBuddy(@ModelAttribute BuddyInfo buddy, Model model) {
        model.addAttribute("buddy", buddy);
        buddyRepo.save(buddy);
        return "result";
    }

    @PostMapping(path = "addBuddy", consumes = "application/json")
    @ResponseBody
    public void addBuddy(@RequestBody BuddyInfo buddy) {
        buddyRepo.save(buddy);
    }

    @GetMapping(path = "getBuddies", produces = "application/json")
    @ResponseBody
    public Collection<BuddyInfo> getBuddyList() {
        Collection<BuddyInfo> returnBuddies = new ArrayList<>();
        buddyRepo.findAll().forEach(returnBuddies::add);
        return returnBuddies;
    }
}