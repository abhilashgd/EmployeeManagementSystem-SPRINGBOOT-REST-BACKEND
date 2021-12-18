package com.employee_management_system.repos;

import com.employee_management_system.domain.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProgrammerRepository extends JpaRepository<Programmer, String> {
}
