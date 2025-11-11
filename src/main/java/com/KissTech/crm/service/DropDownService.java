package com.KissTech.crm.service;

import com.KissTech.crm.model.DropDown;
import com.KissTech.crm.repository.DropDownRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DropDownService {
    private final DropDownRepository dropDownRepository;

    public DropDownService(DropDownRepository dropDownRepository) {
        this.dropDownRepository = dropDownRepository;
    }

    public DropDown save(DropDown dropDown) {
       return dropDownRepository.save(dropDown);
    }

    public DropDown getById(UUID id) {
        return dropDownRepository.findById(id).orElseThrow();
    }

    public List<DropDown> getAll() {
        return dropDownRepository.findAll();
    }
}
