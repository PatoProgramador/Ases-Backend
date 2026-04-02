package com.ases.api.controller;

import com.ases.api.dtos.AppointmentCreatedDTO;
import com.ases.api.dtos.AppointmentDTO;
import com.ases.api.dtos.CreateAppointmentDTO;
import com.ases.api.dtos.UpdateAppointmentDTO;
import com.ases.api.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentCreatedDTO> bookAppointment(@Valid @RequestBody CreateAppointmentDTO createAppointmentDTO) {
        String appointmentId = appointmentService.bookAppointment(createAppointmentDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/appointments/{id}")
                .buildAndExpand(appointmentId)
                .toUri();

        return ResponseEntity.created(location).body(new AppointmentCreatedDTO(appointmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/stylist/{stylistId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByStylistId(@PathVariable String stylistId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByStylistId(stylistId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByCustomerId(customerId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(
            @PathVariable String id,
            @Valid @RequestBody UpdateAppointmentDTO updateAppointmentDTO
    ) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, updateAppointmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
