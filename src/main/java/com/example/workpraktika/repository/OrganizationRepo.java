package com.example.workpraktika.repository;

import com.example.workpraktika.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {
    List<Organization> findByNameContainingIgnoreCase(String name);

}
