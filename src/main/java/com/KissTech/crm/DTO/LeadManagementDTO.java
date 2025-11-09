package com.KissTech.crm.DTO;

import com.KissTech.crm.model.ActivityLog;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class LeadManagementDTO {

    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String courseInterest;
    private String source;
    private String counselor;
    private String notes;
    private String stage;
    private String createdBy;
    private LocalDateTime createdAt;
    private List<ActivityLogDTO> activityLog;
    private List<ReminderLogDTO> reminderLog;
}
