package com.KissTech.crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "attendance")
public class Attendance extends AbstractEntity {

    private String date;
    private String day;
    private String attend;
    private String createAt;

}
