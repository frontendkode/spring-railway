package com.KissTech.crm.createDTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateActivityLogDTO {

    private String type;

    private String description;

    private String logResult;

    private String createdAt;
}
