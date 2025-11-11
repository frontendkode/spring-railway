package com.KissTech.crm.controller;

import com.KissTech.crm.DTO.DashBoardDTO;
import com.KissTech.crm.DTO.DashBoardDataDTO;
import com.KissTech.crm.DTO.DropDownDTO;
import com.KissTech.crm.apiService.DashBoardApiService;
import com.KissTech.crm.utils.RestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="dashboard  Controller ApiS" , description="Manged  dashboard Api")
@RestController
@RequestMapping("kiss-tech/api-das-board")
public class DashBoardController {
private  final DashBoardApiService apiservice;

    public DashBoardController(DashBoardApiService apiservice) {
        this.apiservice = apiservice;
    }

    @PostMapping("/getDailyNotification")
    public ResponseEntity<RestResponse<DashBoardDTO>> getAll() {
        try {
            DashBoardDTO appointmentDTO = apiservice.getDailyNotification();
            RestResponse<DashBoardDTO> response = new RestResponse<>(
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


    @PostMapping("/getDashBoardDatas")
    public ResponseEntity<RestResponse<DashBoardDataDTO>> getDashBoardDatas(@RequestParam(required = false) String fromDate,@RequestParam(required = false) String toDate) {
        try {
            DashBoardDataDTO sa = apiservice.getDashBoardDatas();
            RestResponse<DashBoardDataDTO> response = new RestResponse<>(
                    "successfully",
                    sa,
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
