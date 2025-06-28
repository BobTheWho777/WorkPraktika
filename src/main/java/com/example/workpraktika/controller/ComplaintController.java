package com.example.workpraktika.controller;

import com.example.workpraktika.model.Complaint;
import com.example.workpraktika.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    private final ComplaintService complaintService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("complaints", complaintService.findAll());
        return "complaints/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        return "complaints/form";
    }

    @PostMapping
    public String save(@ModelAttribute Complaint complaint) {
        complaintService.save(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        complaintService.findById(id).ifPresent(complaint -> model.addAttribute("complaint", complaint));
        return "complaints/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        complaintService.deleteById(id);
        return "redirect:/complaints";
    }
}
