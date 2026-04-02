package com.ases.api.model;

import com.ases.api.constants.SlotStatus;
import com.ases.api.dtos.CreateSlotDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "availability_slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilitySlot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String stylistId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus status;
    private Boolean isDeleted;

    public AvailabilitySlot(CreateSlotDTO slotDTO) {
        this.stylistId = slotDTO.getStylistId();
        this.startTime = slotDTO.getStartTime();
        this.endTime = slotDTO.getEndTime();
        this.status = slotDTO.getSlotStatus();
    }
}
