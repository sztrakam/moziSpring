package com.example.mozispring.Service;

import com.example.mozispring.Model.MyAppFilm;
import com.example.mozispring.Model.MyAppFilmRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.mozispring.Model.MyAppFilm;
import com.example.mozispring.Model.MyAppFilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final MyAppFilmRepository filmRepository;

    public FilmService(MyAppFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    @Transactional
    public MyAppFilm updateFilm(Long id, MyAppFilm updatedFilm) {

        MyAppFilm existingFilm = filmRepository.findById(id).orElse(null);

        if (existingFilm == null) {
            return null;
        }

        existingFilm.setCim(updatedFilm.getCim());
        existingFilm.setEv(updatedFilm.getEv());
        existingFilm.setHossz(updatedFilm.getHossz());

        return filmRepository.save(existingFilm);
    }

    public Page<MyAppFilm> getAllFilmek(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    public Optional<MyAppFilm> findById(Long id) {
        return filmRepository.findById(id);
    }

    public MyAppFilm save(MyAppFilm eloadas) {
        return filmRepository.save(eloadas);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return filmRepository.existsById(id);
    }
}

