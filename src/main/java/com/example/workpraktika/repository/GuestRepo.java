package com.example.workpraktika.repository;

import com.example.workpraktika.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo extends JpaRepository<Guest, Long> {
}
