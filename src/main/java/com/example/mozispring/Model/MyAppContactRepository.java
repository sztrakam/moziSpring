package com.example.mozispring.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyAppContactRepository extends JpaRepository<MyAppContact, Long> {
    List<MyAppContact> findAllByOrderByTimestampDesc();
}