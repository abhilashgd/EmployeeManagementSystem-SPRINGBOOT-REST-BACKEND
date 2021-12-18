package com.employee_management_system.rest;

import com.employee_management_system.model.EmployeeDTO;
import com.employee_management_system.service.EmployeeService;
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
@RequestMapping(value = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{facebookid}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable final Long facebookid) {
        return ResponseEntity.ok(employeeService.get(facebookid));
    }

    @PostMapping
    public ResponseEntity<Long> createEmployee(@RequestBody @Valid final EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.create(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{facebookid}")
    public ResponseEntity<Void> updateEmployee(@PathVariable final Long facebookid,
            @RequestBody @Valid final EmployeeDTO employeeDTO) {
        employeeService.update(facebookid, employeeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{facebookid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final Long facebookid) {
        employeeService.delete(facebookid);
        return ResponseEntity.noContent().build();
    }

}
