package com.KissTech.crm.createDTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateDropDownDTO {
    private String dropdownType;
    private List<String> value;
}
