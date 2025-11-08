package com.KissTech.crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table(name = "reminder_log")
public class ReminderLog extends AbstractEntity {

    private String type;

    @Column(length = 1000) // Optional: if description can be long
    private String description;

    private String reminderDate; // Consider using LocalDate instead of String
    private String reminderTime; // Consider using LocalTime or LocalDateTime
    private String createdAt;    // Typo: "createAt" → "createdAt"; consider LocalDateTime
}
