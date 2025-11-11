package com.KissTech.crm.DTO;

import lombok.Data;

@Data
public class FeeCollectionDTO {

   private String collectionRate;
   private String overduePayments;
   private String upcomingCollected;
   private String upiPayment;
   private String cashPayment;
   private String cardPayment;
   private String collectionAmount;
   private String totalDueAmount;


}
