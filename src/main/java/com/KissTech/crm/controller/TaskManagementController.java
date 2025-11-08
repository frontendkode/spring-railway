package com.KissTech.crm.controller;

import com.KissTech.crm.DTO.TaskManagementDTO;
import com.KissTech.crm.apiService.TaskManagementApiService;
import com.KissTech.crm.createDTO.CreateTaskManagementDTO;
import com.KissTech.crm.updateDTO.UpdateTaskManagementDTO;
import com.KissTech.crm.utils.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="TaskManagement Controller ApiS" , description="Manged  TaskManagement Api")
@RestController
@RequestMapping("kiss-tech/api-task")
public class TaskManagementController {


private final TaskManagementApiService taskManagementApiService;

    public TaskManagementController(TaskManagementApiService taskManagementApiService) {
        this.taskManagementApiService = taskManagementApiService;
    }


    @PostMapping("/create")
    public ResponseEntity<RestResponse<TaskManagementDTO>> create(@RequestBody CreateTaskManagementDTO createTaskManagementDTO) {
        try {
            TaskManagementDTO appointmentDTO = taskManagementApiService.create(createTaskManagementDTO);
            RestResponse<TaskManagementDTO> response = new RestResponse<>(
                    "Task created successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to create Task: " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }







    @PostMapping("/getById")
    public ResponseEntity<RestResponse<TaskManagementDTO>> getById(@RequestParam UUID id) {
        try {
            TaskManagementDTO appointmentDTO = taskManagementApiService.getById(id);
            RestResponse<TaskManagementDTO> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }


    @PostMapping("/update")
    public ResponseEntity<RestResponse<TaskManagementDTO>> updateTask(@RequestBody UpdateTaskManagementDTO updateLeadManagementDTO) {
        try {
            TaskManagementDTO appointmentDTO = taskManagementApiService.updateTask(updateLeadManagementDTO);
            RestResponse<TaskManagementDTO> response = new RestResponse<>(
                    "Task updated successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to update Task: " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }





    @PostMapping("/getAllTasks")
    public ResponseEntity<RestResponse<List<TaskManagementDTO>>> getAllTasks() {
        try {
            List<TaskManagementDTO> appointmentDTO = taskManagementApiService.getAllTasks();
            RestResponse<List<TaskManagementDTO>> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }



    @PostMapping("/taskStatusUpdateById")
    public ResponseEntity<RestResponse<TaskManagementDTO>> taskStatusUpdateByID(@RequestParam UUID id,@RequestParam String status) {
        try {
            TaskManagementDTO appointmentDTO = taskManagementApiService.taskStatusUpdateByID(id,status);
            RestResponse<TaskManagementDTO> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }




}
