package com.KissTech.crm.createDTO;

import com.KissTech.crm.model.ActivityLog;
import com.KissTech.crm.model.ReminderLog;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CreateLeadManagementDTO {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String courseInterest;
    private String source;
    private String counselor;
    private String notes;
    private String createdBy; // corresponds to user creating the lead
    private String stage;

    private List<CreateActivityLogDTO> activityLog;

    private List<CreateReminderLogDTO> reminderLog;

}
