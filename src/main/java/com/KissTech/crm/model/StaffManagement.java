package com.KissTech.crm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "staff_management")
public class StaffManagement extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone_no", length = 15)
    private String phoneNo;

    @Column(name = "department", length = 100)
    private String department;

    @Column(name = "joining_date")
    private String joiningDate;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "id_proof", length = 50)
    private String idProof;

    @Column(name = "proof_no", length = 50)
    private String proofNo;

}
