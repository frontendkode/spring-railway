package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DropDownDTO {
    private UUID id;
    private String dropdownType;
    private List<String> values;
}
