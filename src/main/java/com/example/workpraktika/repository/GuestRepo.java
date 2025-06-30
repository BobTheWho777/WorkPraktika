package com.example.workpraktika.repository;

import com.example.workpraktika.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepo extends JpaRepository<Guest, Long> {
    List<Guest> findByNameContainingIgnoreCase(String name);
}
