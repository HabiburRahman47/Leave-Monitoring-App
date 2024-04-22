package com.springboot.project.leavemonitoringapp.controller;

import com.springboot.project.leavemonitoringapp.model.AttendanceLeave;
import com.springboot.project.leavemonitoringapp.model.LeaveApproval;
import com.springboot.project.leavemonitoringapp.service.AttendanceLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    @GetMapping("/leave-approval-records")
    public List<LeaveApproval> getLeaveAttendanceRecords(){
        return attendanceLeaveService.getLeaveApprovalRecord("16439");
    }
//    @GetMapping("/leave-approval-records")
//    public List<LeaveApproval> getLeaveAttendanceRecords(){
//        List<AttendanceLeave> attendanceLeaveList = attendanceLeaveService.getAttendanceLeaveRecord("");
//        List<LeaveApproval> leaveApprovalList = new ArrayList<>();
//        for (AttendanceLeave attendanceLeave: attendanceLeaveList){
//            String headerId = String.valueOf(attendanceLeave.getReportHeaderId());
//            List<LeaveApproval> leaveApproval = attendanceLeaveService.getLeaveApprovalRecord(headerId);
//            leaveApprovalList.addAll(leaveApproval);
//        }
//        return leaveApprovalList;
//    }

    @GetMapping("/leave-issues")
    public void getLeaveIssues(){
         attendanceLeaveService.getLeaveIssue();
    }
}
