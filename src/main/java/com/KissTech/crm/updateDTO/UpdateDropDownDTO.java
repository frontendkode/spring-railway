package com.KissTech.crm.updateDTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateDropDownDTO {
    private UUID id;
    private String dropdownType;
    private List<String> values;
}
