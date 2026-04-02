package com.ases.api.dtos;

import com.ases.api.constants.AppointmentStatus;
import com.ases.api.constants.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentDTO {
    private String slotId;
    private ServiceType serviceType;
    private AppointmentStatus status;
}
