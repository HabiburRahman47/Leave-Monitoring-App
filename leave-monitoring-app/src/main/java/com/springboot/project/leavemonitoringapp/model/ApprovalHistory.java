package com.springboot.project.leavemonitoringapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Cacheable(false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalHistory {

    @Id
    @Column(name = "SRL_NUM")
    private Integer serialNumber;

    @Column(name = "EXPENSE_REPORT_HDR_ID")
    private String expenseReportHeaderId;

    @Column(name = "NTF_RESPONDER")
    private String notifierResponder;

    @Column(name = "NTF_ACTION")
    private String notifierAction;

    @Column(name = "NTF_NOTE")
    private String notifierNote;
}
