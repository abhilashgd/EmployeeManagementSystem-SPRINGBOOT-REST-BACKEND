package com.employee_management_system.repos;

import com.employee_management_system.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, String> {
}
