package com.example.mozispring.Controller;

import com.example.mozispring.Model.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Controller
public class ContentController {

    private final MyAppUserService myAppUserService;

    public ContentController(MyAppUserService myAppUserService) {
        this.myAppUserService = myAppUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    // Főoldal megjelenítése
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {

            return "redirect:/index"; // Irányítsd át a főoldalra sikeres bejelentkezés esetén
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Hibás felhasználónév vagy jelszó!");
            return "login"; // Visszatérés a bejelentkezési oldalra hiba esetén
        }
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
