package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.List;

@Data
public class LeadNotificationDTO {
    private String fullName;
    private List<ReminderLogDTO> reminderLogs;
}
