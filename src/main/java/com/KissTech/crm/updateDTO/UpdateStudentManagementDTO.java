package com.KissTech.crm.updateDTO;

import com.KissTech.crm.createDTO.CreateAttendanceDTO;
import com.KissTech.crm.createDTO.CreateDueDayDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class UpdateStudentManagementDTO {

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
    private UUID id;


    private List<UpdateDueDayDTO> dueDay;
    private List<UpdateAttendanceDTO> attendance;
}
