package com.example.workpraktika.impl;

import com.example.workpraktika.model.additionalService;
import com.example.workpraktika.repository.AdditionalServiceRepo;
import com.example.workpraktika.service.AdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService {
    private final AdditionalServiceRepo additionalServiceRepository;

    public AdditionalServiceServiceImpl(AdditionalServiceRepo additionalServiceRepository) {
        this.additionalServiceRepository = additionalServiceRepository;
    }

    @Override
    public additionalService save(additionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }

    @Override
    public Optional<additionalService> findById(Long id) {
        return additionalServiceRepository.findById(id);
    }

    @Override
    public List<additionalService> findAll() {
        return additionalServiceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        additionalServiceRepository.deleteById(id);
    }

    @Override
    public List<additionalService> searchByName(String name) {
        return additionalServiceRepository.findByNameContainingIgnoreCase(name);
    }

}
