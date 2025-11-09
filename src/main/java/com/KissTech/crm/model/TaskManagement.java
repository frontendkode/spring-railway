package com.KissTech.crm.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "task_management")
public class TaskManagement extends AbstractEntity {

    @Column(name = "task_title", nullable = false, length = 100)
    private String taskTitle;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "task_type", length = 50)
    private String taskType;

    @Column(name = "priority", length = 30)
    private String priority;

    @Column(name = "staff_name", length = 100)
    private String staffName;

    @Column(name = "due_date", length = 50)
    private String dueDate;

    @Column(name = "associated_lead", length = 100)
    private String associatedLead;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_at", length = 50)
    private String createdAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    // ✅ Relationship: Many tasks can be assigned to one staff member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id") // foreign key in task_management table
    private StaffManagement staffManagement;
}
