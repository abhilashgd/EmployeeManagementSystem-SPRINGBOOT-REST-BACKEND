package com.employee_management_system.rest;

import com.employee_management_system.model.ProgrammerDTO;
import com.employee_management_system.service.ProgrammerService;
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
@RequestMapping(value = "/api/programmers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProgrammerController {

    private final ProgrammerService programmerService;

    public ProgrammerController(final ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    @GetMapping
    public ResponseEntity<List<ProgrammerDTO>> getAllProgrammers() {
        return ResponseEntity.ok(programmerService.findAll());
    }

    @GetMapping("/{programmingLanguages}")
    public ResponseEntity<ProgrammerDTO> getProgrammer(
            @PathVariable final String programmingLanguages) {
        return ResponseEntity.ok(programmerService.get(programmingLanguages));
    }

    @PostMapping
    public ResponseEntity<Void> createProgrammer(
            @RequestBody @Valid final ProgrammerDTO programmerDTO) {
        programmerService.create(programmerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{programmingLanguages}")
    public ResponseEntity<Void> updateProgrammer(@PathVariable final String programmingLanguages,
            @RequestBody @Valid final ProgrammerDTO programmerDTO) {
        programmerService.update(programmingLanguages, programmerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{programmingLanguages}")
    public ResponseEntity<Void> deleteProgrammer(@PathVariable final String programmingLanguages) {
        programmerService.delete(programmingLanguages);
        return ResponseEntity.noContent().build();
    }

}
