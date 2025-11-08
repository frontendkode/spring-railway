package com.KissTech.crm.createDTO;

import lombok.Data;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.UUID;

@Data
public class CreateStaffManagementDTO {

    private String name;

    private String role;
    private String email;
    private String phoneNo;
    private String department;

    private String joiningDate;
private String userId;

    private String idProof;
    private String proofNo;
}
