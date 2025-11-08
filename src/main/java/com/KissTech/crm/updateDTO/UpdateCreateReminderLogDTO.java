package com.KissTech.crm.updateDTO;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCreateReminderLogDTO {

    private String type;

    private String description;
private UUID id;
    private String reminderDate; // Consider using LocalDate instead of String
    private String reminderTime; // Consider using LocalTime or LocalDateTime
    private String createdAt;
}
