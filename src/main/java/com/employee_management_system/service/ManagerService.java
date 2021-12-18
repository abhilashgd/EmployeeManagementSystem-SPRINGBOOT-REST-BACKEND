package com.employee_management_system.service;

import com.employee_management_system.domain.Manager;
import com.employee_management_system.model.ManagerDTO;
import com.employee_management_system.repos.ManagerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(final ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<ManagerDTO> findAll() {
        return managerRepository.findAll()
                .stream()
                .map(manager -> mapToDTO(manager, new ManagerDTO()))
                .collect(Collectors.toList());
    }

    public ManagerDTO get(final Integer teamSize) {
        return managerRepository.findById(teamSize)
                .map(manager -> mapToDTO(manager, new ManagerDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final ManagerDTO managerDTO) {
        final Manager manager = new Manager();
        mapToEntity(managerDTO, manager);
        return managerRepository.save(manager).getTeamSize();
    }

    public void update(final Integer teamSize, final ManagerDTO managerDTO) {
        final Manager manager = managerRepository.findById(teamSize)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(managerDTO, manager);
        managerRepository.save(manager);
    }

    public void delete(final Integer teamSize) {
        managerRepository.deleteById(teamSize);
    }

    private ManagerDTO mapToDTO(final Manager manager, final ManagerDTO managerDTO) {
        managerDTO.setTeamSize(manager.getTeamSize());
        managerDTO.setId(manager.getId());
        return managerDTO;
    }

    private Manager mapToEntity(final ManagerDTO managerDTO, final Manager manager) {
        manager.setId(managerDTO.getId());
        return manager;
    }

}
