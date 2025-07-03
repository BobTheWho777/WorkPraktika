package com.example.workpraktika.controller;

import com.example.workpraktika.model.Complaint;
import com.example.workpraktika.service.ComplaintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ComplaintControllerTest {

    @Mock
    private ComplaintService complaintService;

    @Mock
    private Model model;

    @InjectMocks
    private ComplaintController complaintController;

    private Complaint complaint;

    @BeforeEach
    void setUp() {
        complaint = new Complaint();
        complaint.setId(1L);
        complaint.setText("Шум ночью");
        complaint.setDate("2025-07-01");
    }

    @Test
    void list_noSearch_returnsAllComplaints() {
        List<Complaint> complaints = List.of(complaint);
        when(complaintService.findAll()).thenReturn(complaints);

        String view = complaintController.list(null, model);

        verify(complaintService).findAll();
        verify(model).addAttribute("complaints", complaints);
        verify(model).addAttribute("search", null);
        assertEquals("complaints/list", view);
    }

    @Test
    void list_withSearch_returnsFilteredComplaints() {
        String search = "Шум";
        List<Complaint> filtered = List.of(complaint);
        when(complaintService.findByTextContainingIgnoreCase(search)).thenReturn(filtered);

        String view = complaintController.list(search, model);

        verify(complaintService).findByTextContainingIgnoreCase(search);
        verify(model).addAttribute("complaints", filtered);
        verify(model).addAttribute("search", search);
        assertEquals("complaints/list", view);
    }

    @Test
    void createForm_addsEmptyComplaintModel() {
        String view = complaintController.createForm(model);

        verify(model).addAttribute(eq("complaint"), any(Complaint.class));
        assertEquals("complaints/form", view);
    }

    @Test
    void save_callsServiceAndRedirects() {
        String view = complaintController.save(complaint);

        verify(complaintService).save(complaint);
        assertEquals("redirect:/complaints", view);
    }

    @Test
    void editForm_existingComplaint_addsToModel() {
        when(complaintService.findById(1L)).thenReturn(Optional.of(complaint));

        String view = complaintController.editForm(1L, model);

        verify(complaintService).findById(1L);
        verify(model).addAttribute("complaint", complaint);
        assertEquals("complaints/form", view);
    }

    @Test
    void delete_callsDeleteAndRedirects() {
        String view = complaintController.delete(1L);

        verify(complaintService).deleteById(1L);
        assertEquals("redirect:/complaints", view);
    }
}
