package com.example.workpraktika.controller;

import com.example.workpraktika.model.additionalService;
import com.example.workpraktika.service.AdditionalServiceService;
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
class AdditionalServiceControllerTest {

    @Mock
    private AdditionalServiceService additionalServiceService;

    @Mock
    private Model model;

    @InjectMocks
    private AdditionalServiceController controller;

    private additionalService testService;

    @BeforeEach
    void setUp() {
        testService = new additionalService(1L, "WiFi", "200");
    }

    @Test
    void list_noSearch_returnsAllServices() {
        List<additionalService> services = Arrays.asList(testService);
        when(additionalServiceService.findAll()).thenReturn(services);

        String view = controller.list(null, model);

        verify(additionalServiceService).findAll();
        verify(model).addAttribute("services", services);
        verify(model).addAttribute("search", null);
        assertEquals("additional-services/list", view);
    }

    @Test
    void list_withSearch_returnsFilteredServices() {
        String search = "WiFi";
        List<additionalService> filtered = List.of(testService);
        when(additionalServiceService.searchByName(search)).thenReturn(filtered);

        String view = controller.list(search, model);

        verify(additionalServiceService).searchByName(search);
        verify(model).addAttribute("services", filtered);
        verify(model).addAttribute("search", search);
        assertEquals("additional-services/list", view);
    }

    @Test
    void createForm_addsEmptyServiceModel() {
        String view = controller.createForm(model);

        verify(model).addAttribute(eq("service"), any(additionalService.class));
        assertEquals("additional-services/form", view);
    }

    @Test
    void save_callsServiceAndRedirects() {
        String view = controller.save(testService);

        verify(additionalServiceService).save(testService);
        assertEquals("redirect:/additional-services", view);
    }

    @Test
    void editForm_existingService_addsToModel() {
        when(additionalServiceService.findById(1L)).thenReturn(Optional.of(testService));

        String view = controller.editForm(1L, model);

        verify(additionalServiceService).findById(1L);
        verify(model).addAttribute("service", testService);
        assertEquals("additional-services/form", view);
    }

    @Test
    void delete_callsDeleteAndRedirects() {
        String view = controller.delete(1L);

        verify(additionalServiceService).deleteById(1L);
        assertEquals("redirect:/additional-services", view);
    }
}
