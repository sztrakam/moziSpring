package com.example.mozispring.Model;

import com.example.mozispring.Model.MyAppSzinhaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppSzinhazRepository extends JpaRepository<MyAppSzinhaz, Long> {

}
