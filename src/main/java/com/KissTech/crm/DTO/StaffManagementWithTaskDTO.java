package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class StaffManagementWithTaskDTO {

    private UUID id;
    private String name;

    private String role;
    private String email;
    private String phoneNo;
    private String department;

    private String joiningDate;
    private String createdAt;

    private String createdBy;

    private String idProof;
    private String proofNo;
    private List<TaskManagementDTO> taskManagementDTOS;
    private String status;

}
