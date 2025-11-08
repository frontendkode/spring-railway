package com.KissTech.crm.apiService;

import com.KissTech.crm.DTO.TaskManagementDTO;
import com.KissTech.crm.createDTO.CreateTaskManagementDTO;
import com.KissTech.crm.mapper.TaskManagementMapper;
import com.KissTech.crm.model.StaffManagement;
import com.KissTech.crm.model.TaskManagement;
import com.KissTech.crm.service.TaskManagementService;
import com.KissTech.crm.updateDTO.UpdateTaskManagementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskManagementApiService {

    private final TaskManagementMapper taskManagementMapper;
    private final TaskManagementService taskManagementService;

    public TaskManagementApiService(TaskManagementMapper taskManagementMapper, TaskManagementService taskManagementService) {
        this.taskManagementMapper = taskManagementMapper;
        this.taskManagementService = taskManagementService;
    }


    public TaskManagementDTO create(CreateTaskManagementDTO createTaskManagementDTO) {
     return    taskManagementMapper.modelToDTO(taskManagementService.create(taskManagementMapper.createToDTO(createTaskManagementDTO)));
    }

    public TaskManagementDTO getById(UUID id) {
  return taskManagementMapper.modelToDTO(taskManagementService.getById(id));
    }

    public TaskManagementDTO updateTask(UpdateTaskManagementDTO updateLeadManagementDTO) {
        TaskManagement existingLead = taskManagementService.getById(updateLeadManagementDTO.getId());

        taskManagementMapper.updateDtoToModel(updateLeadManagementDTO, existingLead);
        TaskManagement updatedLead = taskManagementService.create(existingLead);
        return taskManagementMapper.modelToDTO(updatedLead);
    }

    public List<TaskManagementDTO> getAllTasks() {

        List<TaskManagement>  data=taskManagementService.getAllStaffs();
        return data.stream().map(taskManagementMapper::modelToDTO).toList();
    }

    public TaskManagementDTO taskStatusUpdateByID(UUID id, String status) {
        TaskManagement taskManagement=taskManagementService.getById(id);
        taskManagement.setStatus(status);
        taskManagementService.create(taskManagement);
        return taskManagementMapper.modelToDTO(taskManagementService.create(taskManagement));
    }
}
