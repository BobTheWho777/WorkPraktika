package com.example.workpraktika.service;

import com.example.workpraktika.model.Complaint;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    Complaint save(Complaint complaint);
    Optional<Complaint> findById(Long id);
    List<Complaint> findAll();
    void deleteById(Long id);
}
