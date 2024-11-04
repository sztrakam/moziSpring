package com.example.mozispring.Controller;

import com.example.mozispring.Model.MyAppEloadas;
import com.example.mozispring.Model.MyAppFilm;
import com.example.mozispring.Model.MyAppSzinhaz;
import com.example.mozispring.Model.MyAppUserService;
import com.example.mozispring.Service.EloadasService;
import com.example.mozispring.Service.FilmService;
import com.example.mozispring.Service.SzinhazService;
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

    @Autowired
    public ContentController(MyAppUserService myAppUserService, EloadasService eloadasService, FilmService filmService, SzinhazService szinhazService) {
        this.myAppUserService = myAppUserService;
        this.eloadasService = eloadasService;
        this.filmService = filmService;
        this.szinhazService = szinhazService;
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
            // Logika a bejelentkezéshez (myAppUserService hívása)
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

    @GetMapping("/data")
    public String showData(Model model) {
        List<MyAppEloadas> eloadasok = eloadasService.getAllEloadasok();
        List<MyAppFilm> filmek = filmService.getAllFilm();
        List<MyAppSzinhaz> szinhazak = szinhazService.getAllSzinhaz();

        model.addAttribute("eloadas", eloadasok);
        model.addAttribute("filmek", filmek);
        model.addAttribute("szinhazak", szinhazak);

        return "data"; // data.html
    }
}
