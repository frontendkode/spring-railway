package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.List;
@Data
public class DashBoardDTO {

   private  List<TaskNotificationDTO> task;
   private List<LeadNotificationDTO> lead;
   private List<StudentNotificationAttendanceDTO> studentAttendance;
   private List<StudentNotificationDueDayDTO> studentDueDays;

}
