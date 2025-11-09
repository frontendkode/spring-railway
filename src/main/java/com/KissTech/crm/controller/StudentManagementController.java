package com.KissTech.crm.controller;

import com.KissTech.crm.DTO.StudentManagementDTO;
import com.KissTech.crm.apiService.StudentManagementApiService;
import com.KissTech.crm.createDTO.*;
import com.KissTech.crm.createDTO.CreateStudentManagementDTO;
import com.KissTech.crm.updateDTO.UpdateStudentManagementDTO;
import com.KissTech.crm.utils.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="StudentManagement Controller ApiS" , description="Manged  StudentManagement Api")
@RestController
@RequestMapping("kiss-tech/api-student")
public class StudentManagementController {
    private final StudentManagementApiService service;

    public StudentManagementController(StudentManagementApiService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<StudentManagementDTO>> create(@RequestBody CreateStudentManagementDTO createStudentManagementDTO) {
        try {
            StudentManagementDTO appointmentDTO = service.create(createStudentManagementDTO);
            RestResponse<StudentManagementDTO> response = new RestResponse<>(
                    "Student created successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to create Student: " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }







    @PostMapping("/getById")
    public ResponseEntity<RestResponse<StudentManagementDTO>> getById(@RequestParam UUID id) {
        try {
            StudentManagementDTO appointmentDTO = service.getById(id);
            RestResponse<StudentManagementDTO> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }


    @PostMapping("/update")
    public ResponseEntity<RestResponse<StudentManagementDTO>> updateStudent(@RequestBody UpdateStudentManagementDTO updateLeadManagementDTO) {
        try {
            StudentManagementDTO appointmentDTO = service.updateStudent(updateLeadManagementDTO);
            RestResponse<StudentManagementDTO> response = new RestResponse<>(
                    "Student updated successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to update Student: " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }





    @PostMapping("/getAllStudents")
    public ResponseEntity<RestResponse<List<StudentManagementDTO>>> getAllStudents() {
        try {
            List<StudentManagementDTO> appointmentDTO = service.getAllStudents();
            RestResponse<List<StudentManagementDTO>> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }



    @PostMapping("/updateEnrolment")
    public ResponseEntity<RestResponse<StudentManagementDTO>> updateEnrolment(@RequestParam UUID id,@RequestParam String status) {
        try {
            StudentManagementDTO appointmentDTO = service.updateEnrolment(id,status);
            RestResponse<StudentManagementDTO> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }



    @PostMapping("/updateDueDay")
    public ResponseEntity<RestResponse<StudentManagementDTO>> updateDueDay(@RequestParam UUID id,@RequestBody CreateDueDayDTO dueDay) {
        try {
            StudentManagementDTO appointmentDTO = service.updateDueDay(id,dueDay);
            RestResponse<StudentManagementDTO> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }




    @PostMapping("/updateAttendance")
    public ResponseEntity<RestResponse<StudentManagementDTO>> updateAttendance(@RequestParam UUID id,@RequestBody CreateAttendanceDTO dueDay) {
        try {
            StudentManagementDTO appointmentDTO = service.updateAttendance(id,dueDay);
            RestResponse<StudentManagementDTO> response = new RestResponse<>(
                    "successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }




    @PostMapping("/updatePaymentDetails")
    public ResponseEntity<RestResponse<String>> updatePaymentDetails(
            @RequestParam UUID dueDayId,
            @RequestParam String status,@RequestParam UUID studentId) {
        try {
            String message = service.updatePaymentDetails(dueDayId, status,studentId);

            RestResponse<String> response = new RestResponse<>(
                    message,
                    null,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>(
                            "Failed: " + e.getMessage(),
                            null,
                            false,
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }



    @PostMapping("/updateAttendanceDetails")
    public ResponseEntity<RestResponse<String>> updateAttendanceDetails(
            @RequestParam UUID attendanceId,
            @RequestParam String status) {
        try {
            String message = service.updateAttendanceDetails(attendanceId, status);

            RestResponse<String> response = new RestResponse<>(
                    message,
                    null,
                    true,
                    HttpStatus.OK.value()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>(
                            "Failed: " + e.getMessage(),
                            null,
                            false,
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }



}
