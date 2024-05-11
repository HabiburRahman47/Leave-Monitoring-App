package com.springboot.project.leavemonitoringapp.service;

import com.springboot.project.leavemonitoringapp.model.*;
import com.springboot.project.leavemonitoringapp.repository.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AttendanceLeaveService {
    @Autowired
    private AttendanceLeaveRepository attendanceLeaveRepository;
    @Autowired
    private LeaveApprovalRepository leaveApprovalRepository;
    @Autowired
    private LeaveIssueRepository leaveIssueRepository;
    @Autowired
    private PFLoanRepository pfLoanRepository;
    @Autowired
    private ApprovalHistoryRepository approvalHistoryRepository;
    @Autowired
    private EntityManager entityManager;

    public List<AttendanceLeave> getAttendanceLeaveRecord(String status){
        return attendanceLeaveRepository.findAttendanceLeaveRecord("");
    }

    public List<LeaveApproval> getLeaveApprovalRecord(String headerId){
        return leaveApprovalRepository.findLeaveApprovalRecord(headerId);
    }
    public PFLoan getPFInfo(String empId){
        return pfLoanRepository.findPFInfo("",empId);
    }

    public Map<String,List<ApprovalHistory>> getLeaveIssue(){
        List<String> headerIdList = List.of("17529","17530");
        Map<String,List<ApprovalHistory>> unsolvedLeaves = new HashMap<>();
        for (String headerId : headerIdList){
            List<ApprovalHistory> leaveApproval = approvalHistoryRepository.findApprovalHistory(headerId);
            unsolvedLeaves.put(headerId,leaveApproval);
        }
        return unsolvedLeaves;
    }
//public Map<String, List<ApprovalHistory>> getLeaveIssue() {
//    List<String> headerIdList = List.of("17529", "17530");
//    Map<String, List<ApprovalHistory>> unsolvedLeaves = new HashMap<>();
//    for (String headerId : headerIdList) {
//        List<ApprovalHistory> leaveApproval = new ArrayList<>(); // Create new list for each iteration
//        leaveApproval.addAll(approvalHistoryRepository.findApprovalHistory(headerId));
//        unsolvedLeaves.put(headerId, leaveApproval);
//    }
//    return unsolvedLeaves;
//}

    public Map<String, List<LeaveApproval>> getLeaveData(){
//        List<String> headerIdList = List.of("17432");
        List<AttendanceLeave> attendanceLeaveList = getAttendanceLeaveRecord("");
        Map<String, List<LeaveApproval>> leaveIssues = new HashMap<>();
        List<String> headerIdList = List.of("17432","17448","17525","17526","16471","16566","16795");
        for (AttendanceLeave attendanceLeave: attendanceLeaveList){
            String headerId = String.valueOf(attendanceLeave.getReportHeaderId());
            List<LeaveApproval> leave = leaveApprovalRepository.findLeaveApprovalRecord(headerId);
            leaveIssues.put(headerId,leave);
        }
        return leaveIssues;
    }

    public Map<String, PFLoan> getPfLoanByEmpId(){
        List<String> empIdList = List.of("218","475","2887","3531","4040","6928");
        Map<String, PFLoan> pfLoanMap = new HashMap<>();
        for (String empId:empIdList){
            PFLoan pfLoanInfo = getPFInfo(empId);
            pfLoanMap.put(empId,pfLoanInfo);
        }
        return pfLoanMap;
    }

    public List<ApprovalHistory> getLeaveArppoval(String headerId){
        return approvalHistoryRepository.findApprovalHistory(headerId);
    }
}
