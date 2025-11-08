package com.KissTech.crm.service;

import com.KissTech.crm.model.StaffManagement;
import com.KissTech.crm.repository.StaffManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffManagementService {
    private final StaffManagementRepository staffManagementRepository;

    public StaffManagementService(StaffManagementRepository staffManagementRepository) {
        this.staffManagementRepository = staffManagementRepository;
    }

    public StaffManagement create(StaffManagement staffManagement) {
       return staffManagementRepository.save(staffManagement);
    }

    public StaffManagement getById(UUID id) {
        return staffManagementRepository.findById(id).orElseThrow();
    }

    public List<StaffManagement> getAllStaffs() {
        return staffManagementRepository.findAll();
    }
}
