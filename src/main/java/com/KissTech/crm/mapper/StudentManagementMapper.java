package com.KissTech.crm.mapper;

import com.KissTech.crm.DTO.StudentDashBoardDTO;
import com.KissTech.crm.DTO.StudentNotificationAttendanceDTO;
import com.KissTech.crm.DTO.StudentNotificationDueDayDTO;
import com.KissTech.crm.model.StudentManagement;
import com.KissTech.crm.createDTO.CreateStudentManagementDTO;
import com.KissTech.crm.updateDTO.UpdateStudentManagementDTO;
import com.KissTech.crm.DTO.StudentManagementDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class StudentManagementMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    private final DueDayMapper dueDayMapper;
    private final AttendanceMapper attendanceMapper;

    public StudentManagementMapper(DueDayMapper dueDayMapper, AttendanceMapper attendanceMapper) {
        this.dueDayMapper = dueDayMapper;
        this.attendanceMapper = attendanceMapper;
    }

    // ✅ Create DTO → Model
    public StudentManagement dtoToModel(CreateStudentManagementDTO dto) {
        StudentManagement model = new StudentManagement();
        model.setFullName(dto.getFullName());
        model.setBatch(dto.getBatch());
        model.setPhoneNumber(dto.getPhoneNumber());
        model.setEmail(dto.getEmail());
        model.setCourse(dto.getCourse());
        model.setClassCount(dto.getClassCount());
        model.setShift(dto.getShift());
        model.setStartTime(dto.getStartTime());
        model.setEndTime(dto.getEndTime());
        model.setClassStartDate(dto.getClassStartDate());
        model.setClassEndDate(dto.getClassEndDate());
        model.setCreatedBy(dto.getUserId());
        model.setCreateAt(String.valueOf(LocalDateTime.now()));
        model.setStatus(dto.getStatus());
        model.setStudentID(dto.getStudentID());

        // Map DueDay list
        if (dto.getDueDay() != null) {
            model.setDueDay(dto.getDueDay().stream()
                    .map(dueDayMapper::dtoToModel)
                    .collect(Collectors.toList()));
        }

        // Map Attendance list
        if (dto.getAttendance() != null) {
            model.setAttendance(dto.getAttendance().stream()
                    .map(attendanceMapper::dtoToModel)
                    .collect(Collectors.toList()));
        }

        return model;
    }

    // ✅ Update DTO → Model
    public StudentManagement updateDtoToModel(UpdateStudentManagementDTO dto, StudentManagement existing) {
        if (dto.getFullName() != null) existing.setFullName(dto.getFullName());
        if (dto.getBatch() != null) existing.setBatch(dto.getBatch());
        if (dto.getPhoneNumber() != null) existing.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getCourse() != null) existing.setCourse(dto.getCourse());
        if (dto.getShift() != null) existing.setShift(dto.getShift());
        if (dto.getClassStartDate() != null) existing.setClassStartDate(dto.getClassStartDate());
        if (dto.getClassEndDate() != null) existing.setClassEndDate(dto.getClassEndDate());
        if (dto.getUserId() != null) existing.setUserId(dto.getUserId());
        if (dto.getStatus() != null) existing.setStatus(dto.getStatus());
        if (dto.getStudentID() != null) existing.setStudentID(dto.getStudentID());
        if (dto.getClassCount() != null) existing.setClassCount(dto.getClassCount());
        if (dto.getTotalFee() != null) existing.setTotalFee(dto.getTotalFee());
        if (dto.getInitialAmt() != null) existing.setInitialAmt(dto.getInitialAmt());
        if (dto.getBalanceFee() != null) existing.setBalanceFee(dto.getBalanceFee());
        if (dto.getEmiEnabled() != null) existing.setEmiEnabled(dto.getEmiEnabled());
        if (dto.getSplit() != null) existing.setSplit(dto.getSplit());
        if (dto.getStartTime() != null) existing.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) existing.setEndTime(dto.getEndTime());

        // Update DueDay list safely
        if (dto.getDueDay() != null) {
            if (existing.getDueDay() == null) {
                existing.setDueDay(new ArrayList<>());
            } else {
                existing.getDueDay().clear(); // keep same list instance
            }
            existing.getDueDay().addAll(dto.getDueDay().stream()
                    .map(dueDayMapper::UpdatedtoToModel)
                    .toList());
        }

// Update Attendance list safely
        if (dto.getAttendance() != null) {
            if (existing.getAttendance() == null) {
                existing.setAttendance(new ArrayList<>());
            } else {
                existing.getAttendance().clear(); // keep same list instance
            }
            existing.getAttendance().addAll(dto.getAttendance().stream()
                    .map(attendanceMapper::UpdatedtoToModel)
                    .toList());
        }


        return existing;
    }

    // ✅ Model → DTO
    public StudentManagementDTO modelToDTO(StudentManagement model) {
        StudentManagementDTO dto = new StudentManagementDTO();
        dto.setId(model.getId());
        dto.setFullName(model.getFullName());
        dto.setBatch(model.getBatch());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setEmail(model.getEmail());
        dto.setCourse(model.getCourse());
        dto.setClassCount(model.getClassCount());
        dto.setShift(model.getShift());
        dto.setStartTime(model.getStartTime());
        dto.setEndTime(model.getEndTime());
        dto.setClassStartDate(model.getClassStartDate());
        dto.setClassEndDate(model.getClassEndDate());
        dto.setCreatedBy(model.getCreatedBy());
        dto.setStatus(model.getStatus());
        dto.setStudentID(model.getStudentID());
        dto.setTotalFee(model.getTotalFee());
        dto.setInitialAmt(model.getInitialAmt());
        dto.setBalanceFee(model.getBalanceFee());
        dto.setEmiEnabled(model.getEmiEnabled());
        dto.setSplit(model.getSplit());

        if (model.getCreateAt() != null) {
            dto.setCreateAt(model.getCreateAt());
        }

        // Map DueDay list
        if (model.getDueDay() != null) {
            dto.setDueDay(model.getDueDay().stream()
                    .map(dueDayMapper::modelToDTO)
                    .collect(Collectors.toList()));
        }

        // Map Attendance list
        if (model.getAttendance() != null) {
            dto.setAttendance(model.getAttendance().stream()
                    .map(attendanceMapper::modelToDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public StudentNotificationDueDayDTO NotifyDueDay(StudentManagement model) {

        StudentNotificationDueDayDTO dto = new StudentNotificationDueDayDTO();
        dto.setFullName(model.getFullName());


        // Map DueDay list
        if (model.getDueDay() != null) {
            dto.setDueDays(model.getDueDay().stream()
                    .map(dueDayMapper::modelToDTO)
                    .collect(Collectors.toList()));
        }


return dto;

    }

    public StudentNotificationAttendanceDTO NotifyAttendance(StudentManagement model) {


        StudentNotificationAttendanceDTO dto = new StudentNotificationAttendanceDTO();
        dto.setFullName(model.getFullName());


        // Map DueDay list
        if (model.getAttendance() != null) {
            dto.setAttendance(model.getAttendance().stream()
                    .map(attendanceMapper::modelToDTO)
                    .collect(Collectors.toList()));
        }


        return dto;

    }

    public StudentDashBoardDTO DashBoard(StudentManagement model) {
        StudentDashBoardDTO dto = new StudentDashBoardDTO();
        dto.setFullName(model.getFullName());
        dto.setCourse(model.getCourse());





        return dto;
    }
}
