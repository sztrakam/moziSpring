package com.example.mozispring.Controller;

import com.example.mozispring.Model.*;
import com.example.mozispring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;

@Controller
public class ContentController {

    private final MyAppUserService myAppUserService;
    private final EloadasService eloadasService;
    private final FilmService filmService;
    private final SzinhazService szinhazService;
    private final UserService userService;

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
        boolean loggedIn = auth != null && auth.isAuthenticated();
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
    public String showData(Model model) {
        List<MyAppEloadas> eloadasok = eloadasService.getAllEloadasok();
        List<MyAppFilm> filmek = filmService.getAllFilm();
        List<MyAppSzinhaz> szinhazak = szinhazService.getAllSzinhaz();

        model.addAttribute("eloadas", eloadasok);
        model.addAttribute("filmek", filmek);
        model.addAttribute("szinhazak", szinhazak);

        return "data";
    }
    @Autowired
    private ContactService contactService;
    @GetMapping("/contact")
    public String showContactPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean loggedIn = auth != null && auth.isAuthenticated();
        model.addAttribute("loggedIn", loggedIn);
        return "contact";
    }
    @PostMapping("/contact")
    public String sendMessage(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String message) {
        MyAppContact contact = new MyAppContact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);

        contactService.createContact(contact);

        return "contact";
    }
}
