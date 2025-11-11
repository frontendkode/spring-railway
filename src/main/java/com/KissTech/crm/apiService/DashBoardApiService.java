package com.KissTech.crm.apiService;

import com.KissTech.crm.DTO.DashBoardDTO;
import com.KissTech.crm.DTO.DashBoardDataDTO;
import com.KissTech.crm.DTO.FeeCollectionDTO;
import com.KissTech.crm.mapper.LeadManagementMapper;
import com.KissTech.crm.mapper.StudentManagementMapper;
import com.KissTech.crm.mapper.TaskManagementMapper;
import com.KissTech.crm.model.DueDay;
import com.KissTech.crm.model.LeadManagement;
import com.KissTech.crm.model.StudentManagement;
import com.KissTech.crm.model.TaskManagement;
import com.KissTech.crm.service.LeadManagementService;
import com.KissTech.crm.service.StudentManagementService;
import com.KissTech.crm.service.TaskManagementService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DashBoardApiService {
    private final TaskManagementService taskManagementService;
    private final LeadManagementService leadManagementService;
    private final StudentManagementService studentManagementService;
    private final StudentManagementMapper studentManagementMapper;
    private final LeadManagementMapper leadManagementMapper;
    private final TaskManagementMapper taskManagementMapper;

    public DashBoardApiService(TaskManagementService taskManagementService,
                               LeadManagementService leadManagementService,
                               StudentManagementService studentManagementService, StudentManagementMapper studentManagementMapper, LeadManagementMapper leadManagementMapper, TaskManagementMapper taskManagementMapper) {
        this.taskManagementService = taskManagementService;
        this.leadManagementService = leadManagementService;
        this.studentManagementService = studentManagementService;
        this.studentManagementMapper = studentManagementMapper;
        this.leadManagementMapper = leadManagementMapper;
        this.taskManagementMapper = taskManagementMapper;
    }

    public DashBoardDTO getDailyNotification() {
        String currentDate = LocalDate.now().toString(); // "yyyy-MM-dd"

        // Filter tasks due today
        List<TaskManagement> tasksDueToday = taskManagementService.getAllStaffs().stream()
                .filter(t -> currentDate.equals(t.getDueDate()) || currentDate.equals(t.getDueDate()) )
                .collect(Collectors.toList());



        // Filter leads with reminders today
        List<LeadManagement> leadsWithRemindersToday = leadManagementService.getAllLeads().stream()
                .filter(lead -> lead.getReminderLogs() != null &&
                        lead.getReminderLogs().stream()
                                .anyMatch(r -> currentDate.equals(r.getReminderDate())))
                .collect(Collectors.toList());

        // Filter students with due day today
        List<StudentManagement> studentsDueToday = studentManagementService.getAllStudents().stream()
                .filter(student -> student.getDueDay() != null &&
                        student.getDueDay().stream()
                                .anyMatch(due -> currentDate.equals(due.getDueDate())))
                .peek(student -> student.setDueDay(
                        student.getDueDay().stream()
                                .filter(due -> currentDate.equals(due.getDueDate()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());


        List<StudentManagement> studentsAttendance = studentManagementService.getAllStudents().stream()
                .filter(student -> student.getAttendance() != null &&
                        student.getAttendance().stream()
                                .anyMatch(a -> currentDate.equals(a.getDate())))
                .peek(student -> student.setAttendance(
                        student.getAttendance().stream()
                                .filter(a -> currentDate.equals(a.getDate()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());


        // Build DTO
        DashBoardDTO dto = new DashBoardDTO();
        dto.setTask(tasksDueToday.stream().map(taskManagementMapper::NotifyDashBoard).toList());
        dto.setLead(leadsWithRemindersToday.stream().map(leadManagementMapper::NotifyDashBoard).toList());
        dto.setStudentDueDays(studentsDueToday.stream().map(studentManagementMapper::NotifyDueDay).toList());
        dto.setStudentAttendance(studentsAttendance.stream().map(studentManagementMapper::NotifyAttendance).toList());

        return dto;
    }

    public DashBoardDataDTO getDashBoardDatads() {
        DashBoardDataDTO dto = new DashBoardDataDTO();

        List<LeadManagement> leads = leadManagementService.getAllLeads();
        List<StudentManagement> students = studentManagementService.getAllStudents();

        dto.setLeadDashBoardDTOS(leads.stream()
                .map(leadManagementMapper::DassBoard)
                .toList());

        dto.setStudentDashBoardDTOS(students.stream()
                .map(studentManagementMapper::DashBoard)
                .toList());

        // === FEE COLLECTION SUMMARY ===
        List<DueDay> allDues = students.stream()
                .filter(s -> s.getDueDay() != null)
                .flatMap(s -> s.getDueDay().stream())
                .toList();

        FeeCollectionDTO feeCollectionDTO = new FeeCollectionDTO();

        // Example: collection rate = percentage of dues with status "Paid"
        long totalDues = allDues.size();
        long paidDues = allDues.stream()
                .filter(d -> "Paid".equalsIgnoreCase(d.getStatus()))
                .count();

        double collectionRate = totalDues > 0 ? (paidDues * 100.0 / totalDues) : 0.0;
        feeCollectionDTO.setCollectionRate(String.format("%.2f%%", collectionRate));

        // Overdue payments = dues with dueDate < today and status != "Paid"
        String currentDate = LocalDate.now().toString();
        long overdueCount = allDues.stream()
                .filter(d -> d.getDueDate() != null
                        && d.getDueDate().compareTo(currentDate) < 0
                        && !"Paid".equalsIgnoreCase(d.getStatus()))
                .count();
        feeCollectionDTO.setOverduePayments(String.valueOf(overdueCount));

        // Upcoming collected = dues with dueDate >= today and status == "Paid"
        long upcomingCollected = allDues.stream()
                .filter(d -> d.getDueDate() != null
                        && d.getDueDate().compareTo(currentDate) >= 0
                        && "Paid".equalsIgnoreCase(d.getStatus()))
                .count();
        feeCollectionDTO.setUpcomingCollected(String.valueOf(upcomingCollected));

        // Payment type breakdown
        long upiCount = allDues.stream().filter(d -> "UPI".equalsIgnoreCase(d.getPaymentType())).count();
        long cashCount = allDues.stream().filter(d -> "CASH".equalsIgnoreCase(d.getPaymentType())).count();
        long cardCount = allDues.stream().filter(d -> "CARD".equalsIgnoreCase(d.getPaymentType())).count();

        feeCollectionDTO.setUpiPayment(String.valueOf(upiCount));
        feeCollectionDTO.setCashPayment(String.valueOf(cashCount));
        feeCollectionDTO.setCardPayment(String.valueOf(cardCount));

        dto.setFeeCollection(feeCollectionDTO);

        return dto;
    }


    public DashBoardDataDTO getDashBoardDatas() {
        DashBoardDataDTO dto = new DashBoardDataDTO();

        List<LeadManagement> leads = leadManagementService.getAllLeads();
        List<StudentManagement> students = studentManagementService.getAllStudents();

        dto.setLeadDashBoardDTOS(leads.stream()
                .map(leadManagementMapper::DassBoard)
                .toList());

        dto.setStudentDashBoardDTOS(students.stream()
                .map(studentManagementMapper::DashBoard)
                .toList());

        // === FEE COLLECTION SUMMARY ===
        List<DueDay> allDues = students.stream()
                .filter(s -> s.getDueDay() != null)
                .flatMap(s -> s.getDueDay().stream())
                .toList();

        FeeCollectionDTO feeCollectionDTO = new FeeCollectionDTO();

        String currentDate = LocalDate.now().toString();

        // Convert amount from String → double
        double totalDueAmount = allDues.stream()
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double paidAmount = allDues.stream()
                .filter(d -> "Paid".equalsIgnoreCase(d.getStatus()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double unpaidAmount = allDues.stream()
                .filter(d -> !"Paid".equalsIgnoreCase(d.getStatus()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double overdueAmount = allDues.stream()
                .filter(d -> d.getDueDate() != null
                        && d.getDueDate().compareTo(currentDate) < 0
                        && !"Paid".equalsIgnoreCase(d.getStatus()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double upcomingCollectedAmount = allDues.stream()
                .filter(d -> d.getDueDate() != null
                        && d.getDueDate().compareTo(currentDate) >= 0
                        && "Paid".equalsIgnoreCase(d.getStatus()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double upiAmount = allDues.stream()
                .filter(d -> "UPI".equalsIgnoreCase(d.getPaymentType()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double cashAmount = allDues.stream()
                .filter(d -> "CASH".equalsIgnoreCase(d.getPaymentType()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double cardAmount = allDues.stream()
                .filter(d -> "CARD".equalsIgnoreCase(d.getPaymentType()))
                .mapToDouble(d -> parseAmount(d.getDueAmt()))
                .sum();

        double collectionRate = totalDueAmount > 0
                ? (paidAmount * 100.0 / totalDueAmount)
                : 0.0;

        // === Set in DTO ===
        feeCollectionDTO.setCollectionRate(String.format("%.2f%%", collectionRate));
        feeCollectionDTO.setOverduePayments(String.format("%.2f", overdueAmount));
        feeCollectionDTO.setUpcomingCollected(String.format("%.2f", unpaidAmount));
        feeCollectionDTO.setUpiPayment(String.format("%.2f", upiAmount));
        feeCollectionDTO.setCashPayment(String.format("%.2f", cashAmount));
        feeCollectionDTO.setCardPayment(String.format("%.2f", cardAmount));
        feeCollectionDTO.setCollectionAmount(String.format("%.2f", upcomingCollectedAmount));

        // Optional: store total + unpaid if needed
         feeCollectionDTO.setTotalDueAmount(String.format("%.2f", totalDueAmount));
        // feeCollectionDTO.setUnpaid(String.format("%.2f", unpaidAmount));

        dto.setFeeCollection(feeCollectionDTO);
        return dto;
    }

    /**
     * Safely parse amount strings into doubles
     */
    private double parseAmount(String amount) {
        try {
            return amount != null && !amount.isBlank() ? Double.parseDouble(amount.trim()) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }


}
