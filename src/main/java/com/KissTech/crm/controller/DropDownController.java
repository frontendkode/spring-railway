package com.KissTech.crm.controller;

import com.KissTech.crm.DTO.DropDownDTO;
import com.KissTech.crm.DTO.LeadManagementDTO;
import com.KissTech.crm.apiService.DropDownApiService;
import com.KissTech.crm.createDTO.CreateDropDownDTO;
import com.KissTech.crm.createDTO.CreateLeadManagementDTO;
import com.KissTech.crm.updateDTO.UpdateDropDownDTO;
import com.KissTech.crm.utils.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="dropdown  Controller ApiS" , description="Manged  dropdown Api")
@RestController
@RequestMapping("kiss-tech/api-dropdown")
public class DropDownController {

private final DropDownApiService dropDownApiService;

    public DropDownController(DropDownApiService dropDownApiService) {
        this.dropDownApiService = dropDownApiService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestResponse<DropDownDTO>> create(@RequestBody CreateDropDownDTO createDropDownDTO) {
        try {
            DropDownDTO dropDownDTO = dropDownApiService.create(createDropDownDTO);
            RestResponse<DropDownDTO> response = new RestResponse<>(
                    "dropdown created successfully",
                    dropDownDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to create : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }

    @PostMapping("/getById")
    public ResponseEntity<RestResponse<DropDownDTO>> getById(@RequestParam UUID id) {
        try {
            DropDownDTO appointmentDTO = dropDownApiService.getById(id);
            RestResponse<DropDownDTO> response = new RestResponse<>(
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



    @PostMapping("/getAll")
    public ResponseEntity<RestResponse<List<DropDownDTO>>> getAll() {
        try {
            List<DropDownDTO> appointmentDTO = dropDownApiService.getAll();
            RestResponse<List<DropDownDTO>> response = new RestResponse<>(
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
    public ResponseEntity<RestResponse<DropDownDTO>> update(@RequestBody UpdateDropDownDTO updateDropDownDTO) {
        try {
            DropDownDTO dropDownDTO = dropDownApiService.update(updateDropDownDTO);
            RestResponse<DropDownDTO> response = new RestResponse<>(
                    "dropdown updated successfully",
                    dropDownDTO,
                    true,
                    HttpStatus.CREATED.value()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RestResponse<>("Failed to update : " + e.getMessage(),
                            null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }

}
