package com.example.workpraktika.repository;

import com.example.workpraktika.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGuestNameContainingIgnoreCase(String name);

}
