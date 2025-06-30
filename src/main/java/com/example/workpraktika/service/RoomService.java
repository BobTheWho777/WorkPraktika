package com.example.workpraktika.service;

import com.example.workpraktika.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room save(Room room);
    Optional<Room> findById(Long id);
    List<Room> findAll();
    void deleteById(Long id);
    List<Room> findByNumberRoom(String numberRoom);

}
