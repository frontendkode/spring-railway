package com.KissTech.crm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lead_management")
public class LeadManagement extends AbstractEntity {

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "course_interest", length = 100)
    private String courseInterest;

    @Column(name = "source", length = 100)
    private String source;

    @Column(name = "counselor", length = 100)
    private String counselor;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // ✅ Use LocalDateTime instead of String
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "stage", length = 50)
    private String stage;


    // ✅ Bi-directional OneToMany for logs
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id") // Foreign key column in activity_log table
    private List<ActivityLog> activityLogs;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id") // Foreign key column in reminder_log table
    private List<ReminderLog> reminderLogs;

    // Automatically set timestamp
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
