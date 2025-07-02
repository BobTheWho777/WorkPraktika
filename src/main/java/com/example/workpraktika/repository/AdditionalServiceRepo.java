package com.example.workpraktika.repository;

import com.example.workpraktika.model.additionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalServiceRepo extends JpaRepository<additionalService, Long>{
    List<additionalService> findByNameContainingIgnoreCase(String name);
}
