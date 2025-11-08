package com.KissTech.crm.updateDTO;

import com.KissTech.crm.createDTO.CreateActivityLogDTO;
import com.KissTech.crm.createDTO.CreateReminderLogDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateLeadManagementDTO {
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String courseInterest;
    private String source;
    private String counselor;
    private String notes;
    private String stage;


    private List<UpdateActivityLogDTO> activityLog;

    private List<UpdateCreateReminderLogDTO> reminderLog;
}
