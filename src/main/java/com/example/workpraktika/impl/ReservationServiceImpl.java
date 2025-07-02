package com.example.workpraktika.impl;

import com.example.workpraktika.model.Reservation;
import com.example.workpraktika.repository.ReservationRepo;
import com.example.workpraktika.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepo reservationRepository;

    public ReservationServiceImpl(ReservationRepo reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> searchByGuestName(String name) {
        return reservationRepository.findByGuestNameContainingIgnoreCase(name);
    }

}
