package com.employee_management_system.service;

import com.employee_management_system.domain.Doctor;
import com.employee_management_system.model.DoctorDTO;
import com.employee_management_system.repos.DoctorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(final DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> mapToDTO(doctor, new DoctorDTO()))
                .collect(Collectors.toList());
    }

    public DoctorDTO get(final String id) {
        return doctorRepository.findById(id)
                .map(doctor -> mapToDTO(doctor, new DoctorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final DoctorDTO doctorDTO) {
        final Doctor doctor = new Doctor();
        mapToEntity(doctorDTO, doctor);
        return doctorRepository.save(doctor).getId();
    }

    public void update(final String id, final DoctorDTO doctorDTO) {
        final Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }

    public void delete(final String id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO mapToDTO(final Doctor doctor, final DoctorDTO doctorDTO) {
        doctorDTO.setId(doctor.getId());
        doctorDTO.setSurgery(doctor.getSurgery());
        return doctorDTO;
    }

    private Doctor mapToEntity(final DoctorDTO doctorDTO, final Doctor doctor) {
        doctor.setId(doctorDTO.getId());
        doctor.setSurgery(doctorDTO.getSurgery());
        return doctor;
    }

}
