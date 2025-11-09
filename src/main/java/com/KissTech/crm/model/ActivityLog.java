package com.KissTech.crm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "activity_log")
public class ActivityLog extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String type;

    @Column(length = 255)
    private String description;

    @Column(length = 100)
    private String logResult;

    @Column(name = "created_at", updatable = false)
    private String createdAt;
}
