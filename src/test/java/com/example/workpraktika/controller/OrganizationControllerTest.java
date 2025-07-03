package com.example.workpraktika.controller;

import com.example.workpraktika.model.Organization;
import com.example.workpraktika.service.OrganizationService;
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
class OrganizationControllerTest {

    @Mock
    private OrganizationService organizationService;

    @Mock
    private Model model;

    @InjectMocks
    private OrganizationController organizationController;

    private Organization org;

    @BeforeEach
    void setUp() {
        org = new Organization(1L, "ООО Ромашка", "2024-01-01", "2024-12-31", "10%");
    }

    @Test
    void list_noSearch_returnsAllOrganizations() {
        List<Organization> organizations = List.of(org);
        when(organizationService.findAll()).thenReturn(organizations);

        String view = organizationController.list(null, model);

        verify(organizationService).findAll();
        verify(model).addAttribute("organizations", organizations);
        verify(model).addAttribute("search", null);
        assertEquals("organizations/list", view);
    }

    @Test
    void list_withSearch_returnsFilteredOrganizations() {
        String search = "Ромашка";
        List<Organization> filtered = List.of(org);
        when(organizationService.searchByName(search)).thenReturn(filtered);

        String view = organizationController.list(search, model);

        verify(organizationService).searchByName(search);
        verify(model).addAttribute("organizations", filtered);
        verify(model).addAttribute("search", search);
        assertEquals("organizations/list", view);
    }

    @Test
    void createForm_addsEmptyOrganizationModel() {
        String view = organizationController.createForm(model);

        verify(model).addAttribute(eq("organization"), any(Organization.class));
        assertEquals("organizations/form", view);
    }

    @Test
    void save_callsServiceAndRedirects() {
        String view = organizationController.save(org);

        verify(organizationService).save(org);
        assertEquals("redirect:/organizations", view);
    }

    @Test
    void editForm_existingOrganization_addsToModel() {
        when(organizationService.findById(1L)).thenReturn(Optional.of(org));

        String view = organizationController.editForm(1L, model);

        verify(organizationService).findById(1L);
        verify(model).addAttribute("organization", org);
        assertEquals("organizations/form", view);
    }

    @Test
    void delete_callsDeleteAndRedirects() {
        String view = organizationController.delete(1L);

        verify(organizationService).deleteById(1L);
        assertEquals("redirect:/organizations", view);
    }
}
