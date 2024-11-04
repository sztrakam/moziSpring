package com.example.mozispring.Model;

import com.example.mozispring.Model.MyAppFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppFilmRepository extends JpaRepository<MyAppFilm, Long> {

}
