package com.ases.api.dao;

import com.ases.api.contracts.AppointmentDAO;
import com.ases.api.model.Appointment;
import com.ases.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentDaoImpl implements AppointmentDAO {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public String save(Appointment appointment) {
        return appointmentRepository.save(appointment).getId();
    }

    @Override
    public List<Appointment> getAppointmentsByStylistId(String stylistId) {
        return appointmentRepository.findByStylistIdAndIsDeletedIsFalse(stylistId);
    }

    @Override
    public List<Appointment> getAppointmentsByCustomerId(String customerId) {
        return appointmentRepository.findByCustomerIdAndIsDeletedIsFalse(customerId);
    }

    @Override
    public Optional<Appointment> getAppointmentById(String appointmentId) {
        return appointmentRepository.findAppointmentByIdAndIsDeletedIsFalse(appointmentId);
    }
}
