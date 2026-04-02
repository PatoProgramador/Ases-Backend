package com.ases.api.repository;

import com.ases.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    Optional<Appointment> findAppointmentByIdAndIsDeletedIsFalse(String appointmentId);
    List<Appointment> findByStylistIdAndIsDeletedIsFalse(String stylistId);
    List<Appointment> findByCustomerIdAndIsDeletedIsFalse(String clientEmail);
}
