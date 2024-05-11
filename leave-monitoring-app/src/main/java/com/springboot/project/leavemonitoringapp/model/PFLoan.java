package com.springboot.project.leavemonitoringapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PFLoan {
    @EmbeddedId
    private PFLoanId id;
    @Column(name = "PF_ELIGIBILITY_STATUS")
    private String eligibilityStatus;

    @Column(name = "PF_BALANCE", insertable = false, updatable = false)
    private String pfBalance;

    @Column(name = "PF_ELIGIBILITY_AMOUNT", insertable = false, updatable = false)
    private String eligibilityAmount;
}

