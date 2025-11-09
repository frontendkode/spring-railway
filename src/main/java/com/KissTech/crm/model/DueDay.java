package com.KissTech.crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dueDate")
public class DueDay extends AbstractEntity {

    private String dueNo;
    private String dueAmt;
    private String dueDate;
    private String status;
    private String createAt;

}
