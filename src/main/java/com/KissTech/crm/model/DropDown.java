package com.KissTech.crm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "dropdown")
public class DropDown extends AbstractEntity {

    @Column(name = "dropdown_type", length = 100, nullable = false)
    private String dropdownType;

    @ElementCollection
    @CollectionTable(
            name = "dropdown_values", // avoid reserved keyword
            joinColumns = @JoinColumn(name = "dropdown_id")
    )
    @Column(name = "value", length = 255)
    private List<String> values;

}
