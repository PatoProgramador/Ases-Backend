package com.ases.api.model;

import com.ases.api.constants.AppointmentStatus;
import com.ases.api.constants.ServiceType;
import com.ases.api.dtos.CreateAppointmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String stylistId;
    private String customerId;
    private String slotId;
    private ServiceType serviceType;
    private AppointmentStatus status;
    private Boolean isDeleted;

    public Appointment(CreateAppointmentDTO appointmentDTO) {
        this.stylistId = appointmentDTO.getStylistId();
        this.customerId = appointmentDTO.getCustomerId();
        this.slotId = appointmentDTO.getSlotId();
        this.serviceType = appointmentDTO.getServiceType();
    }
}
