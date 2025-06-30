package com.example.workpraktika.repository;

import com.example.workpraktika.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepo extends JpaRepository<Complaint, Long> {
    List<Complaint> findByTextContainingIgnoreCase(String text);

}
