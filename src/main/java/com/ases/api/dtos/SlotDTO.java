package com.ases.api.dtos;


import com.ases.api.constants.SlotStatus;
import com.ases.api.model.AvailabilitySlot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlotDTO {
    private String id;
    private String stylistId;
    private String startTime;
    private String endTime;
    private SlotStatus status;

    public SlotDTO(AvailabilitySlot slot) {
        this.id = slot.getId();
        this.stylistId = slot.getStylistId();
        this.startTime = slot.getStartTime().toString();
        this.endTime = slot.getEndTime().toString();
        this.status = slot.getStatus();
    }
}
