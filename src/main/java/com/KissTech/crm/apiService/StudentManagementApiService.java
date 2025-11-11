package com.KissTech.crm.apiService;

import com.KissTech.crm.DTO.StudentManagementDTO;
import com.KissTech.crm.DTO.StudentManagementDTO;
import com.KissTech.crm.createDTO.CreateAttendanceDTO;
import com.KissTech.crm.createDTO.CreateDueDayDTO;
import com.KissTech.crm.createDTO.CreateStudentManagementDTO;
import com.KissTech.crm.createDTO.CreateStudentManagementDTO;
import com.KissTech.crm.mapper.AttendanceMapper;
import com.KissTech.crm.mapper.DueDayMapper;
import com.KissTech.crm.mapper.StudentManagementMapper;
import com.KissTech.crm.model.Attendance;
import com.KissTech.crm.model.DueDay;
import com.KissTech.crm.model.StudentManagement;
import com.KissTech.crm.service.StudentManagementService;
import com.KissTech.crm.updateDTO.UpdateStudentManagementDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentManagementApiService {

    private final StudentManagementService service;
    private final StudentManagementMapper studentManagementMapper;
    private final DueDayMapper dueDayMapper;
    private final AttendanceMapper attendanceMapper;

    public StudentManagementApiService(StudentManagementService service, StudentManagementMapper studentManagementMapper, DueDayMapper dueDayMapper, AttendanceMapper attendanceMapper) {
        this.service = service;
        this.studentManagementMapper = studentManagementMapper;
        this.dueDayMapper = dueDayMapper;
        this.attendanceMapper = attendanceMapper;
    }



    public StudentManagementDTO create(CreateStudentManagementDTO createStudentManagementDTO) {
        return studentManagementMapper.modelToDTO(service.create(studentManagementMapper.dtoToModel(createStudentManagementDTO)));
    }

    public StudentManagementDTO updateStudent(UpdateStudentManagementDTO dto) {
        StudentManagement existingLead = service.getById(dto.getId());

        studentManagementMapper.updateDtoToModel(dto, existingLead);
        StudentManagement updatedLead = service.create(existingLead);
        return studentManagementMapper.modelToDTO(updatedLead);
    }

//    public void updateDtoToModel(UpdateStudentManagementDTO dto, StudentManagement entity) {
//        entity.setFullName(dto.getFullName());
//        entity.setPhoneNumber(dto.getPhoneNumber());
//        entity.setEmail(dto.getEmail());
//        // ... other simple fields
//
//        // DueDay - safe update
//        if (dto.getDueDay() != null) {
//            if (entity.getDueDay() == null) {
//                entity.setDueDay(new ArrayList<>());
//            } else {
//                entity.getDueDay().clear();
//            }
//            entity.getDueDay().addAll(dto.getDueDay().stream()
//                    .map(dueDayMapper::dtoToModel)
//                    .toList());
//        }
//
//        // Attendance - safe update
//        if (dto.getAttendance() != null) {
//            if (entity.getAttendance() == null) {
//                entity.setAttendance(new ArrayList<>());
//            } else {
//                entity.getAttendance().clear();
//            }
//            entity.getAttendance().addAll(dto.getAttendance().stream()
//                    .map(attendanceMapper::dtoToModel)
//                    .toList());
//        }
//    }




    public StudentManagementDTO getById(UUID id) {
        return studentManagementMapper.modelToDTO(service.getById(id));
    }

    public List<StudentManagementDTO> getAllStudents() {

        List<StudentManagement>  data=service.getAllStudents();
        return data.stream().map(studentManagementMapper::modelToDTO).toList();
    }

    public StudentManagementDTO updateEnrolment(UUID id, String status) {
        StudentManagement data=service.updateEnrolment(id,status);
        return studentManagementMapper.modelToDTO(data);
    }

    public StudentManagementDTO updateDueDay(UUID id, CreateDueDayDTO dueDayDto) {
        // 1️⃣ Get existing student
        StudentManagement student = service.getById(id);

        // 2️⃣ Convert DTO → Model
        DueDay newDueDay = dueDayMapper.dtoToModel(dueDayDto);

        // 3️⃣ Add to existing DueDay list
        if (student.getDueDay() != null) {
            student.getDueDay().add(newDueDay);
        } else {
            student.setDueDay(List.of(newDueDay)); // if list is null, create new list
        }

        // 4️⃣ Save updated student
        StudentManagement updatedStudent = service.create(student);

        // 5️⃣ Convert Model → DTO
        return studentManagementMapper.modelToDTO(updatedStudent);
    }

    public StudentManagementDTO updateAttendance(UUID id, CreateAttendanceDTO dueDay) {

        StudentManagement student = service.getById(id);

        // 2️⃣ Convert DTO → Model
        Attendance newDueDay = attendanceMapper.dtoToModel(dueDay);

        // 3️⃣ Add to existing DueDay list
        if (student.getAttendance() != null) {
            student.getAttendance().add(newDueDay);
        } else {
            student.setAttendance(List.of(newDueDay)); // if list is null, create new list
        }

        // 4️⃣ Save updated student
        StudentManagement updatedStudent = service.create(student);

        // 5️⃣ Convert Model → DTO
        return studentManagementMapper.modelToDTO(updatedStudent);
    }

    public String updatePaymentDetails(UUID dueDayId, String status,UUID id,String paymentType,String date) {
       return service.updatePaymentDetails(dueDayId,status,id,paymentType,date);
    }

    public String updateAttendanceDetails(UUID attendanceId, String status) {
   return service.updateAttendanceDetails(attendanceId,status);
    }
}
