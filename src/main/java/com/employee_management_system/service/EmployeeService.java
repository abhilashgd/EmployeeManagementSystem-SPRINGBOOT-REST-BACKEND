package com.employee_management_system.service;

import com.employee_management_system.domain.Doctor;
import com.employee_management_system.domain.Employee;
import com.employee_management_system.domain.Manager;
import com.employee_management_system.domain.Programmer;
import com.employee_management_system.model.EmployeeDTO;
import com.employee_management_system.repos.DoctorRepository;
import com.employee_management_system.repos.EmployeeRepository;
import com.employee_management_system.repos.ManagerRepository;
import com.employee_management_system.repos.ProgrammerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProgrammerRepository programmerRepository;
    private final ManagerRepository managerRepository;
    private final DoctorRepository doctorRepository;

    public EmployeeService(final EmployeeRepository employeeRepository,
            final ProgrammerRepository programmerRepository,
            final ManagerRepository managerRepository, final DoctorRepository doctorRepository) {
        this.employeeRepository = employeeRepository;
        this.programmerRepository = programmerRepository;
        this.managerRepository = managerRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO get(final Long facebookid) {
        return employeeRepository.findById(facebookid)
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final EmployeeDTO employeeDTO) {
        final Employee employee = new Employee();
        mapToEntity(employeeDTO, employee);
        return employeeRepository.save(employee).getFacebookid();
    }

    public void update(final Long facebookid, final EmployeeDTO employeeDTO) {
        final Employee employee = employeeRepository.findById(facebookid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(employeeDTO, employee);
        employeeRepository.save(employee);
    }

    public void delete(final Long facebookid) {
        employeeRepository.deleteById(facebookid);
    }

    private EmployeeDTO mapToDTO(final Employee employee, final EmployeeDTO employeeDTO) {
        employeeDTO.setFacebookid(employee.getFacebookid());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setExperience(employee.getExperience());
        employeeDTO.setProgrammer(employee.getProgrammer() == null ? null : employee.getProgrammer().getProgrammingLanguages());
        employeeDTO.setManager(employee.getManager() == null ? null : employee.getManager().getTeamSize());
        employeeDTO.setDoctor(employee.getDoctor() == null ? null : employee.getDoctor().getId());
        return employeeDTO;
    }

    private Employee mapToEntity(final EmployeeDTO employeeDTO, final Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setExperience(employeeDTO.getExperience());
        if (employeeDTO.getProgrammer() != null && (employee.getProgrammer() == null || !employee.getProgrammer().getProgrammingLanguages().equals(employeeDTO.getProgrammer()))) {
            final Programmer programmer = programmerRepository.findById(employeeDTO.getProgrammer())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "programmer not found"));
            employee.setProgrammer(programmer);
        }
        if (employeeDTO.getManager() != null && (employee.getManager() == null || !employee.getManager().getTeamSize().equals(employeeDTO.getManager()))) {
            final Manager manager = managerRepository.findById(employeeDTO.getManager())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "manager not found"));
            employee.setManager(manager);
        }
        if (employeeDTO.getDoctor() != null && (employee.getDoctor() == null || !employee.getDoctor().getId().equals(employeeDTO.getDoctor()))) {
            final Doctor doctor = doctorRepository.findById(employeeDTO.getDoctor())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "doctor not found"));
            employee.setDoctor(doctor);
        }
        return employee;
    }

}
