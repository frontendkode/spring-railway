package com.KissTech.crm.updateDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateDueDayDTO {

    private String dueNo;
    private String dueAmt;
    private String dueDate;
    private String status;
    private String createAt;
    private UUID id;

    private String paymentType;
    private String paymentDate;
}
