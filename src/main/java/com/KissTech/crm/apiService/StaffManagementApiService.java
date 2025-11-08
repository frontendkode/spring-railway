package com.KissTech.crm.apiService;

import com.KissTech.crm.DTO.LeadManagementDTO;
import com.KissTech.crm.DTO.StaffManagementDTO;
import com.KissTech.crm.DTO.StaffManagementWithTaskDTO;
import com.KissTech.crm.DTO.StaffNameDTO;
import com.KissTech.crm.createDTO.CreateStaffManagementDTO;
import com.KissTech.crm.mapper.StaffManagementMapper;
import com.KissTech.crm.model.LeadManagement;
import com.KissTech.crm.model.StaffManagement;
import com.KissTech.crm.service.StaffManagementService;
import com.KissTech.crm.updateDTO.UpdateLeadManagementDTO;
import com.KissTech.crm.updateDTO.UpdateStaffManagementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffManagementApiService {

    private final StaffManagementMapper staffManagementMapper;
    private final StaffManagementService service;

    public StaffManagementApiService(StaffManagementMapper staffManagementMapper, StaffManagementService service) {
        this.staffManagementMapper = staffManagementMapper;
        this.service = service;
    }

    public StaffManagementDTO create(CreateStaffManagementDTO createStaffManagementDTO) {
       return staffManagementMapper.modelToDTO(service.create(staffManagementMapper.dtoToModel(createStaffManagementDTO)));
    }

    public StaffManagementDTO updateStaff(UpdateStaffManagementDTO dto) {
        StaffManagement existingLead = service.getById(dto.getId());

        staffManagementMapper.updateDtoToModel(dto, existingLead);
        StaffManagement updatedLead = service.create(existingLead);
        return staffManagementMapper.modelToDTO(updatedLead);
    }


    public StaffManagementDTO getById(UUID id) {
        return staffManagementMapper.modelToDTO(service.getById(id));
    }

    public List<StaffManagementDTO> getAllStaffs() {

        List<StaffManagement>  data=service.getAllStaffs();
        return data.stream().map(staffManagementMapper::modelToDTO).toList();
    }

    public List<StaffManagementWithTaskDTO> getAllStaffsWithTask() {
        List<StaffManagement>  data=service.getAllStaffs();
        return data.stream().map(staffManagementMapper::modelToDTOWithTask).toList();

    }

    public List<StaffNameDTO> getAllStaffNameAndId() {
        List<StaffManagement>  data=service.getAllStaffs();
        return data.stream().map(staffManagementMapper::modelToStaffName).toList();

    }
}
