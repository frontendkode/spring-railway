package com.KissTech.crm.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DashBoardDataDTO {
   private List<LeadDashBoardDTO>  leadDashBoardDTOS;
   private List<StudentDashBoardDTO> studentDashBoardDTOS;
   private FeeCollectionDTO feeCollection;
}
