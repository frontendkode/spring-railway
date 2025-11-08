package com.KissTech.crm.service;

import com.KissTech.crm.model.TaskManagement;
import com.KissTech.crm.repository.TaskManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskManagementService {
    private final TaskManagementRepository taskManagementRepository;

    public TaskManagementService(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    public TaskManagement create(TaskManagement toDTO) {
      return   taskManagementRepository.save(toDTO);
    }

    public TaskManagement getById(UUID id) {
        return taskManagementRepository.findById(id).orElseThrow();
    }

    public List<TaskManagement> getAllStaffs() {
        return taskManagementRepository.findAll();
    }

    public List<TaskManagement> findByStaffId(UUID id) {
        return taskManagementRepository.findByStaffManagementId(id);
    }
}
