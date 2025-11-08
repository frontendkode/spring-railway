package com.KissTech.crm.createDTO;

import lombok.Data;
import jakarta.persistence.Column;


@Data

public class CreateReminderLogDTO {

    private String type;

    private String description;

    private String reminderDate; // Consider using LocalDate instead of String
    private String reminderTime; // Consider using LocalTime or LocalDateTime
    private String createdAt;
}
