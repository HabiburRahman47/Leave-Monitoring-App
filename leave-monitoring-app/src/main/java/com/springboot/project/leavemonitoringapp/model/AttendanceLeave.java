package com.springboot.project.leavemonitoringapp.model;

import jakarta.persistence.*;

@Entity
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

    public Long getReportHeaderId() {
        return reportHeaderId;
    }

    public void setReportHeaderId(Long reportHeaderId) {
        this.reportHeaderId = reportHeaderId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getStatusFlg() {
        return statusFlg;
    }

    public void setStatusFlg(String statusFlg) {
        this.statusFlg = statusFlg;
    }

    public String getWfItemKey() {
        return wfItemKey;
    }

    public void setWfItemKey(String wfItemKey) {
        this.wfItemKey = wfItemKey;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(String leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public String getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(String leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getSrlNum() {
        return srlNum;
    }

    public void setSrlNum(Integer srlNum) {
        this.srlNum = srlNum;
    }

    public String getwHour() {
        return wHour;
    }

    public void setwHour(String wHour) {
        this.wHour = wHour;
    }

    public String getActInTime() {
        return actInTime;
    }

    public void setActInTime(String actInTime) {
        this.actInTime = actInTime;
    }

    public String getActOutTime() {
        return actOutTime;
    }

    public void setActOutTime(String actOutTime) {
        this.actOutTime = actOutTime;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(String employmentCategory) {
        this.employmentCategory = employmentCategory;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }
}
