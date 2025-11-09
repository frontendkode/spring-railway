package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ReminderLogDTO {
    private String type;

    private String description;

    private String reminderDate; // Consider using LocalDate instead of String
    private String reminderTime; // Consider using LocalTime or LocalDateTime
    private String createdAt;
    private UUID id;
}
