package com.KissTech.crm.createDTO;

import lombok.Data;

import java.util.UUID;
@Data
public class CreateTaskManagementDTO {

    private String taskTitle;
    private String description;
    private String taskType;
    private String priority;
    private String staffName;
    private String dueDate;
    private String associatedLead;
    private String userId;

    private UUID staffId;
}
