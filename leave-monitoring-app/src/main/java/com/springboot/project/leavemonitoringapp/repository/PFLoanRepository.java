package com.springboot.project.leavemonitoringapp.repository;

import com.springboot.project.leavemonitoringapp.model.PFLoan;
import com.springboot.project.leavemonitoringapp.model.PFLoanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PFLoanRepository extends JpaRepository<PFLoan, PFLoanId> {
    @Query(value = "select \n" +
            "    apps.xxssgil_approval_cust_pkg.pf_loan_eligibility_status (:userId, :empId) pf_eligibility_status,\n" +
            "    apps.xxssgil_approval_cust_pkg.pf_balance (:userId, :empId) pf_balance,\n" +
            "    apps.xxssgil_approval_cust_pkg.pf_loan_eligibility_amount (:userId, :empId) pf_eligibility_amount\n" +
            "from dual", nativeQuery = true)
    PFLoan findPFInfo(@Param("userId") String userId, @Param("empId") String empId);
}
