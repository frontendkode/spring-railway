package com.KissTech.crm.apiService;

import com.KissTech.crm.DTO.LeadDashBoard;
import com.KissTech.crm.DTO.LeadManagementDTO;
import com.KissTech.crm.createDTO.CreateActivityLogDTO;
import com.KissTech.crm.createDTO.CreateLeadManagementDTO;
import com.KissTech.crm.createDTO.CreateReminderLogDTO;
import com.KissTech.crm.mapper.LeadManagementMapper;
import com.KissTech.crm.model.ActivityLog;
import com.KissTech.crm.model.LeadManagement;
import com.KissTech.crm.model.ReminderLog;
import com.KissTech.crm.service.LeadManagementService;
import com.KissTech.crm.updateDTO.UpdateLeadManagementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeadManagementApiService {
    private final LeadManagementMapper leadManagementMapper;
    private final LeadManagementService leadManagementService;

    public LeadManagementApiService(LeadManagementMapper leadManagementMapper, LeadManagementService leadManagementService) {
        this.leadManagementMapper = leadManagementMapper;
        this.leadManagementService = leadManagementService;
    }

    public LeadManagementDTO create(CreateLeadManagementDTO createStaffManagementDTO) {
        return  leadManagementMapper.modelToDto(leadManagementService.create(leadManagementMapper.dtoToModel(createStaffManagementDTO)));
    }

    public LeadManagementDTO getById(UUID id) {
        return leadManagementMapper.modelToDto(leadManagementService.getById(id));
    }

    public LeadManagementDTO updateLead(UpdateLeadManagementDTO dto) {
        LeadManagement existingLead = leadManagementService.getById(dto.getId());

        leadManagementMapper.updateDtoToModel(dto, existingLead);
        LeadManagement updatedLead = leadManagementService.create(existingLead);
        return leadManagementMapper.modelToDto(updatedLead);
    }

    public List<LeadManagementDTO> getAllLeads() {
        List<LeadManagement>  data=leadManagementService.getAllLeads();
        return data.stream().map(leadManagementMapper::modelToDto).toList();
    }

    public LeadDashBoard leadsCount() {
        return leadManagementService.leadCount();
    }

    public LeadManagementDTO updateStage(UUID id,String stage) {
        LeadManagement data= leadManagementService.updateStage(id,stage);
       return leadManagementMapper.modelToDto(data);
    }

    public LeadManagementDTO updateActivity(UUID leadId, CreateActivityLogDTO createActivityLogDTO) {

        LeadManagement existingLead = leadManagementService.getById(leadId);

        ActivityLog data = leadManagementMapper.activityLogDtoToEntity1(createActivityLogDTO);
        existingLead.getActivityLogs().add(data);
        LeadManagement updatedLead = leadManagementService.create(existingLead);
        return leadManagementMapper.modelToDto(updatedLead);

    }

    public LeadManagementDTO updateReminder(UUID leadId, CreateReminderLogDTO createActivityLogDTO) {

        LeadManagement existingLead = leadManagementService.getById(leadId);

        ReminderLog data = leadManagementMapper.reminderLogDtoToEntity1(createActivityLogDTO);
        existingLead.getReminderLogs().add(data);
        LeadManagement updatedLead = leadManagementService.create(existingLead);
        return leadManagementMapper.modelToDto(updatedLead);
    }
}
