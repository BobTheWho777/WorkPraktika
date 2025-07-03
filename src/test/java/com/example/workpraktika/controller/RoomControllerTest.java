package com.example.workpraktika.controller;

import com.example.workpraktika.model.Room;
import com.example.workpraktika.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @Mock
    private Model model;

    @InjectMocks
    private RoomController roomController;

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(1L, "101", "2", "Свободен", "3000");
    }

    @Test
    void list_noSearch_returnsAllRooms() {
        List<Room> rooms = List.of(room);
        when(roomService.findAll()).thenReturn(rooms);

        String view = roomController.list(null, model);

        verify(roomService).findAll();
        verify(model).addAttribute("rooms", rooms);
        verify(model).addAttribute("search", null);
        assertEquals("rooms/list", view);
    }

    @Test
    void list_withSearch_returnsFilteredRooms() {
        String search = "101";
        List<Room> filtered = List.of(room);
        when(roomService.findByNumberRoom(search)).thenReturn(filtered);

        String view = roomController.list(search, model);

        verify(roomService).findByNumberRoom(search);
        verify(model).addAttribute("rooms", filtered);
        verify(model).addAttribute("search", search);
        assertEquals("rooms/list", view);
    }

    @Test
    void createForm_addsEmptyRoomModel() {
        String view = roomController.createForm(model);

        verify(model).addAttribute(eq("room"), any(Room.class));
        assertEquals("rooms/form", view);
    }

    @Test
    void save_callsServiceAndRedirects() {
        String view = roomController.save(room);

        verify(roomService).save(room);
        assertEquals("redirect:/rooms", view);
    }

    @Test
    void editForm_existingRoom_addsToModel() {
        when(roomService.findById(1L)).thenReturn(Optional.of(room));

        String view = roomController.editForm(1L, model);

        verify(roomService).findById(1L);
        verify(model).addAttribute("room", room);
        assertEquals("rooms/form", view);
    }

    @Test
    void delete_callsDeleteAndRedirects() {
        String view = roomController.delete(1L);

        verify(roomService).deleteById(1L);
        assertEquals("redirect:/rooms", view);
    }
}
