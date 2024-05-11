package com.springboot.project.leavemonitoringapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SRL_NUM")
    private Long serialNumber;

    @Column(name = "REPORT_HEADER_ID")
    private String headerId;

    @Column(name = "NTF_RESPONDER")
    private String responderName;

    @Column(name = "APPROVER_NAME")
    private String approverName;

    @Column(name = "APPROVER_ACTION")
    private String approverAction;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "ACTION_DATE")
    private String actionDate;

}
