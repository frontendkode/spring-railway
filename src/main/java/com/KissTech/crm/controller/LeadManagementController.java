package com.KissTech.crm.controller;

import com.KissTech.crm.DTO.LeadDashBoard;
import com.KissTech.crm.DTO.LeadManagementDTO;
import com.KissTech.crm.DTO.StaffManagementDTO;
import com.KissTech.crm.apiService.LeadManagementApiService;
import com.KissTech.crm.createDTO.CreateActivityLogDTO;
import com.KissTech.crm.createDTO.CreateLeadManagementDTO;
import com.KissTech.crm.createDTO.CreateReminderLogDTO;
import com.KissTech.crm.createDTO.CreateStaffManagementDTO;
import com.KissTech.crm.updateDTO.UpdateLeadManagementDTO;
import com.KissTech.crm.utils.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="LeadManagement Controller ApiS" , description="Manged  LeadManagement Api")
@RestController
@RequestMapping("kiss-tech/api-lead")
public class LeadManagementController {

    private final LeadManagementApiService leadManagementApiService;

    public LeadManagementController(LeadManagementApiService leadManagementApiService) {
        this.leadManagementApiService = leadManagementApiService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<LeadManagementDTO>> create(@RequestBody CreateLeadManagementDTO createStaffManagementDTO) {
        try {
            LeadManagementDTO appointmentDTO = leadManagementApiService.create(createStaffManagementDTO);
            RestResponse<LeadManagementDTO> response = new RestResponse<>(
                    "Lead created successfully",
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
    public ResponseEntity<RestResponse<LeadManagementDTO>> getById(@RequestParam UUID id) {
        try {
            LeadManagementDTO appointmentDTO = leadManagementApiService.getById(id);
            RestResponse<LeadManagementDTO> response = new RestResponse<>(
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


    @PostMapping("/updateStage")
    public ResponseEntity<RestResponse<LeadManagementDTO>> updateStage(@RequestParam UUID id,@RequestParam String stage) {
        try {
            LeadManagementDTO appointmentDTO = leadManagementApiService.updateStage(id,stage);
            RestResponse<LeadManagementDTO> response = new RestResponse<>(
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


    @PostMapping("/updateActivity")
    public ResponseEntity<RestResponse<LeadManagementDTO>> updateActivity(@RequestParam UUID leadId,@RequestBody CreateActivityLogDTO createActivityLogDTO) {
        try {
            LeadManagementDTO appointmentDTO = leadManagementApiService.updateActivity(leadId,createActivityLogDTO);
            RestResponse<LeadManagementDTO> response = new RestResponse<>(
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


    @PostMapping("/updateReminder")
    public ResponseEntity<RestResponse<LeadManagementDTO>> updateReminder(@RequestParam UUID leadId,@RequestBody CreateReminderLogDTO createActivityLogDTO) {
        try {
            LeadManagementDTO appointmentDTO = leadManagementApiService.updateReminder(leadId,createActivityLogDTO);
            RestResponse<LeadManagementDTO> response = new RestResponse<>(
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
    public ResponseEntity<RestResponse<LeadManagementDTO>> update(@RequestBody UpdateLeadManagementDTO updateLeadManagementDTO) {
        try {
            LeadManagementDTO appointmentDTO = leadManagementApiService.updateLead(updateLeadManagementDTO);
            RestResponse<LeadManagementDTO> response = new RestResponse<>(
                    "Lead updated successfully",
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





    @PostMapping("/getAllLeads")
    public ResponseEntity<RestResponse<List<LeadManagementDTO>>> getAllLeads() {
        try {
            List<LeadManagementDTO> appointmentDTO = leadManagementApiService.getAllLeads();
            RestResponse<List<LeadManagementDTO>> response = new RestResponse<>(
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



    @PostMapping("/leadsCount")
    public ResponseEntity<RestResponse<LeadDashBoard>> leadsCount() {
        try {
            LeadDashBoard appointmentDTO = leadManagementApiService.leadsCount();
            RestResponse<LeadDashBoard> response = new RestResponse<>(
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
