package com.example.mozispring.Controller;

import com.example.mozispring.Model.*;
import com.example.mozispring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ContentController {

    private final MyAppUserService myAppUserService;
    private final EloadasService eloadasService;
    private final FilmService filmService;
    private final SzinhazService szinhazService;
    private final UserService userService;
    @Autowired
    private MyAppContactRepository myAppContactRepository;

    @Autowired
    public ContentController(MyAppUserService myAppUserService, EloadasService eloadasService, FilmService filmService, SzinhazService szinhazService, UserService userService) {
        this.myAppUserService = myAppUserService;
        this.eloadasService = eloadasService;
        this.filmService = filmService;
        this.szinhazService = szinhazService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean loggedIn = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());
        String username = loggedIn ? auth.getName() : null;

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("username", username);

        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            return "redirect:/index";
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Hibás felhasználónév vagy jelszó!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("felhasznalonev") String username,
                               @RequestParam("jelszo") String password,
                               @RequestParam("szerepkor") String szerepkor,
                               Model model) {
        try {
            MyAppUser newUser = new MyAppUser();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRole(szerepkor);

            userService.saveNewUser(newUser);
            model.addAttribute("successMessage", "Sikeres regisztráció!");
            return "login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/data")
    public String showData(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size) {

        Page<MyAppEloadas> eloadasokPage = eloadasService.getAllEloadasok(PageRequest.of(page, size));
        Page<MyAppFilm> filmPage = filmService.getAllFilm(PageRequest.of(page, size));
        Page<MyAppSzinhaz> szinhazPage = szinhazService.getAllSzinhaz(PageRequest.of(page, size));

        model.addAttribute("eloadasokPage", eloadasokPage);
        model.addAttribute("filmPage", filmPage);
        model.addAttribute("szinhazPage", szinhazPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);

        return "data";
    }

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String showContactPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean loggedIn = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());

        String username = loggedIn ? auth.getName() : "Vendég";

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("username", username);
        return "contact";
    }

    @PostMapping("/contact")
    public String sendMessage(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String message,
                              Model model) {
        MyAppContact contact = new MyAppContact();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal()))
                ? auth.getName()
                : "Vendég";

        contact.setUsername(username);
        contact.setEmail(email);
        contact.setMessage(message);
        contact.setName(name);
        contactService.createContact(contact);

        model.addAttribute("loggedIn", auth != null && auth.isAuthenticated());
        model.addAttribute("successMessage", "Üzenete sikeresen elküldve!");

        return "contact";
    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        List<MyAppContact> contacts = myAppContactRepository.findAllByOrderByTimestampDesc();
        model.addAttribute("contacts", contacts);
        return "messages";
    }
}
