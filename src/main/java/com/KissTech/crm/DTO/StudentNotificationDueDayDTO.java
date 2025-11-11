package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.List;

@Data
public class StudentNotificationDueDayDTO {
    private String fullName;
    private List<DueDayDTO> dueDays;
}
