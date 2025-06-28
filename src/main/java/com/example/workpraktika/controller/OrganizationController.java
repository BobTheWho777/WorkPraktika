package com.example.workpraktika.controller;

import com.example.workpraktika.model.Organization;
import com.example.workpraktika.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    private final OrganizationService organizationService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("organizations", organizationService.findAll());
        return "organizations/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("organization", new Organization());
        return "organizations/form";
    }

    @PostMapping
    public String save(@ModelAttribute Organization organization) {
        organizationService.save(organization);
        return "redirect:/organizations";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        organizationService.findById(id).ifPresent(org -> model.addAttribute("organization", org));
        return "organizations/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        organizationService.deleteById(id);
        return "redirect:/organizations";
    }
}
