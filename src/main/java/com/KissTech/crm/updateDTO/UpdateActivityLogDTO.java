package com.KissTech.crm.updateDTO;

import lombok.Data;

import java.util.UUID;
@Data
public class UpdateActivityLogDTO {


    private String type;

    private String description;

    private String logResult;

    private String createdAt;
    private UUID id;

}
