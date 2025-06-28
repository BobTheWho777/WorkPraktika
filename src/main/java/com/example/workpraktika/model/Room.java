package com.example.workpraktika.model;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number_room;
    private String room_capacity;
    private String status;
    private String price;

    public Room(Long id, String number_room, String room_capacity, String status, String price) {
        this.id = id;
        this.number_room = number_room;
        this.room_capacity = room_capacity;
        this.status = status;
        this.price = price;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber_room() {
        return number_room;
    }

    public void setNumber_room(String number_room) {
        this.number_room = number_room;
    }

    public String getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(String room_capacity) {
        this.room_capacity = room_capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
