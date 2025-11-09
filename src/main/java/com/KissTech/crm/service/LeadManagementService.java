package com.KissTech.crm.service;

import com.KissTech.crm.DTO.LeadDashBoard;
import com.KissTech.crm.model.LeadManagement;
import com.KissTech.crm.repository.LeadManagementRepository;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeadManagementService {

    private final LeadManagementRepository leadManagementRepository;

    public LeadManagementService(LeadManagementRepository leadManagementRepository) {
        this.leadManagementRepository = leadManagementRepository;
    }

    public LeadManagement create(LeadManagement leadManagement) {
        return leadManagementRepository.save(leadManagement);
    }

    public LeadManagement getById(UUID id) {
        return leadManagementRepository.findById(id).orElseThrow();
    }

    public List<LeadManagement> getAllLeads() {
        return leadManagementRepository.findAll();
    }

    public LeadDashBoard leadCount() {
        LeadDashBoard dashBoard = new LeadDashBoard();

        // Total leads
        long totalLeads = leadManagementRepository.count();
        dashBoard.setTotalLeads(String.valueOf(totalLeads));

        // You can calculate other metrics similarly
        // For example, totalVisit, convert, futureInterest, conversionRate
        dashBoard.setTotalVisit("0");
        dashBoard.setConvert("0");
        dashBoard.setFutureInterest("0");
        dashBoard.setConversionRate("0");

        return dashBoard;
    }

    public LeadManagement updateStage(UUID id, String stage) {
        LeadManagement lead = leadManagementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));

        // Optional: Validate stage before updating
        if(stage == null || stage.isEmpty()) {
            throw new IllegalArgumentException("Stage cannot be null or empty");
        }

        lead.setStage(stage);
        return leadManagementRepository.save(lead);
    }

}
