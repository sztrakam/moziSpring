package com.example.mozispring.Model;

import com.example.mozispring.Model.MyAppEloadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppEloadasRepository extends JpaRepository<MyAppEloadas, Long> {
    // Egyedi lekérdezések, ha szükséges
}
