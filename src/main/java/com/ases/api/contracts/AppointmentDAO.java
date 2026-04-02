package com.ases.api.contracts;

import com.ases.api.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentDAO {
    String save(Appointment appointment);
    List<Appointment> getAppointmentsByStylistId(String stylistId);
    List<Appointment> getAppointmentsByCustomerId(String customerId);
    Optional<Appointment> getAppointmentById(String appointmentId);
}
