package com.example.workpraktika.impl;

import com.example.workpraktika.model.Complaint;
import com.example.workpraktika.repository.ComplaintRepo;
import com.example.workpraktika.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepo complaintRepository;

    public ComplaintServiceImpl(ComplaintRepo complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint save(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Optional<Complaint> findById(Long id) {
        return complaintRepository.findById(id);
    }

    @Override
    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        complaintRepository.deleteById(id);
    }

    @Override
    public List<Complaint> findByTextContainingIgnoreCase(String text) {
        return complaintRepository.findByTextContainingIgnoreCase(text);
    }

}
