package com.KissTech.crm.mapper;
import com.KissTech.crm.DTO.AttendanceDTO;
import com.KissTech.crm.createDTO.CreateAttendanceDTO;
import com.KissTech.crm.model.Attendance;
import com.KissTech.crm.updateDTO.UpdateAttendanceDTO;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AttendanceMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public Attendance dtoToModel(CreateAttendanceDTO dto) {
        Attendance model = new Attendance();
        model.setDate(dto.getDate());
        model.setDay(dto.getDay());
        model.setAttend(dto.getAttend());
        model.setCreateAt(String.valueOf(LocalDateTime.now()));
        return model;
    }

    public AttendanceDTO modelToDTO(Attendance model) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setDate(model.getDate());
        dto.setDay(model.getDay());
        dto.setAttend(model.getAttend());
        dto.setId(model.getId());
        if (model.getCreateAt() != null) {
            dto.setCreateAt(model.getCreateAt());        }
        return dto;
    }



    public Attendance UpdatedtoToModel(UpdateAttendanceDTO dto) {
        Attendance model = new Attendance();
        model.setDate(dto.getDate());
        model.setDay(dto.getDay());
        model.setAttend(dto.getAttend());
        model.setCreateAt(String.valueOf(LocalDateTime.now()));
        model.setId(dto.getId());
        return model;
    }

}
