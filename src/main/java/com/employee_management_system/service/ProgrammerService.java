package com.employee_management_system.service;

import com.employee_management_system.domain.Programmer;
import com.employee_management_system.model.ProgrammerDTO;
import com.employee_management_system.repos.ProgrammerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProgrammerService {

    private final ProgrammerRepository programmerRepository;

    public ProgrammerService(final ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public List<ProgrammerDTO> findAll() {
        return programmerRepository.findAll()
                .stream()
                .map(programmer -> mapToDTO(programmer, new ProgrammerDTO()))
                .collect(Collectors.toList());
    }

    public ProgrammerDTO get(final String programmingLanguages) {
        return programmerRepository.findById(programmingLanguages)
                .map(programmer -> mapToDTO(programmer, new ProgrammerDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final ProgrammerDTO programmerDTO) {
        final Programmer programmer = new Programmer();
        mapToEntity(programmerDTO, programmer);
        return programmerRepository.save(programmer).getProgrammingLanguages();
    }

    public void update(final String programmingLanguages, final ProgrammerDTO programmerDTO) {
        final Programmer programmer = programmerRepository.findById(programmingLanguages)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(programmerDTO, programmer);
        programmerRepository.save(programmer);
    }

    public void delete(final String programmingLanguages) {
        programmerRepository.deleteById(programmingLanguages);
    }

    private ProgrammerDTO mapToDTO(final Programmer programmer, final ProgrammerDTO programmerDTO) {
        programmerDTO.setProgrammingLanguages(programmer.getProgrammingLanguages());
        programmerDTO.setId(programmer.getId());
        return programmerDTO;
    }

    private Programmer mapToEntity(final ProgrammerDTO programmerDTO, final Programmer programmer) {
        programmer.setProgrammingLanguages(programmerDTO.getProgrammingLanguages());
        programmer.setId(programmerDTO.getId());
        return programmer;
    }

}
