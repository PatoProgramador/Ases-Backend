package com.ases.api.dtos;

import com.ases.api.constants.AppointmentStatus;
import com.ases.api.constants.ServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentDTO {
    @NotBlank(message = "The stylist ID is required")
    private String stylistId;

    @NotBlank(message = "The customer ID is required")
    private String customerId;

    @NotBlank(message = "The slot ID is required")
    private String slotId;

    @NotNull(message = "Service type is required")
    private ServiceType serviceType;
}
