package com.employee_management_system.repos;

import com.employee_management_system.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
