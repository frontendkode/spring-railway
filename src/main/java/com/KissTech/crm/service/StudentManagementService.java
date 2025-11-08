package com.KissTech.crm.service;

import com.KissTech.crm.model.Attendance;
import com.KissTech.crm.model.DueDay;
import com.KissTech.crm.model.StudentManagement;
import com.KissTech.crm.repository.AttendanceRepository;
import com.KissTech.crm.repository.DueDayRepository;
import com.KissTech.crm.repository.StudentManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class StudentManagementService {


    private final StudentManagementRepository studentManagementRepository;
    private final DueDayRepository dueDayRepository;
    private final AttendanceRepository attendanceRepository;

    public StudentManagementService(StudentManagementRepository studentManagementRepository, DueDayRepository dueDayRepository, AttendanceRepository attendanceRepository) {
        this.studentManagementRepository = studentManagementRepository;
        this.dueDayRepository = dueDayRepository;
        this.attendanceRepository = attendanceRepository;
    }


    public StudentManagement create(StudentManagement studentManagement) {
       return studentManagementRepository.save(studentManagement);
    }

    public StudentManagement getById(UUID id) {
        return studentManagementRepository.findById(id).orElseThrow();
    }

    public List<StudentManagement> getAllStudents() {
        return studentManagementRepository.findAll();
    }

    public StudentManagement updateEnrolment(UUID id, String status) {
        StudentManagement data = studentManagementRepository.findById(id).orElseThrow();
        data.setStatus(status);
        return studentManagementRepository.save(data);
    }


    public String updatePaymentDetails(UUID dueDayId, String status, UUID studentId) {
        // Fetch due day entry
        DueDay data = dueDayRepository.findById(dueDayId)
                .orElseThrow(() -> new RuntimeException("DueDay not found for ID: " + dueDayId));

        // Fetch student record
        StudentManagement student = studentManagementRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + studentId));

        // Convert amounts to double for calculation
        double dueAmount = Double.parseDouble(data.getDueAmt());
        double balance = Double.parseDouble(student.getBalanceFee());

        // Update student's balance
        double updatedBalance = balance - dueAmount;
        student.setBalanceFee(String.valueOf(updatedBalance));

        // Update payment status
        data.setStatus(status);

        // Save both updates
        dueDayRepository.save(data);
        studentManagementRepository.save(student);

        return "Payment status and student balance updated successfully";
    }


    public String updateAttendanceDetails(UUID attendanceId, String status) {


        Attendance data = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found for ID: " + attendanceId));

        data.setAttend(status);
        attendanceRepository.save(data); // ✅ You must save to persist the update

        return "Attendance status updated successfully";
    }
}
