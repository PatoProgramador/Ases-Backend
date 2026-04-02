package com.ases.api.dtos;

import com.ases.api.constants.AppointmentStatus;
import com.ases.api.constants.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentDTO {
    private String id;
    private UserDTO customer;
    private UserDTO stylistId;
    private SlotDTO slot;
    private ServiceType serviceType;
    private AppointmentStatus status;
}
