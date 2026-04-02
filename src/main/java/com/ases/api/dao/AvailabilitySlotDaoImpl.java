package com.ases.api.dao;

import com.ases.api.contracts.AvailabilitySlotDAO;
import com.ases.api.model.AvailabilitySlot;
import com.ases.api.repository.AvailabilitySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AvailabilitySlotDaoImpl implements AvailabilitySlotDAO {
    @Autowired
    private AvailabilitySlotRepository availabilitySlotRepository;

    @Override
    public String save(AvailabilitySlot availabilitySlot) {
        return availabilitySlotRepository.save(availabilitySlot).getId();
    }

    @Override
    public Boolean getByDatesAndStylistId(String stylistId, LocalDateTime startDate, LocalDateTime endDate) {
        return availabilitySlotRepository.findByStartTimeAndEndTimeAndStylistIdAndIsDeletedIsFalse(startDate, endDate, stylistId).isPresent();
    }

    @Override
    public List<AvailabilitySlot> getSlotsByStylistId(String stylistId) {
        return availabilitySlotRepository.findByStylistIdAndIsDeletedIsFalse(stylistId);
    }

    @Override
    public Optional<AvailabilitySlot> getSlotById(String slotId) {
        return availabilitySlotRepository.findByIdAndIsDeletedIsFalse(slotId);
    }
}
