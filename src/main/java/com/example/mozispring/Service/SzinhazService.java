package com.example.mozispring.Service;

import com.example.mozispring.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzinhazService {

    private final MyAppSzinhazRepository szinhazRepository;

    public SzinhazService(MyAppSzinhazRepository szinhazRepository) {
        this.szinhazRepository = szinhazRepository;
    }

    public List<MyAppSzinhaz> getAllSzinhaz() {
        return szinhazRepository.findAll();
    }

}
