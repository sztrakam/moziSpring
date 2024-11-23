package com.example.mozispring.Service;

import com.example.mozispring.Model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzinhazService {

    private final MyAppSzinhazRepository szinhazRepository;

    public SzinhazService(MyAppSzinhazRepository szinhazRepository) {
        this.szinhazRepository = szinhazRepository;
    }

    public Page<MyAppSzinhaz> getAllSzinhaz(Pageable pageable) {
        return szinhazRepository.findAll(pageable);
    }

}
