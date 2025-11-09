package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceDTO {


    private String date;
    private String day;
    private String attend;
    private String createAt;
    private UUID id;
}
