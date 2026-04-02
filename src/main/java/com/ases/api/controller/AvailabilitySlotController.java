package com.ases.api.controller;

import com.ases.api.dtos.CreateSlotDTO;
import com.ases.api.dtos.SlotCreatedDTO;
import com.ases.api.dtos.SlotDTO;
import com.ases.api.dtos.UpdateSlotDTO;
import com.ases.api.service.AvailabilitySlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/availability-slots")
public class AvailabilitySlotController {
    @Autowired
    private AvailabilitySlotService availabilitySlotService;

    @PostMapping
    public ResponseEntity<SlotCreatedDTO> createSlot(@Valid @RequestBody CreateSlotDTO slotCreatedDTO) {
        String slotId = availabilitySlotService.saveSlot(slotCreatedDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/availability-slots/{id}")
                .buildAndExpand(slotId)
                .toUri();

        SlotCreatedDTO slotCreated = new SlotCreatedDTO(slotId);
        return ResponseEntity.created(location).body(slotCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlotDTO> getSlotById(@PathVariable String id) {
        SlotDTO slot = availabilitySlotService.getSlotById(id);
        return ResponseEntity.ok(slot);
    }

    @GetMapping("/stylist/{stylistId}")
    public ResponseEntity<List<SlotDTO>> getSlotsByStylistId(@PathVariable String stylistId) {
        List<SlotDTO> slots = availabilitySlotService.getSlotsByStylistId(stylistId);
        return ResponseEntity.ok(slots);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SlotDTO> updateSlot(
            @PathVariable String id,
            @Valid @RequestBody UpdateSlotDTO updateSlotDTO
    ) {
        SlotDTO updatedSlot = availabilitySlotService.updateSlot(id, updateSlotDTO);
        return ResponseEntity.ok(updatedSlot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable String id) {
        availabilitySlotService.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }
}
