package com.KissTech.crm.mapper;

import com.KissTech.crm.DTO.DueDayDTO;
import com.KissTech.crm.createDTO.CreateDueDayDTO;
import com.KissTech.crm.model.DueDay;
import com.KissTech.crm.updateDTO.UpdateDueDayDTO;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DueDayMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public DueDay dtoToModel(CreateDueDayDTO dto) {
        DueDay model = new DueDay();
        model.setDueNo(dto.getDueNo());
        model.setDueAmt(dto.getDueAmt());
        model.setDueDate(dto.getDueDate());
        model.setStatus(dto.getStatus());
        model.setCreateAt(String.valueOf(LocalDateTime.now()));
        model.setPaymentDate(dto.getPaymentDate());
        model.setPaymentType(dto.getPaymentType());
        return model;
    }

    public DueDayDTO modelToDTO(DueDay model) {
        DueDayDTO dto = new DueDayDTO();
        dto.setDueNo(model.getDueNo());
        dto.setDueAmt(model.getDueAmt());
        dto.setDueDate(model.getDueDate());
        dto.setStatus(model.getStatus());
        dto.setId(model.getId());

        dto.setPaymentDate(model.getPaymentDate());
        dto.setPaymentType(model.getPaymentType());
        if (model.getCreateAt() != null) {
     dto.setCreateAt(model.getCreateAt());
        }
        return dto;
    }


    public DueDay UpdatedtoToModel(UpdateDueDayDTO dto) {
        DueDay model = new DueDay();
        model.setDueNo(dto.getDueNo());
        model.setDueAmt(dto.getDueAmt());
        model.setDueDate(dto.getDueDate());
        model.setStatus(dto.getStatus());
        model.setCreateAt(String.valueOf(LocalDateTime.now()));
        model.setId(dto.getId());


        model.setPaymentDate(dto.getPaymentDate());
        model.setPaymentType(dto.getPaymentType());
        return model;
    }
}
