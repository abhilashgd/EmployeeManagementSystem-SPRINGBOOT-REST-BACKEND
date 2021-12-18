package com.employee_management_system.rest;

import com.employee_management_system.model.ManagerDTO;
import com.employee_management_system.service.ManagerService;
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
@RequestMapping(value = "/api/managers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(final ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<ManagerDTO>> getAllManagers() {
        return ResponseEntity.ok(managerService.findAll());
    }

    @GetMapping("/{teamSize}")
    public ResponseEntity<ManagerDTO> getManager(@PathVariable final Integer teamSize) {
        return ResponseEntity.ok(managerService.get(teamSize));
    }

    @PostMapping
    public ResponseEntity<Integer> createManager(@RequestBody @Valid final ManagerDTO managerDTO) {
        return new ResponseEntity<>(managerService.create(managerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{teamSize}")
    public ResponseEntity<Void> updateManager(@PathVariable final Integer teamSize,
            @RequestBody @Valid final ManagerDTO managerDTO) {
        managerService.update(teamSize, managerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teamSize}")
    public ResponseEntity<Void> deleteManager(@PathVariable final Integer teamSize) {
        managerService.delete(teamSize);
        return ResponseEntity.noContent().build();
    }

}
