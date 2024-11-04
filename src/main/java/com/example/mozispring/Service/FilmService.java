package com.example.mozispring.Service;

import com.example.mozispring.Model.*;
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

}
