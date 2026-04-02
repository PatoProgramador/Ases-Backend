package com.ases.api.dtos;

import com.ases.api.constants.SlotStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateSlotDTO {
    private String stylistId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus slotStatus;
}
