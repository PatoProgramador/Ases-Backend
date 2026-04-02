package com.ases.api.service;

import com.ases.api.ErrorsHandlers.NotFoundException;
import com.ases.api.constants.AppointmentStatus;
import com.ases.api.constants.SlotStatus;
import com.ases.api.contracts.AppointmentDAO;
import com.ases.api.contracts.AvailabilitySlotDAO;
import com.ases.api.contracts.UserDAO;
import com.ases.api.dtos.*;
import com.ases.api.model.Appointment;
import com.ases.api.model.AvailabilitySlot;
import com.ases.api.model.UserModel;
import com.ases.api.utils.CustomBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AvailabilitySlotDAO availabilitySlotDAO;

    public String bookAppointment(CreateAppointmentDTO createAppointmentDTO) {
        Appointment appointment = new Appointment(createAppointmentDTO);
        AvailabilitySlot slot = availabilitySlotDAO.getSlotById(createAppointmentDTO.getSlotId()).orElseThrow(
                () -> new NotFoundException("Slot not found")
        );
        slot.setStatus(SlotStatus.BOOKED);
        appointment.setIsDeleted(false);
        appointment.setStatus(AppointmentStatus.PENDING);
        String appointmentId = appointmentDAO.save(appointment);
        availabilitySlotDAO.save(slot);
        return appointmentId;
    }

    public List<AppointmentDTO> getAppointmentsByStylistId(String stylistId) {
        List<Appointment> appointments = appointmentDAO.getAppointmentsByStylistId(stylistId);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            UserModel customer = userDAO.findById(appointment.getCustomerId()).orElseThrow(
                    () -> new NotFoundException("Customer not found")
            );
            UserModel stylist = userDAO.findById(appointment.getStylistId()).orElseThrow(
                    () -> new NotFoundException("Stylist not found")
            );
            AvailabilitySlot slot = availabilitySlotDAO.getSlotById(appointment.getSlotId()).orElseThrow(
                    () -> new NotFoundException("Slot not found")
            );
            AppointmentDTO appointmentDTO = new AppointmentDTO(
                    appointment.getId(),
                    new UserDTO(customer),
                    new UserDTO(stylist),
                    new SlotDTO(slot),
                    appointment.getServiceType(),
                    appointment.getStatus()
            );
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

    public List<AppointmentDTO> getAppointmentsByCustomerId(String customerId) {
        List<Appointment> appointments = appointmentDAO.getAppointmentsByCustomerId(customerId);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            UserModel customer = userDAO.findById(appointment.getCustomerId()).orElseThrow(
                    () -> new NotFoundException("Customer not found")
            );
            UserModel stylist = userDAO.findById(appointment.getStylistId()).orElseThrow(
                    () -> new NotFoundException("Stylist not found")
            );
            AvailabilitySlot slot = availabilitySlotDAO.getSlotById(appointment.getSlotId()).orElseThrow(
                    () -> new NotFoundException("Slot not found")
            );
            AppointmentDTO appointmentDTO = new AppointmentDTO(
                    appointment.getId(),
                    new UserDTO(customer),
                    new UserDTO(stylist),
                    new SlotDTO(slot),
                    appointment.getServiceType(),
                    appointment.getStatus()
            );
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

    public AppointmentDTO getAppointmentById(String appointmentId) {
        System.out.println("METHOD CALLED with id: " + appointmentId);
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId).orElseThrow(
                () -> new NotFoundException("Appointment not found")
        );
        UserModel customer = userDAO.findById(appointment.getCustomerId()).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        UserModel stylist = userDAO.findById(appointment.getStylistId()).orElseThrow(
                () -> new NotFoundException("Stylist not found")
        );
        System.out.println("slotId from appointment: " + appointment.getSlotId());
        AvailabilitySlot slot = availabilitySlotDAO.getSlotById(appointment.getSlotId()).orElseThrow(
                () -> new NotFoundException("Slot not found")
        );

        return new AppointmentDTO(
                appointment.getId(),
                new UserDTO(customer),
                new UserDTO(stylist),
                new SlotDTO(slot),
                appointment.getServiceType(),
                appointment.getStatus()
        );
    }

    public AppointmentDTO updateAppointment(String appointmentId, UpdateAppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId).orElseThrow(
                () -> new NotFoundException("Appointment not found")
        );
        CustomBeanUtils.copyNonNullProperties(appointmentDTO, appointment);

        String updatedAppointmentId = appointmentDAO.save(appointment);
        return getAppointmentById(updatedAppointmentId);
    }

    public void deleteAppointment(String appointmentId) {
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId).orElseThrow(
                () -> new NotFoundException("Appointment not found")
        );
        appointment.setIsDeleted(true);
        appointmentDAO.save(appointment);
    }
}
