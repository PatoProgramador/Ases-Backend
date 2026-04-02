package com.ases.api.service;

import com.ases.api.ErrorsHandlers.AlreadyExistsException;
import com.ases.api.ErrorsHandlers.NotFoundException;
import com.ases.api.contracts.AvailabilitySlotDAO;
import com.ases.api.dtos.CreateSlotDTO;
import com.ases.api.dtos.SlotDTO;
import com.ases.api.dtos.UpdateSlotDTO;
import com.ases.api.model.AvailabilitySlot;
import com.ases.api.utils.CustomBeanUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilitySlotService {

    @Autowired
    private AvailabilitySlotDAO availabilitySlotDAO;

    public String saveSlot(CreateSlotDTO slotDTO) {
        Boolean existingSlot = availabilitySlotDAO.getByDatesAndStylistId(
                slotDTO.getStylistId(),
                slotDTO.getStartTime(),
                slotDTO.getEndTime()
        );
        if (!existingSlot) {
            AvailabilitySlot availabilitySlot = new AvailabilitySlot(slotDTO);
            availabilitySlot.setIsDeleted(false);
            return availabilitySlotDAO.save(availabilitySlot);
        } else {
            throw new AlreadyExistsException("Slot already exists");
        }
    }

    public SlotDTO getSlotById(String slotId) {
        AvailabilitySlot slot = availabilitySlotDAO.getSlotById(slotId).orElseThrow(
                () -> new NotFoundException("Slot not found")
        );
        return new SlotDTO(slot);
    }

    public List<SlotDTO> getSlotsByStylistId(String stylistId) {
        List<AvailabilitySlot> slots = availabilitySlotDAO.getSlotsByStylistId(stylistId);
        return slots.stream().map(SlotDTO::new).toList();
    }

    @Transactional
    public SlotDTO updateSlot(String slotId, UpdateSlotDTO updateSlotDTO) {
        AvailabilitySlot slot = availabilitySlotDAO.getSlotById(slotId).orElseThrow(
                () -> new NotFoundException("Slot not found")
        );
        CustomBeanUtils.copyNonNullProperties(updateSlotDTO, slot);

        String updatedSlotId = availabilitySlotDAO.save(slot);
        return getSlotById(updatedSlotId);
    }

    @Transactional
    public void deleteSlot(String slotId) {
        AvailabilitySlot slot = availabilitySlotDAO.getSlotById(slotId).orElseThrow(
                () -> new NotFoundException("Slot not found")
        );
        slot.setIsDeleted(true);
        availabilitySlotDAO.save(slot);
    }
}
