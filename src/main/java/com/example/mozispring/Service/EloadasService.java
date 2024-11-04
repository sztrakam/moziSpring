package com.example.mozispring.Service;

import com.example.mozispring.Model.MyAppEloadas;
import com.example.mozispring.Model.MyAppEloadasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EloadasService {

    private final MyAppEloadasRepository eloadasRepository;

    public EloadasService(MyAppEloadasRepository eloadasRepository) {
        this.eloadasRepository = eloadasRepository;
    }

    public List<MyAppEloadas> getAllEloadasok() {
        return eloadasRepository.findAll();
    }

}
