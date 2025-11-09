package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.UUID;
@Data
public class TaskManagementDTO {



    private UUID id;
    private String taskTitle;
    private String description;
    private String taskType;
    private String priority;
    private String staffName;
    private String dueDate;
    private String associatedLead;
    private String createdAt;
    private String createdBy;

    // Include linked staff info if needed
    private UUID staffId;
    private String staffEmail;
    private String staffDepartment;
    private String status;

}
