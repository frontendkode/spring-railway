package com.KissTech.crm.updateDTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTaskManagementDTO {


    private UUID id;
    private String taskTitle;
    private String description;
    private String taskType;
    private String priority;
    private String staffName;
    private String dueDate;
    private String associatedLead;
    private String status;

    // Optional if staff can change
    private UUID staffId;
}
