package com.KissTech.crm.DTO;


import lombok.Data;

import java.util.UUID;

@Data
public class DueDayDTO {

    private String dueNo;
    private String dueAmt;
    private String dueDate;
    private String status;
    private String createAt;
    private UUID id;
}
