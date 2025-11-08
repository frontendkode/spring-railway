package com.KissTech.crm.mapper;

import com.KissTech.crm.createDTO.CreateTaskManagementDTO;
import com.KissTech.crm.DTO.TaskManagementDTO;
import com.KissTech.crm.model.StaffManagement;
import com.KissTech.crm.model.TaskManagement;
import com.KissTech.crm.repository.StaffManagementRepository;
import com.KissTech.crm.service.StaffManagementService;
import com.KissTech.crm.updateDTO.UpdateTaskManagementDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class TaskManagementMapper {
    private final StaffManagementService service;

    public TaskManagementMapper(StaffManagementService service) {
        this.service = service;
    }

    public TaskManagement createToDTO(CreateTaskManagementDTO dto) {
        TaskManagement model = new TaskManagement();
        model.setTaskTitle(dto.getTaskTitle());
        model.setDescription(dto.getDescription());
        model.setTaskType(dto.getTaskType());
        model.setPriority(dto.getPriority());
        model.setStaffName(dto.getStaffName());
        model.setDueDate(dto.getDueDate());
        model.setAssociatedLead(dto.getAssociatedLead());
        model.setCreatedAt(String.valueOf(LocalDateTime.now()));
        model.setCreatedBy(dto.getUserId());
        model.setStatus("Pending");

        // Link staff entity if ID is provided
        if (dto.getStaffId() != null) {
            StaffManagement staff = service.getById(dto.getStaffId());
            model.setStaffManagement(staff);
            model.setStaffName(staff.getName());
        }

        return model;
    }

    public TaskManagementDTO modelToDTO(TaskManagement model) {
        TaskManagementDTO dto = new TaskManagementDTO();
        dto.setId(model.getId());
        dto.setTaskTitle(model.getTaskTitle());
        dto.setDescription(model.getDescription());
        dto.setTaskType(model.getTaskType());
        dto.setPriority(model.getPriority());
        dto.setStaffName(model.getStaffName());
        dto.setDueDate(model.getDueDate());
        dto.setAssociatedLead(model.getAssociatedLead());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setCreatedBy(model.getCreatedBy());
        dto.setStatus(model.getStatus());


        if (model.getStaffManagement() != null) {
            dto.setStaffId(model.getStaffManagement().getId());
            dto.setStaffEmail(model.getStaffManagement().getEmail());
            dto.setStaffDepartment(model.getStaffManagement().getDepartment());
            dto.setStaffName(model.getStaffName());

        }

        return dto;
    }

    public void updateDtoToModel(UpdateTaskManagementDTO dto, TaskManagement existingTask) {
        if (dto.getTaskTitle() != null) existingTask.setTaskTitle(dto.getTaskTitle());
        if (dto.getDescription() != null) existingTask.setDescription(dto.getDescription());
        if (dto.getTaskType() != null) existingTask.setTaskType(dto.getTaskType());
        if (dto.getPriority() != null) existingTask.setPriority(dto.getPriority());
        if (dto.getStaffName() != null) existingTask.setStaffName(dto.getStaffName());
        if (dto.getDueDate() != null) existingTask.setDueDate(dto.getDueDate());
        if (dto.getAssociatedLead() != null) existingTask.setAssociatedLead(dto.getAssociatedLead());
        if(dto.getStatus()!=null)existingTask.setStatus(dto.getStatus());
        if (dto.getStaffId() != null) {
            StaffManagement staff = service.getById(dto.getStaffId());
            existingTask.setStaffManagement(staff);
        }
    }
}
