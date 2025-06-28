package com.example.workpraktika.repository;

import com.example.workpraktika.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepo extends JpaRepository<Complaint, Long> {
}
