package com.example.workpraktika.controller;

import com.example.workpraktika.model.additionalService;
import com.example.workpraktika.service.AdditionalServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/additional-services")

public class AdditionalServiceController {
    private final AdditionalServiceService additionalServiceService;

    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String search, Model model) {
        List<additionalService> services;
        if (search != null && !search.isBlank()) {
            services = additionalServiceService.searchByName(search);
        } else {
            services = additionalServiceService.findAll();
        }

        model.addAttribute("services", services);
        model.addAttribute("search", search);
        return "additional-services/list";
    }


    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("service", new additionalService());
        return "additional-services/form";
    }

    @PostMapping
    public String save(@ModelAttribute additionalService service) {
        additionalServiceService.save(service);
        return "redirect:/additional-services";
    }

    @GetMapping("/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        additionalServiceService.findById(id).ifPresent(service -> model.addAttribute("service", service));
        return "additional-services/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        additionalServiceService.deleteById(id);
        return "redirect:/additional-services";
    }
}
