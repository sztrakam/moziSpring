package com.example.mozispring.Model;

import java.util.Optional;

import com.example.mozispring.Model.MyAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAppUserRepository extends JpaRepository<MyAppUser, Long>{

    Optional<MyAppUser> findByUsername(String username);
}

