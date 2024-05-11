package com.springboot.project.leavemonitoringapp.controller;

import com.springboot.project.leavemonitoringapp.model.*;
import com.springboot.project.leavemonitoringapp.service.AttendanceLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class AttendanceLeaveController {
    @Autowired
    private AttendanceLeaveService attendanceLeaveService;
    @GetMapping("/attendance-leave")
    public List<AttendanceLeave> getAttendanceLeaveRecord(String status){
        return attendanceLeaveService.getAttendanceLeaveRecord(status);
    }
    @GetMapping("/leave-approval-records/{headerId}")
    public List<LeaveApproval> getLeaveAttendanceRecords(@PathVariable String headerId){
        return attendanceLeaveService.getLeaveApprovalRecord(headerId);
    }

    @GetMapping("/leave-issues")
    public Map<String,List<ApprovalHistory>> getLeaveIssues(){
         return attendanceLeaveService.getLeaveIssue();
    }

    @GetMapping("/leave-data")
    public Map<String,List<LeaveApproval>> getLeaveData(){
        return attendanceLeaveService.getLeaveData();
    }

    @GetMapping("/pfLoan-info")
    public Map<String, PFLoan> getPFLoan(){
        return attendanceLeaveService.getPfLoanByEmpId();
    }

    @GetMapping("/leave-approval/{id}")
    public List<ApprovalHistory> getLeaveApproval(@PathVariable String id){
        return attendanceLeaveService.getLeaveArppoval(id);
    }
}
