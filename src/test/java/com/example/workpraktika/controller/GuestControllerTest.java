package com.example.workpraktika.controller;

import com.example.workpraktika.model.Guest;
import com.example.workpraktika.service.GuestService;
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
class GuestControllerTest {

    @Mock
    private GuestService guestService;

    @Mock
    private Model model;

    @InjectMocks
    private GuestController guestController;

    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = new Guest(1L, "Иван", "Иванов", "Иванович", "89111234567");
    }

    @Test
    void list_noSearch_returnsAllGuests() {
        List<Guest> guests = List.of(guest);
        when(guestService.findAll()).thenReturn(guests);

        String view = guestController.list(null, model);

        verify(guestService).findAll();
        verify(model).addAttribute("guests", guests);
        verify(model).addAttribute("search", null);
        assertEquals("guests/list", view);
    }

    @Test
    void list_withSearch_returnsFilteredGuests() {
        String search = "Иван";
        List<Guest> filtered = List.of(guest);
        when(guestService.searchByName(search)).thenReturn(filtered);

        String view = guestController.list(search, model);

        verify(guestService).searchByName(search);
        verify(model).addAttribute("guests", filtered);
        verify(model).addAttribute("search", search);
        assertEquals("guests/list", view);
    }

    @Test
    void createForm_addsEmptyGuestModel() {
        String view = guestController.createForm(model);

        verify(model).addAttribute(eq("guest"), any(Guest.class));
        assertEquals("guests/form", view);
    }

    @Test
    void save_callsServiceAndRedirects() {
        String view = guestController.save(guest);

        verify(guestService).save(guest);
        assertEquals("redirect:/guests", view);
    }

    @Test
    void editForm_existingGuest_addsToModel() {
        when(guestService.findById(1L)).thenReturn(Optional.of(guest));

        String view = guestController.editForm(1L, model);

        verify(guestService).findById(1L);
        verify(model).addAttribute("guest", guest);
        assertEquals("guests/form", view);
    }

    @Test
    void delete_callsDeleteAndRedirects() {
        String view = guestController.delete(1L);

        verify(guestService).deleteById(1L);
        assertEquals("redirect:/guests", view);
    }
}
