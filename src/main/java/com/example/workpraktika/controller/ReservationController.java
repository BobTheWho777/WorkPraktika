package com.example.workpraktika.controller;

import com.example.workpraktika.model.Reservation;
import com.example.workpraktika.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final RoomService roomService;
    private final GuestService guestService;
    private final OrganizationService organizationService;
    private final AdditionalServiceService additionalServiceService;
    private final ComplaintService complaintService;

    public ReservationController(ReservationService reservationService, RoomService roomService, GuestService guestService, OrganizationService organizationService, AdditionalServiceService additionalServiceService, ComplaintService complaintService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.guestService = guestService;
        this.organizationService = organizationService;
        this.additionalServiceService = additionalServiceService;
        this.complaintService = complaintService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservations/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", roomService.findAll());
        model.addAttribute("guests", guestService.findAll());
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("services", additionalServiceService.findAll());
        model.addAttribute("complaints", complaintService.findAll());
        return "reservations/form";
    }

    @PostMapping
    public String save(@ModelAttribute Reservation reservation) {
        reservationService.save(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        reservationService.findById(id).ifPresent(reservation -> model.addAttribute("reservation", reservation));
        model.addAttribute("rooms", roomService.findAll());
        model.addAttribute("guests", guestService.findAll());
        model.addAttribute("organizations", organizationService.findAll());
        model.addAttribute("services", additionalServiceService.findAll());
        model.addAttribute("complaints", complaintService.findAll());
        return "reservations/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        reservationService.deleteById(id);
        return "redirect:/reservations";
    }
}
