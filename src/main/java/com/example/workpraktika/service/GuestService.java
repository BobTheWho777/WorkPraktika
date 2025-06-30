package com.example.workpraktika.service;

import com.example.workpraktika.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    Guest save(Guest guest);
    Optional<Guest> findById(Long id);
    List<Guest> findAll();
    void deleteById(Long id);
    List<Guest> searchByName(String name);

}
