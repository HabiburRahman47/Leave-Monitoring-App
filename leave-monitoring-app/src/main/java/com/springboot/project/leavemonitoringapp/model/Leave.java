package com.springboot.project.leavemonitoringapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_HEADER_ID")
    private Long reportHeaderId;

    @Column(name = "APPLICATION_TYPE")
    private String applicationType;

    @Column(name = "LEAVE_TYPE")
    private String leaveType;

    @Column(name = "ENTRY_DATE")
    private String entryDate;

    @Column(name = "STATUS_FLG")
    private String statusFlag;

}
