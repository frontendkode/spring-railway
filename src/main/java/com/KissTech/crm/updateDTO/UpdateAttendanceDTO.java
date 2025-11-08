package com.KissTech.crm.updateDTO;


import lombok.Data;

import java.util.UUID;

@Data
public class UpdateAttendanceDTO {


    private String date;
    private String day;
    private String attend;
    private String createAt;
    private UUID id;
}
