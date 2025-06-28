package com.example.workpraktika.service;

import com.example.workpraktika.model.additionalService;

import java.util.List;
import java.util.Optional;

public interface AdditionalServiceService {
    additionalService save(additionalService additionalService);
    Optional<additionalService> findById(Long id);
    List<additionalService> findAll();
    void deleteById(Long id);
}
