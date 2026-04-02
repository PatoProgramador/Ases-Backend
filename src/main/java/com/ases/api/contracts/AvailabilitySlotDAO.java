package com.ases.api.contracts;

import com.ases.api.model.AvailabilitySlot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AvailabilitySlotDAO {
    String save(AvailabilitySlot availabilitySlot);
    Boolean getByDatesAndStylistId(String stylistId, LocalDateTime startDate, LocalDateTime endDate);
    List<AvailabilitySlot> getSlotsByStylistId(String stylistId);
    Optional<AvailabilitySlot> getSlotById(String slotId);
}
