package com.employee_management_system.rest;

import com.employee_management_system.model.DoctorDTO;
import com.employee_management_system.service.DoctorService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(final DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable final String id) {
        return ResponseEntity.ok(doctorService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createDoctor(@RequestBody @Valid final DoctorDTO doctorDTO) {
        doctorService.create(doctorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDoctor(@PathVariable final String id,
            @RequestBody @Valid final DoctorDTO doctorDTO) {
        doctorService.update(id, doctorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable final String id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
