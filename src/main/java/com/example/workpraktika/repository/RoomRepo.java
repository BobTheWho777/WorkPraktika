package com.example.workpraktika.repository;

import com.example.workpraktika.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {
    List<Room> findByNumberRoomContainingIgnoreCase(String numberRoom);
}
