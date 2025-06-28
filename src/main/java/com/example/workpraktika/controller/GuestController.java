package com.example.workpraktika.controller;

import com.example.workpraktika.model.Guest;
import com.example.workpraktika.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guests")
public class GuestController {
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    private final GuestService guestService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("guests", guestService.findAll());
        return "guests/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "guests/form";
    }

    @PostMapping
    public String save(@ModelAttribute Guest guest) {
        guestService.save(guest);
        return "redirect:/guests";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        guestService.findById(id).ifPresent(guest -> model.addAttribute("guest", guest));
        return "guests/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        guestService.deleteById(id);
        return "redirect:/guests";
    }
}
