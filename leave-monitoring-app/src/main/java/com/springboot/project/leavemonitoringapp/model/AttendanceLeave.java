package com.springboot.project.leavemonitoringapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_HEADER_ID")
    private Long reportHeaderId;

    @Column(name = "ENTRY_DATE")
    private String entryDate;

    @Column(name = "STATUS_FLG")
    private String statusFlg;

    @Column(name = "WF_ITEM_KEY")
    private String wfItemKey;

    @Column(name = "CREATION_DATE")
    private String creationDate;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "LEAVE_START_DATE")
    private String leaveStartDate;

    @Column(name = "LEAVE_END_DATE")
    private String leaveEndDate;

    @Column(name = "LEAVE_TYPE")
    private String leaveType;

    @Column(name = "SRL_NUM")
    private Integer srlNum;

    @Column(name = "W_HOUR")
    private String wHour;

    @Column(name = "ACT_IN_TIME")
    private String actInTime;

    @Column(name = "ACT_OUT_TIME")
    private String actOutTime;

    @Column(name = "EMPLOYEE_NUMBER")
    private String employeeNumber;

    @Column(name = "EMPLOYMENT_CATEGORY")
    private String employmentCategory;

    @Column(name = "DEPT")
    private String dept;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "WORKING_DATE")
    private String workingDate;

    @Column(name = "WORKING_DAY")
    private String workingDay;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ACTION_STATUS")
    private String actionStatus;

}
