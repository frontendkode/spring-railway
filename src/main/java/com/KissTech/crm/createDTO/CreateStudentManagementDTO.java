package com.KissTech.crm.createDTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateStudentManagementDTO {

    private String fullName;
    private String batch;
    private String phoneNumber;
    private String email;
    private String course;
    private String shift;
    private String classStartDate;
    private String classEndDate;
    private String userId;
    private String status;
   private String  studentID;
   private String classCount;
   private String totalFee;
   private String initialAmt;
   private String balanceFee;
   private String emiEnabled;
   private String split;
   private String startTime;
   private String endTime;
   private List<CreateDueDayDTO> dueDay;
   private List<CreateAttendanceDTO> attendance;



}
