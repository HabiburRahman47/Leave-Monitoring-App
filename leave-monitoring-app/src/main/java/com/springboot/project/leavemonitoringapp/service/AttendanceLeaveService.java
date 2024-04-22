package com.springboot.project.leavemonitoringapp.service;

import com.springboot.project.leavemonitoringapp.model.AttendanceLeave;
import com.springboot.project.leavemonitoringapp.model.LeaveApproval;
import com.springboot.project.leavemonitoringapp.repository.AttendanceLeaveRepository;
import com.springboot.project.leavemonitoringapp.repository.LeaveApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AttendanceLeaveService {
    @Autowired
    private AttendanceLeaveRepository attendanceLeaveRepository;
    @Autowired
    private LeaveApprovalRepository leaveApprovalRepository;
    @Transactional(readOnly = true)
    public List<AttendanceLeave> getAttendanceLeaveRecord(String status){
        return attendanceLeaveRepository.findAttendanceLeaveRecord("");
    }
    @Transactional(readOnly = true)
    public List<LeaveApproval> getLeaveApprovalRecord(String headerId){
        return leaveApprovalRepository.findLeaveApprovalRecord(headerId);
    }

    @Transactional(readOnly = true)
    public void getLeaveIssue(){
        List<AttendanceLeave> attendanceLeaveList = getAttendanceLeaveRecord("");
        for (AttendanceLeave attendanceLeave: attendanceLeaveList){
            String headerId = String.valueOf(attendanceLeave.getReportHeaderId());
            List<LeaveApproval> leaveApproval = getLeaveApprovalRecord(headerId);

            System.out.println("header id:"+headerId);
            System.out.println("leave status:"+attendanceLeave.getStatusFlg());

            System.out.println("Leave Approver status:");
            System.out.println(Arrays.toString(leaveApproval.toArray()));
//            System.out.println(Arrays.toString(leaveApproval.toArray()));
//            leaveApproval.forEach(System.out::println);
        }
    }
}
