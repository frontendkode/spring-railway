package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.UUID;
@Data
public class ActivityLogDTO {

    private String type;

    private String description;

    private String logResult;

    private String createdAt;
    private UUID id;

}
