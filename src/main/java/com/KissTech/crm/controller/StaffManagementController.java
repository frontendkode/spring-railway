package com.KissTech.crm.controller;


import com.KissTech.crm.DTO.LeadManagementDTO;
import com.KissTech.crm.DTO.StaffManagementDTO;
import com.KissTech.crm.DTO.StaffManagementWithTaskDTO;
import com.KissTech.crm.DTO.StaffNameDTO;
import com.KissTech.crm.apiService.StaffManagementApiService;
import com.KissTech.crm.createDTO.CreateStaffManagementDTO;
import com.KissTech.crm.updateDTO.UpdateLeadManagementDTO;
import com.KissTech.crm.updateDTO.UpdateStaffManagementDTO;
import com.KissTech.crm.utils.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="StaffManagement Controller ApiS" , description="Manged  StaffManagement Api")
@RestController
@RequestMapping("kiss-tech/api-staff")
public class StaffManagementController {

private final StaffManagementApiService staffManagementApiService;

    public StaffManagementController(StaffManagementApiService staffManagementApiService) {
        this.staffManagementApiService = staffManagementApiService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<StaffManagementDTO>> create(@RequestBody CreateStaffManagementDTO createStaffManagementDTO) {
        try {
            StaffManagementDTO appointmentDTO = staffManagementApiService.create(createStaffManagementDTO);
            RestResponse<StaffManagementDTO> response = new RestResponse<>(
                    "Staff created successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to create staff: " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }







    @PostMapping("/getById")
    public ResponseEntity<RestResponse<StaffManagementDTO>> getById(@RequestParam UUID id) {
        try {
            StaffManagementDTO appointmentDTO = staffManagementApiService.getById(id);
            RestResponse<StaffManagementDTO> response = new RestResponse<>(
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
    public ResponseEntity<RestResponse<StaffManagementDTO>> updateStaff(@RequestBody UpdateStaffManagementDTO updateLeadManagementDTO) {
        try {
            StaffManagementDTO appointmentDTO = staffManagementApiService.updateStaff(updateLeadManagementDTO);
            RestResponse<StaffManagementDTO> response = new RestResponse<>(
                    "staff updated successfully",
                    appointmentDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to update staff: " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }





    @PostMapping("/getAllStaffs")
    public ResponseEntity<RestResponse<List<StaffManagementDTO>>> getAllStaffs() {
        try {
            List<StaffManagementDTO> appointmentDTO = staffManagementApiService.getAllStaffs();
            RestResponse<List<StaffManagementDTO>> response = new RestResponse<>(
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

    @PostMapping("/getAllStaffsWithTask")
    public ResponseEntity<RestResponse<List<StaffManagementWithTaskDTO>>> getAllStaffsWithTask() {
        try {
            List<StaffManagementWithTaskDTO> appointmentDTO = staffManagementApiService.getAllStaffsWithTask();
            RestResponse<List<StaffManagementWithTaskDTO>> response = new RestResponse<>(
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



    @PostMapping("/getAllStaffNameAndId")
    public ResponseEntity<RestResponse<List<StaffNameDTO>>> getAllStaffNameAndId() {
        try {
            List<StaffNameDTO> appointmentDTO = staffManagementApiService.getAllStaffNameAndId();
            RestResponse<List<StaffNameDTO>> response = new RestResponse<>(
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









}
