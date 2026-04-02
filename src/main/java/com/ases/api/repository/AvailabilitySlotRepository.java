package com.ases.api.repository;

import com.ases.api.model.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, String> {
    Optional<AvailabilitySlot> findByStartTimeAndEndTimeAndStylistIdAndIsDeletedIsFalse(LocalDateTime startTime, LocalDateTime endTime, String stylistId);
    List<AvailabilitySlot> findByStylistIdAndIsDeletedIsFalse(String stylistId);
    Optional<AvailabilitySlot> findByIdAndIsDeletedIsFalse(String id);
}
