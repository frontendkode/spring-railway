package com.KissTech.crm.mapper;

import com.KissTech.crm.DTO.ActivityLogDTO;
import com.KissTech.crm.DTO.ReminderLogDTO;
import com.KissTech.crm.model.ActivityLog;
import com.KissTech.crm.model.LeadManagement;
import com.KissTech.crm.model.ReminderLog;
import com.KissTech.crm.DTO.LeadManagementDTO;
import com.KissTech.crm.createDTO.CreateActivityLogDTO;
import com.KissTech.crm.createDTO.CreateLeadManagementDTO;
import com.KissTech.crm.createDTO.CreateReminderLogDTO;
import com.KissTech.crm.updateDTO.UpdateActivityLogDTO;
import com.KissTech.crm.updateDTO.UpdateCreateReminderLogDTO;
import com.KissTech.crm.updateDTO.UpdateLeadManagementDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeadManagementMapper {

    // ----------------------------
    // CREATE DTO → ENTITY
    // ----------------------------
    public LeadManagement dtoToModel(CreateLeadManagementDTO dto) {
        LeadManagement lead = new LeadManagement();

        lead.setFullName(dto.getFullName());
        lead.setPhoneNumber(dto.getPhoneNumber());
        lead.setEmail(dto.getEmail());
        lead.setCourseInterest(dto.getCourseInterest());
        lead.setSource(dto.getSource());
        lead.setCounselor(dto.getCounselor());
        lead.setNotes(dto.getNotes());
        lead.setCreatedBy(dto.getCreatedBy());
        lead.setStage(dto.getStage());

        // Map activity logs
        if (dto.getActivityLog() != null) {
            List<ActivityLog> logs = dto.getActivityLog().stream()
                    .map(this::activityLogDtoToEntity)
                    .collect(Collectors.toList());
            lead.setActivityLogs(logs);
        }

        // Map reminder logs
        if (dto.getReminderLog() != null) {
            List<ReminderLog> reminders = dto.getReminderLog().stream()
                    .map(this::reminderLogDtoToEntity)
                    .collect(Collectors.toList());
            lead.setReminderLogs(reminders);
        }

        return lead;
    }

    private ActivityLog activityLogDtoToEntity(CreateActivityLogDTO dto) {
        ActivityLog log = new ActivityLog();
        log.setType(dto.getType());
        log.setDescription(dto.getDescription());
        log.setLogResult(dto.getLogResult());
        log.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return log;
    }


    public ActivityLog activityLogDtoToEntity1(CreateActivityLogDTO dto) {
        ActivityLog log = new ActivityLog();
        log.setType(dto.getType());
        log.setDescription(dto.getDescription());
        log.setLogResult(dto.getLogResult());
        log.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return log;
    }

    private ReminderLog reminderLogDtoToEntity(CreateReminderLogDTO dto) {
        ReminderLog reminder = new ReminderLog();
        reminder.setType(dto.getType());
        reminder.setDescription(dto.getDescription());
        reminder.setReminderDate(dto.getReminderDate());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return reminder;
    }
    public ReminderLog reminderLogDtoToEntity1(CreateReminderLogDTO dto) {
        ReminderLog reminder = new ReminderLog();
        reminder.setType(dto.getType());
        reminder.setDescription(dto.getDescription());
        reminder.setReminderDate(dto.getReminderDate());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return reminder;
    }

    // ----------------------------
    // ENTITY → DTO
    // ----------------------------
    public LeadManagementDTO modelToDto(LeadManagement lead) {
        LeadManagementDTO dto = new LeadManagementDTO();

        dto.setFullName(lead.getFullName());
        dto.setPhoneNumber(lead.getPhoneNumber());
        dto.setEmail(lead.getEmail());
        dto.setCourseInterest(lead.getCourseInterest());
        dto.setSource(lead.getSource());
        dto.setCounselor(lead.getCounselor());
        dto.setNotes(lead.getNotes());
        dto.setCreatedBy(lead.getCreatedBy());
        dto.setCreatedAt(lead.getCreatedAt());
        dto.setStage(lead.getStage());
        dto.setId(lead.getId());

        // Map activity logs
        if (lead.getActivityLogs() != null) {
            dto.setActivityLog(
                    lead.getActivityLogs().stream()
                            .sorted(Comparator.comparing(ActivityLog::getCreatedAt)) // ascending

                            .map(this::activityLogEntityToDto)
                            .collect(Collectors.toList())
            );
        }

        // Map reminder logs
        if (lead.getReminderLogs() != null) {
            dto.setReminderLog(
                    lead.getReminderLogs().stream()
                            .sorted(Comparator.comparing(ReminderLog::getCreatedAt)) // ascending

                            .map(this::reminderLogEntityToDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private ActivityLogDTO activityLogEntityToDto(ActivityLog log) {
        ActivityLogDTO dto = new ActivityLogDTO();
        dto.setType(log.getType());
        dto.setDescription(log.getDescription());
        dto.setLogResult(log.getLogResult());
        dto.setId(log.getId());
        dto.setCreatedAt(log.getCreatedAt());
        return dto;
    }

    private ReminderLogDTO reminderLogEntityToDto(ReminderLog reminder) {
        ReminderLogDTO dto = new ReminderLogDTO();
        dto.setType(reminder.getType());
        dto.setDescription(reminder.getDescription());
        dto.setReminderDate(reminder.getReminderDate());
        dto.setReminderTime(reminder.getReminderTime());
        dto.setId(reminder.getId());
        dto.setCreatedAt(reminder.getCreatedAt());
        return dto;
    }

    // ----------------------------
    // UPDATE DTO → EXISTING ENTITY
    // ----------------------------
    public LeadManagement updateDtoToModel(UpdateLeadManagementDTO dto, LeadManagement lead) {
        lead.setFullName(dto.getFullName());
        lead.setPhoneNumber(dto.getPhoneNumber());
        lead.setEmail(dto.getEmail());
        lead.setCourseInterest(dto.getCourseInterest());
        lead.setSource(dto.getSource());
        lead.setCounselor(dto.getCounselor());
        lead.setNotes(dto.getNotes());
        lead.setStage(dto.getStage());

        // Update activity logs
        if (dto.getActivityLog() != null) {
            List<ActivityLog> updatedLogs = dto.getActivityLog().stream()
                    .map(this::updateActivityLogDtoToEntity)
                    .collect(Collectors.toList());
            lead.getActivityLogs().clear();
            lead.getActivityLogs().addAll(updatedLogs);
        }

        // Update reminder logs
        if (dto.getReminderLog() != null) {
            List<ReminderLog> updatedReminders = dto.getReminderLog().stream()
                    .map(this::updateReminderLogDtoToEntity)
                    .collect(Collectors.toList());
            lead.getReminderLogs().clear();
            lead.getReminderLogs().addAll(updatedReminders);
        }

        return lead;
    }

    private ActivityLog updateActivityLogDtoToEntity(UpdateActivityLogDTO dto) {
        ActivityLog log = new ActivityLog();
        log.setId(dto.getId()); // make sure DTO has ID for update
        log.setType(dto.getType());
        log.setDescription(dto.getDescription());
        log.setLogResult(dto.getLogResult());
        log.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : String.valueOf(LocalDateTime.now()));
        return log;
    }

    private ReminderLog updateReminderLogDtoToEntity(UpdateCreateReminderLogDTO dto) {
        ReminderLog reminder = new ReminderLog();
        reminder.setId(dto.getId()); // make sure DTO has ID for update
        reminder.setType(dto.getType());
        reminder.setDescription(dto.getDescription());
        reminder.setReminderDate(dto.getReminderDate());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : String.valueOf(LocalDateTime.now()));
        return reminder;
    }


}
