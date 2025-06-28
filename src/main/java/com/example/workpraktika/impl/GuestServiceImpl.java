package com.example.workpraktika.impl;

import com.example.workpraktika.model.Guest;
import com.example.workpraktika.repository.GuestRepo;
import com.example.workpraktika.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepo guestRepository;

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        guestRepository.deleteById(id);
    }
}
