package com.springboot.project.leavemonitoringapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PFLoanId implements Serializable {
    @Column(name = "PF_BALANCE")
    private String pfBalance;
    @Column(name = "PF_ELIGIBILITY_AMOUNT")
    private String eligibilityAmount;
}
