package com.KissTech.crm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "student_management")
public class StudentManagement extends AbstractEntity {

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "batch", length = 50)
    private String batch;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "course", length = 100)
    private String course;

    @Column(name = "shift", length = 20)
    private String shift;

    @Column(name = "class_start_date", length = 20)
    private String classStartDate;

    @Column(name = "class_end_date", length = 20)
    private String classEndDate;

    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "student_id", length = 50)
    private String studentID;

    @Column(name = "class_count", length = 10)
    private String classCount;

    @Column(name = "total_fee", length = 20)
    private String totalFee;

    @Column(name = "initial_amt", length = 20)
    private String initialAmt;

    @Column(name = "balance_fee", length = 20)
    private String balanceFee;

    @Column(name = "emi_enabled", length = 5)
    private String emiEnabled;

    @Column(name = "split", length = 10)
    private String split;

    @Column(name = "start_time", length = 20)
    private String startTime;

    @Column(name = "end_time", length = 20)
    private String endTime;

    @Column(name = "created_at", length = 50)
    private String createAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;




    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_management_id")
    private List<Attendance> attendance = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_management_id")
    private List<DueDay> dueDay = new ArrayList<>();

}
