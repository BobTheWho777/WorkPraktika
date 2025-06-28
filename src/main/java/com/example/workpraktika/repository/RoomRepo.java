package com.example.workpraktika.repository;

import com.example.workpraktika.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
