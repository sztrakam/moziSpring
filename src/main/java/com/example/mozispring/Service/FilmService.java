package com.example.mozispring.Service;

import com.example.mozispring.Model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final MyAppFilmRepository filmRepository;

    public FilmService(MyAppFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<MyAppFilm> getAllFilm() {
        return filmRepository.findAll();
    }
    public Page<MyAppFilm> getAllFilm(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

}
