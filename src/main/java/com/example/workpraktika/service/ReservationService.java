package com.example.workpraktika.service;

import com.example.workpraktika.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    void deleteById(Long id);
    List<Reservation> searchByGuestName(String name);

}
