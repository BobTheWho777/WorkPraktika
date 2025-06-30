package com.example.workpraktika.service;

import com.example.workpraktika.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    Organization save(Organization organization);
    Optional<Organization> findById(Long id);
    List<Organization> findAll();
    void deleteById(Long id);
    List<Organization> searchByName(String name);

}
