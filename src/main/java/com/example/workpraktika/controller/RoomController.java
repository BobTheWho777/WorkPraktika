package com.example.workpraktika.controller;

import com.example.workpraktika.model.Room;
import com.example.workpraktika.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    private final RoomService roomService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        return "rooms/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        return "rooms/form";
    }

    @PostMapping
    public String save(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        roomService.findById(id).ifPresent(room -> model.addAttribute("room", room));
        return "rooms/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roomService.deleteById(id);
        return "redirect:/rooms";
    }
}
