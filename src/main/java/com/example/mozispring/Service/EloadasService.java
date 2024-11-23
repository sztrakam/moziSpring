package com.example.mozispring.Service;

import com.example.mozispring.Model.MyAppEloadas;
import com.example.mozispring.Model.MyAppEloadasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EloadasService {

    private final MyAppEloadasRepository eloadasRepository;

    public EloadasService(MyAppEloadasRepository eloadasRepository) {
        this.eloadasRepository = eloadasRepository;
    }

    public Page<MyAppEloadas> getAllEloadasok(Pageable pageable) {
        return eloadasRepository.findAll(pageable);
    }
}
