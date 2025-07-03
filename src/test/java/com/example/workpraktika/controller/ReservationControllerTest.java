package com.example.workpraktika.controller;

import com.example.workpraktika.model.*;
import com.example.workpraktika.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;
    @Mock
    private RoomService roomService;
    @Mock
    private GuestService guestService;
    @Mock
    private OrganizationService organizationService;
    @Mock
    private AdditionalServiceService additionalServiceService;
    @Mock
    private ComplaintService complaintService;
    @Mock
    private Model model;

    @InjectMocks
    private ReservationController reservationController;

    private Room room;
    private Guest guest;
    private Organization organization;
    private additionalService addService;
    private Complaint complaint;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        room = new Room(1L, "101", "2", "Свободен", "3000");
        guest = new Guest(1L, "Иван", "Иванов", "Иванович", "1234567890");
        organization = new Organization(1L, "Орг1", "2023-01-01", "2023-12-31", "10%");
        addService = new additionalService(1L, "Wi-Fi", "500");
        complaint = new Complaint("2023-06-01", "Шум ночью", 1L);
        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setRoom(room);
        reservation.setGuest(guest);
        reservation.setOrganization(organization);
        reservation.setAdditionalServices(List.of(addService));
        reservation.setComplaints(List.of(complaint));
        reservation.setFloor("3");
        reservation.setReservations("Забронировано");
        reservation.setNumberOfPeople("2");
        reservation.setDateReservation("2023-06-01");
        reservation.setDateIn("2023-06-10");
        reservation.setDateOut("2023-06-15");
        reservation.setTotalDebt("0");
    }

    @Test
    void list_noSearch_returnsAllReservations() {
        List<Reservation> reservations = List.of(reservation);
        when(reservationService.findAll()).thenReturn(reservations);

        String view = reservationController.list(null, model);

        verify(reservationService).findAll();
        verify(model).addAttribute("reservations", reservations);
        verify(model).addAttribute("search", null);
        assertEquals("reservations/list", view);
    }

    @Test
    void list_withSearch_returnsFilteredReservations() {
        List<Reservation> filtered = List.of(reservation);
        String search = "Иван";
        when(reservationService.searchByGuestName(search)).thenReturn(filtered);

        String view = reservationController.list(search, model);

        verify(reservationService).searchByGuestName(search);
        verify(model).addAttribute("reservations", filtered);
        verify(model).addAttribute("search", search);
        assertEquals("reservations/list", view);
    }

    @Test
    void createForm_addsAllRequiredAttributes() {
        when(roomService.findAll()).thenReturn(List.of(room));
        when(guestService.findAll()).thenReturn(List.of(guest));
        when(organizationService.findAll()).thenReturn(List.of(organization));
        when(additionalServiceService.findAll()).thenReturn(List.of(addService));
        when(complaintService.findAll()).thenReturn(List.of(complaint));

        String view = reservationController.createForm(model);

        verify(model).addAttribute(eq("reservation"), any(Reservation.class));
        verify(model).addAttribute("rooms", List.of(room));
        verify(model).addAttribute("guests", List.of(guest));
        verify(model).addAttribute("organizations", List.of(organization));
        verify(model).addAttribute("services", List.of(addService));
        verify(model).addAttribute("complaints", List.of(complaint));
        assertEquals("reservations/form", view);
    }

    @Test
    void save_resolvesEntitiesAndSaves() {
        // Без этого ничего не найдёт
        when(roomService.findById(room.getId())).thenReturn(Optional.of(room));
        when(guestService.findById(guest.getId())).thenReturn(Optional.of(guest));
        when(organizationService.findById(organization.getId())).thenReturn(Optional.of(organization));
        when(additionalServiceService.findById(addService.getId())).thenReturn(Optional.of(addService));
        when(complaintService.findById(complaint.getId())).thenReturn(Optional.of(complaint));

        String view = reservationController.save(reservation);

        verify(roomService).findById(room.getId());
        verify(guestService).findById(guest.getId());
        verify(organizationService).findById(organization.getId());
        verify(additionalServiceService).findById(addService.getId());
        verify(complaintService).findById(complaint.getId());
        verify(reservationService).save(reservation);
        assertEquals("redirect:/reservations", view);
    }

    @Test
    void editForm_addsReservationAndAllLists() {
        when(reservationService.findById(1L)).thenReturn(Optional.of(reservation));
        when(roomService.findAll()).thenReturn(List.of(room));
        when(guestService.findAll()).thenReturn(List.of(guest));
        when(organizationService.findAll()).thenReturn(List.of(organization));
        when(additionalServiceService.findAll()).thenReturn(List.of(addService));
        when(complaintService.findAll()).thenReturn(List.of(complaint));

        String view = reservationController.editForm(1L, model);

        verify(reservationService).findById(1L);
        verify(model).addAttribute("reservation", reservation);
        verify(model).addAttribute("rooms", List.of(room));
        verify(model).addAttribute("guests", List.of(guest));
        verify(model).addAttribute("organizations", List.of(organization));
        verify(model).addAttribute("services", List.of(addService));
        verify(model).addAttribute("complaints", List.of(complaint));
        assertEquals("reservations/form", view);
    }

    @Test
    void delete_callsDeleteAndRedirects() {
        String view = reservationController.delete(1L);

        verify(reservationService).deleteById(1L);
        assertEquals("redirect:/reservations", view);
    }
}
