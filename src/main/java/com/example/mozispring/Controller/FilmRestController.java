package com.example.mozispring.Controller;

import com.example.mozispring.Model.MyAppFilm;
import com.example.mozispring.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/filmek")
public class FilmRestController {

    private final FilmService filmService;

    @Autowired
    public FilmRestController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public Page<MyAppFilm> getAllEloadasok(Pageable pageable) {
        return filmService.getAllFilmek(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyAppFilm> getEloadasById(@PathVariable Long id) {
        return filmService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MyAppFilm createFilm(@RequestBody MyAppFilm film) {
        return filmService.save(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyAppFilm> updateFilm(@PathVariable Long id, @RequestBody MyAppFilm updatedFilm) {
        MyAppFilm existingFilm = filmService.updateFilm(id, updatedFilm);

        if (existingFilm == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existingFilm);

    }
        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEloadas(@PathVariable Long id) {
        if (filmService.existsById(id)) {
            filmService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}



